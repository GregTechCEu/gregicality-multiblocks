package gregicality.machines.jei.multiblock.info.standard;

import gregicality.machines.GregicalityMultiblocks;
import gregicality.machines.common.block.GCYMultiMetaBlocks;
import gregicality.machines.common.block.blocks.BlockUniqueCasing;
import gregicality.machines.common.metatileentities.GCYMultiMetaTileEntities;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.common.metatileentities.electric.multiblockpart.MetaTileEntityMufflerHatch;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

public class LargeDistilleryInfo extends MultiblockInfoPage {

    @Override
    public MultiblockControllerBase getController() {
        return GCYMultiMetaTileEntities.LARGE_DISTILLERY;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        List<MultiblockShapeInfo> shapeInfos = new ArrayList<>();

        shapeInfos.add(MultiblockShapeInfo.builder()
                .aisle("#XXX#", "#XXX#", "##X##", "#####")
                .aisle("XXXXX", "X###X", "#X#X#", "#XXX#")
                .aisle("XXFXX", "X#P#X", "X#P#X", "#XCX#")
                .aisle("XXXXE", "X###X", "#X#X#", "#XXX#")
                .aisle("#IMO#", "#XSX#", "##A##", "#####")
                .where('S', getController(), EnumFacing.WEST)
                .where('X', MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STAINLESS_CLEAN))
                .where('P', MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.STEEL_PIPE))
                .where('#', Blocks.AIR.getDefaultState())
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.HV], EnumFacing.WEST)
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[GTValues.HV], EnumFacing.WEST)
                .where('O', MetaTileEntities.ITEM_EXPORT_BUS[GTValues.HV], EnumFacing.WEST)
                .where('F', MetaTileEntities.FLUID_IMPORT_HATCH[GTValues.HV], EnumFacing.DOWN)
                .where('A', MetaTileEntities.FLUID_EXPORT_HATCH[GTValues.HV], EnumFacing.WEST)
                .where('C', MetaTileEntities.MUFFLER_HATCH[GTValues.HV - 1], EnumFacing.UP)
                .where('M', maintenanceIfEnabled(MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STAINLESS_CLEAN)), EnumFacing.WEST)
                .build());

        shapeInfos.add(MultiblockShapeInfo.builder()
                .aisle("#XXX#", "#XXX#", "##X##", "##X##", "##X##", "##X##", "##X##", "##X##", "#####")
                .aisle("XXXXX", "X###X", "#X#X#", "#X#X#", "#X#X#", "#X#X#", "#X#X#", "#X#X#", "#XXX#")
                .aisle("XXFXX", "X#P#X", "X#P#X", "X#P#X", "X#P#X", "X#P#X", "X#P#X", "X#P#X", "#XCX#")
                .aisle("XXXXE", "X###X", "#X#X#", "#X#X#", "#X#X#", "#X#X#", "#X#X#", "#X#X#", "#XXX#")
                .aisle("#IMO#", "#XSX#", "##A##", "##A##", "##A##", "##A##", "##A##", "##A##", "#####")
                .where('S', getController(), EnumFacing.WEST)
                .where('X', MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STAINLESS_CLEAN))
                .where('P', MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.STEEL_PIPE))
                .where('#', Blocks.AIR.getDefaultState())
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.HV], EnumFacing.WEST)
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[GTValues.HV], EnumFacing.WEST)
                .where('O', MetaTileEntities.ITEM_EXPORT_BUS[GTValues.HV], EnumFacing.WEST)
                .where('F', MetaTileEntities.FLUID_IMPORT_HATCH[GTValues.HV], EnumFacing.DOWN)
                .where('A', MetaTileEntities.FLUID_EXPORT_HATCH[GTValues.HV], EnumFacing.WEST)
                .where('C', MetaTileEntities.MUFFLER_HATCH[GTValues.HV - 1], EnumFacing.UP)
                .where('M', maintenanceIfEnabled(MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STAINLESS_CLEAN)), EnumFacing.WEST)
                .build());

        return shapeInfos;
    }

    @Override
    public String[] getDescription() {
        return new String[]{I18n.format(String.format("%s.multiblock.large_distillery.description", GregicalityMultiblocks.MODID))};
    }

    @Override
    public float getDefaultZoom() {
        return 0.8f;
    }

    @Override
    protected void generateBlockTooltips() {
        super.generateBlockTooltips();

        ITextComponent tooltip = new TextComponentTranslation("gregtech.multiblock.preview.limit", 40).setStyle(new Style().setColor(TextFormatting.AQUA));
        addBlockTooltip(MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.STAINLESS_CLEAN), tooltip);

        ITextComponent fluidTooltip = new TextComponentTranslation("gregtech.multiblock.preview.limit_per_layer", 1).setStyle(new Style().setColor(TextFormatting.DARK_RED));
        ITextComponent inputTooltip = new TextComponentTranslation("gregtech.multiblock.preview.only_location", I18n.format("gregtech.multiblock.preview.location.b_l")).setStyle(new Style().setColor(TextFormatting.DARK_RED));
        ITextComponent outputTooltip = new TextComponentTranslation("gregtech.multiblock.preview.distillation_multi_fluid").setStyle(new Style().setColor(TextFormatting.RED));

        addBlockTooltip(MetaTileEntities.FLUID_EXPORT_HATCH[GTValues.HV].getStackForm(), fluidTooltip);
        addBlockTooltip(MetaTileEntities.FLUID_IMPORT_HATCH[GTValues.HV].getStackForm(), inputTooltip);
        addBlockTooltip(MetaTileEntities.ITEM_IMPORT_BUS[GTValues.HV].getStackForm(), inputTooltip);
        addBlockTooltip(MetaTileEntities.ITEM_EXPORT_BUS[GTValues.HV].getStackForm(), inputTooltip);
        addBlockTooltip(MetaTileEntities.FLUID_EXPORT_HATCH[GTValues.HV].getStackForm(), outputTooltip);

        ITextComponent mufflerTooltip = new TextComponentTranslation("gregtech.multiblock.preview.only_location", I18n.format("gregtech.multiblock.preview.location.t_c")).setStyle(new Style().setColor(TextFormatting.DARK_RED));
        addBlockTooltip(MetaTileEntities.MUFFLER_HATCH[GTValues.HV - 1].getStackForm(), mufflerTooltip);
    }
}
