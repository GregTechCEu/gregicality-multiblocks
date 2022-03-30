package gregicality.multiblocks.common.metatileentities.multiblock.standard;

import gregicality.multiblocks.api.render.GCYMTextures;
import gregicality.multiblocks.common.block.GCYMMetaBlocks;
import gregicality.multiblocks.common.block.blocks.BlockUniqueCasing;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.recipes.RecipeMaps;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.BlockGlassCasing;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class MetaTileEntityMegaVacuumFreezer extends RecipeMapMultiblockController {

    private static final int MAX_PARALLEL = 2048;

    public MetaTileEntityMegaVacuumFreezer(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, RecipeMaps.VACUUM_RECIPES);
        this.recipeMapWorkable = new MegaVacuumFreezerRecipeLogic(this);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity metaTileEntityHolder) {
        return new MetaTileEntityMegaVacuumFreezer(this.metaTileEntityId);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("XXXXXXX#KKK", "XXXXXXX#KVK", "XXXXXXX#KVK", "XXXXXXX#KVK", "XXXXXXX#KKK", "XXXXXXX####", "XXXXXXX####")
                .aisle("XXXXXXX#KVK", "XPPPPPPPPPV", "XPAPAPX#VPV", "XPPPPPPPPPV", "XPAPAPX#KVK", "XPPPPPX####", "XXXXXXX####")
                .aisle("XXXXXXX#KVK", "XPAPAPX#VPV", "XAAAAAX#VPV", "XPAAAPX#VPV", "XAAAAAX#KVK", "XPAPAPX####", "XXXXXXX####")
                .aisle("XXXXXXX#KVK", "XPAPAPPPPPV", "XAAAAAX#VPV", "XPAAAPPPPPV", "XAAAAAX#KVK", "XPAPAPX####", "XXXXXXX####")
                .aisle("XXXXXXX#KKK", "XPPPPPX#KVK", "XPAAAPX#KVK", "XPAAAPX#KVK", "XPAAAPX#KKK", "XPPPPPX####", "XXXXXXX####")
                .aisle("#XXXXX#####", "#XXSXX#####", "#XGGGX#####", "#XGGGX#####", "#XGGGX#####", "#XXXXX#####", "###########")
                .where('S', selfPredicate())
                .where('X', states(getCasingState()).setMinGlobalLimited(140).or(autoAbilities()))
                .where('G', states(getCasingState2()))
                .where('K', states(getCasingState3()))
                .where('V', states(getCasingState4()))
                .where('P', states(getCasingState5()))
                .where('A', air())
                .where('#', any())
                .build();
    }

    private IBlockState getCasingState() {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.ALUMINIUM_FROSTPROOF);
    }

    private IBlockState getCasingState2() {
        return MetaBlocks.TRANSPARENT_CASING.getState(BlockGlassCasing.CasingType.TEMPERED_GLASS);
    }

    private IBlockState getCasingState3() {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STAINLESS_CLEAN);
    }

    private IBlockState getCasingState4() {
        return GCYMMetaBlocks.UNIQUE_CASING.getState(BlockUniqueCasing.UniqueCasingType.HEAT_VENT);
    }

    private IBlockState getCasingState5() {
        return MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TUNGSTENSTEEL_PIPE);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return Textures.FROST_PROOF_CASING;
    }

    @Nonnull
    @Override
    protected OrientedOverlayRenderer getFrontOverlay() {
        return GCYMTextures.MEGA_VACUUM_FREEZER_OVERLAY;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gcym.machine.mega_vacuum_freezer.tooltip.1", MAX_PARALLEL));
    }

    @SuppressWarnings("InnerClassMayBeStatic")
    private class MegaVacuumFreezerRecipeLogic extends MultiblockRecipeLogic {

        public MegaVacuumFreezerRecipeLogic(RecipeMapMultiblockController tileEntity) {
            super(tileEntity);
        }

        @Override
        public int getParallelLimit() {
            return MAX_PARALLEL;
        }
    }
}
