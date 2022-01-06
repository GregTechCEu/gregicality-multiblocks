package gregicality.machines.common.metatileentities.multiblock.standard;

import gregicality.machines.api.metatileentity.GCYMRecipeMapMultiblockController;
import gregicality.machines.api.render.GCYMTextures;
import gregicality.machines.common.block.GCYMMetaBlocks;
import gregicality.machines.common.block.blocks.BlockLargeMultiblockCasing;
import gregicality.machines.common.block.blocks.BlockUniqueCasing;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.recipes.RecipeMaps;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class MetaTileEntityLargeMacerator extends GCYMRecipeMapMultiblockController {

    public MetaTileEntityLargeMacerator(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, RecipeMaps.MACERATOR_RECIPES);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder metaTileEntityHolder) {
        return new MetaTileEntityLargeMacerator(this.metaTileEntityId);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("XXXXX", "XXXXX", "XXXXX", "XXXXX")
                .aisle("XXXXX", "XCCCX", "XCCCX", "X###X")
                .aisle("XXXXX", "XCTCX", "XCCCX", "X###X")
                .aisle("XXXXX", "XCCCX", "XCCCX", "X###X")
                .aisle("XXXXX", "XXSXX", "XXXXX", "XXXXX")
                .where('S', selfPredicate())
                .where('X', states(getCasingState()).setMinGlobalLimited(55).or(autoAbilities()))
                .where('C', states(getCasingState2()))
                .where('T', tieredCasing().or(states(getCasingState2())))
                .where('#', air())
                .build();
    }

    private IBlockState getCasingState() {
        return GCYMMetaBlocks.LARGE_MULTIBLOCK_CASING.getState(BlockLargeMultiblockCasing.CasingType.MACERATOR_CASING);
    }

    private IBlockState getCasingState2() {
        return GCYMMetaBlocks.UNIQUE_CASING.getState(BlockUniqueCasing.UniqueCasingType.CRUSHING_WHEELS);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return GCYMTextures.MACERATOR_CASING;
    }

    @Nonnull
    @Override
    protected OrientedOverlayRenderer getFrontOverlay() {
        return GCYMTextures.LARGE_MACERATOR_OVERLAY;
    }
}
