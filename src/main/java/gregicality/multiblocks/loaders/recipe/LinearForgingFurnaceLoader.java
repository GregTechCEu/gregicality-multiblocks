package gregicality.multiblocks.loaders.recipe;

import gregicality.multiblocks.api.recipes.GCYMRecipeMaps;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.category.GTRecipeCategory;

public class LinearForgingFurnaceLoader {

    private LinearForgingFurnaceLoader() {}

    public static void assembleCompositeMaps() {
        for (Recipe recipe : RecipeMaps.BLAST_RECIPES.getRecipeList()) {
            buildToMap(recipe, GCYMRecipeMaps.LINEAR_FORGING_RECIPES[2]);
        }
        for (Recipe recipe : GCYMRecipeMaps.ALLOY_BLAST_RECIPES.getRecipeList()) {
            buildToMap(recipe, GCYMRecipeMaps.LINEAR_FORGING_RECIPES[2]);
        }

        for (Recipe recipe : GCYMRecipeMaps.LINEAR_FORGING_RECIPES[4].getRecipeList()) {
            buildToMap(recipe, GCYMRecipeMaps.LINEAR_FORGING_RECIPES[6]);
        }
        for (Recipe recipe : GCYMRecipeMaps.LINEAR_FORGING_RECIPES[5].getRecipeList()) {
            buildToMap(recipe, GCYMRecipeMaps.LINEAR_FORGING_RECIPES[6]);
        }

        for (Recipe recipe : GCYMRecipeMaps.LINEAR_FORGING_RECIPES[7].getRecipeList()) {
            buildToMap(recipe, GCYMRecipeMaps.LINEAR_FORGING_RECIPES[9]);
        }
        for (Recipe recipe : GCYMRecipeMaps.LINEAR_FORGING_RECIPES[8].getRecipeList()) {
            buildToMap(recipe, GCYMRecipeMaps.LINEAR_FORGING_RECIPES[9]);
        }
    }

    private static void buildToMap(Recipe recipe, RecipeMap<?> map) {
        GTRecipeCategory category = GTRecipeCategory.getByName(map.getUnlocalizedName());
        if (category != null)
            new RecipeBuilder<>(recipe, map).category(category).buildAndRegister();
    }
}
