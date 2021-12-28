package gregicality.machines.common.metatileentities.multiblock.unique;

import gregicality.machines.api.recipes.GCYMRecipeMaps;
import gregicality.machines.api.render.GCYMTextures;
import gregicality.machines.common.block.GCYMMetaBlocks;
import gregicality.machines.common.block.blocks.BlockUniqueCasing;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMaps;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer;
import gregtech.common.blocks.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class MetaTileEntityChemicalPlant extends RecipeMapMultiblockController {

    public MetaTileEntityChemicalPlant(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GCYMRecipeMaps.CHEMICAL_PLANT_RECIPES);
        this.recipeMaps = new RecipeMap[]{GCYMRecipeMaps.CHEMICAL_PLANT_RECIPES, RecipeMaps.LARGE_CHEMICAL_RECIPES};
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder metaTileEntityHolder) {
        return new MetaTileEntityChemicalPlant(this.metaTileEntityId);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("X###X", "XXXXX", "XGGGX", "XXXXX", "X###X")
                .aisle("X###X", "XXXXX", "XAAAX", "XXXXX", "X###X")
                .aisle("XPPPX", "XAAAX", "XCCCX", "XAAAX", "XPPPX")
                .aisle("X###X", "XXXXX", "XAAAX", "XXXXX", "X###X")
                .aisle("X###X", "XXSXX", "XGGGX", "XXXXX", "X###X")
                .where('S', selfPredicate())
                .where('X', states(getCasingState()).setMinGlobalLimited(50).or(autoAbilities()))
                .where('G', states(getCasingState2()))
                .where('P', states(getCasingState3()))
                .where('C', states(getCasingState4()))
                .where('A', air())
                .where('#', any())
                .build();
    }

    private IBlockState getCasingState() {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.PTFE_INERT_CASING);
    }

    private IBlockState getCasingState2() {
        return MetaBlocks.TRANSPARENT_CASING.getState(BlockGlassCasing.CasingType.TEMPERED_GLASS);
    }

    private IBlockState getCasingState3() {
        return MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.POLYTETRAFLUOROETHYLENE_PIPE);
    }

    private IBlockState getCasingState4() {
        return GCYMMetaBlocks.UNIQUE_CASING.getState(BlockUniqueCasing.UniqueCasingType.MOLYBDENUM_DISILICIDE_COIL);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return Textures.INERT_PTFE_CASING;
    }

    @Nonnull
    @Override
    protected OrientedOverlayRenderer getFrontOverlay() {
        return GCYMTextures.CHEMICAL_PLANT_OVERLAY;
    }

    @Override
    public boolean canBeDistinct() {
        return true;
    }

    @Override
    public boolean hasMultipleRecipeMaps() {
        return true;
    }
}
