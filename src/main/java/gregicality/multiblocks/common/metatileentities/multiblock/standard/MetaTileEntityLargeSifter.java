package gregicality.multiblocks.common.metatileentities.multiblock.standard;

import gregicality.multiblocks.api.GCYMValues;
import gregicality.multiblocks.api.metatileentity.GCYMRecipeMapMultiblockController;
import gregicality.multiblocks.api.render.GCYMTextures;
import gregicality.multiblocks.common.block.GCYMMetaBlocks;
import gregicality.multiblocks.common.block.blocks.BlockLargeMultiblockCasing;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMaps;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer;
import gregtech.common.blocks.BlockMultiblockCasing;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;

import javax.annotation.Nonnull;

import static gregtech.api.util.RelativeDirection.*;

public class MetaTileEntityLargeSifter extends GCYMRecipeMapMultiblockController {

    public MetaTileEntityLargeSifter(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, determineRecipeMaps());
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity metaTileEntityHolder) {
        return new MetaTileEntityLargeSifter(this.metaTileEntityId);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start(RIGHT, FRONT, UP)
                .aisle("#X#X#", "XXXXX", "#XXX#", "XXXXX", "#X#X#")
                .aisle("#X#X#", "XAXAX", "#XTX#", "XAXAX", "#X#X#")
                .aisle("#XXX#", "XCCCX", "XCCCX", "XCCCX", "#XXX#")
                .aisle("#XSX#", "XCCCX", "XCCCX", "XCCCX", "#XXX#")
                .aisle("#XXX#", "X###X", "X###X", "X###X", "#XXX#")
                .where('S', selfPredicate())
                .where('X', states(getCasingState()).setMinGlobalLimited(50).or(autoAbilities()))
                .where('C', states(getCasingState2()))
                .where('T', tieredCasing().or(air()))
                .where('A', air())
                .where('#', any())
                .build();
    }

    private IBlockState getCasingState() {
        return GCYMMetaBlocks.LARGE_MULTIBLOCK_CASING.getState(BlockLargeMultiblockCasing.CasingType.VIBRATION_SAFE_CASING);
    }

    private IBlockState getCasingState2() {
        return MetaBlocks.MULTIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.GRATE_CASING);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return GCYMTextures.VIBRATION_SAFE_CASING;
    }

    @Nonnull
    @Override
    protected OrientedOverlayRenderer getFrontOverlay() {
        return GCYMTextures.LARGE_SIFTER_OVERLAY;
    }

    @Nonnull
    private static RecipeMap<?>[] determineRecipeMaps() {
        RecipeMap<?> sieveMap = RecipeMap.getByName("electric_sieve");
        if (Loader.isModLoaded(GCYMValues.GREGIFICATION_MODID) && sieveMap != null) {
            return new RecipeMap<?>[]{RecipeMaps.SIFTER_RECIPES, sieveMap};
        }
        return new RecipeMap<?>[]{RecipeMaps.SIFTER_RECIPES};
    }
}
