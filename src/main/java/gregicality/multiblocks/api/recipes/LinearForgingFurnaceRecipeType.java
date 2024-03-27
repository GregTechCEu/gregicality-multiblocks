package gregicality.multiblocks.api.recipes;

import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMaps;

public enum LinearForgingFurnaceRecipeType {
    NONE, BLAST, ALLOY, BLAST_COOLED, ALLOY_COOLED, BLAST_FORGING_COOLED, ALLOY_FORGING_COOLED;

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
}
