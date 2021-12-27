package gregicality.machines.loaders.recipe;

import gregicality.machines.common.metatileentities.GCYMMetaTileEntities;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.blocks.BlockGlassCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.items.MetaItems;
import gregtech.common.metatileentities.MetaTileEntities;

import static gregicality.machines.api.unification.GCYMMaterials.*;
import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class GCYMMetaTileEntityLoader {

    public static void init() {
        ModHandler.addShapedRecipe("large_macerator", GCYMMetaTileEntities.LARGE_MACERATOR.getStackForm(),
                "TCT", "PSP", "MWM",
                'T', new UnificationEntry(plate, TungstenCarbide),
                'C', new UnificationEntry(circuit, MarkerMaterials.Tier.Elite),
                'P', MetaItems.ELECTRIC_PISTON_IV.getStackForm(),
                'S', MetaTileEntities.MACERATOR[IV - 1].getStackForm(),
                'M', MetaItems.ELECTRIC_MOTOR_IV.getStackForm(),
                'W', new UnificationEntry(cableGtSingle, Tungsten));

        ModHandler.addShapedRecipe("alloy_blast_smelter", GCYMMetaTileEntities.ALLOY_BLAST_SMELTER.getStackForm(),
                "TCT", "WSW", "TCT",
                'T', new UnificationEntry(plate, TantalumCarbide),
                'C', new UnificationEntry(circuit, MarkerMaterials.Tier.Elite),
                'S', MetaTileEntities.ALLOY_SMELTER[IV - 1].getStackForm(),
                'W', new UnificationEntry(cableGtSingle, Tungsten));

        ModHandler.addShapedRecipe("large_arc_furnace", GCYMMetaTileEntities.LARGE_ARC_FURNACE.getStackForm(),
                "WGW", "CSC", "TTT",
                'T', new UnificationEntry(plate, TantalumCarbide),
                'C', new UnificationEntry(circuit, MarkerMaterials.Tier.Elite),
                'G', new UnificationEntry(stick, Graphite),
                'S', MetaTileEntities.ARC_FURNACE[IV - 1].getStackForm(),
                'W', new UnificationEntry(cableGtSingle, Tungsten));

        ModHandler.addShapedRecipe("large_assembler", GCYMMetaTileEntities.LARGE_ASSEMBLER.getStackForm(),
                "RER", "SCA", "PWP",
                'R', MetaItems.ROBOT_ARM_IV.getStackForm(),
                'E', MetaItems.EMITTER_IV.getStackForm(),
                'C', new UnificationEntry(circuit, MarkerMaterials.Tier.Elite),
                'P', MetaItems.ELECTRIC_PISTON_IV.getStackForm(),
                'S', MetaTileEntities.ASSEMBLER[IV - 1].getStackForm(),
                'A', MetaTileEntities.CIRCUIT_ASSEMBLER[IV - 1].getStackForm(),
                'W', new UnificationEntry(cableGtSingle, Tungsten));

        ModHandler.addShapedRecipe("large_autoclave", GCYMMetaTileEntities.LARGE_AUTOCLAVE.getStackForm(),
                "ACA", "ASA", "PWP",
                'A', new UnificationEntry(plateDouble, HSLASteel),
                'C', new UnificationEntry(circuit, MarkerMaterials.Tier.Elite),
                'P', MetaItems.ELECTRIC_PUMP_IV.getStackForm(),
                'S', MetaTileEntities.AUTOCLAVE[IV - 1].getStackForm(),
                'W', new UnificationEntry(cableGtSingle, Tungsten));


        ModHandler.addShapedRecipe("large_bender", GCYMMetaTileEntities.LARGE_BENDER.getStackForm(),
                "PWP", "BCS", "FWH",
                'C', new UnificationEntry(circuit, MarkerMaterials.Tier.Elite),
                'P', MetaItems.ELECTRIC_PISTON_IV.getStackForm(),
                'B', MetaTileEntities.BENDER[IV - 1].getStackForm(),
                'S', MetaTileEntities.COMPRESSOR[IV - 1].getStackForm(),
                'F', MetaTileEntities.FORMING_PRESS[IV - 1].getStackForm(),
                'H', MetaTileEntities.FORGE_HAMMER[IV - 1].getStackForm(),
                'W', new UnificationEntry(cableGtSingle, Tungsten));

        ModHandler.addShapedRecipe("large_brewer", GCYMMetaTileEntities.LARGE_BREWERY.getStackForm(),
                "SCS", "BFH", "PWP",
                'S', new UnificationEntry(spring, MolybdenumDisilicide),
                'C', new UnificationEntry(circuit, MarkerMaterials.Tier.Elite),
                'P', MetaItems.ELECTRIC_PUMP_IV.getStackForm(),
                'B', MetaTileEntities.BREWERY[IV - 1].getStackForm(),
                'F', MetaTileEntities.FERMENTER[IV - 1].getStackForm(),
                'H', MetaTileEntities.FLUID_HEATER[IV - 1].getStackForm(),
                'W', new UnificationEntry(cableGtSingle, Tungsten));

        ModHandler.addShapedRecipe("large_centrifuge", GCYMMetaTileEntities.LARGE_CENTRIFUGE.getStackForm(),
                "HPH", "RCT", "MWM",
                'C', new UnificationEntry(circuit, MarkerMaterials.Tier.Elite),
                'H', new UnificationEntry(spring, MolybdenumDisilicide),
                'P', new UnificationEntry(pipeHugeFluid, StainlessSteel),
                'R', MetaTileEntities.CENTRIFUGE[IV - 1].getStackForm(),
                'T', MetaTileEntities.THERMAL_CENTRIFUGE[IV - 1].getStackForm(),
                'M', MetaItems.ELECTRIC_MOTOR_IV.getStackForm(),
                'W', new UnificationEntry(cableGtSingle, Tungsten));

        ModHandler.addShapedRecipe("large_chemical_bath", GCYMMetaTileEntities.LARGE_CHEMICAL_BATH.getStackForm(),
                "PGP", "BCO", "MWM",
                'C', new UnificationEntry(circuit, MarkerMaterials.Tier.Elite),
                'B', MetaTileEntities.CHEMICAL_BATH[IV - 1].getStackForm(),
                'O', MetaTileEntities.ORE_WASHER[IV - 1].getStackForm(),
                'G', MetaBlocks.TRANSPARENT_CASING.getItemVariant(BlockGlassCasing.CasingType.TEMPERED_GLASS),
                'P', MetaItems.ELECTRIC_PUMP_IV.getStackForm(),
                'M', MetaItems.CONVEYOR_MODULE_IV.getStackForm(),
                'W', new UnificationEntry(cableGtSingle, Tungsten));

        ModHandler.addShapedRecipe("chemical_plant", GCYMMetaTileEntities.CHEMICAL_PLANT.getStackForm(),
                "LRL", "PMP", "CSC",
                'L', new UnificationEntry(pipeLargeFluid, Polybenzimidazole),
                'C', new UnificationEntry(circuit, MarkerMaterials.Tier.Master),
                'S', MetaTileEntities.LARGE_CHEMICAL_REACTOR.getStackForm(),
                'P', new UnificationEntry(spring, MolybdenumDisilicide),
                'R', new UnificationEntry(rotor, Iridium),
                'M', MetaItems.ELECTRIC_MOTOR_IV.getStackForm());

        ModHandler.addShapedRecipe("large_extractor", GCYMMetaTileEntities.LARGE_EXTRACTOR.getStackForm(),
                "PGP", "BCO", "MWM",
                'C', new UnificationEntry(circuit, MarkerMaterials.Tier.Elite),
                'B', MetaTileEntities.EXTRACTOR[IV - 1].getStackForm(),
                'O', MetaTileEntities.CANNER[IV - 1].getStackForm(),
                'G', MetaBlocks.TRANSPARENT_CASING.getItemVariant(BlockGlassCasing.CasingType.TEMPERED_GLASS),
                'P', MetaItems.ELECTRIC_PUMP_IV.getStackForm(),
                'M', MetaItems.ELECTRIC_PISTON_IV.getStackForm(),
                'W', new UnificationEntry(cableGtSingle, Tungsten));

        ModHandler.addShapedRecipe("large_cutter", GCYMMetaTileEntities.LARGE_CUTTER.getStackForm(),
                "SPS", "BCO", "MWM",
                'C', new UnificationEntry(circuit, MarkerMaterials.Tier.Elite),
                'B', MetaTileEntities.CUTTER[IV - 1].getStackForm(),
                'O', MetaTileEntities.LATHE[IV - 1].getStackForm(),
                'S', new UnificationEntry(toolHeadBuzzSaw, TungstenCarbide),
                'P', MetaItems.CONVEYOR_MODULE_IV.getStackForm(),
                'M', MetaItems.ELECTRIC_MOTOR_IV.getStackForm(),
                'W', new UnificationEntry(cableGtSingle, Tungsten));

        ModHandler.addShapedRecipe("large_distillery", GCYMMetaTileEntities.LARGE_DISTILLERY.getStackForm(),
                "LCL", "PSP", "LCL",
                'L', new UnificationEntry(pipeLargeFluid, Iridium),
                'C', new UnificationEntry(circuit, MarkerMaterials.Tier.Elite),
                'P', MetaItems.ELECTRIC_PUMP_IV.getStackForm(),
                'S', MetaTileEntities.DISTILLATION_TOWER.getStackForm());

        ModHandler.addShapedRecipe("large_electrolyzer", GCYMMetaTileEntities.LARGE_ELECTROLYZER.getStackForm(),
                "LCL", "RSR", "LWL",
                'L', new UnificationEntry(wireGtDouble, Osmium),
                'R', new UnificationEntry(stickLong, Platinum),
                'C', new UnificationEntry(circuit, MarkerMaterials.Tier.Elite),
                'S', MetaTileEntities.ELECTROLYZER[IV - 1].getStackForm(),
                'W', new UnificationEntry(cableGtSingle, Tungsten));

        ModHandler.addShapedRecipe("large_polarizer", GCYMMetaTileEntities.LARGE_POLARIZER.getStackForm(),
                "SWS", "BCO", "SWS",
                'C', new UnificationEntry(circuit, MarkerMaterials.Tier.Elite),
                'B', MetaTileEntities.POLARIZER[IV - 1].getStackForm(),
                'O', MetaTileEntities.ELECTROMAGNETIC_SEPARATOR[IV - 1].getStackForm(),
                'S', new UnificationEntry(wireGtDouble, Osmium),
                'W', new UnificationEntry(cableGtSingle, Tungsten));

        ModHandler.addShapedRecipe("large_extruder", GCYMMetaTileEntities.LARGE_EXTRUDER.getStackForm(),
                "LCL", "PSP", "OWO",
                'L', new UnificationEntry(pipeLargeItem, Ultimet),
                'C', new UnificationEntry(circuit, MarkerMaterials.Tier.Elite),
                'S', MetaTileEntities.EXTRUDER[IV - 1].getStackForm(),
                'P', MetaItems.ELECTRIC_PISTON_IV.getStackForm(),
                'O', new UnificationEntry(spring, MolybdenumDisilicide),
                'W', new UnificationEntry(cableGtSingle, Tungsten));

        ModHandler.addShapedRecipe("large_solidifier", GCYMMetaTileEntities.LARGE_SOLIDIFIER.getStackForm(),
                "LCL", "PSP", "LWL",
                'L', new UnificationEntry(pipeNormalFluid, Polyethylene),
                'C', new UnificationEntry(circuit, MarkerMaterials.Tier.Elite),
                'S', MetaTileEntities.FLUID_SOLIDIFIER[IV - 1].getStackForm(),
                'P', MetaItems.ELECTRIC_PUMP_IV.getStackForm(),
                'W', new UnificationEntry(cableGtSingle, Tungsten));

        ModHandler.addShapedRecipe("large_mixer", GCYMMetaTileEntities.LARGE_MIXER.getStackForm(),
                "LCL", "RSR", "MWM",
                'L', new UnificationEntry(pipeNormalFluid, Polybenzimidazole),
                'R', new UnificationEntry(rotor, Iridium),
                'C', new UnificationEntry(circuit, MarkerMaterials.Tier.Elite),
                'S', MetaTileEntities.MIXER[IV - 1].getStackForm(),
                'M', MetaItems.ELECTRIC_MOTOR_IV.getStackForm(),
                'W', new UnificationEntry(cableGtSingle, Tungsten));

        ModHandler.addShapedRecipe("large_packager", GCYMMetaTileEntities.LARGE_PACKAGER.getStackForm(),
                "RCR", "PSP", "MPM",
                'P', new UnificationEntry(plate, HSLASteel),
                'C', new UnificationEntry(circuit, MarkerMaterials.Tier.Extreme),
                'S', MetaTileEntities.PACKER[HV - 1].getStackForm(),
                'R', MetaItems.ROBOT_ARM_HV.getStackForm(),
                'M', MetaItems.CONVEYOR_MODULE_HV.getStackForm());

        ModHandler.addShapedRecipe("large_engraver", GCYMMetaTileEntities.LARGE_ENGRAVER.getStackForm(),
                "ECE", "PSP", "DWD",
                'C', new UnificationEntry(circuit, MarkerMaterials.Tier.Elite),
                'S', MetaTileEntities.LASER_ENGRAVER[IV - 1].getStackForm(),
                'E', MetaItems.EMITTER_IV.getStackForm(),
                'P', MetaItems.ELECTRIC_PISTON_IV.getStackForm(),
                'D', new UnificationEntry(plateDense, TantalumCarbide),
                'W', new UnificationEntry(cableGtSingle, Tungsten));

        ModHandler.addShapedRecipe("large_sifter", GCYMMetaTileEntities.LARGE_SIFTER.getStackForm(),
                "ACA", "PSP", "AWA",
                'C', new UnificationEntry(circuit, MarkerMaterials.Tier.Elite),
                'S', MetaTileEntities.SIFTER[IV - 1].getStackForm(),
                'P', MetaItems.ELECTRIC_PISTON_IV.getStackForm(),
                'A', new UnificationEntry(plate, HSLASteel),
                'W', new UnificationEntry(cableGtSingle, Tungsten));

        ModHandler.addShapedRecipe("large_wiremill", GCYMMetaTileEntities.LARGE_WIREMILL.getStackForm(),
                "ACA", "RSR", "MWM",
                'C', new UnificationEntry(circuit, MarkerMaterials.Tier.Elite),
                'S', MetaTileEntities.SIFTER[IV - 1].getStackForm(),
                'M', MetaItems.ELECTRIC_MOTOR_IV.getStackForm(),
                'R', new UnificationEntry(stickLong, HSLASteel),
                'A', new UnificationEntry(plate, HSLASteel),
                'W', new UnificationEntry(cableGtSingle, Tungsten));

        ModHandler.addShapedRecipe("electric_implosion_compressor", GCYMMetaTileEntities.ELECTRIC_IMPLOSION_COMPRESSOR.getStackForm(),
                "PCP", "FSF", "PCP",
                'C', new UnificationEntry(circuit, MarkerMaterials.Tier.Ultimate),
                'S', MetaTileEntities.IMPLOSION_COMPRESSOR.getStackForm(),
                'P', MetaItems.ELECTRIC_PISTON_IV.getStackForm(),
                'F', MetaItems.FIELD_GENERATOR_IV.getStackForm());

        // todo replication
//        ModHandler.addShapedRecipe("large_mass_fabricator", GCYMMetaTileEntities.LARGE_MASS_FABRICATOR.getStackForm(),
//                "FCF", "ESE", "FWF",
//                'C', new UnificationEntry(circuit, MarkerMaterials.Tier.Infinite),
//                'S', MetaTileEntities.MASS_FABRICATOR[ZPM - 1].getStackForm(), //todo mid tier configs
//                'F', MetaItems.FIELD_GENERATOR_ZPM.getStackForm(),
//                'E', MetaItems.EMITTER_ZPM.getStackForm(),
//                'W', new UnificationEntry(cableGtDouble, VanadiumGallium));

        // todo replication
//        ModHandler.addShapedRecipe("large_replicator", GCYMMetaTileEntities.LARGE_REPLICATOR.getStackForm(),
//                "FCF", "ESE", "FWF",
//                'C', new UnificationEntry(circuit, MarkerMaterials.Tier.Infinite),
//                'S', MetaTileEntities.REPLICATOR[ZPM - 1].getStackForm(), //todo mid tier configs
//                'F', MetaItems.FIELD_GENERATOR_ZPM.getStackForm(),
//                'E', MetaBlocks.FUSION_CASING.getItemVariant(BlockFusionCasing.CasingType.FUSION_COIL),
//                'W', new UnificationEntry(cableGtDouble, VanadiumGallium));

        ModHandler.addShapedRecipe("mega_blast_furnace", GCYMMetaTileEntities.MEGA_BLAST_FURNACE.getStackForm(),
                "PCP", "FSF", "DWD",
                'C', new UnificationEntry(circuit, MarkerMaterials.Tier.Infinite),
                'S', MetaTileEntities.ELECTRIC_BLAST_FURNACE.getStackForm(),
                'F', MetaItems.FIELD_GENERATOR_UV.getStackForm(),
                'P', new UnificationEntry(spring, Neutronium),
                'D', new UnificationEntry(plateDense, Neutronium),
                'W', new UnificationEntry(wireGtQuadruple, RutheniumTriniumAmericiumNeutronate));

        ModHandler.addShapedRecipe("mega_vacuum_freezer", GCYMMetaTileEntities.MEGA_VACUUM_FREEZER.getStackForm(),
                "PCP", "FSF", "DWD",
                'C', new UnificationEntry(circuit, MarkerMaterials.Tier.Infinite),
                'S', MetaTileEntities.VACUUM_FREEZER.getStackForm(),
                'F', MetaItems.FIELD_GENERATOR_UV.getStackForm(),
                'P', new UnificationEntry(pipeNormalFluid, Neutronium),
                'D', new UnificationEntry(plateDense, Neutronium),
                'W', new UnificationEntry(wireGtQuadruple, RutheniumTriniumAmericiumNeutronate));

        // Parallel Hatches
        ModHandler.addShapedRecipe("parallel_hatch_iv", GCYMMetaTileEntities.PARALLEL_HATCH[IV - IV].getStackForm(),
                "SCE", "FHF", "WCW",
                'C', new UnificationEntry(circuit, MarkerMaterials.Tier.Master),
                'H', MetaTileEntities.HULL[IV].getStackForm(),
                'S', MetaItems.SENSOR_IV.getStackForm(),
                'E', MetaItems.EMITTER_IV.getStackForm(),
                'F', MetaItems.FIELD_GENERATOR_IV.getStackForm(),
                'W', new UnificationEntry(cableGtDouble, Tungsten));

        ModHandler.addShapedRecipe("parallel_hatch_luv", GCYMMetaTileEntities.PARALLEL_HATCH[LuV - IV].getStackForm(),
                "SCE", "FHF", "WCW",
                'C', new UnificationEntry(circuit, MarkerMaterials.Tier.Ultimate),
                'H', MetaTileEntities.HULL[LuV].getStackForm(),
                'S', MetaItems.SENSOR_LUV.getStackForm(),
                'E', MetaItems.EMITTER_LUV.getStackForm(),
                'F', MetaItems.FIELD_GENERATOR_LUV.getStackForm(),
                'W', new UnificationEntry(cableGtDouble, NiobiumTitanium));

        ModHandler.addShapedRecipe("parallel_hatch_zpm", GCYMMetaTileEntities.PARALLEL_HATCH[ZPM - IV].getStackForm(),
                "SCE", "FHF", "WCW",
                'C', new UnificationEntry(circuit, MarkerMaterials.Tier.Super),
                'H', MetaTileEntities.HULL[ZPM].getStackForm(),
                'S', MetaItems.SENSOR_ZPM.getStackForm(),
                'E', MetaItems.EMITTER_ZPM.getStackForm(),
                'F', MetaItems.FIELD_GENERATOR_ZPM.getStackForm(),
                'W', new UnificationEntry(cableGtDouble, VanadiumGallium));

        ModHandler.addShapedRecipe("parallel_hatch_uv", GCYMMetaTileEntities.PARALLEL_HATCH[UV - IV].getStackForm(),
                "SCE", "FHF", "WCW",
                'C', new UnificationEntry(circuit, MarkerMaterials.Tier.Infinite),
                'H', MetaTileEntities.HULL[UV].getStackForm(),
                'S', MetaItems.SENSOR_UV.getStackForm(),
                'E', MetaItems.EMITTER_UV.getStackForm(),
                'F', MetaItems.FIELD_GENERATOR_UV.getStackForm(),
                'W', new UnificationEntry(cableGtDouble, YttriumBariumCuprate));
    }
}
