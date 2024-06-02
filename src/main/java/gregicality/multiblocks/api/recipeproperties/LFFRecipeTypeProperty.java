package gregicality.multiblocks.api.recipeproperties;

import net.minecraft.client.Minecraft;

import gregtech.api.recipes.recipeproperties.RecipeProperty;

import gregicality.multiblocks.api.recipes.LinearForgingFurnaceRecipeType;

public class LFFRecipeTypeProperty extends RecipeProperty<LinearForgingFurnaceRecipeType> {

    public static final String KEY = "lff_recipe_type";

    private static LFFRecipeTypeProperty INSTANCE;

    protected LFFRecipeTypeProperty() {
        super(KEY, LinearForgingFurnaceRecipeType.class);
    }

    public static LFFRecipeTypeProperty getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LFFRecipeTypeProperty();
        }
        return INSTANCE;
    }

    @Override
    public void drawInfo(Minecraft minecraft, int x, int y, int color, Object value) {}

    @Override
    public int getInfoHeight(Object value) {
        return 0;
    }
}
