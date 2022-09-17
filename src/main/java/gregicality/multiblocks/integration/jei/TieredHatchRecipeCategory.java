package gregicality.multiblocks.integration.jei;

import com.google.common.base.Suppliers;
import gregicality.multiblocks.GregicalityMultiblocks;
import gregicality.multiblocks.common.metatileentities.GCYMMetaTileEntities;
import gregtech.api.gui.GuiTextures;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredientRenderer;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Supplier;

public class TieredHatchRecipeCategory implements IRecipeCategory<TieredHatchRecipeWrapper> {

    public static final String UID = String.format("%s.tiered_hatches", GregicalityMultiblocks.MODID);

    private static final int SLOT_SIZE = 18;

    private final IDrawable iconDrawable;
    private final IDrawable backgroundDrawable;
    private final IDrawable slot;
    private final IDrawable tier;

    private static final Supplier<IIngredientRenderer<ItemStack>> itemRenderer = Suppliers.memoize(() -> {
            return GCYMJeiPlugin.ingredientRegistry.getIngredientRenderer(VanillaTypes.ITEM);
    });

    public TieredHatchRecipeCategory(@Nonnull IGuiHelper guiHelper) {
        this.iconDrawable = guiHelper.createDrawableIngredient(GCYMMetaTileEntities.TIERED_HATCH.getStackForm());
        this.backgroundDrawable = guiHelper.createBlankDrawable(99, 54);

        this.slot = guiHelper.drawableBuilder(GuiTextures.SLOT.imageLocation, 0, 0, 18, 18)
                .setTextureSize(18, 18)
                .build();
        this.tier = guiHelper.drawableBuilder(GuiTextures.DISPLAY.imageLocation, 0, 0, 22, 12)
                .setTextureSize(22, 12)
                .build();
    }

    @Nonnull
    @Override
    public String getUid() {
        return UID;
    }

    @Nonnull
    @Override
    public String getTitle() {
        return I18n.format("gcym.recipe_category.tiered_hatch");
    }

    @Nonnull
    @Override
    public String getModName() {
        return GregicalityMultiblocks.MODID;
    }

    @Nonnull
    @Override
    public IDrawable getBackground() {
        return backgroundDrawable;
    }

    @Nullable
    @Override
    public IDrawable getIcon() {
        return iconDrawable;
    }

    @Override
    public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull TieredHatchRecipeWrapper recipeWrapper, @Nonnull IIngredients ingredients) {
        IGuiItemStackGroup stacks = recipeLayout.getItemStacks();
        // controller
        stacks.init(0, true, itemRenderer.get(),
                SLOT_SIZE * 4, 9,
                SLOT_SIZE, SLOT_SIZE,
                1, 1);
        stacks.set(0, recipeWrapper.metaTileEntity);

        // components
        for (int y = 0; y < 2; y++) {
            for (int x = 0; x < 3; x++) {
                final int slotIndex = y * 3 + x;
                stacks.init(slotIndex + 1, true, itemRenderer.get(),
                        SLOT_SIZE * x + 9, SLOT_SIZE * y + 9,
                        SLOT_SIZE, SLOT_SIZE,
                        1, 1);
            }
        }
        for (int i = 0; i < recipeWrapper.itemStacks.size(); i++) {
            stacks.set(1 + i, recipeWrapper.itemStacks.get(i));
        }
    }

    @Override
    public void drawExtras(@Nonnull Minecraft minecraft) {
        // controller
        this.slot.draw(minecraft, SLOT_SIZE * 4, 9);

        // tier
        this.tier.draw(minecraft, SLOT_SIZE * 4 - 2, SLOT_SIZE + 15 - 2);

        for (int y = 0; y < 2; y++) {
            for (int x = 0; x < 3; x++) {
                this.slot.draw(minecraft, SLOT_SIZE * x + 9, SLOT_SIZE * y + 9);
            }
        }
    }
}
