package gregicality.machines.integration.jei.multiblock.info.standard;

import gregicality.machines.GregicalityMultiblocks;
import gregicality.machines.common.block.GCYMultiMetaBlocks;
import gregicality.machines.common.block.blocks.BlockLargeMultiblockCasing;
import gregicality.machines.common.block.blocks.BlockUniqueCasing;
import gregicality.machines.common.metatileentities.GCYMultiMetaTileEntities;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
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

public class LargeMaceratorInfo extends MultiblockInfoPage {

    @Override
    public MultiblockControllerBase getController() {
        return GCYMultiMetaTileEntities.LARGE_MACERATOR;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        List<MultiblockShapeInfo> shapeInfos = new ArrayList<>();

        shapeInfos.add(MultiblockShapeInfo.builder()
                .aisle("#XXX#", "#XXX#", "#XXX#", "XXXXX")
                .aisle("XXXXX", "XCCCX", "XCCCX", "X###X")
                .aisle("XXXXX", "XCCCX", "XCCCX", "X###X")
                .aisle("XXXXX", "XCCCX", "XCCCX", "X###X")
                .aisle("#OME#", "#ISX#", "#XXX#", "XXXXX")
                .where('S', getController(), EnumFacing.WEST)
                .where('X', GCYMultiMetaBlocks.LARGE_MULTIBLOCK_CASING.getState(BlockLargeMultiblockCasing.CasingType.MACERATOR_CASING))
                .where('C', GCYMultiMetaBlocks.UNIQUE_CASING.getState(BlockUniqueCasing.UniqueCasingType.CRUSHING_WHEELS))
                .where('#', Blocks.AIR.getDefaultState())
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.HV], EnumFacing.WEST)
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[GTValues.HV], EnumFacing.WEST)
                .where('O', MetaTileEntities.ITEM_EXPORT_BUS[GTValues.HV], EnumFacing.WEST)
                .where('M', maintenanceIfEnabled(GCYMultiMetaBlocks.LARGE_MULTIBLOCK_CASING.getState(BlockLargeMultiblockCasing.CasingType.MACERATOR_CASING)), EnumFacing.WEST)
                .build());

        shapeInfos.add(MultiblockShapeInfo.builder()
                .aisle("#XXX#", "#XXX#", "#XXX#", "#XXX#", "XXXXX")
                .aisle("XXXXX", "XCCCX", "XCCCX", "XCCCX", "X###X")
                .aisle("XXXXX", "XCCCX", "XCCCX", "XCCCX", "X###X")
                .aisle("XXXXX", "XCCCX", "XCCCX", "XCCCX", "X###X")
                .aisle("#OME#", "#ISX#", "#XXX#", "#XXX#", "XXXXX")
                .where('S', getController(), EnumFacing.WEST)
                .where('X', GCYMultiMetaBlocks.LARGE_MULTIBLOCK_CASING.getState(BlockLargeMultiblockCasing.CasingType.MACERATOR_CASING))
                .where('C', GCYMultiMetaBlocks.UNIQUE_CASING.getState(BlockUniqueCasing.UniqueCasingType.CRUSHING_WHEELS))
                .where('#', Blocks.AIR.getDefaultState())
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.HV], EnumFacing.WEST)
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[GTValues.HV], EnumFacing.WEST)
                .where('O', MetaTileEntities.ITEM_EXPORT_BUS[GTValues.HV], EnumFacing.WEST)
                .where('M', maintenanceIfEnabled(GCYMultiMetaBlocks.LARGE_MULTIBLOCK_CASING.getState(BlockLargeMultiblockCasing.CasingType.MACERATOR_CASING)), EnumFacing.WEST)
                .build());

        shapeInfos.add(MultiblockShapeInfo.builder()
                .aisle("#XXX#", "#XXX#", "#XXX#", "#XXX#", "#XXX#", "XXXXX")
                .aisle("XXXXX", "XCCCX", "XCCCX", "XCCCX", "XCCCX", "X###X")
                .aisle("XXXXX", "XCCCX", "XCCCX", "XCCCX", "XCCCX", "X###X")
                .aisle("XXXXX", "XCCCX", "XCCCX", "XCCCX", "XCCCX", "X###X")
                .aisle("#OME#", "#ISX#", "#XXX#", "#XXX#", "#XXX#", "XXXXX")
                .where('S', getController(), EnumFacing.WEST)
                .where('X', GCYMultiMetaBlocks.LARGE_MULTIBLOCK_CASING.getState(BlockLargeMultiblockCasing.CasingType.MACERATOR_CASING))
                .where('C', GCYMultiMetaBlocks.UNIQUE_CASING.getState(BlockUniqueCasing.UniqueCasingType.CRUSHING_WHEELS))
                .where('#', Blocks.AIR.getDefaultState())
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.HV], EnumFacing.WEST)
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[GTValues.HV], EnumFacing.WEST)
                .where('O', MetaTileEntities.ITEM_EXPORT_BUS[GTValues.HV], EnumFacing.WEST)
                .where('M', maintenanceIfEnabled(GCYMultiMetaBlocks.LARGE_MULTIBLOCK_CASING.getState(BlockLargeMultiblockCasing.CasingType.MACERATOR_CASING)), EnumFacing.WEST)
                .build());

        return shapeInfos;
    }

    @Override
    public String[] getDescription() {
        return new String[]{I18n.format(String.format("%s.multiblock.large_macerator.description", GregicalityMultiblocks.MODID))};
    }

    @Override
    public float getDefaultZoom() {
        return 0.8f;
    }

    @Override
    protected void generateBlockTooltips() {
        super.generateBlockTooltips();

        ITextComponent tooltip = new TextComponentTranslation("gregtech.multiblock.preview.limit", 30).setStyle(new Style().setColor(TextFormatting.AQUA));
        addBlockTooltip(GCYMultiMetaBlocks.LARGE_MULTIBLOCK_CASING.getItemVariant(BlockLargeMultiblockCasing.CasingType.MACERATOR_CASING), tooltip);
    }
}
