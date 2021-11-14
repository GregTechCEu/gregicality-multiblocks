package gregicality.machines.jei.multiblock.info.standard;

import gregicality.machines.GregicalityMultiblocks;
import gregicality.machines.common.block.GCYMultiMetaBlocks;
import gregicality.machines.common.block.blocks.BlockUniqueCasing;
import gregicality.machines.common.metatileentities.GCYMultiMetaTileEntities;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.api.unification.material.Materials;
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
                .where('X', MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.TUNGSTENSTEEL_ROBUST))
                .where('C', MetaBlocks.FUSION_COIL.getState(BlockFusionCoil.CoilType.SUPERCONDUCTOR))
                .where('G', MetaBlocks.TRANSPARENT_CASING.getState(BlockTransparentCasing.CasingType.REINFORCED_GLASS))
                .where('V', GCYMultiMetaBlocks.UNIQUE_CASING.getState(BlockUniqueCasing.UniqueCasingType.HEAT_VENT))
                .where('K', MetaBlocks.FUSION_COIL.getState(BlockFusionCoil.CoilType.FUSION_COIL))
                .where('#', Blocks.AIR.getDefaultState())
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.HV], EnumFacing.WEST)
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[GTValues.HV], EnumFacing.WEST)
                .where('F', MetaTileEntities.FLUID_IMPORT_HATCH[GTValues.HV], EnumFacing.WEST)
                .where('O', MetaTileEntities.FLUID_EXPORT_HATCH[GTValues.HV], EnumFacing.WEST)
                .where('M', maintenanceIfEnabled(MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.TUNGSTENSTEEL_ROBUST)), EnumFacing.WEST)
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
        addBlockTooltip(MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.TUNGSTENSTEEL_ROBUST), tooltip);
    }
}
