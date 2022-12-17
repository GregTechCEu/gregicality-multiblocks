package gregicality.multiblocks.integration.jei;

import gregicality.multiblocks.api.utils.GCYMLog;
import gregtech.api.GTValues;
import gregtech.api.GregTechAPI;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.recipes.ingredients.GTRecipeInput;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TieredHatchRecipeWrapper implements IRecipeWrapper {

    public int tier;
    public ItemStack metaTileEntity;
    public List<GTRecipeInput> inputs;
    public List<List<ItemStack>> itemStacks;

    public TieredHatchRecipeWrapper(@Nonnull ResourceLocation location, @Nonnull List<GTRecipeInput> inputs, int tier) {
        //noinspection constantConditions
        MetaTileEntity mte = GregTechAPI.MTE_REGISTRY.getObject(location);
        if (mte == null) {
            GCYMLog.logger.error("MetaTileEntity with id {} was null", location);
            return;
        }

        this.metaTileEntity = mte.getStackForm();
        this.inputs = new ArrayList<>(inputs);
        this.itemStacks = inputs.stream()
                .map(i -> Arrays.asList(Arrays.stream(i.getInputStacks())
                        .map(ItemStack::copy)
                        .toArray(ItemStack[]::new)))
                .collect(Collectors.toList());

        this.tier = tier;
    }

    @Override
    public void getIngredients(@Nonnull IIngredients ingredients) {
        List<List<ItemStack>> inputLists = new ArrayList<>();
        for (List<ItemStack> list : this.itemStacks) {
            inputLists.add(new ArrayList<>(list));
        }
        inputLists.add(Collections.singletonList(this.metaTileEntity));
        ingredients.setInputLists(VanillaTypes.ITEM, inputLists);
        ingredients.setOutputLists(VanillaTypes.ITEM, Collections.singletonList(Collections.singletonList(metaTileEntity)));
    }

    @Override
    public void drawInfo(@Nonnull Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
        int offset = (18 - minecraft.fontRenderer.getStringWidth(GTValues.VNF[this.tier])) / 2;
        minecraft.fontRenderer.drawString(GTValues.VNF[this.tier], 18 * 4 + offset, 18 + 9 + 6, 0x111111, true);
    }
}
