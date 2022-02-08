package gregicality.multiblocks.loaders.recipe.handlers;

import gregicality.multiblocks.api.recipes.GCYMRecipeMaps;
import gregicality.multiblocks.api.unification.properties.GCYMPropertyKey;
import gregtech.api.GTValues;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.builders.BlastRecipeBuilder;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.BlastProperty;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.common.items.MetaItems;
import gregtech.loaders.recipe.CraftingComponent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;

public class GCYMMaterialRecipeHandler {

    public static void register() {
        OrePrefix.ingot.addProcessingHandler(PropertyKey.BLAST, GCYMMaterialRecipeHandler::processIngot);
    }

    public static void processIngot(OrePrefix ingotPrefix, @Nonnull Material material, BlastProperty property) {
        // ignore non-alloys
        if (material.getMaterialComponents().size() <= 1) return;

        // get the output fluid
        Fluid molten = null;
        if (material.hasProperty(GCYMPropertyKey.ALLOY_BLAST))
            molten = material.getProperty(GCYMPropertyKey.ALLOY_BLAST).getFluid();
        if (!OrePrefix.ingotHot.doGenerateItem(material) && material.hasProperty(PropertyKey.FLUID))
            molten = material.getProperty(PropertyKey.FLUID).getFluid();
        if (molten == null) return;

        RecipeBuilder<BlastRecipeBuilder> builder = GCYMRecipeMaps.ALLOY_BLAST_RECIPES.recipeBuilder()
                .blastFurnaceTemp(material.getBlastTemperature());

        // apply the duration override
        int duration = property.getDurationOverride();
        if (duration < 0)
            duration = Math.max(1, (int) (material.getMass() * material.getBlastTemperature() / 100L));

        // apply the EUt override
        int EUt = property.getEUtOverride();
        if (EUt < 0)
            EUt = GTValues.VA[GTValues.MV];
        builder.EUt(EUt);

        // calculate the output amount
        // if fluids with no dusts are found in the composition, stop with this material
        int outputAmount = 0;
        for (MaterialStack materialStack : material.getMaterialComponents()) {
            if (materialStack.material.hasProperty(PropertyKey.DUST))
                builder.input(OrePrefix.dust, materialStack.material, (int) materialStack.amount);
            else if (materialStack.material.hasProperty(PropertyKey.FLUID))
                return;
            else return;
            outputAmount += materialStack.amount;
        }

        // add the fluid output with the correct amount
        builder.fluidOutputs(new FluidStack(molten, GTValues.L * outputAmount));

        // apply alloy blast duration reduction: 3/4
        duration *= outputAmount * 3.0 / 4;

        // build the gas recipe if it exists
        if (property.getGasTier() != null) {
            RecipeBuilder<BlastRecipeBuilder> builderGas = builder.copy();
            FluidStack gas = CraftingComponent.EBF_GASES.get(property.getGasTier()).copy();
            builderGas.notConsumable(new IntCircuitIngredient(material.getMaterialComponents().size() + 10))
                    .fluidInputs(new FluidStack(gas, gas.amount * outputAmount))
                    .duration((int) (duration * 0.67))
                    .buildAndRegister();
        }

        // build the non-gas recipe
        builder.notConsumable(new IntCircuitIngredient(material.getMaterialComponents().size()))
                .duration(duration)
                .buildAndRegister();

        // if the material does not need a vacuum freezer, exit
        if (!OrePrefix.ingotHot.doGenerateItem(material)) return;

        // build the freezer recipe
        RecipeMaps.VACUUM_RECIPES.recipeBuilder()
                .fluidInputs(new FluidStack(molten, GTValues.L))
                .duration((int) material.getMass() * 3)
                .notConsumable(MetaItems.SHAPE_MOLD_INGOT.getStackForm())
                .output(OrePrefix.ingot, material)
                .buildAndRegister();
    }
}
