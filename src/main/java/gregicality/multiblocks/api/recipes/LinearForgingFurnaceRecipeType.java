package gregicality.multiblocks.api.recipes;

import static gregicality.multiblocks.api.recipes.GCYMRecipeMaps.LFF_RECIPES_AS_LIST;

import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMaps;

public enum LinearForgingFurnaceRecipeType {

    NONE,
    BLAST,
    ALLOY,
    BLAST_COOLED,
    ALLOY_COOLED,
    BLAST_FORGING_COOLED,
    ALLOY_FORGING_COOLED;

    public static LinearForgingFurnaceRecipeType getRecipeType(Recipe recipe) {
        if (RecipeMaps.BLAST_RECIPES.getRecipeList().contains(recipe))
            return LinearForgingFurnaceRecipeType.BLAST;
        if (GCYMRecipeMaps.ALLOY_BLAST_RECIPES.getRecipeList().contains(recipe))
            return LinearForgingFurnaceRecipeType.ALLOY;
        if (GCYMRecipeMaps.LINEAR_FORGING_RECIPES[4].getRecipeList().contains(recipe))
            return LinearForgingFurnaceRecipeType.BLAST_COOLED;
        if (GCYMRecipeMaps.LINEAR_FORGING_RECIPES[5].getRecipeList().contains(recipe))
            return LinearForgingFurnaceRecipeType.ALLOY_COOLED;
        if (GCYMRecipeMaps.LINEAR_FORGING_RECIPES[7].getRecipeList().contains(recipe))
            return LinearForgingFurnaceRecipeType.BLAST_FORGING_COOLED;
        if (GCYMRecipeMaps.LINEAR_FORGING_RECIPES[8].getRecipeList().contains(recipe))
            return LinearForgingFurnaceRecipeType.ALLOY_FORGING_COOLED;
        return NONE;
    }

    public static LinearForgingFurnaceRecipeType getRecipeType(Recipe recipe, RecipeMap<?> map) {
        int index = LFF_RECIPES_AS_LIST.indexOf(map);
        return switch (index) {
            case 0, 1, 2 -> {
                if (RecipeMaps.BLAST_RECIPES.getRecipeList().contains(recipe))
                    yield LinearForgingFurnaceRecipeType.BLAST;
                if (GCYMRecipeMaps.ALLOY_BLAST_RECIPES.getRecipeList().contains(recipe))
                    yield LinearForgingFurnaceRecipeType.ALLOY;
                yield NONE;
            }
            case 4, 5, 6 -> {
                if (GCYMRecipeMaps.LINEAR_FORGING_RECIPES[4].getRecipeList().contains(recipe))
                    yield LinearForgingFurnaceRecipeType.BLAST_COOLED;
                if (GCYMRecipeMaps.LINEAR_FORGING_RECIPES[5].getRecipeList().contains(recipe))
                    yield LinearForgingFurnaceRecipeType.ALLOY_COOLED;
                yield NONE;
            }
            case 7, 8, 9 -> {
                if (GCYMRecipeMaps.LINEAR_FORGING_RECIPES[7].getRecipeList().contains(recipe))
                    yield LinearForgingFurnaceRecipeType.BLAST_FORGING_COOLED;
                if (GCYMRecipeMaps.LINEAR_FORGING_RECIPES[8].getRecipeList().contains(recipe))
                    yield LinearForgingFurnaceRecipeType.ALLOY_FORGING_COOLED;
                yield NONE;
            }
            default -> NONE;
        };
    }
}
