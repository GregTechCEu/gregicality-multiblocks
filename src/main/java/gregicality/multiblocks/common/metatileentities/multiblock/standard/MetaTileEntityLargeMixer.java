package gregicality.multiblocks.common.metatileentities.multiblock.standard;

import gregicality.multiblocks.api.metatileentity.GCYMRecipeMapMultiblockController;
import gregicality.multiblocks.api.render.GCYMTextures;
import gregicality.multiblocks.api.unification.GCYMMaterials;
import gregicality.multiblocks.common.block.GCYMMetaBlocks;
import gregicality.multiblocks.common.block.blocks.BlockLargeMultiblockCasing;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.recipes.RecipeMaps;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.BlockTurbineCasing;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class MetaTileEntityLargeMixer extends GCYMRecipeMapMultiblockController {

    public MetaTileEntityLargeMixer(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, RecipeMaps.MIXER_RECIPES);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity metaTileEntityHolder) {
        return new MetaTileEntityLargeMixer(this.metaTileEntityId);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("#XXX#", "#XXX#", "#XXX#", "#XXX#", "#XXX#", "##F##")
                .aisle("XXXXX", "XACAX", "XAAAX", "XACAX", "XAAAX", "##F##")
                .aisle("XXXXX", "XCPCX", "XAPAX", "XCPCX", "XAPAX", "FFTFF")
                .aisle("XXXXX", "XACAX", "XAAAX", "XACAX", "XAAAX", "##F##")
                .aisle("#XXX#", "#XSX#", "#XXX#", "#XXX#", "#XXX#", "##F##")
                .where('S', selfPredicate())
                .where('X', states(getCasingState()).setMinGlobalLimited(50).or(autoAbilities()))
                .where('P', states(getCasingState2()))
                .where('C', states(getCasingState3()))
                .where('T', tieredCasing().or(states(getCasingState4())))
                .where('F', states(getFrameState()))
                .where('A', air())
                .where('#', any())
                .build();
    }

    private IBlockState getCasingState() {
        return GCYMMetaBlocks.LARGE_MULTIBLOCK_CASING.getState(BlockLargeMultiblockCasing.CasingType.MIXER_CASING);
    }

    private IBlockState getCasingState2() {
        return MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.POLYTETRAFLUOROETHYLENE_PIPE);
    }

    private IBlockState getCasingState3() {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.PTFE_INERT_CASING);
    }

    private IBlockState getCasingState4() {
        return MetaBlocks.TURBINE_CASING.getState(BlockTurbineCasing.TurbineCasingType.STAINLESS_STEEL_GEARBOX);
    }

    private IBlockState getFrameState() {
        return MetaBlocks.FRAMES.get(GCYMMaterials.HastelloyX).getBlock(GCYMMaterials.HastelloyX);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return GCYMTextures.MIXER_CASING;
    }

    @Nonnull
    @Override
    protected OrientedOverlayRenderer getFrontOverlay() {
        return GCYMTextures.LARGE_MIXER_OVERLAY;
    }

    @Override
    public boolean canBeDistinct() {
        return true;
    }
}
