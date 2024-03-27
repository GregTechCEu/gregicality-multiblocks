package gregicality.multiblocks.common.metatileentities.multiblock.advanced;

import gregicality.multiblocks.api.capability.impl.GCYMHeatingCoilRecipeLogic;
import gregicality.multiblocks.api.metatileentity.GCYMMultiShapeMultiblockController;
import gregicality.multiblocks.api.recipes.GCYMRecipeMaps;
import gregicality.multiblocks.api.render.GCYMTextures;
import gregicality.multiblocks.common.block.GCYMMetaBlocks;
import gregicality.multiblocks.common.block.blocks.BlockLargeMultiblockCasing;
import gregicality.multiblocks.common.block.blocks.BlockUniqueCasing;
import gregicality.multiblocks.common.metatileentities.GCYMMetaTileEntities;
import gregtech.api.GTValues;
import gregtech.api.GregTechAPI;
import gregtech.api.block.IHeatingCoilBlockStats;
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
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.*;
import gregtech.common.metatileentities.MetaTileEntities;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class MetaTileEntityLinearForgingFurnace extends GCYMMultiShapeMultiblockController implements IHeatingCoil {

    private int blastFurnaceTemperature;
    private int rowCount;
    private int inDisplayWorld = -1;

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
        int VAs = ((LinkedList<?>)context.get("VABlock")).size();
        return switch (this.getRecipeMapIndex()) {
            default -> // Blast
                    (VAs - 36) / 12;
            case 1 -> // Alloy
                    (VAs - 70) / 14;
//            case 2 -> // Dual
//            case 3 -> // Freezer
//            case 4 -> // Blast Cooling
//            case 5 -> // Alloy Cooling
//            case 6 -> // Dual Cooling
//            case 7 -> // Blast Forging
//            case 8 -> // Alloy Forging
//            case 9 -> // Dual Forging
        };
    }

    @Override
    protected @NotNull BlockPattern getStructurePattern(int index) {
        TraceabilityPredicate casing = states(getCasingState()).setMinLayerLimited(10);
        TraceabilityPredicate auto = autoAbilities(true, true, false, false, false, false, false);
        return (switch (index) {
            default -> // Blast
                    FactoryBlockPattern.start(RelativeDirection.RIGHT, RelativeDirection.UP, RelativeDirection.FRONT)
                            .aisle("XXX EFCFFCFE ZZZ", "XSX EICVVCIE ZZZ", "XXX EFCFFCFE ZZZ")
                            .aisle("XXX ECCCCCCE ZZZ", "XPPPP######PPPPZ", "XXX ECCCCCCE ZZZ").setRepeatable(1, 16)
                            .aisle("XXX EFCFFCFE ZZZ", "XMX EICVVCIE ZZZ", "XXX EFCFFCFE ZZZ");
            case 1 -> // Alloy
                    FactoryBlockPattern.start(RelativeDirection.RIGHT, RelativeDirection.UP, RelativeDirection.FRONT)
                            .aisle("                 ", "    AICIVICIA    ", "    AICIVICIA    ", "    AICIVICIA    ", "                 ")
                            .aisle("    AFCFVFCFA    ", "XXX A#######A ZZZ", "XSX A#######A ZZZ", "XXX A#######A ZZZ", "    AFCFVFCFA    ")
                            .aisle("    ACCCVCCCA    ", "XXX A#######A ZZZ", "XPPPP#######PPPPZ", "XXX A#######A ZZZ", "    ACCCVCCCA    ").setRepeatable(1, 16)
                            .aisle("    AFCFVFCFA    ", "XXX A#######A ZZZ", "XMX A#######A ZZZ", "XXX A#######A ZZZ", "    AFCFVFCFA    ")
                            .aisle("                 ", "    AICIVICIA    ", "    AICIVICIA    ", "    AICIVICIA    ", "                 ");
//            case 2 -> // Dual
//            case 3 -> // Freezer
//            case 4 -> // Blast Cooling
//            case 5 -> // Alloy Cooling
//            case 6 -> // Dual Cooling
//            case 7 -> // Blast Forging
//            case 8 -> // Alloy Forging
//            case 9 -> // Dual Forging
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
                .where('H',
                        () -> ConfigHolder.machines.enableMaintenance ? MetaTileEntities.MAINTENANCE_HATCH :
                                getCasingState(),
                        EnumFacing.SOUTH);
        MetaTileEntityLinearForgingFurnace fakeController = (MetaTileEntityLinearForgingFurnace)
                GCYMMetaTileEntities.LINEAR_FORGING_FURNACE.createMetaTileEntity(null);
        //Blast
        fakeController.inDisplayWorld(0);
        doRowCopies(shapeInfo, builder,
                a -> a.where('S', fakeController, EnumFacing.SOUTH)
                        .aisle("XXX EFCFFCFE XXX", "XMX EICVVCIE XXX", "XXX EFCFFCFE XXX"),
                a -> a.aisle("XXX ECCCCCCE XXX", "XPPPP      PPPPX", "XXX ECCCCCCE XXX"),
                a -> a.aisle("XXX EFCFFCFE XXX", "YSX EICVVCIE HLX", "ZXN EFCFFCFE OXT"));
        //Alloy
        fakeController.inDisplayWorld(1);
        doRowCopies(shapeInfo, builder,
                a -> a.where('S', fakeController, EnumFacing.SOUTH)
                        .aisle("                 ", "    AICIVICIA    ", "    AICIVICIA    ", "    AICIVICIA    ", "                 ")
                        .aisle("    AFCFVFCFA    ", "XXX A#######A XXX", "XMX A#######A XXX", "XXX A#######A XXX", "    AFCFVFCFA    "),
                a -> a.aisle("    ACCCVCCCA    ", "XXX A#######A XXX", "XPPPP#######PPPPX", "XXX A#######A XXX", "    ACCCVCCCA    "),
                a -> a.aisle("    AFCFVFCFA    ", "XXX A#######A XXX", "YSX A#######A HLX", "ZXN A#######A OXT", "    AFCFVFCFA    ")
                        .aisle("                 ", "    AICIVICIA    ", "    AICIVICIA    ", "    AICIVICIA    ", "                 "));
        return shapeInfo;
    }

    public static void doRowCopies(ArrayList<MultiblockShapeInfo> shapeInfo, MultiblockShapeInfo.Builder builder,
                               Consumer<MultiblockShapeInfo.Builder> preActions,
                               Consumer<MultiblockShapeInfo.Builder> copyActions,
                               Consumer<MultiblockShapeInfo.Builder> postActions) {
        int z = -1;
        for (int i = 1; i <= 16; i*=2) { //1, 2, 4, 8, 16
            MultiblockShapeInfo.Builder copy = builder.shallowCopy();
            if (z != -1) {
                copy.where('L', GCYMMetaTileEntities.PARALLEL_HATCH[z], EnumFacing.SOUTH);
            }
            z++;

            preActions.accept(copy);
            for (int j = 0; j < i; j++) {
                copyActions.accept(copy);
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
    public void invalidateStructure() {
        super.invalidateStructure();
        this.blastFurnaceTemperature = 0;
        this.rowCount = 0;
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
