package gregicality.machines.common.metatileentities.multiblock.standard;

import gregicality.machines.api.render.GCYMultiTextures;
import gregicality.machines.common.block.GCYMultiMetaBlocks;
import gregicality.machines.common.block.blocks.BlockLargeMultiblockCasing2;
import gregicality.machines.common.block.blocks.BlockUniqueCasing;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.render.OrientedOverlayRenderer;
import gregtech.common.blocks.BlockFusionCasing;
import gregtech.common.blocks.BlockTransparentCasing;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class MetaTileEntityLargeMassFabricator extends RecipeMapMultiblockController {

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = new MultiblockAbility[]{
            MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.IMPORT_FLUIDS,
            MultiblockAbility.EXPORT_FLUIDS, MultiblockAbility.INPUT_ENERGY
    };

    public MetaTileEntityLargeMassFabricator(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, RecipeMaps.MASS_FABRICATOR_RECIPES);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder metaTileEntityHolder) {
        return new MetaTileEntityLargeMassFabricator(this.metaTileEntityId);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("XXCXX", "XGCGX", "CCVCC", "XGCGX", "XXCXX")
                .aisle("XXCXX", "GAAAG", "CAKAC", "GAAAG", "XXCXX")
                .aisle("CCVCC", "CAKAC", "VKKKV", "CAKAC", "CCVCC")
                .aisle("XXCXX", "GAAAG", "CAKAC", "GAAAG", "XXCXX")
                .aisle("XXCXX", "XGCGX", "CCSCC", "XGCGX", "XXCXX")
                .setAmountAtLeast('L', 30)
                .where('S', selfPredicate())
                .where('X', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES))
                        .or(maintenancePredicate(getCasingState())))
                .where('C', statePredicate(getCasingState2()))
                .where('G', statePredicate(getCasingState3()))
                .where('V', statePredicate(getCasingState4()))
                .where('K', statePredicate(getCasingState5()))
                .where('A', isAirPredicate())
                .where('#', (tile) -> true)
                .where('L', statePredicate(getCasingState()))
                .build();
    }

    private IBlockState getCasingState() {
        return GCYMultiMetaBlocks.LARGE_MULTIBLOCK_CASING_2.getState(BlockLargeMultiblockCasing2.CasingType.ATOMIC_CASING);
    }

    private IBlockState getCasingState2() {
        return MetaBlocks.FUSION_CASING.getState(BlockFusionCasing.CasingType.SUPERCONDUCTOR_COIL);
    }

    private IBlockState getCasingState3() {
        return MetaBlocks.TRANSPARENT_CASING.getState(BlockTransparentCasing.CasingType.FUSION_GLASS);
    }

    private IBlockState getCasingState4() {
        return GCYMultiMetaBlocks.UNIQUE_CASING.getState(BlockUniqueCasing.UniqueCasingType.HEAT_VENT);
    }

    private IBlockState getCasingState5() {
        return MetaBlocks.FUSION_CASING.getState(BlockFusionCasing.CasingType.FUSION_COIL);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return GCYMultiTextures.ATOMIC_CASING;
    }

    @Nonnull
    @Override
    protected OrientedOverlayRenderer getFrontOverlay() {
        return GCYMultiTextures.LARGE_MASS_FABRICATOR_OVERLAY;
    }
}
