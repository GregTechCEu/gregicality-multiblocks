package gregicality.multiblocks.loaders.recipe;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.google.common.collect.ImmutableList;

import gregtech.api.GTValues;
import gregtech.api.fluids.store.FluidStorageKeys;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.builders.BlastRecipeBuilder;
import gregtech.api.recipes.category.GTRecipeCategory;
import gregtech.api.recipes.chance.output.impl.ChancedFluidOutput;
import gregtech.api.recipes.chance.output.impl.ChancedItemOutput;
import gregtech.api.recipes.ingredients.GTRecipeFluidInput;
import gregtech.api.recipes.ingredients.GTRecipeInput;
import gregtech.api.recipes.ingredients.GTRecipeItemInput;
import gregtech.api.recipes.recipeproperties.IRecipePropertyStorage;
import gregtech.api.recipes.recipeproperties.TemperatureProperty;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.items.MetaItems;

import gregicality.multiblocks.api.recipeproperties.LFFRecipeTypeProperty;
import gregicality.multiblocks.api.recipes.GCYMRecipeMaps;
import gregicality.multiblocks.api.recipes.LinearForgingFurnaceRecipeType;
import gregicality.multiblocks.api.unification.GCYMMaterialFlags;
import gregicality.multiblocks.api.utils.GCYMLog;
import gregicality.multiblocks.common.GCYMConfigHolder;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;

public class LinearForgingFurnaceLoader {

    private final static int MAXIMUM_SEARCH_MULTIPLIER = 8;

    private static final Map<OrePrefix, List<ItemStack>> PREFIX_MODIFIERS = new Object2ObjectOpenHashMap<>();

    private static final List<RecipeBuilder<?>> EMPTY_BUILDER_LIST = new ObjectArrayList<>();

    private static int count = 0;

    private static void populateReferences() {
        // TODO create unique metaitems to separate out the forging recipes
        PREFIX_MODIFIERS.put(OrePrefix.nugget, ImmutableList.of(
                MetaItems.SHAPE_MOLD_NUGGET.getStackForm(), MetaItems.SHAPE_EXTRUDER_INGOT.getStackForm()));
        PREFIX_MODIFIERS.put(OrePrefix.ingot, ImmutableList.of(
                MetaItems.SHAPE_MOLD_INGOT.getStackForm(), MetaItems.SHAPE_EXTRUDER_INGOT.getStackForm()));
        PREFIX_MODIFIERS.put(OrePrefix.block, ImmutableList.of(
                MetaItems.SHAPE_MOLD_BLOCK.getStackForm(), MetaItems.SHAPE_EXTRUDER_INGOT.getStackForm()));
        PREFIX_MODIFIERS.put(OrePrefix.plate, ImmutableList.of(
                MetaItems.SHAPE_EXTRUDER_PLATE.getStackForm(), MetaItems.SHAPE_MOLD_INGOT.getStackForm()));
        PREFIX_MODIFIERS.put(OrePrefix.plateDouble, ImmutableList.of(
                MetaItems.SHAPE_EXTRUDER_PLATE.getStackForm(), MetaItems.SHAPE_MOLD_PLATE.getStackForm()));
        PREFIX_MODIFIERS.put(OrePrefix.plateDense, ImmutableList.of(
                MetaItems.SHAPE_EXTRUDER_PLATE.getStackForm(), MetaItems.SHAPE_MOLD_BLOCK.getStackForm()));
        PREFIX_MODIFIERS.put(OrePrefix.stick, ImmutableList.of(
                MetaItems.SHAPE_EXTRUDER_ROD.getStackForm(), MetaItems.SHAPE_MOLD_INGOT.getStackForm()));
        PREFIX_MODIFIERS.put(OrePrefix.stickLong, ImmutableList.of(
                MetaItems.SHAPE_EXTRUDER_ROD_LONG.getStackForm(), MetaItems.SHAPE_MOLD_INGOT.getStackForm()));
        PREFIX_MODIFIERS.put(OrePrefix.frameGt, ImmutableList.of(
                MetaItems.SHAPE_EXTRUDER_ROD.getStackForm(), MetaItems.SHAPE_MOLD_BLOCK.getStackForm()));
        PREFIX_MODIFIERS.put(OrePrefix.bolt, ImmutableList.of(
                MetaItems.SHAPE_EXTRUDER_BOLT.getStackForm()));
        PREFIX_MODIFIERS.put(OrePrefix.spring, ImmutableList.of(
                MetaItems.SHAPE_EXTRUDER_ROD_LONG.getStackForm(), MetaItems.SHAPE_MOLD_CYLINDER.getStackForm()));
        PREFIX_MODIFIERS.put(OrePrefix.springSmall, ImmutableList.of(
                MetaItems.SHAPE_EXTRUDER_ROD.getStackForm(), MetaItems.SHAPE_MOLD_CYLINDER.getStackForm()));
        PREFIX_MODIFIERS.put(OrePrefix.round, ImmutableList.of(
                MetaItems.SHAPE_MOLD_NUGGET.getStackForm(), MetaItems.SHAPE_MOLD_BALL.getStackForm()));
        PREFIX_MODIFIERS.put(OrePrefix.gear, ImmutableList.of(
                MetaItems.SHAPE_EXTRUDER_GEAR.getStackForm()));
        PREFIX_MODIFIERS.put(OrePrefix.gearSmall, ImmutableList.of(
                MetaItems.SHAPE_EXTRUDER_GEAR_SMALL.getStackForm()));
        PREFIX_MODIFIERS.put(OrePrefix.rotor, ImmutableList.of(
                MetaItems.SHAPE_EXTRUDER_ROTOR.getStackForm()));
        PREFIX_MODIFIERS.put(OrePrefix.turbineBlade, ImmutableList.of(
                MetaItems.SHAPE_EXTRUDER_ROD_LONG.getStackForm(), MetaItems.SHAPE_EXTRUDER_PLATE.getStackForm()));
        PREFIX_MODIFIERS.put(OrePrefix.foil, ImmutableList.of(
                MetaItems.SHAPE_EXTRUDER_FOIL.getStackForm()));
        // this prefix exists but is never used, it's here just in case.
        PREFIX_MODIFIERS.put(OrePrefix.pipeTinyItem, ImmutableList.of(
                MetaItems.SHAPE_EXTRUDER_PIPE_TINY.getStackForm()));
        PREFIX_MODIFIERS.put(OrePrefix.pipeTinyFluid, ImmutableList.of(
                MetaItems.SHAPE_EXTRUDER_PIPE_TINY.getStackForm()));
        PREFIX_MODIFIERS.put(OrePrefix.pipeSmallItem, ImmutableList.of(
                MetaItems.SHAPE_EXTRUDER_PIPE_SMALL.getStackForm()));
        PREFIX_MODIFIERS.put(OrePrefix.pipeSmallFluid, ImmutableList.of(
                MetaItems.SHAPE_EXTRUDER_PIPE_SMALL.getStackForm()));
        PREFIX_MODIFIERS.put(OrePrefix.pipeNormalItem, ImmutableList.of(
                MetaItems.SHAPE_EXTRUDER_PIPE_NORMAL.getStackForm()));
        PREFIX_MODIFIERS.put(OrePrefix.pipeNormalFluid, ImmutableList.of(
                MetaItems.SHAPE_EXTRUDER_PIPE_NORMAL.getStackForm()));
        PREFIX_MODIFIERS.put(OrePrefix.pipeLargeItem, ImmutableList.of(
                MetaItems.SHAPE_EXTRUDER_PIPE_LARGE.getStackForm()));
        PREFIX_MODIFIERS.put(OrePrefix.pipeLargeFluid, ImmutableList.of(
                MetaItems.SHAPE_EXTRUDER_PIPE_LARGE.getStackForm()));
        PREFIX_MODIFIERS.put(OrePrefix.pipeHugeItem, ImmutableList.of(
                MetaItems.SHAPE_EXTRUDER_PIPE_HUGE.getStackForm()));
        PREFIX_MODIFIERS.put(OrePrefix.pipeHugeFluid, ImmutableList.of(
                MetaItems.SHAPE_EXTRUDER_PIPE_HUGE.getStackForm()));
    }

    private LinearForgingFurnaceLoader() {}

    public static void registerLate() {
        GCYMLog.logger.info("Registering Linear Forging Furnace recipes...");
        populateReferences();
        long time = System.currentTimeMillis();
        registerCooled();
        GCYMLog.logger.debug(String.format("Registered %s cooled recipes in %s milliseconds.", count,
                System.currentTimeMillis() - time));
        count = 0;
        time = System.currentTimeMillis();
        registerForgingCooled();
        GCYMLog.logger.debug(String.format("Registered %s cooled forging recipes in %s milliseconds.", count,
                System.currentTimeMillis() - time));
        time = System.currentTimeMillis();
        assembleCompositeMaps();
        GCYMLog.logger.debug(String.format("Assembled composite maps in %s milliseconds.",
                System.currentTimeMillis() - time));
    }

    private static void registerCooled() {
        RecipeMap<BlastRecipeBuilder> map = (RecipeMap<BlastRecipeBuilder>) GCYMRecipeMaps.LINEAR_FORGING_RECIPES[4];
        GTRecipeCategory category = GTRecipeCategory.getByName(map.getUnlocalizedName());
        if (category == null)
            throw new IllegalStateException("Component recipemap " + map.getUnlocalizedName() + " had no a category!");
        for (Recipe blastRecipe : RecipeMaps.BLAST_RECIPES.getRecipeList()) {
            var recipe = produceCooledRecipe(blastRecipe, map, category);
            if (recipe != null) {
                count++;
                recipe.buildAndRegister();
            }
        }

        map = (RecipeMap<BlastRecipeBuilder>) GCYMRecipeMaps.LINEAR_FORGING_RECIPES[5];
        category = GTRecipeCategory.getByName(map.getUnlocalizedName());
        if (category == null)
            throw new IllegalStateException("Component recipemap " + map.getUnlocalizedName() + " had no a category!");
        for (Recipe blastRecipe : GCYMRecipeMaps.ALLOY_BLAST_RECIPES.getRecipeList()) {
            var recipe = produceCooledRecipe(blastRecipe, map, category);
            if (recipe != null) {
                count++;
                recipe.buildAndRegister();
            }
        }
    }

    private static @Nullable BlastRecipeBuilder produceCooledRecipe(Recipe blastRecipe,
                                                                    RecipeMap<BlastRecipeBuilder> targetMap,
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
        if (freezerRecipe == null) {
            GTRecipeCategory category = GTRecipeCategory.getByName(targetMap.getUnlocalizedName());
            if (category != null)
                return new BlastRecipeBuilder(blastRecipe, targetMap).category(category);
            return null;
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
                if (simplestRatio.getLeft() * simplestRatio.getRight() >
                        MAXIMUM_SEARCH_MULTIPLIER * MAXIMUM_SEARCH_MULTIPLIER / 2)
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
                if (simplestRatio.getLeft() * simplestRatio.getRight() >
                        MAXIMUM_SEARCH_MULTIPLIER * MAXIMUM_SEARCH_MULTIPLIER / 2)
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
        List<ChancedItemOutput> chancedOutputs = new ObjectArrayList<>();
        blastRecipe.getChancedOutputs().getChancedEntries().forEach(a -> {
            var stack = a.getIngredient().copy();
            stack.setCount(stack.getCount() * finalBlastRepetitions);
            chancedOutputs.add(new ChancedItemOutput(stack, a.getChance(), a.getChanceBoost()));
        });
        freezerRecipe.getChancedOutputs().getChancedEntries().forEach(a -> {
            var stack = a.getIngredient().copy();
            stack.setCount(stack.getCount() * finalFreezerRepetitions);
            chancedOutputs.add(new ChancedItemOutput(stack, a.getChance(), a.getChanceBoost()));
        });
        List<ChancedFluidOutput> chancedFluidOutputs = new ObjectArrayList<>();
        blastRecipe.getChancedFluidOutputs().getChancedEntries().forEach(a -> {
            var stack = a.getIngredient().copy();
            stack.amount *= finalBlastRepetitions;
            chancedFluidOutputs.add(new ChancedFluidOutput(stack, a.getChance(), a.getChanceBoost()));
        });
        freezerRecipe.getChancedFluidOutputs().getChancedEntries().forEach(a -> {
            var stack = a.getIngredient().copy();
            stack.amount *= finalFreezerRepetitions;
            chancedFluidOutputs.add(new ChancedFluidOutput(stack, a.getChance(), a.getChanceBoost()));
        });

        // freezer/blast OCs to reach new voltage are perfect, that's one of the benefits.
        double newEU = (double) blastRecipe.getEUt() * blastRecipe.getDuration() * blastRepetitions +
                (double) freezerRecipe.getEUt() * freezerRecipe.getDuration() * freezerRepetitions;
        int newEUt = Math.max(blastRecipe.getEUt(), freezerRecipe.getEUt());

        // builder from the blast recipe to copy all properties we don't later override.
        var builder = new BlastRecipeBuilder(blastRecipe, targetMap)
                .clearInputs().inputs(finalizedRI(compositeInputs).toArray(new GTRecipeInput[] {}))
                .clearFluidInputs().fluidInputs(finalizedRI(compositeFluidInputs))
                .clearOutputs().outputs(finalizedIS(compositeOutputs))
                .clearFluidOutputs().fluidOutputs(finalizedFS(compositeFluidOutputs))
                .clearChancedOutput().chancedOutputs(chancedOutputs)
                .clearChancedFluidOutputs().chancedFluidOutputs(chancedFluidOutputs)
                .category(mapCategory)
                .EUt(newEUt)
                .duration((int) (Math.ceil(newEU / newEUt) *
                        GCYMConfigHolder.linearForgingFurnaceSettings.coolingDurationModifier));

        int temp = blastRecipe.getProperty(TemperatureProperty.getInstance(), 0);
        builder.applyProperty(TemperatureProperty.getInstance(), null);
        builder.applyProperty(TemperatureProperty.getInstance(), temp +
                GCYMConfigHolder.linearForgingFurnaceSettings.coolingTemperaturePenalty);
        return builder;
    }

    private static void registerForgingCooled() {
        RecipeMap<BlastRecipeBuilder> map = (RecipeMap<BlastRecipeBuilder>) GCYMRecipeMaps.LINEAR_FORGING_RECIPES[7];
        GTRecipeCategory category = GTRecipeCategory.getByName(map.getUnlocalizedName());
        if (category == null)
            throw new IllegalStateException("Component recipemap " + map.getUnlocalizedName() + " had no a category!");
        for (Recipe blastRecipe : RecipeMaps.BLAST_RECIPES.getRecipeList()) {
            for (RecipeBuilder<?> recipeBuilder : produceForgingCooledRecipes(blastRecipe, map, category)) {
                count++;
                recipeBuilder.buildAndRegister();
            }
        }

        map = (RecipeMap<BlastRecipeBuilder>) GCYMRecipeMaps.LINEAR_FORGING_RECIPES[8];
        category = GTRecipeCategory.getByName(map.getUnlocalizedName());
        if (category == null)
            throw new IllegalStateException("Component recipemap " + map.getUnlocalizedName() + " had no a category!");
        for (Recipe blastRecipe : GCYMRecipeMaps.ALLOY_BLAST_RECIPES.getRecipeList()) {
            for (RecipeBuilder<?> recipeBuilder : produceForgingCooledRecipes(blastRecipe, map, category)) {
                count++;
                recipeBuilder.buildAndRegister();
            }
        }
    }

    private static List<RecipeBuilder<?>> produceForgingCooledRecipes(Recipe blastRecipe,
                                                                      RecipeMap<BlastRecipeBuilder> targetMap,
                                                                      @NotNull GTRecipeCategory mapCategory) {
        for (GTRecipeInput input : blastRecipe.getInputs()) {
            var stacks = input.getInputStacks();
            if (stacks != null) {
                var mat = OreDictUnifier.getMaterial(stacks[0]);
                if (mat != null) {
                    if (mat.material.hasFlag(GCYMMaterialFlags.NO_FORGING_RECIPES_IN)) return EMPTY_BUILDER_LIST;
                }
            }
        }
        // also check fluid inputs? How to even do that?

        RecipeBuilder<BlastRecipeBuilder> baseBuilder = produceCooledRecipe(blastRecipe, targetMap, mapCategory);
        if (baseBuilder == null) return EMPTY_BUILDER_LIST;
        baseBuilder.duration((int) (baseBuilder.getDuration() *
                GCYMConfigHolder.linearForgingFurnaceSettings.forgingDurationModifier));
        int temp = baseBuilder.copy().build().getResult().getProperty(TemperatureProperty.getInstance(), 0);
        baseBuilder.applyProperty(TemperatureProperty.getInstance(), null);
        baseBuilder.applyProperty(TemperatureProperty.getInstance(), temp +
                GCYMConfigHolder.linearForgingFurnaceSettings.forgingTemperaturePenalty);
        List<RecipeBuilder<?>> builders = new ObjectArrayList<>();
        for (var pair : PREFIX_MODIFIERS.entrySet()) {
            var builder = attemptForgingCooledRecipe(pair.getKey(), baseBuilder.copy());
            if (builder != null) {
                for (ItemStack stack : pair.getValue()) {
                    builder.notConsumable(stack);
                }
                builders.add(builder);
            }
        }

        return builders;
    }

    private static @Nullable RecipeBuilder<?> attemptForgingCooledRecipe(OrePrefix prefix,
                                                                         RecipeBuilder<?> baseBuilder) {
        Set<Integer> multipliers = new ObjectOpenHashSet<>();
        for (var stack : baseBuilder.getOutputs()) {
            var mat = OreDictUnifier.getMaterial(stack);
            var stackPrefix = OreDictUnifier.getPrefix(stack);
            if (mat != null && stackPrefix != null) {
                if (mat.material.hasFlag(GCYMMaterialFlags.NO_FORGING_OUT)) continue;

                long cost = prefix.getMaterialAmount(mat.material);
                long provided = stackPrefix.getMaterialAmount(mat.material) * stack.getCount();
                multipliers.add(findMultiplier(cost, provided));
            }
        }
        Optional<Integer> ia = multipliers.stream().reduce(LinearForgingFurnaceLoader::lcm);
        // noinspection SimplifyOptionalCallChains
        if (!ia.isPresent()) return null;
        int inputMultiplier = Math.max(ia.get(), 1);

        RecipeBuilder<?> internalBaseBuilder = baseBuilder.copy();

        internalBaseBuilder.duration(baseBuilder.getDuration() * inputMultiplier);

        internalBaseBuilder.clearInputs().inputs(finalizedRI(baseBuilder.getInputs().stream()
                .map(a -> a.copyWithAmount(a.getAmount() * inputMultiplier))
                .collect(Collectors.toList())).toArray(new GTRecipeInput[] {}));
        internalBaseBuilder.clearFluidInputs().fluidInputs(finalizedRI(baseBuilder.getFluidInputs().stream()
                .map(a -> a.copyWithAmount(a.getAmount() * inputMultiplier))
                .collect(Collectors.toList())));
        internalBaseBuilder.clearFluidOutputs().fluidOutputs(baseBuilder.getFluidOutputs().stream()
                .map(a -> {
                    FluidStack b = a.copy();
                    b.amount *= inputMultiplier;
                    return b;
                }).collect(Collectors.toList()));
        internalBaseBuilder.clearChancedOutput().chancedOutputs(baseBuilder.getChancedOutputs().stream()
                .map(a -> {
                    var stack = a.getIngredient().copy();
                    stack.setCount(stack.getCount() * inputMultiplier);
                    return new ChancedItemOutput(stack, a.getChance(), a.getChanceBoost());
                }).collect(Collectors.toList()));
        internalBaseBuilder.clearChancedFluidOutputs().chancedFluidOutputs(baseBuilder.getChancedFluidOutputs().stream()
                .map(a -> {
                    var stack = a.getIngredient().copy();
                    stack.amount *= inputMultiplier;
                    return new ChancedFluidOutput(stack, a.getChance(), a.getChanceBoost());
                }).collect(Collectors.toList()));

        internalBaseBuilder.clearOutputs();
        boolean changed = false;
        for (ItemStack stack : baseBuilder.getOutputs()) {
            var mat = OreDictUnifier.getMaterial(stack);
            var stackPrefix = OreDictUnifier.getPrefix(stack);
            if (mat != null && stackPrefix != null) {
                boolean flag = mat.material.hasFlag(GCYMMaterialFlags.NO_FORGING_OUT);
                var newStack = flag ? ItemStack.EMPTY : OreDictUnifier.get(prefix, mat.material);
                if (newStack == ItemStack.EMPTY) internalBaseBuilder.output(stack.getItem(), stack.getCount());
                else {
                    internalBaseBuilder.output(prefix, mat.material, (int) (inputMultiplier * stack.getCount() *
                            stackPrefix.getMaterialAmount(mat.material) / prefix.getMaterialAmount(mat.material)));
                    changed = true;
                }
            }
        }
        return changed ? internalBaseBuilder : null;
    }

    public static void assembleCompositeMaps() {
        for (Recipe recipe : RecipeMaps.BLAST_RECIPES.getRecipeList()) {
            assignRecipeType(recipe, LinearForgingFurnaceRecipeType.BLAST);
            buildToMap(recipe, GCYMRecipeMaps.LINEAR_FORGING_RECIPES[2]);
        }
        for (Recipe recipe : GCYMRecipeMaps.ALLOY_BLAST_RECIPES.getRecipeList()) {
            assignRecipeType(recipe, LinearForgingFurnaceRecipeType.ALLOY);
            buildToMap(recipe, GCYMRecipeMaps.LINEAR_FORGING_RECIPES[2]);
        }

        for (Recipe recipe : GCYMRecipeMaps.LINEAR_FORGING_RECIPES[4].getRecipeList()) {
            assignRecipeType(recipe, LinearForgingFurnaceRecipeType.BLAST_COOLED);
            buildToMap(recipe, GCYMRecipeMaps.LINEAR_FORGING_RECIPES[6]);
        }
        for (Recipe recipe : GCYMRecipeMaps.LINEAR_FORGING_RECIPES[5].getRecipeList()) {
            assignRecipeType(recipe, LinearForgingFurnaceRecipeType.ALLOY_COOLED);
            buildToMap(recipe, GCYMRecipeMaps.LINEAR_FORGING_RECIPES[6]);
        }

        for (Recipe recipe : GCYMRecipeMaps.LINEAR_FORGING_RECIPES[7].getRecipeList()) {
            assignRecipeType(recipe, LinearForgingFurnaceRecipeType.BLAST_FORGING_COOLED);
            buildToMap(recipe, GCYMRecipeMaps.LINEAR_FORGING_RECIPES[9]);
        }
        for (Recipe recipe : GCYMRecipeMaps.LINEAR_FORGING_RECIPES[8].getRecipeList()) {
            assignRecipeType(recipe, LinearForgingFurnaceRecipeType.ALLOY_FORGING_COOLED);
            buildToMap(recipe, GCYMRecipeMaps.LINEAR_FORGING_RECIPES[9]);
        }
    }

    private static void assignRecipeType(Recipe recipe, LinearForgingFurnaceRecipeType type) {
        IRecipePropertyStorage storage = recipe.getRecipePropertyStorage();
        storage.freeze(false); // is this illegal?
        storage.store(LFFRecipeTypeProperty.getInstance(), type);
        storage.freeze(true);
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
        return list.stream().peek(a -> {
            if (a instanceof GTRecipeItemInput itemInput) {
                for (var stack : itemInput.getInputStacks()) {
                    if (stack.getItem() != MetaItems.SHAPE_MOLD_INGOT.getMetaItem())
                        stack.setCount(a.getAmount());
                    else {
                        a.withAmount(0);
                    }
                }
            } else if (a instanceof GTRecipeFluidInput fluidInput) {
                fluidInput.getInputFluidStack().amount = a.getAmount();
            }
        }).filter(a -> a.getAmount() != 0).collect(Collectors.toList());
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

    /**
     * Finds the smallest whole multiplier that allows the provided quantity to satisfy the cost with a whole output.
     */
    private static int findMultiplier(long cost, long provided) {
        long ratio = provided * GTValues.M / cost;
        long frac = ratio % GTValues.M;
        // case whole number
        if (frac == 0) return 1;
        long denominator = GTValues.M * GTValues.M / frac;
        long numerator = ratio * denominator / GTValues.M;
        long gcd = gcd(numerator, denominator);
        return (int) (denominator / gcd);
    }

    private static int gcd(int a, int b) {
        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }

    private static long gcd(long a, long b) {
        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }

    private static int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }
}
