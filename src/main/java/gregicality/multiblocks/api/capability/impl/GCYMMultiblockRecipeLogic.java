package gregicality.multiblocks.api.capability.impl;

import gregicality.multiblocks.api.capability.IParallelMultiblock;
import gregicality.multiblocks.api.metatileentity.GCYMMultiblockAbility;
import gregicality.multiblocks.api.metatileentity.IGCYMMultiBlock;
import gregicality.multiblocks.api.metatileentity.ITieredHatch;
import gregicality.multiblocks.common.GCYMConfigHolder;
import gregtech.api.GTValues;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.recipes.Recipe;

import java.util.List;

public class GCYMMultiblockRecipeLogic extends MultiblockRecipeLogic {

    public GCYMMultiblockRecipeLogic(RecipeMapMultiblockController tileEntity) {
        super(tileEntity);
    }

    @Override
    public int getParallelLimit() {
        if (metaTileEntity instanceof IParallelMultiblock && ((IParallelMultiblock) metaTileEntity).isParallel())
            return ((IParallelMultiblock) metaTileEntity).getMaxParallel();

        return 1;
    }

    @Override
    public RecipeMapMultiblockController getMetaTileEntity() {
        return (RecipeMapMultiblockController) super.getMetaTileEntity();
    }

    @Override
    protected long getMaxVoltage() {
        // gate base recipe tier by tiered hatch
        if (!GCYMConfigHolder.globalMultiblocks.enableTieredCasings) {
            // not tiered, so get the regular max
            return super.getMaxVoltage();
        }

        if (getMetaTileEntity() instanceof IGCYMMultiBlock && !((IGCYMMultiBlock) getMetaTileEntity()).isTiered()) {
            // not tiered, so get the regular max
            return super.getMaxVoltage();
        }

        List<ITieredHatch> list = getMetaTileEntity().getAbilities(GCYMMultiblockAbility.TIERED_HATCH);
        if (list.isEmpty()) {
            // no tiered hatch present, so get the maximum based on the default
            return Math.min(super.getMaxVoltage(), GTValues.V[GCYMConfigHolder.globalMultiblocks.baseMultiblockTier]);
        }
        // get the maximum based on the hatch
        return Math.min(GTValues.V[list.get(0).getMaxVoltageTier()], super.getMaxVoltage());
    }

    //    @Override //TODO add @Override in CEu 2.5.0
    protected long getMaxParallelVoltage() {
        // use the hatch's max voltage for parallel
        return super.getMaxVoltage();
    }

    @Override
    public long getMaximumOverclockVoltage() {
        // use the hatch's max voltage for OC
        return super.getMaxVoltage();
    }

    //TODO Remove this override in CEu 2.5.0
    @Override
    protected boolean prepareRecipe(Recipe recipe) {
        recipe = recipe.trimRecipeOutputs(recipe, this.getRecipeMap(), this.metaTileEntity.getItemOutputLimit(), this.metaTileEntity.getFluidOutputLimit());
        recipe = this.findParallelRecipe(this, recipe, this.getInputInventory(), this.getInputTank(), this.getOutputInventory(), this.getOutputTank(), this.getMaxParallelVoltage(), this.getParallelLimit());
        if (recipe != null && this.setupAndConsumeRecipeInputs(recipe, this.getInputInventory())) {
            this.setupRecipe(recipe);
            return true;
        } else {
            return false;
        }
    }

    //TODO Remove this override in CEu 2.5.0
    @Override
    protected boolean prepareRecipeDistinct(Recipe recipe) {
        recipe = recipe.trimRecipeOutputs(recipe, getRecipeMap(), metaTileEntity.getItemOutputLimit(), metaTileEntity.getFluidOutputLimit());

        recipe = findParallelRecipe(
                this,
                recipe,
                currentDistinctInputBus,
                getInputTank(),
                getOutputInventory(),
                getOutputTank(),
                getMaxParallelVoltage(),
                getParallelLimit());

        if (recipe != null && setupAndConsumeRecipeInputs(recipe, currentDistinctInputBus)) {
            setupRecipe(recipe);
            return true;
        }

        return false;
    }
}
