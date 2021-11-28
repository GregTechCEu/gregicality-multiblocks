package gregicality.machines.integration.jei.multiblock.info.standard;

import gregicality.machines.GregicalityMultiblocks;
import gregicality.machines.common.block.GCYMultiMetaBlocks;
import gregicality.machines.common.block.blocks.BlockLargeMultiblockCasing2;
import gregicality.machines.common.block.blocks.BlockUniqueCasing;
import gregicality.machines.common.metatileentities.GCYMultiMetaTileEntities;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.BlockFusionCasing;
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

import java.util.Collections;
import java.util.List;

public class LargeMassFabricatorInfo extends MultiblockInfoPage {

    @Override
    public MultiblockControllerBase getController() {
        return GCYMultiMetaTileEntities.LARGE_MASS_FABRICATOR;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        return Collections.singletonList(MultiblockShapeInfo.builder()
                .aisle("XXCXX", "XGCGX", "CCVCC", "XGCGX", "XXCXX")
                .aisle("XXCXX", "GAAAG", "CAKAC", "GAAAG", "XXCXX")
                .aisle("CCVCC", "CAKAC", "VKKKV", "CAKAC", "CCVCC")
                .aisle("XXCXX", "GAAAG", "CAKAC", "GAAAG", "XXCXX")
                .aisle("FMCEO", "IGCGO", "CCSCC", "XGCGX", "XXCXX")
                .where('S', getController(), EnumFacing.WEST)
                .where('X', GCYMultiMetaBlocks.LARGE_MULTIBLOCK_CASING_2.getState(BlockLargeMultiblockCasing2.CasingType.ATOMIC_CASING))
                .where('C', MetaBlocks.FUSION_CASING.getState(BlockFusionCasing.CasingType.SUPERCONDUCTOR_COIL))
                .where('G', MetaBlocks.TRANSPARENT_CASING.getState(BlockTransparentCasing.CasingType.FUSION_GLASS))
                .where('V', GCYMultiMetaBlocks.UNIQUE_CASING.getState(BlockUniqueCasing.UniqueCasingType.HEAT_VENT))
                .where('K', MetaBlocks.FUSION_CASING.getState(BlockFusionCasing.CasingType.FUSION_COIL))
                .where('#', Blocks.AIR.getDefaultState())
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.HV], EnumFacing.WEST)
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[GTValues.HV], EnumFacing.WEST)
                .where('F', MetaTileEntities.FLUID_IMPORT_HATCH[GTValues.HV], EnumFacing.WEST)
                .where('O', MetaTileEntities.FLUID_EXPORT_HATCH[GTValues.HV], EnumFacing.WEST)
                .where('M', maintenanceIfEnabled(GCYMultiMetaBlocks.LARGE_MULTIBLOCK_CASING_2.getState(BlockLargeMultiblockCasing2.CasingType.ATOMIC_CASING)), EnumFacing.WEST)
                .build());
    }


    @Override
    public String[] getDescription() {
        return new String[]{I18n.format(String.format("%s.multiblock.large_mass_fabricator.description", GregicalityMultiblocks.MODID))};
    }

    @Override
    public float getDefaultZoom() {
        return 0.8f;
    }

    @Override
    protected void generateBlockTooltips() {
        super.generateBlockTooltips();

        ITextComponent tooltip = new TextComponentTranslation("gregtech.multiblock.preview.limit", 30).setStyle(new Style().setColor(TextFormatting.AQUA));
        addBlockTooltip(GCYMultiMetaBlocks.LARGE_MULTIBLOCK_CASING_2.getItemVariant(BlockLargeMultiblockCasing2.CasingType.ATOMIC_CASING), tooltip);
    }
}
