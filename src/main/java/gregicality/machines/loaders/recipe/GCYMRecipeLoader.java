package gregicality.machines.loaders.recipe;

import gregicality.machines.loaders.recipe.handlers.GCYMMaterialRecipeHandler;

public class GCYMRecipeLoader {

    public static void init() {
        GCYMMetaTileEntityLoader.init();
        GCYMCasingLoader.init();
        GCYMMixerRecipes.init();
        GCYMMaterialRecipeHandler.register();
    }
}
