package gregicality.machines.jei.multiblock.info.standard;

import gregicality.machines.GregicalityMultiblocks;
import gregicality.machines.common.block.GCYMultiMetaBlocks;
import gregicality.machines.common.block.blocks.BlockLargeMultiblockCasing;
import gregicality.machines.common.block.blocks.BlockUniqueCasing;
import gregicality.machines.common.metatileentities.GCYMultiMetaTileEntities;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.*;
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

public class AlloyBlastSmelterInfo extends MultiblockInfoPage {

    @Override
    public MultiblockControllerBase getController() {
        return GCYMultiMetaTileEntities.ALLOY_BLAST_SMELTER;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        List<MultiblockShapeInfo> shapeInfos = new ArrayList<>();

        for (BlockWireCoil.CoilType coilType : BlockWireCoil.CoilType.values()){
            shapeInfos.add(MultiblockShapeInfo.builder()
                    .aisle("#XEX#", "#CCC#", "#GGG#", "#CCC#", "#XXX#")
                    .aisle("XXXXX", "C###C", "G###G", "C###C", "XXXXX")
                    .aisle("XXXXX", "C###C", "G###G", "C###C", "XXAXX")
                    .aisle("FXXXM", "C###C", "G###G", "C###C", "XXXXX")
                    .aisle("#ISO#", "#CCC#", "#GGG#", "#CCC#", "#XXX#")
                    .where('S', getController(), EnumFacing.WEST)
                    .where('X', GCYMultiMetaBlocks.LARGE_MULTIBLOCK_CASING.getState(BlockLargeMultiblockCasing.CasingType.BLAST_CASING))
                    .where('C', MetaBlocks.WIRE_COIL.getState(coilType))
                    .where('G', GCYMultiMetaBlocks.UNIQUE_CASING.getState(BlockUniqueCasing.UniqueCasingType.HEAT_VENT))
                    .where('#', Blocks.AIR.getDefaultState())
                    .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.HV], EnumFacing.EAST)
                    .where('F', MetaTileEntities.FLUID_IMPORT_HATCH[GTValues.HV], EnumFacing.WEST)
                    .where('I', MetaTileEntities.ITEM_IMPORT_BUS[GTValues.HV], EnumFacing.WEST)
                    .where('O', MetaTileEntities.FLUID_EXPORT_HATCH[GTValues.HV], EnumFacing.WEST)
                    .where('A', MetaTileEntities.MUFFLER_HATCH[GTValues.HV - 1], EnumFacing.UP)
                    .where('M', maintenanceIfEnabled(GCYMultiMetaBlocks.LARGE_MULTIBLOCK_CASING.getState(BlockLargeMultiblockCasing.CasingType.BLAST_CASING)), EnumFacing.WEST)
                    .build());
        }

        for (BlockWireCoil2.CoilType2 coilType : BlockWireCoil2.CoilType2.values()){
            shapeInfos.add(MultiblockShapeInfo.builder()
                    .aisle("#XEX#", "#CCC#", "#GGG#", "#CCC#", "#XXX#")
                    .aisle("XXXXX", "C###C", "G###G", "C###C", "XXXXX")
                    .aisle("XXXXX", "C###C", "G###G", "C###C", "XXAXX")
                    .aisle("FXXXM", "C###C", "G###G", "C###C", "XXXXX")
                    .aisle("#ISO#", "#CCC#", "#GGG#", "#CCC#", "#XXX#")
                    .where('S', getController(), EnumFacing.WEST)
                    .where('X', GCYMultiMetaBlocks.LARGE_MULTIBLOCK_CASING.getState(BlockLargeMultiblockCasing.CasingType.BLAST_CASING))
                    .where('C', MetaBlocks.WIRE_COIL2.getState(coilType))
                    .where('G', GCYMultiMetaBlocks.UNIQUE_CASING.getState(BlockUniqueCasing.UniqueCasingType.HEAT_VENT))
                    .where('#', Blocks.AIR.getDefaultState())
                    .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.HV], EnumFacing.EAST)
                    .where('F', MetaTileEntities.FLUID_IMPORT_HATCH[GTValues.HV], EnumFacing.WEST)
                    .where('I', MetaTileEntities.ITEM_IMPORT_BUS[GTValues.HV], EnumFacing.WEST)
                    .where('O', MetaTileEntities.FLUID_EXPORT_HATCH[GTValues.HV], EnumFacing.WEST)
                    .where('A', MetaTileEntities.MUFFLER_HATCH[GTValues.HV - 1], EnumFacing.UP)
                    .where('M', maintenanceIfEnabled(GCYMultiMetaBlocks.LARGE_MULTIBLOCK_CASING.getState(BlockLargeMultiblockCasing.CasingType.BLAST_CASING)), EnumFacing.WEST)
                    .build());
        }
        return shapeInfos;
    }


    @Override
    public String[] getDescription() {
        return new String[]{I18n.format(String.format("%s.multiblock.alloy_blast_smelter.description", GregicalityMultiblocks.MODID))};
    }

    @Override
    public float getDefaultZoom() {
        return 0.8f;
    }

    @Override
    protected void generateBlockTooltips() {
        super.generateBlockTooltips();

        ITextComponent tooltip = new TextComponentTranslation("gregtech.multiblock.preview.limit", 30).setStyle(new Style().setColor(TextFormatting.AQUA));
        addBlockTooltip(GCYMultiMetaBlocks.LARGE_MULTIBLOCK_CASING.getItemVariant(BlockLargeMultiblockCasing.CasingType.BLAST_CASING), tooltip);

        ITextComponent mufflerTooltip = new TextComponentTranslation("gregtech.multiblock.preview.only_location", I18n.format("gregtech.multiblock.preview.location.t_c")).setStyle(new Style().setColor(TextFormatting.DARK_RED));
        addBlockTooltip(MetaTileEntities.MUFFLER_HATCH[GTValues.HV - 1].getStackForm(), mufflerTooltip);
    }
}
