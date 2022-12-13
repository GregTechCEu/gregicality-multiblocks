package gregicality.multiblocks.loaders;

import com.google.common.collect.ImmutableSet;
import gregicality.multiblocks.api.metatileentity.ITieredHatch;
import gregicality.multiblocks.api.utils.GCYMUtility;
import gregicality.multiblocks.common.block.GCYMMetaBlocks;
import gregicality.multiblocks.common.block.blocks.BlockUniqueCasing;
import gregicality.multiblocks.common.metatileentities.GCYMMetaTileEntities;
import gregtech.api.recipes.ingredients.GTRecipeOreInput;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.items.MetaItems;

import static gregtech.api.GTValues.*;

public final class TieredHatchLoader {

    public static void register() {
        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_MACERATOR, LuV,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        MetaItems.ELECTRIC_MOTOR_LuV.getStackForm(2),
                        MetaItems.ELECTRIC_PISTON_LUV.getStackForm(2),
                        MetaItems.COMPONENT_GRINDER_TUNGSTEN.getStackForm()
                )));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_MACERATOR, ZPM,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        MetaItems.ELECTRIC_MOTOR_ZPM.getStackForm(2),
                        MetaItems.ELECTRIC_PISTON_ZPM.getStackForm(2),
                        MetaItems.COMPONENT_GRINDER_TUNGSTEN.getStackForm()
                )));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_MACERATOR, UV,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        MetaItems.ELECTRIC_MOTOR_UV.getStackForm(2),
                        MetaItems.ELECTRIC_PISTON_UV.getStackForm(2),
                        MetaItems.COMPONENT_GRINDER_TUNGSTEN.getStackForm()
                )));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_ARC_FURNACE, LuV,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        MetaItems.ELECTRIC_PUMP_LuV.getStackForm(),
                        OreDictUnifier.get(OrePrefix.plate, Materials.RhodiumPlatedPalladium, 4)
                )));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_ARC_FURNACE, ZPM,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        MetaItems.ELECTRIC_PUMP_ZPM.getStackForm(),
                        OreDictUnifier.get(OrePrefix.plate, Materials.NaquadahAlloy, 4)
                )));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_ARC_FURNACE, UV,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        MetaItems.ELECTRIC_PUMP_UV.getStackForm(),
                        OreDictUnifier.get(OrePrefix.plate, Materials.Darmstadtium, 4)
                )));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_ASSEMBLER, LuV,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        MetaItems.ROBOT_ARM_LuV.getStackForm(2),
                        MetaItems.CONVEYOR_MODULE_LuV.getStackForm(2)
                )),
                GTRecipeOreInput.getOrCreate(OrePrefix.circuit, MarkerMaterials.Tier.LuV));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_ASSEMBLER, ZPM,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        MetaItems.ROBOT_ARM_ZPM.getStackForm(2),
                        MetaItems.CONVEYOR_MODULE_ZPM.getStackForm(2)
                )),
                GTRecipeOreInput.getOrCreate(OrePrefix.circuit, MarkerMaterials.Tier.ZPM));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_ASSEMBLER, UV,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        MetaItems.ROBOT_ARM_UV.getStackForm(2),
                        MetaItems.CONVEYOR_MODULE_UV.getStackForm(2)
                )),
                GTRecipeOreInput.getOrCreate(OrePrefix.circuit, MarkerMaterials.Tier.UV));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_AUTOCLAVE, LuV,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        MetaItems.ELECTRIC_PUMP_LuV.getStackForm(),
                        MetaItems.CONVEYOR_MODULE_LuV.getStackForm(),
                        OreDictUnifier.get(OrePrefix.rotor, Materials.RhodiumPlatedPalladium)
                )));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_AUTOCLAVE, ZPM,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        MetaItems.ELECTRIC_PUMP_ZPM.getStackForm(),
                        MetaItems.CONVEYOR_MODULE_ZPM.getStackForm(),
                        OreDictUnifier.get(OrePrefix.rotor, Materials.NaquadahAlloy)
                )));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_AUTOCLAVE, UV,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        MetaItems.ELECTRIC_PUMP_UV.getStackForm(),
                        MetaItems.CONVEYOR_MODULE_UV.getStackForm(),
                        OreDictUnifier.get(OrePrefix.rotor, Materials.Darmstadtium)
                )));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_BENDER, LuV,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        MetaItems.ELECTRIC_PISTON_LUV.getStackForm(2)
                )));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_BENDER, ZPM,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        MetaItems.ELECTRIC_PISTON_ZPM.getStackForm(2)
                )));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_BENDER, UV,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        MetaItems.ELECTRIC_PISTON_UV.getStackForm(2)
                )));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_BREWERY, LuV,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        MetaItems.ELECTRIC_PUMP_LuV.getStackForm(2),
                        OreDictUnifier.get(OrePrefix.rotor, Materials.RhodiumPlatedPalladium)
                )));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_BREWERY, ZPM,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        MetaItems.ELECTRIC_PUMP_ZPM.getStackForm(2),
                        OreDictUnifier.get(OrePrefix.rotor, Materials.NaquadahAlloy)
                )));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_BREWERY, UV,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        MetaItems.ELECTRIC_PUMP_UV.getStackForm(2),
                        OreDictUnifier.get(OrePrefix.rotor, Materials.Darmstadtium)
                )));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_CENTRIFUGE, LuV,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        MetaItems.ELECTRIC_MOTOR_LuV.getStackForm(2)
                )));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_CENTRIFUGE, ZPM,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        MetaItems.ELECTRIC_MOTOR_ZPM.getStackForm(2)
                )));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_CENTRIFUGE, UV,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        MetaItems.ELECTRIC_MOTOR_UV.getStackForm(2)
                )));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_CHEMICAL_BATH, LuV,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        MetaItems.ELECTRIC_PUMP_LuV.getStackForm(),
                        MetaItems.CONVEYOR_MODULE_LuV.getStackForm()
                )));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_CHEMICAL_BATH, ZPM,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        MetaItems.ELECTRIC_PUMP_ZPM.getStackForm(),
                        MetaItems.CONVEYOR_MODULE_ZPM.getStackForm()
                )));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_CHEMICAL_BATH, UV,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        MetaItems.ELECTRIC_PUMP_UV.getStackForm(),
                        MetaItems.CONVEYOR_MODULE_UV.getStackForm()
                )));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_EXTRACTOR, LuV,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        MetaItems.ELECTRIC_PUMP_LuV.getStackForm(),
                        MetaItems.ELECTRIC_PISTON_LUV.getStackForm()
                )));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_EXTRACTOR, ZPM,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        MetaItems.ELECTRIC_PUMP_ZPM.getStackForm(),
                        MetaItems.ELECTRIC_PISTON_ZPM.getStackForm()
                )));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_EXTRACTOR, UV,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        MetaItems.ELECTRIC_PUMP_UV.getStackForm(),
                        MetaItems.ELECTRIC_PISTON_UV.getStackForm()
                )));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_CUTTER, LuV,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        MetaItems.ELECTRIC_MOTOR_LuV.getStackForm(),
                        MetaItems.CONVEYOR_MODULE_LuV.getStackForm(),
                        OreDictUnifier.get(OrePrefix.toolHeadBuzzSaw, Materials.HSSS)
                )));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_CUTTER, ZPM,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        MetaItems.ELECTRIC_MOTOR_ZPM.getStackForm(),
                        MetaItems.CONVEYOR_MODULE_ZPM.getStackForm(),
                        OreDictUnifier.get(OrePrefix.toolHeadBuzzSaw, Materials.Duranium)
                )));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_CUTTER, UV,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        MetaItems.ELECTRIC_MOTOR_UV.getStackForm(),
                        MetaItems.CONVEYOR_MODULE_UV.getStackForm(),
                        OreDictUnifier.get(OrePrefix.toolHeadBuzzSaw, Materials.Tritanium)
                )));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_DISTILLERY, LuV,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        MetaItems.ELECTRIC_PUMP_LuV.getStackForm(2),
                        OreDictUnifier.get(OrePrefix.spring, Materials.HSSG)
                )));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_DISTILLERY, ZPM,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        MetaItems.ELECTRIC_PUMP_ZPM.getStackForm(2),
                        OreDictUnifier.get(OrePrefix.spring, Materials.Naquadah)
                )));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_DISTILLERY, UV,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        MetaItems.ELECTRIC_PUMP_UV.getStackForm(2),
                        OreDictUnifier.get(OrePrefix.spring, Materials.NaquadahAlloy)
                )));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_ELECTROLYZER, LuV,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        OreDictUnifier.get(OrePrefix.wireGtSingle, Materials.Osmium, 8),
                        GCYMMetaBlocks.UNIQUE_CASING.getItemVariant(BlockUniqueCasing.UniqueCasingType.ELECTROLYTIC_CELL, 4)
                )));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_ELECTROLYZER, ZPM,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        OreDictUnifier.get(OrePrefix.wireGtDouble, Materials.Osmium, 8),
                        GCYMMetaBlocks.UNIQUE_CASING.getItemVariant(BlockUniqueCasing.UniqueCasingType.ELECTROLYTIC_CELL, 4)
                )));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_ELECTROLYZER, UV,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        OreDictUnifier.get(OrePrefix.wireGtQuadruple, Materials.Osmium, 8),
                        GCYMMetaBlocks.UNIQUE_CASING.getItemVariant(BlockUniqueCasing.UniqueCasingType.ELECTROLYTIC_CELL, 4)
                )));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_POLARIZER, LuV,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        MetaItems.CONVEYOR_MODULE_LuV.getStackForm(),
                        OreDictUnifier.get(OrePrefix.wireGtSingle, Materials.NiobiumNitride, 8)
                )));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_POLARIZER, ZPM,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        MetaItems.CONVEYOR_MODULE_ZPM.getStackForm(),
                        OreDictUnifier.get(OrePrefix.wireGtDouble, Materials.VanadiumGallium, 8)
                )));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_POLARIZER, UV,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        MetaItems.CONVEYOR_MODULE_UV.getStackForm(),
                        OreDictUnifier.get(OrePrefix.wireGtQuadruple, Materials.YttriumBariumCuprate, 8)
                )));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_EXTRUDER, LuV,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        MetaItems.ELECTRIC_PISTON_LUV.getStackForm(2),
                        OreDictUnifier.get(OrePrefix.pipeNormalFluid, Materials.NiobiumTitanium)
                )));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_EXTRUDER, ZPM,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        MetaItems.ELECTRIC_PISTON_ZPM.getStackForm(2),
                        OreDictUnifier.get(OrePrefix.pipeLargeFluid, Materials.Iridium)
                )));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_EXTRUDER, UV,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        MetaItems.ELECTRIC_PISTON_UV.getStackForm(2),
                        OreDictUnifier.get(OrePrefix.pipeHugeFluid, Materials.Naquadah)
                )));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_SOLIDIFIER, LuV,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        MetaItems.ELECTRIC_PUMP_LuV.getStackForm(),
                        OreDictUnifier.get(OrePrefix.pipeNormalFluid, Materials.Polybenzimidazole)
                )));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_SOLIDIFIER, ZPM,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        MetaItems.ELECTRIC_PUMP_ZPM.getStackForm(),
                        OreDictUnifier.get(OrePrefix.pipeLargeFluid, Materials.Polybenzimidazole)
                )));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_SOLIDIFIER, UV,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        MetaItems.ELECTRIC_PUMP_UV.getStackForm(),
                        OreDictUnifier.get(OrePrefix.pipeHugeFluid, Materials.Polybenzimidazole)
                )));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_MIXER, LuV,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        MetaItems.ELECTRIC_MOTOR_LuV.getStackForm(),
                        OreDictUnifier.get(OrePrefix.rotor, Materials.RhodiumPlatedPalladium)
                )));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_MIXER, ZPM,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        MetaItems.ELECTRIC_MOTOR_ZPM.getStackForm(),
                        OreDictUnifier.get(OrePrefix.rotor, Materials.NaquadahAlloy)
                )));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_MIXER, UV,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        MetaItems.ELECTRIC_MOTOR_UV.getStackForm(),
                        OreDictUnifier.get(OrePrefix.rotor, Materials.Darmstadtium)
                )));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_PACKAGER, LuV,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        MetaItems.ROBOT_ARM_LuV.getStackForm(2)
                )));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_PACKAGER, ZPM,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        MetaItems.ROBOT_ARM_ZPM.getStackForm(2)
                )));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_PACKAGER, UV,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        MetaItems.ROBOT_ARM_UV.getStackForm(2)
                )));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_ENGRAVER, LuV,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        MetaItems.EMITTER_LuV.getStackForm(),
                        MetaItems.ELECTRIC_PISTON_LUV.getStackForm(2)
                )),
                GTRecipeOreInput.getOrCreate(OrePrefix.circuit, MarkerMaterials.Tier.LuV));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_ENGRAVER, ZPM,
                GCYMUtility.stacksToInput( ImmutableSet.of(
                        MetaItems.EMITTER_ZPM.getStackForm(),
                        MetaItems.ELECTRIC_PISTON_ZPM.getStackForm(2)
                )),
                GTRecipeOreInput.getOrCreate(OrePrefix.circuit, MarkerMaterials.Tier.ZPM));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_ENGRAVER, UV,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        MetaItems.EMITTER_UV.getStackForm(),
                        MetaItems.ELECTRIC_PISTON_UV.getStackForm(2)
                )),
                GTRecipeOreInput.getOrCreate(OrePrefix.circuit, MarkerMaterials.Tier.UV));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_SIFTER, LuV,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        MetaItems.ELECTRIC_PISTON_LUV.getStackForm(2),
                        MetaItems.ITEM_FILTER.getStackForm()
                )));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_SIFTER, ZPM,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        MetaItems.ELECTRIC_PISTON_ZPM.getStackForm(2),
                        MetaItems.ITEM_FILTER.getStackForm(2)
                )));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_SIFTER, UV,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        MetaItems.ELECTRIC_PISTON_UV.getStackForm(2),
                        MetaItems.ITEM_FILTER.getStackForm(4)
                )));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_WIREMILL, LuV,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        MetaItems.ELECTRIC_MOTOR_LuV.getStackForm(4)
                )));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_WIREMILL, ZPM,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        MetaItems.ELECTRIC_MOTOR_ZPM.getStackForm(4)
                )));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_WIREMILL, UV,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        MetaItems.ELECTRIC_MOTOR_UV.getStackForm(4)
                )));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_CIRCUIT_ASSEMBLER, LuV,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        MetaItems.ROBOT_ARM_LuV.getStackForm(),
                        MetaItems.EMITTER_LuV.getStackForm(),
                        MetaItems.CONVEYOR_MODULE_LuV.getStackForm(2)
                )),
                GTRecipeOreInput.getOrCreate(OrePrefix.circuit, MarkerMaterials.Tier.ZPM));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_CIRCUIT_ASSEMBLER, ZPM,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        MetaItems.ROBOT_ARM_ZPM.getStackForm(),
                        MetaItems.EMITTER_ZPM.getStackForm(),
                        MetaItems.CONVEYOR_MODULE_ZPM.getStackForm(2)
                )),
                GTRecipeOreInput.getOrCreate(OrePrefix.circuit, MarkerMaterials.Tier.UV));

        ITieredHatch.addMultiblockTier(GCYMMetaTileEntities.LARGE_CIRCUIT_ASSEMBLER, UV,
                GCYMUtility.stacksToInput(ImmutableSet.of(
                        MetaItems.ROBOT_ARM_UV.getStackForm(),
                        MetaItems.EMITTER_UV.getStackForm(),
                        MetaItems.CONVEYOR_MODULE_UV.getStackForm(2)
                )),
                GTRecipeOreInput.getOrCreate(OrePrefix.circuit, MarkerMaterials.Tier.UHV));

        // gcys will add its own items for these outside of gcym
    }
}
