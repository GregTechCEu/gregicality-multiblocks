package gregicality.multiblocks.loaders.recipe;

import gregicality.multiblocks.api.recipes.GCYMRecipeMaps;
import gregicality.multiblocks.api.utils.GCYMLog;
import gregtech.api.fluids.store.FluidStorageKeys;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.category.GTRecipeCategory;
import gregtech.api.recipes.ingredients.GTRecipeFluidInput;
import gregtech.api.recipes.ingredients.GTRecipeInput;
import gregtech.api.recipes.ingredients.GTRecipeItemInput;
import gregtech.api.recipes.ingredients.GTRecipeOreInput;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.info.MaterialFlag;
import gregtech.api.unification.material.info.MaterialFlags;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.items.MetaItems;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.Level;
import org.jetbrains.annotations.NotNull;
import scala.Int;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class LinearForgingFurnaceLoader {

    private final static int MAXIMUM_SEARCH_MULTIPLIER = 8;

    private static final Map<MaterialFlag, OrePrefix> FORGEABLE_MATERIAL_FLAGS = new Object2ObjectOpenHashMap<>(15, 1);

    private static final List<OrePrefix> FORGEABLE_PIPES = new ObjectArrayList<>(9);

    private static void populateReferences() {
        FORGEABLE_MATERIAL_FLAGS.put(MaterialFlags.GENERATE_DENSE, OrePrefix.plateDense);
        FORGEABLE_MATERIAL_FLAGS.put(MaterialFlags.GENERATE_FRAME, OrePrefix.frameGt);
        FORGEABLE_MATERIAL_FLAGS.put(MaterialFlags.GENERATE_PLATE, OrePrefix.plate);
        FORGEABLE_MATERIAL_FLAGS.put(MaterialFlags.GENERATE_DOUBLE_PLATE, OrePrefix.plateDouble);
        FORGEABLE_MATERIAL_FLAGS.put(MaterialFlags.GENERATE_FOIL, OrePrefix.foil);
        FORGEABLE_MATERIAL_FLAGS.put(MaterialFlags.GENERATE_BOLT_SCREW, OrePrefix.bolt);
        FORGEABLE_MATERIAL_FLAGS.put(MaterialFlags.GENERATE_GEAR, OrePrefix.gear);
        FORGEABLE_MATERIAL_FLAGS.put(MaterialFlags.GENERATE_SMALL_GEAR, OrePrefix.gearSmall);
        FORGEABLE_MATERIAL_FLAGS.put(MaterialFlags.GENERATE_LONG_ROD, OrePrefix.stickLong);
        FORGEABLE_MATERIAL_FLAGS.put(MaterialFlags.GENERATE_ROD, OrePrefix.stick);
        FORGEABLE_MATERIAL_FLAGS.put(MaterialFlags.GENERATE_RING, OrePrefix.ring);
        FORGEABLE_MATERIAL_FLAGS.put(MaterialFlags.GENERATE_ROTOR, OrePrefix.rotor);
        FORGEABLE_MATERIAL_FLAGS.put(MaterialFlags.GENERATE_ROUND, OrePrefix.round);
        FORGEABLE_MATERIAL_FLAGS.put(MaterialFlags.GENERATE_SPRING, OrePrefix.spring);
        FORGEABLE_MATERIAL_FLAGS.put(MaterialFlags.GENERATE_SPRING_SMALL, OrePrefix.springSmall);

        FORGEABLE_PIPES.add(OrePrefix.pipeHugeItem);
        FORGEABLE_PIPES.add(OrePrefix.pipeLargeItem);
        FORGEABLE_PIPES.add(OrePrefix.pipeNormalItem);
        FORGEABLE_PIPES.add(OrePrefix.pipeSmallItem);
        FORGEABLE_PIPES.add(OrePrefix.pipeHugeFluid);
        FORGEABLE_PIPES.add(OrePrefix.pipeLargeFluid);
        FORGEABLE_PIPES.add(OrePrefix.pipeNormalFluid);
        // skip quadruple and nonuple; they can be straightforwardly crafted.
        FORGEABLE_PIPES.add(OrePrefix.pipeSmallFluid);
        FORGEABLE_PIPES.add(OrePrefix.pipeTinyFluid);
    }

    private LinearForgingFurnaceLoader() {}

    public static void registerLate() {
        populateReferences();
        registerCooled();
        registerForgingCooled();
        assembleCompositeMaps();
    }

    private static void registerCooled() {
        RecipeMap<?> map = GCYMRecipeMaps.LINEAR_FORGING_RECIPES[4];
        GTRecipeCategory category = GTRecipeCategory.getByName(map.getUnlocalizedName());
        if (category == null)
            throw new IllegalStateException("Component recipemap " + map.getUnlocalizedName() + " had no a category!");
        for (Recipe blastRecipe : RecipeMaps.BLAST_RECIPES.getRecipeList()) {
            produceCooledRecipe(blastRecipe, map, category);
        }

        map = GCYMRecipeMaps.LINEAR_FORGING_RECIPES[5];
        category = GTRecipeCategory.getByName(map.getUnlocalizedName());
        if (category == null)
            throw new IllegalStateException("Component recipemap " + map.getUnlocalizedName() + " had no a category!");
        for (Recipe blastRecipe : GCYMRecipeMaps.ALLOY_BLAST_RECIPES.getRecipeList()) {
            produceCooledRecipe(blastRecipe, map, category);
        }
    }

    private static void produceCooledRecipe(Recipe blastRecipe, RecipeMap<?> targetMap,
                                            @NotNull GTRecipeCategory mapCategory) {

        List<ItemStack> compositeOutputs = deepCopyIS(blastRecipe.getOutputs());
        List<FluidStack> compositeFluidOutputs = deepCopyFS(blastRecipe.getFluidOutputs());
        Recipe freezerRecipe = null;

        int blastRepetitions = 0;
        int freezerRepetitions = 0;

        // look for a matching freezer recipe, up to the maximum search multiplier
        ItemStack moldStack = new ItemStack(MetaItems.SHAPE_MOLD_INGOT.getMetaItem(), 1,
                MetaItems.SHAPE_MOLD_INGOT.getMetaValue());
        FluidStack heliumStack = Materials.Helium.getFluid(FluidStorageKeys.LIQUID, 50000);
        compositeOutputs.add(moldStack);
        compositeFluidOutputs.add(heliumStack);
        while (blastRepetitions <= MAXIMUM_SEARCH_MULTIPLIER) {
            blastRepetitions++;
            freezerRecipe = RecipeMaps.VACUUM_RECIPES
                    .findRecipe(Long.MAX_VALUE, compositeOutputs, compositeFluidOutputs);
            if (freezerRecipe == null) {
                freezerRecipe = RecipeMaps.FLUID_SOLIDFICATION_RECIPES
                        .findRecipe(Long.MAX_VALUE, compositeOutputs, compositeFluidOutputs);
            }
            if (freezerRecipe == null) {
                double mult = (double) (blastRepetitions + 1) / blastRepetitions;
                compositeOutputs.forEach(a -> a.setCount((int) (a.getCount() * mult)));
                compositeFluidOutputs.forEach(a -> a.amount = (int) (a.amount * mult));
            } else break;
        }
        compositeOutputs.remove(moldStack);
        compositeFluidOutputs.remove(heliumStack);
        // if no freezer recipe is found, just add it to the map unchanged.
        if (freezerRecipe == null) {
            buildToMap(blastRecipe, targetMap);
            return;
        }

        List<GTRecipeInput> compositeInputs = deepCopyRI(freezerRecipe.getInputs());
        List<GTRecipeInput> compositeFluidInputs = deepCopyRI(freezerRecipe.getFluidInputs());

        // check if there's any small ratio of blast to freezer repetitions, otherwise do a procedural cancel
        AtomicReference<Double> ratio = new AtomicReference<>(-1d);
        AtomicInteger targetFreezerRepetitions = new AtomicInteger();

        List<ItemStack> finalCompositeOutputs = compositeOutputs;
        compositeInputs.forEach(a -> finalCompositeOutputs.forEach(b -> {
            if (ratio.get() == -1 && a.acceptsStack(b)) {
                double stackRatio = (double) b.getCount() / a.getAmount();
                Pair<Integer, Integer> simplestRatio = simplestRatio(stackRatio, b.getCount());
                // prevent excessively large ratios
                if (simplestRatio.getLeft() * simplestRatio.getRight()
                        > MAXIMUM_SEARCH_MULTIPLIER * MAXIMUM_SEARCH_MULTIPLIER / 2)
                    return;
                ratio.set(stackRatio);
                targetFreezerRepetitions.set(Math.max(b.getCount(), targetFreezerRepetitions.get()));
            }
        }));
        List<FluidStack> finalCompositeFluidOutputs = compositeFluidOutputs;
        compositeFluidInputs.forEach(a -> finalCompositeFluidOutputs.forEach(b -> {
            if (ratio.get() == -1 && a.acceptsFluid(b)) {
                double stackRatio = (double) b.amount / a.getAmount();
                Pair<Integer, Integer> simplestRatio = simplestRatio(stackRatio, b.amount);
                // prevent excessively large ratios
                if (simplestRatio.getLeft() * simplestRatio.getRight()
                        > MAXIMUM_SEARCH_MULTIPLIER * MAXIMUM_SEARCH_MULTIPLIER / 2)
                    return;
                ratio.set(stackRatio);
                targetFreezerRepetitions.set(Math.max(b.amount, targetFreezerRepetitions.get()));
            }
        }));

        if (ratio.get() != -1) {
            // smart cancellation of freezer inputs with blast outputs based on identified ratio.
            Pair<Integer, Integer> simplestRatio = simplestRatio(ratio.get(), targetFreezerRepetitions.get());
            blastRepetitions *= simplestRatio.getRight();
            freezerRepetitions = simplestRatio.getLeft();
            List<ItemStack> constructingOutputs = deepCopyIS(compositeOutputs);
            List<FluidStack> constructingFluidOutputs = deepCopyFS(compositeFluidOutputs);

            List<GTRecipeInput> constructingInputs = deepCopyRI(compositeInputs);
            List<GTRecipeInput> constructingFluidInputs = deepCopyRI(compositeFluidInputs);

            int finalFreezerRepetitions = freezerRepetitions;
            int finalBlastRepetitions = blastRepetitions;
            constructingOutputs.forEach(b -> b.setCount(b.getCount() * finalBlastRepetitions));
            constructingInputs.forEach(a -> {
                a.withAmount(a.getAmount() * finalFreezerRepetitions);
                constructingOutputs.forEach(b -> {
                    if (a.acceptsStack(b)) {
                        int dif = a.getAmount() - b.getCount();
                        a.withAmount(Math.max(0, dif));
                        b.setCount(Math.max(0, -dif));
                    }
                });
            });
            constructingFluidOutputs.forEach(b -> b.amount *= finalBlastRepetitions);
            constructingFluidInputs.forEach(a -> {
                a.withAmount(a.getAmount() * finalFreezerRepetitions);
                constructingFluidOutputs.forEach(b -> {
                    if (a.acceptsFluid(b)) {
                        int dif = a.getAmount() - b.amount;
                        a.withAmount(Math.max(0, dif));
                        b.amount = Math.max(0, -dif);
                    }
                });
            });

            compositeOutputs = constructingOutputs;
            compositeFluidOutputs = constructingFluidOutputs;

            compositeInputs = constructingInputs;
            compositeFluidInputs = constructingFluidInputs;
        } else {
            // fallback procedural cancellation of freezer inputs with blast outputs
            AtomicBoolean noNegativeOutputs = new AtomicBoolean(true);
            AtomicBoolean noZeroedOutput = new AtomicBoolean(true);
            while (noNegativeOutputs.get() && noZeroedOutput.get()) {
                List<ItemStack> constructingOutputs = deepCopyIS(compositeOutputs);
                List<FluidStack> constructingFluidOutputs = deepCopyFS(compositeFluidOutputs);

                List<GTRecipeInput> constructingInputs = deepCopyRI(compositeInputs);
                List<GTRecipeInput> constructingFluidInputs = deepCopyRI(compositeFluidInputs);

                constructingInputs.forEach(a -> constructingOutputs.forEach(b -> {
                    if (a.acceptsStack(b)) {
                        int difference = b.getCount() - a.getAmount();
                        if (difference == 0) noZeroedOutput.set(false);
                        a.withAmount(0);
                        if (difference < 0) noNegativeOutputs.set(false);
                        b.setCount(difference);
                    }
                }));
                constructingFluidInputs.forEach(a -> constructingFluidOutputs.forEach(b -> {
                    if (a.acceptsFluid(b)) {
                        int difference = b.amount - a.getAmount();
                        if (difference == 0) noZeroedOutput.set(false);
                        a.withAmount(0);
                        if (difference < 0) noNegativeOutputs.set(false);
                        b.amount = difference;
                    }
                }));

                if (noNegativeOutputs.get()) {
                    freezerRepetitions++;
                    compositeOutputs = constructingOutputs;
                    compositeFluidOutputs = constructingFluidOutputs;

                    compositeInputs = constructingInputs;
                    compositeFluidInputs = constructingFluidInputs;

                    if (noZeroedOutput.get()) {
                        freezerRecipe.getOutputs().forEach(a -> {
                            for (var b : constructingOutputs) {
                                if (ItemStack.areItemStacksEqual(a, b))
                                    a.setCount(a.getCount() + b.getCount());
                            }
                        });
                        freezerRecipe.getFluidOutputs().forEach(a -> {
                            for (var b : constructingFluidOutputs) {
                                if (a.isFluidEqual(b))
                                    a.amount += b.amount;
                            }
                        });
                        freezerRecipe.getInputs().forEach(a -> {
                            for (var b : constructingInputs) {
                                if (a.equals(b.copyWithAmount(a.getAmount())))
                                    a.withAmount(a.getAmount() + b.getAmount());
                            }
                        });
                        freezerRecipe.getFluidInputs().forEach(a -> {
                            for (var b : constructingFluidInputs) {
                                if (a.equals(b.copyWithAmount(a.getAmount())))
                                    a.withAmount(a.getAmount() + b.getAmount());
                            }
                        });
                    }
                }
            }
        }

        // do final recipe balancing
        int finalBlastRepetitions = blastRepetitions;
        compositeInputs.addAll(blastRecipe.getInputs().stream()
                .map(a -> a.copyWithAmount(a.getAmount() * finalBlastRepetitions)).collect(Collectors.toList()));
        compositeFluidInputs.addAll(blastRecipe.getFluidInputs().stream()
                .map(a -> a.copyWithAmount(a.getAmount() * finalBlastRepetitions)).collect(Collectors.toList()));
        int finalFreezerRepetitions = freezerRepetitions;
        compositeOutputs.addAll(freezerRecipe.getOutputs().stream().map(a -> {
            var b = a.copy();
            b.setCount(b.getCount() * finalFreezerRepetitions);
            return b;
        }).collect(Collectors.toList()));
        compositeFluidOutputs.addAll(freezerRecipe.getFluidOutputs().stream().map(a -> {
            var b = a.copy();
            b.amount = b.amount * finalFreezerRepetitions;
            return b;
        }).collect(Collectors.toList()));

        // freezer/blast OCs to reach new voltage are perfect, that's one of the benefits.
        double newEU = (double) blastRecipe.getEUt() * blastRecipe.getDuration() * blastRepetitions +
                (double) freezerRecipe.getEUt() * freezerRecipe.getDuration() * freezerRepetitions;
        int newEUt = Math.max(blastRecipe.getEUt(), freezerRecipe.getEUt());

        // build from the blast recipe to copy all properties we don't later override.
        try {
            new RecipeBuilder<>(blastRecipe, targetMap)
                    .clearInputs().inputs(finalizedRI(compositeInputs).toArray(new GTRecipeInput[]{}))
                    .clearFluidInputs().fluidInputs(finalizedRI(compositeFluidInputs))
                    .clearOutputs().outputs(finalizedIS(compositeOutputs))
                    .clearFluidOutputs().fluidOutputs(finalizedFS(compositeFluidOutputs))
                    .category(mapCategory)
                    .EUt(newEUt)
                    .duration((int) Math.ceil(newEU / newEUt))
                    .buildAndRegister();
        } catch (IllegalArgumentException e) {
            registerForgingCooled();
        }

    }

    private static void registerForgingCooled() {

    }

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

    private static List<GTRecipeInput> deepCopyRI(List<GTRecipeInput> list) {
        return list.stream().map(a -> a.copyWithAmount(a.getAmount())).collect(Collectors.toList());
    }

    private static List<GTRecipeInput> finalizedRI(List<GTRecipeInput> list) {
        return list.stream().filter(a -> a.getAmount() != 0).peek(a -> {
            if (a instanceof GTRecipeItemInput itemInput) {
                for (var stack : itemInput.getInputStacks()) {
                    if (stack.getItem() != MetaItems.SHAPE_MOLD_INGOT.getMetaItem())
                        stack.setCount(a.getAmount());
                }
            } else if (a instanceof GTRecipeFluidInput fluidInput) {
                fluidInput.getInputFluidStack().amount = a.getAmount();
            }
        }).collect(Collectors.toList());
    }

    private static List<ItemStack> deepCopyIS(List<ItemStack> list) {
        return list.stream().map(ItemStack::copy).collect(Collectors.toList());
    }

    private static List<ItemStack> finalizedIS(List<ItemStack> list) {
        return list.stream().filter(a -> a.getCount() != 0).collect(Collectors.toList());
    }

    private static List<FluidStack> deepCopyFS(List<FluidStack> list) {
        return list.stream().map(FluidStack::copy).collect(Collectors.toList());
    }

    private static List<FluidStack> finalizedFS(List<FluidStack> list) {
        return list.stream().filter(a -> a.amount != 0).collect(Collectors.toList());
    }

    private static Pair<Integer, Integer> simplestRatio(double ratio, int denominatorMult) {

        int a = (int) (ratio * denominatorMult);
        int b = denominatorMult;
        int gcd = gcd(a, b);
        a /= gcd;
        b /= gcd;
        return new ImmutablePair<>(a, b);
    }

    private static int gcd(int a, int b) {
        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }
}
