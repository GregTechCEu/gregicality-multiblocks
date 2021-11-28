package gregicality.machines.integration.jei.multiblock.info.standard;

import gregicality.machines.GregicalityMultiblocks;
import gregicality.machines.common.block.GCYMultiMetaBlocks;
import gregicality.machines.common.block.blocks.BlockLargeMultiblockCasing2;
import gregicality.machines.common.metatileentities.GCYMultiMetaTileEntities;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.BlockMultiblockCasing;
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

public class LargeSifterInfo extends MultiblockInfoPage {

    @Override
    public MultiblockControllerBase getController() {
        return GCYMultiMetaTileEntities.LARGE_SIFTER;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        List<MultiblockShapeInfo> shapeInfos = new ArrayList<>();

        shapeInfos.add(MultiblockShapeInfo.builder()
                .aisle("#X#X#", "#X#X#", "#XXX#", "#XXX#", "#XXX#")
                .aisle("XXXXX", "X#X#X", "XCCCX", "XCCCX", "X###X")
                .aisle("#XXX#", "#X#X#", "XCCCX", "XCCCX", "X###X")
                .aisle("XXXXX", "X#X#X", "XCCCX", "XCCCX", "X###X")
                .aisle("#X#X#", "#X#X#", "#OME#", "#ISX#", "#XXX#")
                .where('S', getController(), EnumFacing.WEST)
                .where('X', GCYMultiMetaBlocks.LARGE_MULTIBLOCK_CASING_2.getState(BlockLargeMultiblockCasing2.CasingType.SIFTER_CASING))
                .where('C', MetaBlocks.MULTIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.GRATE_CASING))
                .where('#', Blocks.AIR.getDefaultState())
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.HV], EnumFacing.WEST)
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[GTValues.HV], EnumFacing.WEST)
                .where('O', MetaTileEntities.ITEM_EXPORT_BUS[GTValues.HV], EnumFacing.WEST)
                .where('M', maintenanceIfEnabled(GCYMultiMetaBlocks.LARGE_MULTIBLOCK_CASING_2.getState(BlockLargeMultiblockCasing2.CasingType.SIFTER_CASING)), EnumFacing.WEST)
                .build());

        shapeInfos.add(MultiblockShapeInfo.builder()
                .aisle("#X#X#", "#X#X#", "#XXX#", "#XXX#", "#XXX#", "#XXX#")
                .aisle("XXXXX", "X#X#X", "XCCCX", "XCCCX", "XCCCX", "X###X")
                .aisle("#XXX#", "#X#X#", "XCCCX", "XCCCX", "XCCCX", "X###X")
                .aisle("XXXXX", "X#X#X", "XCCCX", "XCCCX", "XCCCX", "X###X")
                .aisle("#X#X#", "#X#X#", "#OME#", "#XXX#", "#ISX#", "#XXX#")
                .where('S', getController(), EnumFacing.WEST)
                .where('X', GCYMultiMetaBlocks.LARGE_MULTIBLOCK_CASING_2.getState(BlockLargeMultiblockCasing2.CasingType.SIFTER_CASING))
                .where('C', MetaBlocks.MULTIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.GRATE_CASING))
                .where('#', Blocks.AIR.getDefaultState())
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.HV], EnumFacing.WEST)
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[GTValues.HV], EnumFacing.WEST)
                .where('O', MetaTileEntities.ITEM_EXPORT_BUS[GTValues.HV], EnumFacing.WEST)
                .where('M', maintenanceIfEnabled(GCYMultiMetaBlocks.LARGE_MULTIBLOCK_CASING_2.getState(BlockLargeMultiblockCasing2.CasingType.SIFTER_CASING)), EnumFacing.WEST)
                .build());

        shapeInfos.add(MultiblockShapeInfo.builder()
                .aisle("#X#X#", "#X#X#", "#XXX#", "#XXX#", "#XXX#", "#XXX#", "#XXX#")
                .aisle("XXXXX", "X#X#X", "XCCCX", "XCCCX", "XCCCX", "XCCCX", "X###X")
                .aisle("#XXX#", "#X#X#", "XCCCX", "XCCCX", "XCCCX", "XCCCX", "X###X")
                .aisle("XXXXX", "X#X#X", "XCCCX", "XCCCX", "XCCCX", "XCCCX", "X###X")
                .aisle("#X#X#", "#X#X#", "#OME#", "#XXX#", "#XXX#", "#ISX#", "#XXX#")
                .where('S', getController(), EnumFacing.WEST)
                .where('X', GCYMultiMetaBlocks.LARGE_MULTIBLOCK_CASING_2.getState(BlockLargeMultiblockCasing2.CasingType.SIFTER_CASING))
                .where('C', MetaBlocks.MULTIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.GRATE_CASING))
                .where('#', Blocks.AIR.getDefaultState())
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.HV], EnumFacing.WEST)
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[GTValues.HV], EnumFacing.WEST)
                .where('O', MetaTileEntities.ITEM_EXPORT_BUS[GTValues.HV], EnumFacing.WEST)
                .where('M', maintenanceIfEnabled(GCYMultiMetaBlocks.LARGE_MULTIBLOCK_CASING_2.getState(BlockLargeMultiblockCasing2.CasingType.SIFTER_CASING)), EnumFacing.WEST)
                .build());

        return shapeInfos;
    }

    @Override
    public String[] getDescription() {
        return new String[]{I18n.format(String.format("%s.multiblock.large_sifter.description", GregicalityMultiblocks.MODID))};
    }

    @Override
    public float getDefaultZoom() {
        return 0.8f;
    }

    @Override
    protected void generateBlockTooltips() {
        super.generateBlockTooltips();

        ITextComponent tooltip = new TextComponentTranslation("gregtech.multiblock.preview.limit", 55).setStyle(new Style().setColor(TextFormatting.AQUA));
        addBlockTooltip(GCYMultiMetaBlocks.LARGE_MULTIBLOCK_CASING_2.getItemVariant(BlockLargeMultiblockCasing2.CasingType.SIFTER_CASING), tooltip);
    }
}
