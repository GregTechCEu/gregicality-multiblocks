package gregicality.machines.integration.jei.multiblock.info.standard;

import gregicality.machines.GregicalityMultiblocks;
import gregicality.machines.common.block.GCYMultiMetaBlocks;
import gregicality.machines.common.block.blocks.BlockLargeMultiblockCasing;
import gregicality.machines.common.block.blocks.BlockUniqueCasing;
import gregicality.machines.common.metatileentities.GCYMultiMetaTileEntities;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.BlockTransparentCasing;
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

public class LargeCutterInfo extends MultiblockInfoPage {

    @Override
    public MultiblockControllerBase getController() {
        return GCYMultiMetaTileEntities.LARGE_CUTTER;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        List<MultiblockShapeInfo> shapeInfos = new ArrayList<>();

        shapeInfos.add(MultiblockShapeInfo.builder()
                .aisle("XXXXXXX", "XXXXXXX", "XXXXXXX", "##XXXXX")
                .aisle("XXXXXXX", "X#XCCCX", "XXX###X", "##XXXXX")
                .aisle("XXXXXXX", "X#XCCCX", "XXX###X", "##XXXXX")
                .aisle("XMFEIOX", "XSXGGGX", "XXXGGGX", "##XXXXX")
                .where('S', getController(), EnumFacing.WEST)
                .where('X', GCYMultiMetaBlocks.LARGE_MULTIBLOCK_CASING.getState(BlockLargeMultiblockCasing.CasingType.CUTTER_CASING))
                .where('G', MetaBlocks.TRANSPARENT_CASING.getState(BlockTransparentCasing.CasingType.TEMPERED_GLASS))
                .where('C', GCYMultiMetaBlocks.UNIQUE_CASING.getState(BlockUniqueCasing.UniqueCasingType.SLICING_BLADES))
                .where('#', Blocks.AIR.getDefaultState())
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.HV], EnumFacing.WEST)
                .where('F', MetaTileEntities.FLUID_IMPORT_HATCH[GTValues.HV], EnumFacing.WEST)
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[GTValues.HV], EnumFacing.WEST)
                .where('O', MetaTileEntities.ITEM_EXPORT_BUS[GTValues.HV], EnumFacing.WEST)
                .where('M', maintenanceIfEnabled(GCYMultiMetaBlocks.LARGE_MULTIBLOCK_CASING.getState(BlockLargeMultiblockCasing.CasingType.CUTTER_CASING)), EnumFacing.WEST)
                .build());

        shapeInfos.add(MultiblockShapeInfo.builder()
                .aisle("XXXXXXX", "XXXXXXXX", "XXXXXXXX", "##XXXXX")
                .aisle("XXXXXXX", "XXXCCCCX", "X#X####X", "##XXXXX")
                .aisle("XXXXXXX", "XXXCCCCX", "X#X####X", "##XXXXX")
                .aisle("XMFEIOX", "XSXGGGGX", "XXXGGGGX", "##XXXXX")
                .where('S', getController(), EnumFacing.WEST)
                .where('X', GCYMultiMetaBlocks.LARGE_MULTIBLOCK_CASING.getState(BlockLargeMultiblockCasing.CasingType.CUTTER_CASING))
                .where('G', MetaBlocks.TRANSPARENT_CASING.getState(BlockTransparentCasing.CasingType.TEMPERED_GLASS))
                .where('C', GCYMultiMetaBlocks.UNIQUE_CASING.getState(BlockUniqueCasing.UniqueCasingType.SLICING_BLADES))
                .where('#', Blocks.AIR.getDefaultState())
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.HV], EnumFacing.WEST)
                .where('F', MetaTileEntities.FLUID_IMPORT_HATCH[GTValues.HV], EnumFacing.WEST)
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[GTValues.HV], EnumFacing.WEST)
                .where('O', MetaTileEntities.ITEM_EXPORT_BUS[GTValues.HV], EnumFacing.WEST)
                .where('M', maintenanceIfEnabled(GCYMultiMetaBlocks.LARGE_MULTIBLOCK_CASING.getState(BlockLargeMultiblockCasing.CasingType.CUTTER_CASING)), EnumFacing.WEST)
                .build());

        shapeInfos.add(MultiblockShapeInfo.builder()
                .aisle("XXXXXXX", "XXXXXXXXX", "XXXXXXXXX", "##XXXXX")
                .aisle("XXXXXXX", "XXXCCCCCX", "X#X#####X", "##XXXXX")
                .aisle("XXXXXXX", "XXXCCCCCX", "X#X#####X", "##XXXXX")
                .aisle("XMFEIOX", "XSXGGGGGX", "XXXGGGGGX", "##XXXXX")
                .where('S', getController(), EnumFacing.WEST)
                .where('X', GCYMultiMetaBlocks.LARGE_MULTIBLOCK_CASING.getState(BlockLargeMultiblockCasing.CasingType.CUTTER_CASING))
                .where('G', MetaBlocks.TRANSPARENT_CASING.getState(BlockTransparentCasing.CasingType.TEMPERED_GLASS))
                .where('C', GCYMultiMetaBlocks.UNIQUE_CASING.getState(BlockUniqueCasing.UniqueCasingType.SLICING_BLADES))
                .where('#', Blocks.AIR.getDefaultState())
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.HV], EnumFacing.WEST)
                .where('F', MetaTileEntities.FLUID_IMPORT_HATCH[GTValues.HV], EnumFacing.WEST)
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[GTValues.HV], EnumFacing.WEST)
                .where('O', MetaTileEntities.ITEM_EXPORT_BUS[GTValues.HV], EnumFacing.WEST)
                .where('M', maintenanceIfEnabled(GCYMultiMetaBlocks.LARGE_MULTIBLOCK_CASING.getState(BlockLargeMultiblockCasing.CasingType.CUTTER_CASING)), EnumFacing.WEST)
                .build());

        return shapeInfos;
    }


    @Override
    public String[] getDescription() {
        return new String[]{I18n.format(String.format("%s.multiblock.large_cutter.description", GregicalityMultiblocks.MODID))};
    }

    @Override
    public float getDefaultZoom() {
        return 0.8f;
    }

    @Override
    protected void generateBlockTooltips() {
        super.generateBlockTooltips();

        ITextComponent tooltip = new TextComponentTranslation("gregtech.multiblock.preview.limit", 30).setStyle(new Style().setColor(TextFormatting.AQUA));
        addBlockTooltip(GCYMultiMetaBlocks.LARGE_MULTIBLOCK_CASING.getItemVariant(BlockLargeMultiblockCasing.CasingType.CUTTER_CASING), tooltip);
    }
}
