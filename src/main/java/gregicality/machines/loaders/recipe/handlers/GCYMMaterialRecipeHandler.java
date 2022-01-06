package gregicality.machines.loaders.recipe.handlers;

import gregicality.machines.api.recipes.GCYMRecipeMaps;
import gregicality.machines.api.unification.properties.GCYMPropertyKey;
import gregtech.api.GTValues;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.builders.BlastRecipeBuilder;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialFlags;
import gregtech.api.unification.material.properties.BlastProperty;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.common.items.MetaItems;
import gregtech.loaders.recipe.CraftingComponent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

public class GCYMMaterialRecipeHandler {

    public static void register() {
        OrePrefix.ingot.addProcessingHandler(PropertyKey.BLAST, GCYMMaterialRecipeHandler::processIngot);
    }

    public static void processIngot(OrePrefix ingotPrefix, Material material, BlastProperty property) {
        if (material.hasFlag(MaterialFlags.DISABLE_DECOMPOSITION)) return;

        // ignore non-alloys
        if (material.getMaterialComponents().size() <= 1) return;

        if (!material.hasProperty(PropertyKey.FLUID)) return;

        Fluid fluid = material.getProperty(PropertyKey.FLUID).getFluid();
        if (fluid == null) return;

        Fluid molten = null;
        if (material.hasProperty(GCYMPropertyKey.ALLOY_BLAST))
            molten = material.getProperty(GCYMPropertyKey.ALLOY_BLAST).getFluid();

        RecipeBuilder<BlastRecipeBuilder> builder = GCYMRecipeMaps.ALLOY_BLAST_RECIPES.recipeBuilder()
                .blastFurnaceTemp(material.getBlastTemperature());

        int duration = property.getDurationOverride();
        if (duration < 0)
            duration = Math.max(1, (int) (material.getMass() * material.getBlastTemperature() / 100L));

        int EUt = property.getEUtOverride();
        if (EUt < 0)
            EUt = GTValues.VA[GTValues.MV];
        builder.EUt(EUt);

        int outputAmount = 0;
        for (MaterialStack materialStack : material.getMaterialComponents()) {
            if (materialStack.material.hasProperty(PropertyKey.DUST))
                builder.input(OrePrefix.dust, materialStack.material, (int) materialStack.amount);
            else if (materialStack.material.hasProperty(PropertyKey.FLUID))
                return;
            else return;
            outputAmount += materialStack.amount;
        }

        duration *= outputAmount * 3.0 / 4;

        if (OrePrefix.ingotHot.doGenerateItem(material)) {
            if (molten == null) return;
            builder.fluidOutputs(new FluidStack(molten, GTValues.L * outputAmount));
        } else builder.fluidOutputs(new FluidStack(fluid, GTValues.L * outputAmount));

        RecipeBuilder<BlastRecipeBuilder> builderGas = builder.copy();

        builder.notConsumable(new IntCircuitIngredient(material.getMaterialComponents().size()))
                .duration(duration)
                .buildAndRegister();

        if (property.getGasTier() != null) {
            FluidStack gas = CraftingComponent.EBF_GASES.get(property.getGasTier()).copy();
            builderGas.notConsumable(new IntCircuitIngredient(material.getMaterialComponents().size() + 10))
                    .fluidInputs(new FluidStack(gas, gas.amount * outputAmount))
                    .duration((int) (duration * 0.67))
                    .buildAndRegister();
        }

        if (!OrePrefix.ingotHot.doGenerateItem(material)) return;

        if (molten == null) return;

        RecipeBuilder<SimpleRecipeBuilder> freezerBuilder = RecipeMaps.VACUUM_RECIPES.recipeBuilder()
                .fluidInputs(new FluidStack(molten, GTValues.L))
                .duration((int) material.getMass() * 3);

        if (material.hasProperty(PropertyKey.INGOT))
            freezerBuilder.notConsumable(MetaItems.SHAPE_MOLD_INGOT.getStackForm()).output(OrePrefix.ingot, material).buildAndRegister();
        else
            freezerBuilder.fluidOutputs(material.getFluid(GTValues.L)).buildAndRegister();
    }
}
