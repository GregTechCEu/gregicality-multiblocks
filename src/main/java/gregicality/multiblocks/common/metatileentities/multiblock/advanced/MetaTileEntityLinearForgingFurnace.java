package gregicality.multiblocks.common.metatileentities.multiblock.advanced;

import java.util.*;
import java.util.function.Consumer;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import gregtech.api.GTValues;
import gregtech.api.block.IHeatingCoilBlockStats;
import gregtech.api.block.VariantActiveBlock;
import gregtech.api.capability.GregtechDataCodes;
import gregtech.api.capability.IHeatingCoil;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.MultiblockDisplayText;
import gregtech.api.pattern.*;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.recipeproperties.TemperatureProperty;
import gregtech.api.util.GTUtility;
import gregtech.api.util.RelativeDirection;
import gregtech.api.util.TextComponentUtil;
import gregtech.api.util.TextFormattingUtil;
import gregtech.api.util.function.TriConsumer;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.*;
import gregtech.common.metatileentities.MetaTileEntities;

import gregicality.multiblocks.api.capability.impl.GCYMHeatingCoilRecipeLogic;
import gregicality.multiblocks.api.metatileentity.GCYMMultiShapeMultiblockController;
import gregicality.multiblocks.api.recipes.GCYMRecipeMaps;
import gregicality.multiblocks.api.recipes.LinearForgingFurnaceRecipeType;
import gregicality.multiblocks.api.render.GCYMTextures;
import gregicality.multiblocks.common.block.GCYMMetaBlocks;
import gregicality.multiblocks.common.block.blocks.BlockLargeMultiblockCasing;
import gregicality.multiblocks.common.block.blocks.BlockUniqueCasing;
import gregicality.multiblocks.common.metatileentities.GCYMMetaTileEntities;

public class MetaTileEntityLinearForgingFurnace extends GCYMMultiShapeMultiblockController implements IHeatingCoil {

    private int blastFurnaceTemperature;
    private int rowCount;
    private int inDisplayWorld = -1;

    private LinearForgingFurnaceRecipeType lastRecipeType = LinearForgingFurnaceRecipeType.NONE;

    public MetaTileEntityLinearForgingFurnace(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GCYMRecipeMaps.LINEAR_FORGING_RECIPES);
        this.recipeMapWorkable = new GCYMHeatingCoilRecipeLogic(this);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityLinearForgingFurnace(this.metaTileEntityId).inDisplayWorld(this.inDisplayWorld);
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        MultiblockDisplayText.builder(textList, isStructureFormed())
                .setWorkingStatus(recipeMapWorkable.isWorkingEnabled(), recipeMapWorkable.isActive())
                .addEnergyUsageLine(getEnergyContainer())
                .addEnergyTierLine(GTUtility.getTierByVoltage(recipeMapWorkable.getMaxVoltage()))
                .addCustom(tl -> {
                    // Coil heat capacity line
                    if (isStructureFormed()) {
                        ITextComponent heatString = TextComponentUtil.stringWithColor(
                                TextFormatting.RED,
                                TextFormattingUtil.formatNumbers(blastFurnaceTemperature) + "K");

                        tl.add(TextComponentUtil.translationWithColor(
                                TextFormatting.GRAY,
                                "gregtech.multiblock.blast_furnace.max_temperature",
                                heatString));
                    }
                })
                .addParallelsLine(recipeMapWorkable.getParallelLimit())
                .addWorkingStatusLine()
                .addProgressLine(recipeMapWorkable.getProgressPercent());
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        Object type = context.get("CoilType");
        this.rowCount = calculateRowCount(context);
        if (type instanceof IHeatingCoilBlockStats) {
            this.blastFurnaceTemperature = ((IHeatingCoilBlockStats) type).getCoilTemperature();
        } else {
            this.blastFurnaceTemperature = BlockWireCoil.CoilType.CUPRONICKEL.getCoilTemperature();
        }

        this.blastFurnaceTemperature += 100 *
                Math.max(0, GTUtility.getTierByVoltage(getEnergyContainer().getInputVoltage()) - GTValues.MV);
    }

    protected int calculateRowCount(PatternMatchContext context) {
        int VAs = ((LinkedList<?>) context.get("VABlock")).size();
        return switch (this.getRecipeMapIndex()) {
            default -> // Blast
                (VAs - 36) / 12;
            case 1 -> // Alloy
                (VAs - 70) / 14;
            case 2 -> // Dual
                (VAs - 106) / 26;
            case 3 -> // Freezer
                (VAs - 2) / 3;
            case 4 -> // Cooled Blast
                (VAs - 38) / 15;
            case 5 -> // Cooled Alloy
                (VAs - 72) / 17;
            case 6 -> // Cooled Dual
                (VAs - 108) / 29;
            case 7 -> // Cooled Blast Forging
                (VAs - 38) / 17;
            case 8 -> // Cooled Alloy Forging
                (VAs - 72) / 19;
            case 9 -> // Cooled Dual Forging
                (VAs - 108) / 31;
        };
    }

    @Override
    protected @NotNull BlockPattern getStructurePattern(int index) {
        TraceabilityPredicate casing = states(getCasingState()).setMinLayerLimited(10);
        TraceabilityPredicate auto = autoAbilities(true, true, false, false, false, false, false);
        return (switch (index) {
            default -> // Blast
                FactoryBlockPattern.start(RelativeDirection.RIGHT, RelativeDirection.UP, RelativeDirection.FRONT)
                        .aisle(
                                "XXX EFCFFCFE ZZZ",
                                "XSX EICVVCIE ZZZ",
                                "XXX EFCFFCFE ZZZ")
                        .aisle(
                                "XXX ECCCCCCE ZZZ",
                                "XPPPP######PPPPZ",
                                "XXX ECCCCCCE ZZZ")
                        .setRepeatable(1, 16)
                        .aisle(
                                "XXX EFCFFCFE ZZZ",
                                "XMX EICVVCIE ZZZ",
                                "XXX EFCFFCFE ZZZ");
            case 1 -> // Alloy
                FactoryBlockPattern.start(RelativeDirection.RIGHT, RelativeDirection.UP, RelativeDirection.FRONT)
                        .aisle(
                                "                 ",
                                "    AICIVICIA    ",
                                "    AICIVICIA    ",
                                "    AICIVICIA    ",
                                "                 ")
                        .aisle(
                                "    AFCFVFCFA    ",
                                "XXX A#######A ZZZ",
                                "XSX A#######A ZZZ",
                                "XXX A#######A ZZZ",
                                "    AFCFVFCFA    ")
                        .aisle(
                                "    ACCCVCCCA    ",
                                "XXX A#######A ZZZ",
                                "XPPPP#######PPPPZ",
                                "XXX A#######A ZZZ",
                                "    ACCCVCCCA    ")
                        .setRepeatable(1, 16)
                        .aisle(
                                "    AFCFVFCFA    ",
                                "XXX A#######A ZZZ",
                                "XMX A#######A ZZZ",
                                "XXX A#######A ZZZ",
                                "    AFCFVFCFA    ")
                        .aisle(
                                "                 ",
                                "    AICIVICIA    ",
                                "    AICIVICIA    ",
                                "    AICIVICIA    ",
                                "                 ");
            case 2 -> // Dual
                    FactoryBlockPattern.start(RelativeDirection.RIGHT, RelativeDirection.UP, RelativeDirection.FRONT)
                            .aisle(
                                    "                 ",
                                    "                 ",
                                    "                 ",
                                    "                 ",
                                    "                 ",
                                    "    AICIVICIA    ",
                                    "    AICIVICIA    ",
                                    "    AICIVICIA    ",
                                    "                 ")
                            .aisle(
                                    "EEE EFCFFCFE  EEE",
                                    "EEE EICVVCIE  EEE",
                                    "EEE EFCFFCFE  EEE",
                                    "                 ",
                                    "    AFCFVFCFA    ",
                                    "XXX A#######A ZZZ",
                                    "XSX A#######A ZZZ",
                                    "XXX A#######A ZZZ",
                                    "    AFCFVFCFA    ")
                            .aisle(
                                    "EEE ECCCCCCE  EEE",
                                    "EPPPP######PPPPPE",
                                    "EPE ECCCCCCE  EPE",
                                    " P             P ",
                                    " P  ACCCVCCCA  P ",
                                    "XPX A#######A ZPZ",
                                    "XPPPP#######PPPPZ",
                                    "XXX A#######A ZZZ",
                                    "    ACCCVCCCA    ")
                            .setRepeatable(1)
                            .aisle(
                                    "EEE ECCCCCCE  EEE",
                                    "EPPPP######PPPPPE",
                                    "EEE ECCCCCCE  EEE",
                                    "                 ",
                                    "    ACCCVCCCA    ",
                                    "XXX A#######A ZZZ",
                                    "XPPPP#######PPPPZ",
                                    "XXX A#######A ZZZ",
                                    "    ACCCVCCCA    ")
                            .setRepeatable(0, 14)
                            .aisle(
                                    "EEE ECCCCCCE  EEE",
                                    "EPPPP######PPPPPE",
                                    "EPE ECCCCCCE  EPE",
                                    " P             P ",
                                    " P  ACCCVCCCA  P ",
                                    "XPX A#######A ZPZ",
                                    "XPPPP#######PPPPZ",
                                    "XXX A#######A ZZZ",
                                    "    ACCCVCCCA    ")
                            .setRepeatable(0, 1)
                            .aisle(
                                    "EEE EFCFFCFE  EEE",
                                    "EEE EICVVCIE  EEE",
                                    "EEE EFCFFCFE  EEE",
                                    "                 ",
                                    "    AFCFVFCFA    ",
                                    "XXX A#######A ZZZ",
                                    "XMX A#######A ZZZ",
                                    "XXX A#######A ZZZ",
                                    "    AFCFVFCFA    ")
                            .aisle(
                                    "                 ",
                                    "                 ",
                                    "                 ",
                                    "                 ",
                                    "                 ",
                                    "    AICIVICIA    ",
                                    "    AICIVICIA    ",
                                    "    AICIVICIA    ",
                                    "                 ");
            case 3 -> // Freezer
                FactoryBlockPattern.start(RelativeDirection.RIGHT, RelativeDirection.UP, RelativeDirection.FRONT)
                        .aisle(
                                "XXX HHH ZZZ",
                                "XSX HHH ZZZ",
                                "XXX HHH ZZZ",
                                "    BVB    ",
                                "    BBB    ")
                        .aisle(
                                "XXX HHH ZZZ",
                                "XPPPP#PPPPZ",
                                "XXX HPH ZZZ",
                                "    VPV    ",
                                "    BVB    ")
                        .setRepeatable(1, 16)
                        .aisle(
                                "XXX HHH ZZZ",
                                "XMX HHH ZZZ",
                                "XXX HHH ZZZ",
                                "    BVB    ",
                                "    BBB    ");
            case 4 -> // Cooled Blast
                FactoryBlockPattern.start(RelativeDirection.RIGHT, RelativeDirection.UP, RelativeDirection.FRONT)
                        .aisle(
                                "XXX EFCFFCFE HHH ZZZ",
                                "XSX EICVVCIE HHH ZZZ",
                                "XXX EFCFFCFE HHH ZZZ",
                                "             BVB    ",
                                "             BBB    ")
                        .aisle(
                                "XXX ECCCCCCE HHH ZZZ",
                                "XPPPP######PPP#PPPPZ",
                                "XXX ECCCCCCE HPH ZZZ",
                                "             VPV    ",
                                "             BVB    ")
                        .setRepeatable(1, 16)
                        .aisle(
                                "XXX EFCFFCFE HHH ZZZ",
                                "XMX EICVVCIE HHH ZZZ",
                                "XXX EFCFFCFE HHH ZZZ",
                                "             BVB    ",
                                "             BBB    ");
            case 5 -> // Cooled Alloy
                FactoryBlockPattern.start(RelativeDirection.RIGHT, RelativeDirection.UP, RelativeDirection.FRONT)
                        .aisle(
                                "                     ",
                                "    AICIVICIA        ",
                                "    AICIVICIA        ",
                                "    AICIVICIA        ",
                                "                     ",
                                "                     ")
                        .aisle(
                                "    AFCFVFCFA        ",
                                "XXX A#######A HHH ZZZ",
                                "XSX A#######A HHH ZZZ",
                                "XXX A#######A HHH ZZZ",
                                "    AFCFVFCFA BVB    ",
                                "              BBB    ")
                        .aisle(
                                "    ACCCVCCCA        ",
                                "XXX A#######A HHH ZZZ",
                                "XPPPP#######PPP#PPPPZ",
                                "XXX A#######A HPH ZZZ",
                                "    ACCCVCCCA VPV    ",
                                "              BVB    ")
                        .setRepeatable(1, 16)
                        .aisle(
                                "    AFCFVFCFA        ",
                                "XXX A#######A HHH ZZZ",
                                "XMX A#######A HHH ZZZ",
                                "XXX A#######A HHH ZZZ",
                                "    AFCFVFCFA BVB    ",
                                "              BBB    ")
                        .aisle(
                                "                     ",
                                "    AICIVICIA        ",
                                "    AICIVICIA        ",
                                "    AICIVICIA        ",
                                "                     ",
                                "                     ");
            case 6 -> // Cooled Dual
                    FactoryBlockPattern.start(RelativeDirection.RIGHT, RelativeDirection.UP, RelativeDirection.FRONT)
                            .aisle(
                                    "                     ",
                                    "                     ",
                                    "                     ",
                                    "                     ",
                                    "                     ",
                                    "    AICIVICIA        ",
                                    "    AICIVICIA        ",
                                    "    AICIVICIA        ",
                                    "                     ",
                                    "                     ")
                            .aisle(
                                    "EEE EFCFFCFE  EEE    ",
                                    "EEE EICVVCIE  EEE    ",
                                    "EEE EFCFFCFE  EEE    ",
                                    "                     ",
                                    "    AFCFVFCFA        ",
                                    "XXX A#######A HHH ZZZ",
                                    "XSX A#######A HHH ZZZ",
                                    "XXX A#######A HHH ZZZ",
                                    "    AFCFVFCFA BVB    ",
                                    "              BBB    ")
                            .aisle(
                                    "EEE ECCCCCCE  EEE    ",
                                    "EPPPP######PPPPPE    ",
                                    "EPE ECCCCCCE  EPE    ",
                                    " P             P     ",
                                    " P  ACCCVCCCA  P     ",
                                    "XPX A#######A HPH ZZZ",
                                    "XPPPP#######PPP#PPPPZ",
                                    "XXX A#######A HPH ZZZ",
                                    "    ACCCVCCCA VPV    ",
                                    "              BVB    ")
                            .setRepeatable(1)
                            .aisle(
                                    "EEE ECCCCCCE  EEE    ",
                                    "EPPPP######PPPPPE    ",
                                    "EEE ECCCCCCE  EEE    ",
                                    "                     ",
                                    "    ACCCVCCCA        ",
                                    "XXX A#######A HHH ZZZ",
                                    "XPPPP#######PPP#PPPPZ",
                                    "XXX A#######A HPH ZZZ",
                                    "    ACCCVCCCA VPV    ",
                                    "              BVB    ")
                            .setRepeatable(0, 14)
                            .aisle(
                                    "EEE ECCCCCCE  EEE    ",
                                    "EPPPP######PPPPPE    ",
                                    "EPE ECCCCCCE  EPE    ",
                                    " P             P     ",
                                    " P  ACCCVCCCA  P     ",
                                    "XPX A#######A HPH ZZZ",
                                    "XPPPP#######PPP#PPPPZ",
                                    "XXX A#######A HPH ZZZ",
                                    "    ACCCVCCCA VPV    ",
                                    "              BVB    ")
                            .setRepeatable(0, 1)
                            .aisle(
                                    "EEE EFCFFCFE  EEE    ",
                                    "EEE EICVVCIE  EEE    ",
                                    "EEE EFCFFCFE  EEE    ",
                                    "                     ",
                                    "    AFCFVFCFA        ",
                                    "XXX A#######A HHH ZZZ",
                                    "XMX A#######A HHH ZZZ",
                                    "XXX A#######A HHH ZZZ",
                                    "    AFCFVFCFA BVB    ",
                                    "              BBB    ")
                            .aisle(
                                    "                     ",
                                    "                     ",
                                    "                     ",
                                    "                     ",
                                    "                     ",
                                    "    AICIVICIA        ",
                                    "    AICIVICIA        ",
                                    "    AICIVICIA        ",
                                    "                     ",
                                    "                     ");
            case 7 -> // Cooled Blast Forging
                    FactoryBlockPattern.start(RelativeDirection.RIGHT, RelativeDirection.UP, RelativeDirection.FRONT)
                            .aisle(
                                    "            WWWW    ",
                                    "            WWWW    ",
                                    "            WWWW    ",
                                    "                    ",
                                    "                    ",
                                    "XXX EFCFFCFE HHH ZZZ",
                                    "XSX EICVVCIE HHH ZZZ",
                                    "XXX EFCFFCFE HHH ZZZ",
                                    "             BVB    ",
                                    "             BBB    ")
                            .aisle(
                                    "            WDDW    ",
                                    "            WGGF    ",
                                    "            WIPW    ",
                                    "              P     ",
                                    "              P     ",
                                    "XXX ECCCCCCE HPH ZZZ",
                                    "XPPPP######PPP#PPPPZ",
                                    "XXX ECCCCCCE HPH ZZZ",
                                    "             VPV    ",
                                    "             BVB    ")
                            .setRepeatable(1)
                            .aisle(
                                    "            WDDW    ",
                                    "            WGGF    ",
                                    "            WIWW    ",
                                    "                    ",
                                    "                    ",
                                    "XXX ECCCCCCE HHH ZZZ",
                                    "XPPPP######PPP#PPPPZ",
                                    "XXX ECCCCCCE HPH ZZZ",
                                    "             VPV    ",
                                    "             BVB    ")
                            .setRepeatable(0, 14)
                            .aisle(
                                    "            WDDW    ",
                                    "            WGGF    ",
                                    "            WIPW    ",
                                    "              P     ",
                                    "              P     ",
                                    "XXX ECCCCCCE HPH ZZZ",
                                    "XPPPP######PPP#PPPPZ",
                                    "XXX ECCCCCCE HPH ZZZ",
                                    "             VPV    ",
                                    "             BVB    ")
                            .setRepeatable(0, 1)
                            .aisle(
                                    "            WWWW    ",
                                    "            WWWW    ",
                                    "            WWWW    ",
                                    "                    ",
                                    "                    ",
                                    "XXX EFCFFCFE HHH ZZZ",
                                    "XMX EICVVCIE HHH ZZZ",
                                    "XXX EFCFFCFE HHH ZZZ",
                                    "             BVB    ",
                                    "             BBB    ");
            case 8 -> // Cooled Alloy Forging
                    FactoryBlockPattern.start(RelativeDirection.RIGHT, RelativeDirection.UP, RelativeDirection.FRONT)
                            .aisle(
                                    "                     ",
                                    "                     ",
                                    "                     ",
                                    "                     ",
                                    "                     ",
                                    "    AICIVICIA        ",
                                    "    AICIVICIA        ",
                                    "    AICIVICIA        ",
                                    "                     ",
                                    "                     ")
                            .aisle(
                                    "             WWWW    ",
                                    "             WWWW    ",
                                    "             WWWW    ",
                                    "                     ",
                                    "    AFCFVFCFA        ",
                                    "XXX A#######A HHH ZZZ",
                                    "XSX A#######A HHH ZZZ",
                                    "XXX A#######A HHH ZZZ",
                                    "    AFCFVFCFA BVB    ",
                                    "              BBB    ")
                            .aisle(
                                    "             WDDW    ",
                                    "             WGGF    ",
                                    "             WIPW    ",
                                    "               P     ",
                                    "    ACCCVCCCA  P     ",
                                    "XXX A#######A HPH ZZZ",
                                    "XPPPP#######PPP#PPPPZ",
                                    "XXX A#######A HPH ZZZ",
                                    "    ACCCVCCCA VPV    ",
                                    "              BVB    ")
                            .setRepeatable(1)
                            .aisle(
                                    "             WDDW    ",
                                    "             WGGF    ",
                                    "             WIWW    ",
                                    "                     ",
                                    "    ACCCVCCCA        ",
                                    "XXX A#######A HHH ZZZ",
                                    "XPPPP#######PPP#PPPPZ",
                                    "XXX A#######A HPH ZZZ",
                                    "    ACCCVCCCA VPV    ",
                                    "              BVB    ")
                            .setRepeatable(0, 14)
                            .aisle(
                                    "             WDDW    ",
                                    "             WGGF    ",
                                    "             WIPW    ",
                                    "               P     ",
                                    "    ACCCVCCCA  P     ",
                                    "XXX A#######A HPH ZZZ",
                                    "XPPPP#######PPP#PPPPZ",
                                    "XXX A#######A HPH ZZZ",
                                    "    ACCCVCCCA VPV    ",
                                    "              BVB    ")
                            .setRepeatable(0, 1)
                            .aisle(
                                    "             WWWW    ",
                                    "             WWWW    ",
                                    "             WWWW    ",
                                    "                     ",
                                    "    AFCFVFCFA        ",
                                    "XXX A#######A HHH ZZZ",
                                    "XMX A#######A HHH ZZZ",
                                    "XXX A#######A HHH ZZZ",
                                    "    AFCFVFCFA BVB    ",
                                    "              BBB    ")
                            .aisle(
                                    "                     ",
                                    "                     ",
                                    "                     ",
                                    "                     ",
                                    "                     ",
                                    "    AICIVICIA        ",
                                    "    AICIVICIA        ",
                                    "    AICIVICIA        ",
                                    "                     ",
                                    "                     ");
            case 9 -> // Cooled Dual Forging
                    FactoryBlockPattern.start(RelativeDirection.RIGHT, RelativeDirection.UP, RelativeDirection.FRONT)
                            .aisle(
                                    "                     ",
                                    "                     ",
                                    "                     ",
                                    "                     ",
                                    "                     ",
                                    "    AICIVICIA        ",
                                    "    AICIVICIA        ",
                                    "    AICIVICIA        ",
                                    "                     ",
                                    "                     ")
                            .aisle(
                                    "EEE EFCFFCFE WWWW    ",
                                    "EEE EICVVCIE WWWW    ",
                                    "EEE EFCFFCFE WWWW    ",
                                    "                     ",
                                    "    AFCFVFCFA        ",
                                    "XXX A#######A HHH ZZZ",
                                    "XSX A#######A HHH ZZZ",
                                    "XXX A#######A HHH ZZZ",
                                    "    AFCFVFCFA BVB    ",
                                    "              BBB    ")
                            .aisle(
                                    "EEE ECCCCCCE WDDW    ",
                                    "EPPPP######PPPGGF    ",
                                    "EPE ECCCCCCE WIPW    ",
                                    " P             P     ",
                                    " P  ACCCVCCCA  P     ",
                                    "XPX A#######A HPH ZZZ",
                                    "XPPPP#######PPP#PPPPZ",
                                    "XXX A#######A HPH ZZZ",
                                    "    ACCCVCCCA VPV    ",
                                    "              BVB    ")
                            .setRepeatable(1)
                            .aisle(
                                    "EEE ECCCCCCE WDDW    ",
                                    "EPPPP######PPPGGF    ",
                                    "EEE ECCCCCCE WIWW    ",
                                    "                     ",
                                    "    ACCCVCCCA        ",
                                    "XXX A#######A HHH ZZZ",
                                    "XPPPP#######PPP#PPPPZ",
                                    "XXX A#######A HPH ZZZ",
                                    "    ACCCVCCCA VPV    ",
                                    "              BVB    ")
                            .setRepeatable(0, 14)
                            .aisle(
                                    "EEE ECCCCCCE WDDW    ",
                                    "EPPPP######PPPGGF    ",
                                    "EPE ECCCCCCE WIPW    ",
                                    " P             P     ",
                                    " P  ACCCVCCCA  P     ",
                                    "XPX A#######A HPH ZZZ",
                                    "XPPPP#######PPP#PPPPZ",
                                    "XXX A#######A HPH ZZZ",
                                    "    ACCCVCCCA VPV    ",
                                    "              BVB    ")
                            .setRepeatable(0, 1)
                            .aisle(
                                    "EEE EFCFFCFE WWWW    ",
                                    "EEE EICVVCIE WWWW    ",
                                    "EEE EFCFFCFE WWWW    ",
                                    "                     ",
                                    "    AFCFVFCFA        ",
                                    "XXX A#######A HHH ZZZ",
                                    "XMX A#######A HHH ZZZ",
                                    "XXX A#######A HHH ZZZ",
                                    "    AFCFVFCFA BVB    ",
                                    "              BBB    ")
                            .aisle(
                                    "                     ",
                                    "                     ",
                                    "                     ",
                                    "                     ",
                                    "                     ",
                                    "    AICIVICIA        ",
                                    "    AICIVICIA        ",
                                    "    AICIVICIA        ",
                                    "                     ",
                                    "                     ");
        })
                .where('S', selfPredicate())
                .where('M', abilities(MultiblockAbility.MUFFLER_HATCH))
                .where('W', casing)
                .where('X', casing.or(auto)
                        .or(abilities(MultiblockAbility.IMPORT_FLUIDS, MultiblockAbility.IMPORT_ITEMS)))
                .where('Z', casing.or(auto)
                        .or(abilities(MultiblockAbility.EXPORT_FLUIDS, MultiblockAbility.EXPORT_ITEMS)))
                .where('C', heatingCoils())
                .where('E', states(getCasingStateEBF()))
                .where('A', states(getCasingStateABS()))
                .where('H', states(getCasingStateVacuum()))
                .where('B', states(getCasingStateStainless()))
                .where('D', states(getCasingStateGlass()))
                .where('G', states(getGearboxState()))
                .where('F', states(getFireboxState()))
                .where('I', states(getIntakeState()))
                .where('P', states(getPipeState()))
                .where('V', states(getVentState()))
                .where('#', air())
                .where(' ', any())
                .build();
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        ArrayList<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
        MultiblockShapeInfo.Builder builder = MultiblockShapeInfo.builder()
                .where('M', MetaTileEntities.MUFFLER_HATCH[GTValues.HV], EnumFacing.NORTH)
                .where('X', getCasingState())
                .where('C', MetaBlocks.WIRE_COIL.getDefaultState())
                .where('E', getCasingStateEBF())
                .where('A', getCasingStateABS())
                .where('H', getCasingStateVacuum())
                .where('B', getCasingStateStainless())
                .where('D', getCasingStateGlass())
                .where('G', getGearboxState())
                .where('F', getFireboxState())
                .where('I', getIntakeState())
                .where('P', getPipeState())
                .where('V', getVentState())
                .where(' ', Blocks.AIR.getDefaultState())
                .where('Z', MetaTileEntities.ITEM_IMPORT_BUS[GTValues.HV], EnumFacing.SOUTH)
                .where('O', MetaTileEntities.ITEM_EXPORT_BUS[GTValues.HV], EnumFacing.SOUTH)
                .where('N', MetaTileEntities.FLUID_IMPORT_HATCH[GTValues.HV], EnumFacing.SOUTH)
                .where('T', MetaTileEntities.FLUID_EXPORT_HATCH[GTValues.HV], EnumFacing.SOUTH)
                .where('Y', MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.EV], EnumFacing.SOUTH)
                .where('L', getCasingState())
                .where('U',
                        () -> ConfigHolder.machines.enableMaintenance ? MetaTileEntities.MAINTENANCE_HATCH :
                                getCasingState(),
                        EnumFacing.SOUTH);
        MetaTileEntityLinearForgingFurnace fakeController = (MetaTileEntityLinearForgingFurnace) GCYMMetaTileEntities.LINEAR_FORGING_FURNACE
                .createMetaTileEntity(null);
        // Blast
        fakeController.inDisplayWorld(0);
        doRowCopies(shapeInfo, builder,
                a -> a.where('S', fakeController, EnumFacing.SOUTH)
                        .aisle("XXX EFCFFCFE XXX", "XMX EICVVCIE XXX", "XXX EFCFFCFE XXX"),
                (a, i, j) -> a.aisle("XXX ECCCCCCE XXX", "XPPPP      PPPPX", "XXX ECCCCCCE XXX"),
                a -> a.aisle("XXX EFCFFCFE XXX", "YSX EICVVCIE ULX", "ZXN EFCFFCFE OXT"));
        // Alloy
        fakeController.inDisplayWorld(1);
        doRowCopies(shapeInfo, builder,
                a -> a.where('S', fakeController, EnumFacing.SOUTH)
                        .aisle("                 ", "    AICIVICIA    ", "    AICIVICIA    ", "    AICIVICIA    ",
                                "                 ")
                        .aisle("    AFCFVFCFA    ", "XXX A#######A XXX", "XMX A#######A XXX", "XXX A#######A XXX",
                                "    AFCFVFCFA    "),
                (a, i, j) -> a.aisle("    ACCCVCCCA    ", "XXX A#######A XXX", "XPPPP#######PPPPX", "XXX A#######A XXX",
                        "    ACCCVCCCA    "),
                a -> a.aisle("    AFCFVFCFA    ", "XXX A#######A XXX", "YSX A#######A ULX", "ZXN A#######A OXT",
                        "    AFCFVFCFA    ")
                        .aisle("                 ", "    AICIVICIA    ", "    AICIVICIA    ", "    AICIVICIA    ",
                                "                 "));
        // Dual
        fakeController.inDisplayWorld(2);
        doRowCopies(shapeInfo, builder,
                a -> a.where('S', fakeController, EnumFacing.SOUTH)
                        .aisle("                 ", "                 ", "                 ", "                 ",
                                "                 ", "    AICIVICIA    ", "    AICIVICIA    ", "    AICIVICIA    ",
                                "                 ")
                        .aisle("EEE EFCFFCFE  EEE", "EEE EICVVCIE  EEE", "EEE EFCFFCFE  EEE", "                 ",
                                "    AFCFVFCFA    ", "XXX A#######A XXX", "XMX A#######A XXX", "XXX A#######A XXX",
                                "    AFCFVFCFA    "),
                (a, i, j) -> {
                    if (j == 0 || j == i - 1) {
                        a.aisle("EEE ECCCCCCE  EEE", "EPPPP######PPPPPE", "EPE ECCCCCCE  EPE", " P             P ",
                                " P  ACCCVCCCA  P ", "XPX A#######A XPX", "XPPPP#######PPPPX", "XXX A#######A XXX",
                                "    ACCCVCCCA    ");
                    } else {
                        a.aisle("EEE ECCCCCCE  EEE", "EPPPP######PPPPPE", "EEE ECCCCCCE  EEE", "                 ",
                                "    ACCCVCCCA    ", "XXX A#######A XXX", "XPPPP#######PPPPX", "XXX A#######A XXX",
                                "    ACCCVCCCA    ");
                    }
                },
                a -> a.aisle("EEE EFCFFCFE  EEE", "EEE EICVVCIE  EEE", "EEE EFCFFCFE  EEE", "                 ",
                        "    AFCFVFCFA    ", "XXX A#######A XXX", "YSX A#######A ULX", "ZXN A#######A OXT",
                        "    AFCFVFCFA    ")
                        .aisle("                 ", "                 ", "                 ", "                 ",
                                "                 ", "    AICIVICIA    ", "    AICIVICIA    ", "    AICIVICIA    ",
                                "                 "));
        // Freezer
        fakeController.inDisplayWorld(3);
        doRowCopies(shapeInfo, builder,
                a -> a.where('S', fakeController, EnumFacing.SOUTH)
                        .aisle("XXX HHH XXX", "XMX HHH XXX", "XXX HHH XXX", "    BVB    ", "    BBB    "),
                (a, i, j) -> a.aisle("XXX HHH XXX", "XPPPP#PPPPX", "XXX HPH XXX", "    VPV    ", "    BVB    "),
                a -> a.aisle("XXX HHH XXX", "YSX HHH ULX", "ZXN HHH OXT", "    BVB    ", "    BBB    "));
        // Cooled Blast
        fakeController.inDisplayWorld(4);
        doRowCopies(shapeInfo, builder,
                a -> a.where('S', fakeController, EnumFacing.SOUTH)
                        .aisle("XXX EFCFFCFE HHH XXX", "XMX EICVVCIE HHH XXX", "XXX EFCFFCFE HHH XXX",
                                "             BVB    ", "             BBB    "),
                (a, i, j) -> a.aisle("XXX ECCCCCCE HHH XXX", "XPPPP      PPP PPPPX", "XXX ECCCCCCE HPH XXX",
                        "             VPV    ", "             BVB    "),
                a -> a.aisle("XXX EFCFFCFE HHH XXX", "YSX EICVVCIE HHH ULX", "ZXN EFCFFCFE HHH OXT",
                        "             BVB    ", "             BBB    "));
        // Cooled Alloy
        fakeController.inDisplayWorld(5);
        doRowCopies(shapeInfo, builder,
                a -> a.where('S', fakeController, EnumFacing.SOUTH)
                        .aisle("                     ", "    AICIVICIA        ", "    AICIVICIA        ",
                                "    AICIVICIA        ", "                     ", "                     ")
                        .aisle("    AFCFVFCFA        ", "XXX A#######A HHH XXX", "XMX A#######A HHH XXX",
                                "XXX A#######A HHH XXX", "    AFCFVFCFA BVB    ", "              BBB    "),
                (a, i, j) -> a.aisle("    ACCCVCCCA        ", "XXX A#######A HHH XXX", "XPPPP#######PPP#PPPPX",
                        "XXX A#######A HPH XXX", "    ACCCVCCCA VPV    ", "              BVB    "),
                a -> a.aisle("    AFCFVFCFA        ", "XXX A#######A HHH XXX", "YSX A#######A HHH ULX",
                        "ZXN A#######A HHH OXT", "    AFCFVFCFA BVB    ", "              BBB    ")
                        .aisle("                     ", "    AICIVICIA        ", "    AICIVICIA        ",
                                "    AICIVICIA        ", "                     ", "                     "));
        // Cooled Dual
        fakeController.inDisplayWorld(6);
        doRowCopies(shapeInfo, builder,
                a -> a.where('S', fakeController, EnumFacing.SOUTH)
                        .aisle("                     ", "                     ", "                     ",
                                "                     ", "                     ", "    AICIVICIA        ",
                                "    AICIVICIA        ", "    AICIVICIA        ", "                     ",
                                "                     ")
                        .aisle("EEE EFCFFCFE  EEE    ", "EEE EICVVCIE  EEE    ", "EEE EFCFFCFE  EEE    ",
                                "                     ", "    AFCFVFCFA        ", "XXX A#######A HHH XXX",
                                "XMX A#######A HHH XXX", "XXX A#######A HHH XXX", "    AFCFVFCFA BVB    ",
                                "              BBB    "),
                (a, i, j) -> {
                    if (j == 0 || j == i - 1) {
                        a.aisle("EEE ECCCCCCE  EEE    ", "EPPPP######PPPPPE    ", "EPE ECCCCCCE  EPE    ",
                                " P             P     ", " P  ACCCVCCCA  P     ", "XPX A#######A HPH XXX",
                                "XPPPP#######PPP#PPPPX", "XXX A#######A HPH XXX", "    ACCCVCCCA VPV    ",
                                "              BVB    ");
                    } else {
                        a.aisle("EEE ECCCCCCE  EEE    ", "EPPPP######PPPPPE    ", "EEE ECCCCCCE  EEE    ",
                                "                     ", "    ACCCVCCCA        ", "XXX A#######A HHH XXX",
                                "XPPPP#######PPP#PPPPX", "XXX A#######A HPH XXX", "    ACCCVCCCA VPV    ",
                                "              BVB    ");
                    }
                },
                a -> a.aisle("EEE EFCFFCFE  EEE    ", "EEE EICVVCIE  EEE    ", "EEE EFCFFCFE  EEE    ",
                                "                     ", "    AFCFVFCFA        ", "XXX A#######A HHH XXX",
                                "YSX A#######A HHH ULX", "ZXN A#######A HHH OXT", "    AFCFVFCFA BVB    ",
                                "              BBB    ")
                        .aisle("                     ", "                     ", "                     ",
                                "                     ", "                     ", "    AICIVICIA        ",
                                "    AICIVICIA        ", "    AICIVICIA        ", "                     ",
                                "                     "));
        // Cooled Blast Forging
        fakeController.inDisplayWorld(7);
        doRowCopies(shapeInfo, builder,
                a -> a.where('S', fakeController, EnumFacing.SOUTH)
                        .aisle("            XXXX    ", "            XXXX    ", "            XXXX    ", "                    ", "                    ", "XXX EFCFFCFE HHH XXX", "XMX EICVVCIE HHH XXX", "XXX EFCFFCFE HHH XXX", "             BVB    ", "             BBB    "),
                (a, i, j) -> {
                    if (j == 0 || j == i - 1) {
                        a.aisle("            XDDX    ", "            XGGF    ", "            XIPX    ", "              P     ", "              P     ", "XXX ECCCCCCE HPH XXX", "XPPPP######PPP#PPPPX", "XXX ECCCCCCE HPH XXX", "             VPV    ", "             BVB    ");
                    } else {
                        a.aisle("            XDDX    ", "            XGGF    ", "            XIXX    ", "                    ", "                    ", "XXX ECCCCCCE HHH XXX", "XPPPP######PPP#PPPPX", "XXX ECCCCCCE HPH XXX", "             VPV    ", "             BVB    ");
                    }
                },
                a -> a.aisle("            XXXX    ", "            XXXX    ", "            XXXX    ", "                    ", "                    ", "XXX EFCFFCFE HHH XXX", "YSX EICVVCIE HHH ULX", "ZXN EFCFFCFE HHH OXT", "             BVB    ", "             BBB    "));
        // Cooled Alloy Forging
        fakeController.inDisplayWorld(8);
        doRowCopies(shapeInfo, builder,
                a -> a.where('S', fakeController, EnumFacing.SOUTH)
                        .aisle("                     ", "                     ", "                     ", "                     ", "                     ", "    AICIVICIA        ", "    AICIVICIA        ", "    AICIVICIA        ", "                     ", "                     ")
                        .aisle("             XXXX    ", "             XXXX    ", "             XXXX    ", "                     ", "    AFCFVFCFA        ", "XXX A#######A HHH XXX", "XMX A#######A HHH XXX", "XXX A#######A HHH XXX", "    AFCFVFCFA BVB    ", "              BBB    "),
                (a, i, j) -> {
                    if (j == 0 || j == i - 1) {
                        a.aisle("             XDDX    ", "             XGGF    ", "             XIPX    ", "               P     ", "    ACCCVCCCA  P     ", "XXX A#######A HPH XXX", "XPPPP#######PPP#PPPPX", "XXX A#######A HPH XXX", "    ACCCVCCCA VPV    ", "              BVB    ");
                    } else {
                        a.aisle("             XDDX    ", "             XGGF    ", "             XIXX    ", "                     ", "    ACCCVCCCA        ", "XXX A#######A HHH XXX", "XPPPP#######PPP#PPPPX", "XXX A#######A HPH XXX", "    ACCCVCCCA VPV    ", "              BVB    ");
                    }
                },
                a -> a.aisle("             XXXX    ", "             XXXX    ", "             XXXX    ", "                     ", "    AFCFVFCFA        ", "XXX A#######A HHH XXX", "YSX A#######A HHH ULX", "ZXN A#######A HHH OXT", "    AFCFVFCFA BVB    ", "              BBB    ")
                        .aisle("                     ", "                     ", "                     ", "                     ", "                     ", "    AICIVICIA        ", "    AICIVICIA        ", "    AICIVICIA        ", "                     ", "                     "));
        // Cooled Dual Forging
        fakeController.inDisplayWorld(9);
        doRowCopies(shapeInfo, builder,
                a -> a.where('S', fakeController, EnumFacing.SOUTH)
                        .aisle("                     ", "                     ", "                     ",
                                "                     ", "                     ", "    AICIVICIA        ",
                                "    AICIVICIA        ", "    AICIVICIA        ", "                     ",
                                "                     ")
                        .aisle("EEE EFCFFCFE XXXX    ", "EEE EICVVCIE XXXX    ", "EEE EFCFFCFE XXXX    ",
                                "                     ", "    AFCFVFCFA        ", "XXX A#######A HHH XXX",
                                "XMX A#######A HHH XXX", "XXX A#######A HHH XXX", "    AFCFVFCFA BVB    ",
                                "              BBB    "),
                (a, i, j) -> {
                    if (j == 0 || j == i - 1) {
                        a.aisle("EEE ECCCCCCE XDDX    ", "EPPPP######PPPGGF    ", "EPE ECCCCCCE XIPX    ",
                                " P             P     ", " P  ACCCVCCCA  P     ", "XPX A#######A HPH XXX",
                                "XPPPP#######PPP#PPPPX", "XXX A#######A HPH XXX", "    ACCCVCCCA VPV    ",
                                "              BVB    ");
                    } else {
                        a.aisle("EEE ECCCCCCE XDDX    ", "EPPPP######PPPGGF    ", "EEE ECCCCCCE XIXX    ",
                                "                     ", "    ACCCVCCCA        ", "XXX A#######A HHH XXX",
                                "XPPPP#######PPP#PPPPX", "XXX A#######A HPH XXX", "    ACCCVCCCA VPV    ",
                                "              BVB    ");
                    }
                },
                a -> a.aisle("EEE EFCFFCFE XXXX    ", "EEE EICVVCIE XXXX    ", "EEE EFCFFCFE XXXX    ",
                                "                     ", "    AFCFVFCFA        ", "XXX A#######A HHH XXX",
                                "YSX A#######A HHH ULX", "ZXN A#######A HHH OXT", "    AFCFVFCFA BVB    ",
                                "              BBB    ")
                        .aisle("                     ", "                     ", "                     ",
                                "                     ", "                     ", "    AICIVICIA        ",
                                "    AICIVICIA        ", "    AICIVICIA        ", "                     ",
                                "                     "));
        return shapeInfo;
    }

    public static void doRowCopies(ArrayList<MultiblockShapeInfo> shapeInfo, MultiblockShapeInfo.Builder builder,
                                   Consumer<MultiblockShapeInfo.Builder> preActions,
                                   TriConsumer<MultiblockShapeInfo.Builder, Integer, Integer> copyActions,
                                   Consumer<MultiblockShapeInfo.Builder> postActions) {
        int z = -1;
        for (int i = 1; i <= 16; i *= 2) { // 1, 2, 4, 8, 16
            MultiblockShapeInfo.Builder copy = builder.shallowCopy();
            if (z != -1) {
                copy.where('L', GCYMMetaTileEntities.PARALLEL_HATCH[z], EnumFacing.SOUTH);
            }
            z++;

            preActions.accept(copy);
            for (int j = 0; j < i; j++) {
                copyActions.accept(copy, i, j);
            }
            postActions.accept(copy);
            shapeInfo.add(copy.build());
        }
    }

    private static IBlockState getCasingState() {
        return GCYMMetaBlocks.LARGE_MULTIBLOCK_CASING
                .getState(BlockLargeMultiblockCasing.CasingType.FORGING_CASING);
    }

    private static IBlockState getCasingStateEBF() {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.INVAR_HEATPROOF);
    }

    private static IBlockState getCasingStateABS() {
        return GCYMMetaBlocks.LARGE_MULTIBLOCK_CASING
                .getState(BlockLargeMultiblockCasing.CasingType.HIGH_TEMPERATURE_CASING);
    }

    private static IBlockState getCasingStateVacuum() {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.ALUMINIUM_FROSTPROOF);
    }

    private static IBlockState getCasingStateStainless() {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STAINLESS_CLEAN);
    }

    private static IBlockState getCasingStateGlass() {
        return MetaBlocks.TRANSPARENT_CASING.getState(BlockGlassCasing.CasingType.TEMPERED_GLASS);
    }

    private static IBlockState getGearboxState() {
        return MetaBlocks.TURBINE_CASING.getState(BlockTurbineCasing.TurbineCasingType.TUNGSTENSTEEL_GEARBOX);
    }

    private static IBlockState getFireboxState() {
        return MetaBlocks.BOILER_FIREBOX_CASING.getState(BlockFireboxCasing.FireboxCasingType.TUNGSTENSTEEL_FIREBOX);
    }

    private static IBlockState getIntakeState() {
        return MetaBlocks.MULTIBLOCK_CASING
                .getState(BlockMultiblockCasing.MultiblockCasingType.EXTREME_ENGINE_INTAKE_CASING);
    }

    private static IBlockState getPipeState() {
        return MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TUNGSTENSTEEL_PIPE);
    }

    private static IBlockState getVentState() {
        return GCYMMetaBlocks.UNIQUE_CASING.getState(BlockUniqueCasing.UniqueCasingType.HEAT_VENT);
    }

    @Override
    public void update() {
        super.update();
        if (!this.getWorld().isRemote) {
            if (this.lastRecipeType != this.getRecipeType() && this.isActive())
                this.replaceVariantBlocksActive(true);
        }
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        this.blastFurnaceTemperature = 0;
        this.rowCount = 0;
        this.lastRecipeType = LinearForgingFurnaceRecipeType.NONE;
    }

    @Override
    protected void replaceVariantBlocksActive(boolean isActive) {
        if (this.variantActiveBlocks != null && !this.variantActiveBlocks.isEmpty()) {
            int id = this.getWorld().provider.getDimension();
            this.writeCustomData(GregtechDataCodes.VARIANT_RENDER_UPDATE, (buf) -> {
                buf.writeInt(id);
                buf.writeBoolean(isActive);
                buf.writeInt(this.getRecipeMapIndex());
                buf.writeInt(this.getRecipeType().ordinal());

                buf.writeInt(this.variantActiveBlocks.size());
                Iterator<BlockPos> var4 = this.variantActiveBlocks.iterator();

                while (var4.hasNext()) {
                    BlockPos blockPos = var4.next();
                    VariantActiveBlock.setBlockActive(id, blockPos, isActive &&
                            shouldVABlockBeActive(blockPos, this.getRecipeMapIndex(), this.getRecipeType()));
                    buf.writeBlockPos(blockPos);
                }

            });
        }
    }

    @Override
    public void receiveCustomData(int dataId, PacketBuffer buf) {
        if (dataId == GregtechDataCodes.VARIANT_RENDER_UPDATE) {
            int minZ = Integer.MAX_VALUE;
            int minY = Integer.MAX_VALUE;
            int minX = Integer.MAX_VALUE;
            int maxZ = Integer.MIN_VALUE;
            int maxY = Integer.MIN_VALUE;
            int maxX = Integer.MIN_VALUE;
            int id = buf.readInt();
            boolean isActive = buf.readBoolean();
            int recipeMapIndex = buf.readInt();
            LinearForgingFurnaceRecipeType recipeType = LinearForgingFurnaceRecipeType.values()[buf.readInt()];

            int size = buf.readInt();

            for (int i = 0; i < size; ++i) {
                BlockPos blockPos = buf.readBlockPos();
                VariantActiveBlock.setBlockActive(id, blockPos,
                        isActive && shouldVABlockBeActive(blockPos, recipeMapIndex, recipeType));
                minX = Math.min(minX, blockPos.getX());
                minY = Math.min(minY, blockPos.getY());
                minZ = Math.min(minZ, blockPos.getZ());
                maxX = Math.max(maxX, blockPos.getX());
                maxY = Math.max(maxY, blockPos.getY());
                maxZ = Math.max(maxZ, blockPos.getZ());
            }

            if (this.getWorld().provider.getDimension() == id) {
                this.getWorld().markBlockRangeForRenderUpdate(new BlockPos(minX, minY, minZ),
                        new BlockPos(maxX, maxY, maxZ));
            }
            return;
        }

        super.receiveCustomData(dataId, buf);
    }

    protected boolean shouldVABlockBeActive(BlockPos pos, int recipeMapIndex,
                                            LinearForgingFurnaceRecipeType recipeType) {
        if (recipeMapIndex != 2 && recipeMapIndex != 6 && recipeMapIndex != 9) return true;
        return switch (recipeType) {
            default -> true;
            case BLAST -> {// bottom half
                EnumFacing relativeDown = RelativeDirection.DOWN
                        .getRelativeFacing(this.getFrontFacing(), this.getUpwardsFacing(), this.isFlipped());
                BlockPos comparingPos = this.getPos().offset(relativeDown, 3);

                int x = comparingPos.getX() - this.getPos().getX();
                int y = comparingPos.getY() - this.getPos().getY();
                int z = comparingPos.getZ() - this.getPos().getZ();
                yield ((pos.getX() - comparingPos.getX()) * x > 0) || ((pos.getY() - comparingPos.getY()) * y > 0) ||
                        ((pos.getZ() - comparingPos.getZ()) * z > 0);
            }
            case ALLOY -> {// top half
                EnumFacing relativeDown = RelativeDirection.DOWN
                        .getRelativeFacing(this.getFrontFacing(), this.getUpwardsFacing(), this.isFlipped());
                BlockPos comparingPos = this.getPos().offset(relativeDown, 3);

                int x = comparingPos.getX() - this.getPos().getX();
                int y = comparingPos.getY() - this.getPos().getY();
                int z = comparingPos.getZ() - this.getPos().getZ();
                yield ((pos.getX() - comparingPos.getX()) * x < 0) || ((pos.getY() - comparingPos.getY()) * y < 0) ||
                        ((pos.getZ() - comparingPos.getZ()) * z < 0);
            }
            case BLAST_COOLED, BLAST_FORGING_COOLED -> {// bottom half
                EnumFacing relativeDown = RelativeDirection.DOWN
                        .getRelativeFacing(this.getFrontFacing(), this.getUpwardsFacing(), this.isFlipped());
                EnumFacing relativeLeft = RelativeDirection.LEFT
                        .getRelativeFacing(this.getFrontFacing(), this.getUpwardsFacing(), this.isFlipped());

                BlockPos comparingPos = this.getPos().offset(relativeDown, 3);
                int x = comparingPos.getX() - this.getPos().getX();
                int y = comparingPos.getY() - this.getPos().getY();
                int z = comparingPos.getZ() - this.getPos().getZ();
                boolean flag;
                flag = ((pos.getX() - comparingPos.getX()) * x > 0) || ((pos.getY() - comparingPos.getY()) * y > 0) ||
                        ((pos.getZ() - comparingPos.getZ()) * z > 0);

                comparingPos = this.getPos().offset(relativeLeft, 11);
                x = comparingPos.getX() - this.getPos().getX();
                y = comparingPos.getY() - this.getPos().getY();
                z = comparingPos.getZ() - this.getPos().getZ();
                flag |= ((pos.getX() - comparingPos.getX()) * x > 0) || ((pos.getY() - comparingPos.getY()) * y > 0) ||
                        ((pos.getZ() - comparingPos.getZ()) * z > 0);
                yield flag;
            }
            case ALLOY_COOLED, ALLOY_FORGING_COOLED -> {// top half
                EnumFacing relativeDown = RelativeDirection.DOWN
                        .getRelativeFacing(this.getFrontFacing(), this.getUpwardsFacing(), this.isFlipped());
                EnumFacing relativeLeft = RelativeDirection.LEFT
                        .getRelativeFacing(this.getFrontFacing(), this.getUpwardsFacing(), this.isFlipped());

                BlockPos comparingPos = this.getPos().offset(relativeDown, 3);
                int x = comparingPos.getX() - this.getPos().getX();
                int y = comparingPos.getY() - this.getPos().getY();
                int z = comparingPos.getZ() - this.getPos().getZ();
                boolean flag;
                flag = ((pos.getX() - comparingPos.getX()) * x < 0) || ((pos.getY() - comparingPos.getY()) * y < 0) ||
                        ((pos.getZ() - comparingPos.getZ()) * z < 0);

                comparingPos = this.getPos().offset(relativeLeft, 11);
                x = comparingPos.getX() - this.getPos().getX();
                y = comparingPos.getY() - this.getPos().getY();
                z = comparingPos.getZ() - this.getPos().getZ();
                flag |= ((pos.getX() - comparingPos.getX()) * x > 0) || ((pos.getY() - comparingPos.getY()) * y > 0) ||
                        ((pos.getZ() - comparingPos.getZ()) * z > 0);
                yield flag;
            }
        };
    }

    protected LinearForgingFurnaceRecipeType getRecipeType() {
        Recipe recipe = this.getRecipeMapWorkable().getPreviousRecipe();
        LinearForgingFurnaceRecipeType type = LinearForgingFurnaceRecipeType.getRecipeType(recipe,
                this.getCurrentRecipeMap());
        if (type == LinearForgingFurnaceRecipeType.NONE) {
            type = this.lastRecipeType;
        } else this.lastRecipeType = type;
        return type;
    }

    @Override
    public boolean checkRecipe(@NotNull Recipe recipe, boolean consumeIfSuccess) {
        return this.blastFurnaceTemperature >= recipe.getProperty(TemperatureProperty.getInstance(), 0);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, @NotNull List<String> tooltip,
                               boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gregtech.machine.electric_blast_furnace.tooltip.1"));
        tooltip.add(I18n.format("gregtech.machine.electric_blast_furnace.tooltip.2"));
        tooltip.add(I18n.format("gregtech.machine.electric_blast_furnace.tooltip.3"));
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return GCYMTextures.FORGING_CASING;
    }

    @Override
    protected @NotNull OrientedOverlayRenderer getFrontOverlay() {
        return GCYMTextures.LINEAR_FORGING_FURNACE_OVERLAY;
    }

    @Override
    public boolean hasMufflerMechanics() {
        return this.getRecipeMapIndex() != 3; // recipe map #4 is just vacuum
    }

    @Override
    public boolean canBeDistinct() {
        return true;
    }

    @Override
    public int getCurrentTemperature() {
        return this.blastFurnaceTemperature;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        data.setInteger("LastRecipeType", this.getRecipeType().ordinal());
        return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        this.lastRecipeType = LinearForgingFurnaceRecipeType.values()[data.getInteger("LastRecipeType")];
    }

    @Override
    public int getMaxParallel() {
        return Math.min(super.getMaxParallel(), this.rowCount * this.rowCount);
    }

    protected MetaTileEntityLinearForgingFurnace inDisplayWorld(int recipeMapIndex) {
        this.inDisplayWorld = recipeMapIndex;
        return this;
    }

    @Override
    public int getRecipeMapIndex() {
        return this.inDisplayWorld == -1 ? super.getRecipeMapIndex() : this.inDisplayWorld;
    }
}
