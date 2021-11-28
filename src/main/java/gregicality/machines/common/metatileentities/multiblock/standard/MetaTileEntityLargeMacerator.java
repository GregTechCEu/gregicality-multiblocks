package gregicality.machines.common.metatileentities.multiblock.standard;

import gregicality.machines.api.render.GCYMultiTextures;
import gregicality.machines.common.block.GCYMultiMetaBlocks;
import gregicality.machines.common.block.blocks.BlockLargeMultiblockCasing;
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
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

import static gregtech.api.util.RelativeDirection.*;

public class MetaTileEntityLargeMacerator extends RecipeMapMultiblockController {

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = new MultiblockAbility[]{
            MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS,
            MultiblockAbility.INPUT_ENERGY
    };

    public MetaTileEntityLargeMacerator(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, RecipeMaps.MACERATOR_RECIPES);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder metaTileEntityHolder) {
        return new MetaTileEntityLargeMacerator(this.metaTileEntityId);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start(RIGHT, FRONT, UP)
                .aisle("#XXX#", "XXXXX", "XXXXX", "XXXXX", "#XXX#")
                .aisle("#XSX#", "XCCCX", "XCCCX", "XCCCX", "#XXX#")
                .aisle("#XXX#", "XCCCX", "XCCCX", "XCCCX", "#XXX#").setRepeatable(1, 3)
                .aisle("XXXXX", "X###X", "X###X", "X###X", "XXXXX")
                .setAmountAtLeast('L', 30)
                .where('S', selfPredicate())
                .where('X', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES))
                        .or(maintenancePredicate(getCasingState())))
                .where('C', statePredicate(getCasingState2()))
                .where('#', (tile) -> true)
                .where('L', statePredicate(getCasingState()))
                .build();
    }

    private IBlockState getCasingState() {
        return GCYMultiMetaBlocks.LARGE_MULTIBLOCK_CASING.getState(BlockLargeMultiblockCasing.CasingType.MACERATOR_CASING);
    }

    private IBlockState getCasingState2() {
        return GCYMultiMetaBlocks.UNIQUE_CASING.getState(BlockUniqueCasing.UniqueCasingType.CRUSHING_WHEELS);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return GCYMultiTextures.MACERATOR_CASING;
    }

    @Nonnull
    @Override
    protected OrientedOverlayRenderer getFrontOverlay() {
        return GCYMultiTextures.LARGE_MACERATOR_OVERLAY;
    }
}
