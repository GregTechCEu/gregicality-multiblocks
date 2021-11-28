package gregicality.machines.common.metatileentities.multiblock.standard;

import gregicality.machines.api.render.GCYMultiTextures;
import gregicality.machines.common.block.GCYMultiMetaBlocks;
import gregicality.machines.common.block.blocks.BlockLargeMultiblockCasing;
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
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

import static gregtech.api.util.RelativeDirection.*;

public class MetaTileEntityLargeBrewery extends RecipeMapMultiblockController {

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = new MultiblockAbility[]{
            MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.IMPORT_FLUIDS,
            MultiblockAbility.EXPORT_FLUIDS, MultiblockAbility.INPUT_ENERGY
    };

    public MetaTileEntityLargeBrewery(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, RecipeMaps.BREWING_RECIPES); //todo make this also a fermenter, fluid heater
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder metaTileEntityHolder) {
        return new MetaTileEntityLargeBrewery(this.metaTileEntityId);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start(RIGHT, FRONT, UP)
                .aisle("#XXX#", "XXXXX", "XXXXX", "XXXXX", "#XXX#")
                .aisle("#XSX#", "XAAAX", "XACAX", "XAAAX", "#XXX#")
                .aisle("#XXX#", "XAAAX", "XACAX", "XAAAX", "#XXX#").setRepeatable(1, 3)
                .aisle("#XXX#", "XXAXX", "XACAX", "XXAXX", "#XXX#")
                .aisle("#####", "##X##", "#XMX#", "##X##", "#####")
                .setAmountAtLeast('L', 30)
                .where('S', selfPredicate())
                .where('X', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES))
                        .or(maintenancePredicate(getCasingState())))
                .where('C', statePredicate(getCasingState2()))
                .where('M', abilityPartPredicate(MultiblockAbility.MUFFLER_HATCH))
                .where('A', isAirPredicate())
                .where('#', (tile) -> true)
                .where('L', statePredicate(getCasingState()))
                .build();
    }

    private IBlockState getCasingState() {
        return GCYMultiMetaBlocks.LARGE_MULTIBLOCK_CASING.getState(BlockLargeMultiblockCasing.CasingType.BREWERY_CASING);
    }

    private IBlockState getCasingState2() {
        return MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.STEEL_PIPE);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return GCYMultiTextures.BREWERY_CASING;
    }

    @Nonnull
    @Override
    protected OrientedOverlayRenderer getFrontOverlay() {
        return GCYMultiTextures.LARGE_BREWERY_OVERLAY;
    }

    @Override
    public boolean hasMufflerMechanics() {
        return true;
    }
}
