package gregicality.machines.integration.jei.multiblock.info.standard;

import gregicality.machines.GregicalityMultiblocks;
import gregicality.machines.common.block.GCYMultiMetaBlocks;
import gregicality.machines.common.block.blocks.BlockLargeMultiblockCasing;
import gregicality.machines.common.metatileentities.GCYMultiMetaTileEntities;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
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

public class LargeSolidifierInfo extends MultiblockInfoPage {

    @Override
    public MultiblockControllerBase getController() {
        return GCYMultiMetaTileEntities.LARGE_SOLIDIFIER;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        List<MultiblockShapeInfo> shapeInfos = new ArrayList<>();

        shapeInfos.add(MultiblockShapeInfo.builder()
                .aisle("#XXX#", "#XXX#", "#XXX#")
                .aisle("XXXXX", "XC#CX", "XXXXX")
                .aisle("XXXXX", "X###X", "XXXXX")
                .aisle("XXXXX", "XC#CX", "XXXXX")
                .aisle("#EMO#", "#FSI#", "#XXX#")
                .where('S', getController(), EnumFacing.WEST)
                .where('X', GCYMultiMetaBlocks.LARGE_MULTIBLOCK_CASING.getState(BlockLargeMultiblockCasing.CasingType.FLUID_SAFE_CASING))
                .where('C', MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.STEEL_PIPE))
                .where('#', Blocks.AIR.getDefaultState())
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.HV], EnumFacing.WEST)
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[GTValues.HV], EnumFacing.WEST)
                .where('F', MetaTileEntities.FLUID_IMPORT_HATCH[GTValues.HV], EnumFacing.WEST)
                .where('O', MetaTileEntities.ITEM_EXPORT_BUS[GTValues.HV], EnumFacing.EAST)
                .where('M', maintenanceIfEnabled(GCYMultiMetaBlocks.LARGE_MULTIBLOCK_CASING.getState(BlockLargeMultiblockCasing.CasingType.FLUID_SAFE_CASING)), EnumFacing.WEST)
                .build());

        shapeInfos.add(MultiblockShapeInfo.builder()
                .aisle("#XXX#", "#XXX#", "#XXX#", "#XXX#")
                .aisle("XXXXX", "XC#CX", "XC#CX", "XXXXX")
                .aisle("XXXXX", "X###X", "X###X", "XXXXX")
                .aisle("XXXXX", "XC#CX", "XC#CX", "XXXXX")
                .aisle("#EMO#", "#FSI#", "#XXX#", "#XXX#")
                .where('S', getController(), EnumFacing.WEST)
                .where('X', GCYMultiMetaBlocks.LARGE_MULTIBLOCK_CASING.getState(BlockLargeMultiblockCasing.CasingType.FLUID_SAFE_CASING))
                .where('C', MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.STEEL_PIPE))
                .where('#', Blocks.AIR.getDefaultState())
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.HV], EnumFacing.WEST)
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[GTValues.HV], EnumFacing.WEST)
                .where('F', MetaTileEntities.FLUID_IMPORT_HATCH[GTValues.HV], EnumFacing.WEST)
                .where('O', MetaTileEntities.ITEM_EXPORT_BUS[GTValues.HV], EnumFacing.EAST)
                .where('M', maintenanceIfEnabled(GCYMultiMetaBlocks.LARGE_MULTIBLOCK_CASING.getState(BlockLargeMultiblockCasing.CasingType.FLUID_SAFE_CASING)), EnumFacing.WEST)
                .build());

        shapeInfos.add(MultiblockShapeInfo.builder()
                .aisle("#XXX#", "#XXX#", "#XXX#", "#XXX#", "#XXX#")
                .aisle("XXXXX", "XC#CX", "XC#CX", "XC#CX", "XXXXX")
                .aisle("XXXXX", "X###X", "X###X", "X###X", "XXXXX")
                .aisle("XXXXX", "XC#CX", "XC#CX", "XC#CX", "XXXXX")
                .aisle("#EMO#", "#FSI#", "#XXX#", "#XXX#", "#XXX#")
                .where('S', getController(), EnumFacing.WEST)
                .where('X', GCYMultiMetaBlocks.LARGE_MULTIBLOCK_CASING.getState(BlockLargeMultiblockCasing.CasingType.FLUID_SAFE_CASING))
                .where('C', MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.STEEL_PIPE))
                .where('#', Blocks.AIR.getDefaultState())
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.HV], EnumFacing.WEST)
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[GTValues.HV], EnumFacing.WEST)
                .where('F', MetaTileEntities.FLUID_IMPORT_HATCH[GTValues.HV], EnumFacing.WEST)
                .where('O', MetaTileEntities.ITEM_EXPORT_BUS[GTValues.HV], EnumFacing.EAST)
                .where('M', maintenanceIfEnabled(GCYMultiMetaBlocks.LARGE_MULTIBLOCK_CASING.getState(BlockLargeMultiblockCasing.CasingType.FLUID_SAFE_CASING)), EnumFacing.WEST)
                .build());

        return shapeInfos;
    }

    @Override
    public String[] getDescription() {
        return new String[]{I18n.format(String.format("%s.multiblock.large_solidifier.description", GregicalityMultiblocks.MODID))};
    }

    @Override
    public float getDefaultZoom() {
        return 0.8f;
    }

    @Override
    protected void generateBlockTooltips() {
        super.generateBlockTooltips();

        ITextComponent tooltip = new TextComponentTranslation("gregtech.multiblock.preview.limit", 35).setStyle(new Style().setColor(TextFormatting.AQUA));
        addBlockTooltip(GCYMultiMetaBlocks.LARGE_MULTIBLOCK_CASING.getItemVariant(BlockLargeMultiblockCasing.CasingType.FLUID_SAFE_CASING), tooltip);
    }
}
