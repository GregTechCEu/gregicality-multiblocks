package gregicality.machines.common.metatileentities.multiblock.unique;

import gregicality.machines.api.metatileentity.GCYMMultiblockAbility;
import gregicality.machines.api.recipes.GCYMRecipeMaps;
import gregicality.machines.api.render.GCYMultiTextures;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.render.OrientedOverlayRenderer;
import gregtech.api.render.Textures;
import gregtech.common.blocks.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class MetaTileEntityChemicalPlant extends RecipeMapMultiblockController {

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = new MultiblockAbility[]{
            MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS,
            MultiblockAbility.IMPORT_FLUIDS, MultiblockAbility.EXPORT_FLUIDS,
            MultiblockAbility.INPUT_ENERGY, GCYMMultiblockAbility.PARALLEL_HATCH
    };

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
                .setAmountAtLeast('L', 50)
                .where('S', selfPredicate())
                .where('X', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES))
                        .or(maintenancePredicate(getCasingState())))
                .where('G', statePredicate(getCasingState2()))
                .where('P', statePredicate(getCasingState3()))
                .where('C', statePredicate(getCasingState4()))
                .where('A', isAirPredicate())
                .where('#', (tile) -> true)
                .where('L', statePredicate(getCasingState()))
                .build();
    }

    private IBlockState getCasingState() {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.PTFE_INERT_CASING);
    }

    private IBlockState getCasingState2() {
        return MetaBlocks.TRANSPARENT_CASING.getState(BlockTransparentCasing.CasingType.TEMPERED_GLASS);
    }

    private IBlockState getCasingState3() {
        return MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.POLYTETRAFLUOROETHYLENE_PIPE);
    }

    private IBlockState getCasingState4() {
        return MetaBlocks.WIRE_COIL.getState(BlockWireCoil.CoilType.CUPRONICKEL);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return Textures.INERT_PTFE_CASING;
    }

    @Nonnull
    @Override
    protected OrientedOverlayRenderer getFrontOverlay() {
        return GCYMultiTextures.CHEMICAL_PLANT_OVERLAY;
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
