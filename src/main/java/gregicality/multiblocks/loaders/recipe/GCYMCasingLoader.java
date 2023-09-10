package gregicality.multiblocks.loaders.recipe;

import gregtech.api.GTValues;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.items.MetaItems;

import gregicality.multiblocks.api.unification.GCYMMaterials;
import gregicality.multiblocks.common.block.GCYMMetaBlocks;
import gregicality.multiblocks.common.block.blocks.BlockLargeMultiblockCasing;
import gregicality.multiblocks.common.block.blocks.BlockUniqueCasing;

public final class GCYMCasingLoader {

    private GCYMCasingLoader() {}

    public static void init() {
        // Multiblock Casings
        ModHandler.addShapedRecipe(true, "casing_large_macerator",
                GCYMMetaBlocks.LARGE_MULTIBLOCK_CASING
                        .getItemVariant(BlockLargeMultiblockCasing.CasingType.MACERATOR_CASING, 2),
                "PhP", "PFP", "PwP", 'P', new UnificationEntry(OrePrefix.plate, GCYMMaterials.Zeron100), 'F',
                new UnificationEntry(OrePrefix.frameGt, Materials.Titanium));
        ModHandler.addShapedRecipe(true, "casing_high_temperature",
                GCYMMetaBlocks.LARGE_MULTIBLOCK_CASING
                        .getItemVariant(BlockLargeMultiblockCasing.CasingType.HIGH_TEMPERATURE_CASING, 2),
                "DhD", "PFP", "DwD", 'P', new UnificationEntry(OrePrefix.plate, GCYMMaterials.TitaniumCarbide), 'D',
                new UnificationEntry(OrePrefix.plate, GCYMMaterials.HSLASteel), 'F',
                new UnificationEntry(OrePrefix.frameGt, Materials.TungstenCarbide));
        ModHandler.addShapedRecipe(true, "casing_large_assembler",
                GCYMMetaBlocks.LARGE_MULTIBLOCK_CASING
                        .getItemVariant(BlockLargeMultiblockCasing.CasingType.ASSEMBLING_CASING, 2),
                "PhP", "PFP", "PwP", 'P', new UnificationEntry(OrePrefix.plate, GCYMMaterials.Stellite100), 'F',
                new UnificationEntry(OrePrefix.frameGt, Materials.Tungsten));
        ModHandler.addShapedRecipe(true, "casing_stress_proof",
                GCYMMetaBlocks.LARGE_MULTIBLOCK_CASING
                        .getItemVariant(BlockLargeMultiblockCasing.CasingType.STRESS_PROOF_CASING, 2),
                "PhP", "PFP", "PwP", 'P', new UnificationEntry(OrePrefix.plate, GCYMMaterials.MaragingSteel300), 'F',
                new UnificationEntry(OrePrefix.frameGt, Materials.StainlessSteel));
        ModHandler.addShapedRecipe(true, "casing_corrosion_proof",
                GCYMMetaBlocks.LARGE_MULTIBLOCK_CASING
                        .getItemVariant(BlockLargeMultiblockCasing.CasingType.CORROSION_PROOF_CASING, 2),
                "PhP", "PFP", "PwP", 'P', new UnificationEntry(OrePrefix.plate, Materials.CobaltBrass), 'F',
                new UnificationEntry(OrePrefix.frameGt, GCYMMaterials.HSLASteel));
        ModHandler.addShapedRecipe(true, "casing_vibration_safe",
                GCYMMetaBlocks.LARGE_MULTIBLOCK_CASING
                        .getItemVariant(BlockLargeMultiblockCasing.CasingType.VIBRATION_SAFE_CASING, 2),
                "PhP", "PFP", "PwP", 'P', new UnificationEntry(OrePrefix.plate, GCYMMaterials.IncoloyMA956), 'F',
                new UnificationEntry(OrePrefix.frameGt, GCYMMaterials.IncoloyMA956));
        ModHandler.addShapedRecipe(true, "casing_watertight",
                GCYMMetaBlocks.LARGE_MULTIBLOCK_CASING
                        .getItemVariant(BlockLargeMultiblockCasing.CasingType.WATERTIGHT_CASING, 2),
                "PhP", "PFP", "PwP", 'P', new UnificationEntry(OrePrefix.plate, GCYMMaterials.WatertightSteel), 'F',
                new UnificationEntry(OrePrefix.frameGt, GCYMMaterials.WatertightSteel));
        ModHandler.addShapedRecipe(true, "casing_large_cutter",
                GCYMMetaBlocks.LARGE_MULTIBLOCK_CASING
                        .getItemVariant(BlockLargeMultiblockCasing.CasingType.CUTTER_CASING, 2),
                "PhP", "PFP", "PwP", 'P', new UnificationEntry(OrePrefix.plate, GCYMMaterials.HastelloyC276), 'F',
                new UnificationEntry(OrePrefix.frameGt, GCYMMaterials.HastelloyC276));
        ModHandler.addShapedRecipe(true, "casing_nonconducting",
                GCYMMetaBlocks.LARGE_MULTIBLOCK_CASING
                        .getItemVariant(BlockLargeMultiblockCasing.CasingType.NONCONDUCTING_CASING, 2),
                "PhP", "PFP", "PwP", 'P', new UnificationEntry(OrePrefix.plate, GCYMMaterials.HSLASteel), 'F',
                new UnificationEntry(OrePrefix.frameGt, GCYMMaterials.HSLASteel));
        ModHandler.addShapedRecipe(true, "casing_large_mixer",
                GCYMMetaBlocks.LARGE_MULTIBLOCK_CASING
                        .getItemVariant(BlockLargeMultiblockCasing.CasingType.MIXER_CASING, 2),
                "PhP", "PFP", "PwP", 'P', new UnificationEntry(OrePrefix.plate, GCYMMaterials.HastelloyX), 'F',
                new UnificationEntry(OrePrefix.frameGt, GCYMMaterials.MaragingSteel300));
        ModHandler.addShapedRecipe(true, "casing_large_engraver",
                GCYMMetaBlocks.LARGE_MULTIBLOCK_CASING
                        .getItemVariant(BlockLargeMultiblockCasing.CasingType.ENGRAVER_CASING, 2),
                "PhP", "PFP", "PwP", 'P', new UnificationEntry(OrePrefix.plate, GCYMMaterials.TitaniumTungstenCarbide),
                'F', new UnificationEntry(OrePrefix.frameGt, Materials.Titanium));
        ModHandler.addShapedRecipe(true, "casing_atomic",
                GCYMMetaBlocks.LARGE_MULTIBLOCK_CASING
                        .getItemVariant(BlockLargeMultiblockCasing.CasingType.ATOMIC_CASING, 2),
                "PhP", "PFP", "PwP", 'P', new UnificationEntry(OrePrefix.plateDouble, GCYMMaterials.Trinaquadalloy),
                'F', new UnificationEntry(OrePrefix.frameGt, Materials.NaquadahAlloy));
        ModHandler.addShapedRecipe(true, "casing_steam",
                GCYMMetaBlocks.LARGE_MULTIBLOCK_CASING
                        .getItemVariant(BlockLargeMultiblockCasing.CasingType.STEAM_CASING, 2),
                "PhP", "PFP", "PwP", 'P', new UnificationEntry(OrePrefix.plate, Materials.Brass), 'F',
                new UnificationEntry(OrePrefix.frameGt, Materials.Brass));

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(OrePrefix.plate, GCYMMaterials.Zeron100, 6)
                .input(OrePrefix.frameGt, Materials.Titanium)
                .notConsumable(new IntCircuitIngredient(6))
                .outputs(GCYMMetaBlocks.LARGE_MULTIBLOCK_CASING
                        .getItemVariant(BlockLargeMultiblockCasing.CasingType.MACERATOR_CASING, 2))
                .duration(50).EUt(16).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(OrePrefix.plate, GCYMMaterials.HSLASteel, 4)
                .input(OrePrefix.plate, GCYMMaterials.TitaniumCarbide, 2)
                .input(OrePrefix.frameGt, Materials.TungstenCarbide)
                .notConsumable(new IntCircuitIngredient(6))
                .outputs(GCYMMetaBlocks.LARGE_MULTIBLOCK_CASING
                        .getItemVariant(BlockLargeMultiblockCasing.CasingType.HIGH_TEMPERATURE_CASING, 2))
                .duration(50).EUt(16).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(OrePrefix.plate, GCYMMaterials.Stellite100, 6)
                .input(OrePrefix.frameGt, Materials.Tungsten)
                .notConsumable(new IntCircuitIngredient(6))
                .outputs(GCYMMetaBlocks.LARGE_MULTIBLOCK_CASING
                        .getItemVariant(BlockLargeMultiblockCasing.CasingType.ASSEMBLING_CASING, 2))
                .duration(50).EUt(16).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(OrePrefix.plate, GCYMMaterials.MaragingSteel300, 6)
                .input(OrePrefix.frameGt, Materials.StainlessSteel)
                .notConsumable(new IntCircuitIngredient(6))
                .outputs(GCYMMetaBlocks.LARGE_MULTIBLOCK_CASING
                        .getItemVariant(BlockLargeMultiblockCasing.CasingType.STRESS_PROOF_CASING, 2))
                .duration(50).EUt(16).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(OrePrefix.plate, Materials.CobaltBrass, 6)
                .input(OrePrefix.frameGt, GCYMMaterials.HSLASteel)
                .notConsumable(new IntCircuitIngredient(6))
                .outputs(GCYMMetaBlocks.LARGE_MULTIBLOCK_CASING
                        .getItemVariant(BlockLargeMultiblockCasing.CasingType.CORROSION_PROOF_CASING, 2))
                .duration(50).EUt(16).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(OrePrefix.plate, GCYMMaterials.IncoloyMA956, 6)
                .input(OrePrefix.frameGt, GCYMMaterials.IncoloyMA956)
                .notConsumable(new IntCircuitIngredient(6))
                .outputs(GCYMMetaBlocks.LARGE_MULTIBLOCK_CASING
                        .getItemVariant(BlockLargeMultiblockCasing.CasingType.VIBRATION_SAFE_CASING, 2))
                .duration(50).EUt(16).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(OrePrefix.plate, GCYMMaterials.WatertightSteel, 6)
                .input(OrePrefix.frameGt, GCYMMaterials.WatertightSteel)
                .notConsumable(new IntCircuitIngredient(6))
                .outputs(GCYMMetaBlocks.LARGE_MULTIBLOCK_CASING
                        .getItemVariant(BlockLargeMultiblockCasing.CasingType.WATERTIGHT_CASING, 2))
                .duration(50).EUt(16).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(OrePrefix.plate, GCYMMaterials.HastelloyC276, 6)
                .input(OrePrefix.frameGt, GCYMMaterials.HastelloyC276)
                .notConsumable(new IntCircuitIngredient(6))
                .outputs(GCYMMetaBlocks.LARGE_MULTIBLOCK_CASING
                        .getItemVariant(BlockLargeMultiblockCasing.CasingType.CUTTER_CASING, 2))
                .duration(50).EUt(16).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(OrePrefix.plate, GCYMMaterials.HSLASteel, 6)
                .input(OrePrefix.frameGt, GCYMMaterials.HSLASteel)
                .notConsumable(new IntCircuitIngredient(6))
                .outputs(GCYMMetaBlocks.LARGE_MULTIBLOCK_CASING
                        .getItemVariant(BlockLargeMultiblockCasing.CasingType.NONCONDUCTING_CASING, 2))
                .duration(50).EUt(16).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(OrePrefix.plate, GCYMMaterials.HastelloyX, 6)
                .input(OrePrefix.frameGt, GCYMMaterials.MaragingSteel300)
                .notConsumable(new IntCircuitIngredient(6))
                .outputs(GCYMMetaBlocks.LARGE_MULTIBLOCK_CASING
                        .getItemVariant(BlockLargeMultiblockCasing.CasingType.MIXER_CASING, 2))
                .duration(50).EUt(16).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(OrePrefix.plate, GCYMMaterials.TitaniumTungstenCarbide, 6)
                .input(OrePrefix.frameGt, Materials.Titanium)
                .notConsumable(new IntCircuitIngredient(6))
                .outputs(GCYMMetaBlocks.LARGE_MULTIBLOCK_CASING
                        .getItemVariant(BlockLargeMultiblockCasing.CasingType.ENGRAVER_CASING, 2))
                .duration(50).EUt(16).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(OrePrefix.plateDouble, GCYMMaterials.Trinaquadalloy, 6)
                .input(OrePrefix.frameGt, Materials.NaquadahAlloy)
                .notConsumable(new IntCircuitIngredient(6))
                .outputs(GCYMMetaBlocks.LARGE_MULTIBLOCK_CASING
                        .getItemVariant(BlockLargeMultiblockCasing.CasingType.ATOMIC_CASING, 2))
                .duration(50).EUt(16).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(OrePrefix.plate, Materials.Brass, 6)
                .input(OrePrefix.frameGt, Materials.Brass)
                .notConsumable(new IntCircuitIngredient(6))
                .outputs(GCYMMetaBlocks.LARGE_MULTIBLOCK_CASING
                        .getItemVariant(BlockLargeMultiblockCasing.CasingType.STEAM_CASING, 2))
                .duration(50).EUt(16).buildAndRegister();

        // Unique Casings
        ModHandler.addShapedRecipe(true, "casing_crushing_wheels",
                GCYMMetaBlocks.UNIQUE_CASING.getItemVariant(BlockUniqueCasing.UniqueCasingType.CRUSHING_WHEELS, 2),
                "SSS", "GCG", "GMG", 'S', new UnificationEntry(OrePrefix.gearSmall, Materials.TungstenCarbide), 'G',
                new UnificationEntry(OrePrefix.gear, Materials.Ultimet), 'C',
                GCYMMetaBlocks.LARGE_MULTIBLOCK_CASING
                        .getItemVariant(BlockLargeMultiblockCasing.CasingType.MACERATOR_CASING),
                'M', MetaItems.ELECTRIC_MOTOR_IV.getStackForm());
        ModHandler.addShapedRecipe(true, "casing_slicing_blades",
                GCYMMetaBlocks.UNIQUE_CASING.getItemVariant(BlockUniqueCasing.UniqueCasingType.SLICING_BLADES, 2),
                "SSS", "GCG", "GMG", 'S', new UnificationEntry(OrePrefix.plate, Materials.TungstenCarbide), 'G',
                new UnificationEntry(OrePrefix.gear, Materials.Ultimet), 'C',
                GCYMMetaBlocks.LARGE_MULTIBLOCK_CASING
                        .getItemVariant(BlockLargeMultiblockCasing.CasingType.CUTTER_CASING),
                'M', MetaItems.ELECTRIC_MOTOR_IV.getStackForm());
        ModHandler.addShapedRecipe(true, "casing_electrolytic_cell",
                GCYMMetaBlocks.UNIQUE_CASING.getItemVariant(BlockUniqueCasing.UniqueCasingType.ELECTROLYTIC_CELL, 2),
                "WWW", "WCW", "KAK", 'W', new UnificationEntry(OrePrefix.wireGtDouble, Materials.Platinum), 'C',
                GCYMMetaBlocks.LARGE_MULTIBLOCK_CASING
                        .getItemVariant(BlockLargeMultiblockCasing.CasingType.NONCONDUCTING_CASING),
                'K', new UnificationEntry(OrePrefix.circuit, MarkerMaterials.Tier.IV), 'A',
                new UnificationEntry(OrePrefix.cableGtSingle, Materials.Tungsten));
        ModHandler.addShapedRecipe(true, "casing_heat_vent",
                GCYMMetaBlocks.UNIQUE_CASING.getItemVariant(BlockUniqueCasing.UniqueCasingType.HEAT_VENT, 2), "PDP",
                "RLR", "PDP", 'P', new UnificationEntry(OrePrefix.plate, GCYMMaterials.TantalumCarbide), 'D',
                new UnificationEntry(OrePrefix.plateDouble, GCYMMaterials.MolybdenumDisilicide), 'R',
                new UnificationEntry(OrePrefix.rotor, Materials.Titanium), 'L',
                new UnificationEntry(OrePrefix.stickLong, GCYMMaterials.MolybdenumDisilicide));

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(OrePrefix.gearSmall, Materials.TungstenCarbide, 3)
                .input(OrePrefix.gear, Materials.Ultimet, 4)
                .inputs(MetaItems.ELECTRIC_MOTOR_IV.getStackForm())
                .inputs(GCYMMetaBlocks.LARGE_MULTIBLOCK_CASING
                        .getItemVariant(BlockLargeMultiblockCasing.CasingType.MACERATOR_CASING))
                .outputs(GCYMMetaBlocks.UNIQUE_CASING.getItemVariant(BlockUniqueCasing.UniqueCasingType.CRUSHING_WHEELS,
                        2))
                .duration(50).EUt(16).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(OrePrefix.plate, Materials.TungstenCarbide, 3)
                .input(OrePrefix.gear, Materials.Ultimet, 4)
                .inputs(MetaItems.ELECTRIC_MOTOR_IV.getStackForm())
                .inputs(GCYMMetaBlocks.LARGE_MULTIBLOCK_CASING
                        .getItemVariant(BlockLargeMultiblockCasing.CasingType.CUTTER_CASING))
                .outputs(GCYMMetaBlocks.UNIQUE_CASING.getItemVariant(BlockUniqueCasing.UniqueCasingType.SLICING_BLADES,
                        2))
                .duration(50).EUt(16).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(OrePrefix.wireGtDouble, Materials.Platinum, 5)
                .input(OrePrefix.circuit, MarkerMaterials.Tier.IV, 2)
                .input(OrePrefix.cableGtSingle, Materials.Tungsten)
                .inputs(GCYMMetaBlocks.LARGE_MULTIBLOCK_CASING
                        .getItemVariant(BlockLargeMultiblockCasing.CasingType.NONCONDUCTING_CASING))
                .outputs(GCYMMetaBlocks.UNIQUE_CASING
                        .getItemVariant(BlockUniqueCasing.UniqueCasingType.ELECTROLYTIC_CELL, 2))
                .duration(50).EUt(16).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(OrePrefix.plate, GCYMMaterials.TantalumCarbide, 4)
                .input(OrePrefix.rotor, Materials.Titanium, 2)
                .input(OrePrefix.plateDouble, GCYMMaterials.MolybdenumDisilicide, 2)
                .input(OrePrefix.stickLong, GCYMMaterials.MolybdenumDisilicide)
                .outputs(GCYMMetaBlocks.UNIQUE_CASING.getItemVariant(BlockUniqueCasing.UniqueCasingType.HEAT_VENT, 2))
                .duration(50).EUt(16).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(OrePrefix.ring, GCYMMaterials.MolybdenumDisilicide, 32)
                .input(OrePrefix.foil, Materials.Graphene, 16)
                .fluidInputs(GCYMMaterials.HSLASteel.getFluid(GTValues.L))
                .outputs(GCYMMetaBlocks.UNIQUE_CASING
                        .getItemVariant(BlockUniqueCasing.UniqueCasingType.MOLYBDENUM_DISILICIDE_COIL))
                .duration(500).EUt(GTValues.VA[GTValues.EV]).buildAndRegister();
    }
}
