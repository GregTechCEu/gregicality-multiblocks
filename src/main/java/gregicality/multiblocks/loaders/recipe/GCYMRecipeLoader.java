package gregicality.multiblocks.loaders.recipe;

import gregicality.multiblocks.loaders.recipe.handlers.GCYMMaterialRecipeHandler;

public class GCYMRecipeLoader {

    public static void init() {
        GCYMMetaTileEntityLoader.init();
        GCYMCasingLoader.init();
        GCYMMixerRecipes.init();
        GCYMMiscRecipes.init();
        GCYMMaterialRecipeHandler.register();
    }
}
