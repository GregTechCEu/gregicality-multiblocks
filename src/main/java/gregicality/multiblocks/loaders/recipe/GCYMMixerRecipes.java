package gregicality.multiblocks.loaders.recipe;

import static gregicality.multiblocks.api.unification.GCYMMaterials.*;
import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;

import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;

public final class GCYMMixerRecipes {

    private GCYMMixerRecipes() {}

    public static void init() {
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Titanium)
                .input(dust, Carbon)
                .notConsumable(new IntCircuitIngredient(1))
                .output(dust, TitaniumCarbide, 2)
                .duration(160).EUt(VA[EV]).buildAndRegister();

        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Tantalum)
                .input(dust, Carbon)
                .notConsumable(new IntCircuitIngredient(1))
                .output(dust, TantalumCarbide, 2)
                .duration(150).EUt(VA[EV]).buildAndRegister();

        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Molybdenum)
                .input(dust, Silicon, 2)
                .notConsumable(new IntCircuitIngredient(1))
                .output(dust, MolybdenumDisilicide, 3)
                .duration(180).EUt(VA[EV]).buildAndRegister();

        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Invar, 2)
                .input(dust, Vanadium, 1)
                .input(dust, Titanium, 1)
                .input(dust, Molybdenum, 1)
                .notConsumable(new IntCircuitIngredient(1))
                .output(dust, HSLASteel, 5)
                .duration(140).EUt(VA[HV]).buildAndRegister();
    }
}
