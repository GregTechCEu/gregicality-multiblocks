package gregicality.machines.common.metatileentities.multiblock.standard;

import gregicality.machines.api.metatileentity.GCYMMultiblockAbility;
import gregicality.machines.api.metatileentity.GCYMRecipeMapMultiblockController;
import gregicality.machines.api.render.GCYMultiTextures;
import gregicality.machines.common.block.GCYMultiMetaBlocks;
import gregicality.machines.common.block.blocks.BlockLargeMultiblockCasing;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.render.OrientedOverlayRenderer;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

import static gregtech.api.util.RelativeDirection.*;

public class MetaTileEntityLargeExtractor extends GCYMRecipeMapMultiblockController {

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = new MultiblockAbility[]{
            MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS,
            MultiblockAbility.IMPORT_FLUIDS, MultiblockAbility.EXPORT_FLUIDS,
            MultiblockAbility.INPUT_ENERGY, GCYMMultiblockAbility.PARALLEL_HATCH
    };

    public MetaTileEntityLargeExtractor(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, RecipeMaps.EXTRACTOR_RECIPES);
        this.recipeMaps = new RecipeMap[]{RecipeMaps.EXTRACTOR_RECIPES, RecipeMaps.CANNER_RECIPES};
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder metaTileEntityHolder) {
        return new MetaTileEntityLargeExtractor(this.metaTileEntityId);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start(RIGHT, FRONT, UP)
                .aisle("XXXXX", "XXXXX", "XXXXX")
                .aisle("XXSXX", "XCACX", "XXXXX")
                .aisle("XXXXX", "XCACX", "XXXXX").setRepeatable(0, 2)
                .aisle("XXXXX", "XXXXX", "XXXXX")
                .setAmountAtLeast('L', 30)
                .where('S', selfPredicate())
                .where('X', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES))
                        .or(maintenancePredicate(getCasingState())))
                .where('C', statePredicate(getCasingState2()))
                .where('A', isAirPredicate())
                .where('L', statePredicate(getCasingState()))
                .build();
    }

    private IBlockState getCasingState() {
        return GCYMultiMetaBlocks.LARGE_MULTIBLOCK_CASING.getState(BlockLargeMultiblockCasing.CasingType.FLUID_SAFE_CASING);
    }

    private IBlockState getCasingState2() {
        return MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.STEEL_PIPE);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return GCYMultiTextures.FLUID_SAFE_CASING;
    }

    @Nonnull
    @Override
    protected OrientedOverlayRenderer getFrontOverlay() {
        return GCYMultiTextures.LARGE_EXTRACTOR_OVERLAY;
    }

    @Override
    public boolean hasMultipleRecipeMaps() {
        return true;
    }
}
