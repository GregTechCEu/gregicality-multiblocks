package gregicality.multiblocks.api.render;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;

import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer;
import gregtech.client.renderer.texture.cube.SimpleOverlayRenderer;

import gregicality.multiblocks.GregicalityMultiblocks;

@Mod.EventBusSubscriber(modid = GregicalityMultiblocks.MODID, value = Side.CLIENT)
public final class GCYMTextures {

    // Multiblock Controllers
    public static OrientedOverlayRenderer LARGE_MACERATOR_OVERLAY;
    public static OrientedOverlayRenderer ALLOY_BLAST_SMELTER_OVERLAY;
    public static OrientedOverlayRenderer LARGE_ARC_FURNACE_OVERLAY;
    public static OrientedOverlayRenderer LARGE_ASSEMBLER_OVERLAY;
    public static OrientedOverlayRenderer LARGE_CIRCUIT_ASSEMBLER_OVERLAY;
    public static OrientedOverlayRenderer LARGE_AUTOCLAVE_OVERLAY;
    public static OrientedOverlayRenderer LARGE_BENDER_OVERLAY;
    public static OrientedOverlayRenderer LARGE_BREWERY_OVERLAY;
    public static OrientedOverlayRenderer LARGE_CENTRIFUGE_OVERLAY;
    public static OrientedOverlayRenderer LARGE_CHEMICAL_BATH_OVERLAY;
    public static OrientedOverlayRenderer CHEMICAL_PLANT_OVERLAY;
    public static OrientedOverlayRenderer LARGE_EXTRACTOR_OVERLAY;
    public static OrientedOverlayRenderer LARGE_CUTTER_OVERLAY;
    public static OrientedOverlayRenderer LARGE_DISTILLERY_OVERLAY;
    public static OrientedOverlayRenderer LARGE_ELECTROLYZER_OVERLAY;
    public static OrientedOverlayRenderer LARGE_POLARIZER_OVERLAY;
    public static OrientedOverlayRenderer LARGE_EXTRUDER_OVERLAY;
    public static OrientedOverlayRenderer LARGE_SOLIDIFIER_OVERLAY;
    public static OrientedOverlayRenderer LARGE_MIXER_OVERLAY;
    public static OrientedOverlayRenderer LARGE_PACKAGER_OVERLAY;
    public static OrientedOverlayRenderer LARGE_ENGRAVER_OVERLAY;
    public static OrientedOverlayRenderer LARGE_SIFTER_OVERLAY;
    public static OrientedOverlayRenderer LARGE_WIREMILL_OVERLAY;
    public static OrientedOverlayRenderer ELECTRIC_IMPLOSION_OVERLAY;
    public static OrientedOverlayRenderer LARGE_MASS_FABRICATOR_OVERLAY;
    public static OrientedOverlayRenderer LARGE_REPLICATOR_OVERLAY;
    public static OrientedOverlayRenderer MEGA_BLAST_FURNACE_OVERLAY;
    public static OrientedOverlayRenderer MEGA_VACUUM_FREEZER_OVERLAY;
    public static OrientedOverlayRenderer STEAM_ENGINE_OVERLAY;

    // Hatches
    public static OrientedOverlayRenderer PARALLEL_HATCH_MK1_OVERLAY;
    public static OrientedOverlayRenderer PARALLEL_HATCH_MK2_OVERLAY;
    public static OrientedOverlayRenderer PARALLEL_HATCH_MK3_OVERLAY;
    public static OrientedOverlayRenderer PARALLEL_HATCH_MK4_OVERLAY;
    public static OrientedOverlayRenderer TIERED_HATCH_OVERLAY;

    // Casings
    public static SimpleOverlayRenderer MACERATOR_CASING;
    public static SimpleOverlayRenderer BLAST_CASING;
    public static SimpleOverlayRenderer ASSEMBLING_CASING;
    public static SimpleOverlayRenderer STRESS_PROOF_CASING;
    public static SimpleOverlayRenderer CORROSION_PROOF_CASING;
    public static SimpleOverlayRenderer VIBRATION_SAFE_CASING;
    public static SimpleOverlayRenderer WATERTIGHT_CASING;
    public static SimpleOverlayRenderer CUTTER_CASING;
    public static SimpleOverlayRenderer NONCONDUCTING_CASING;
    public static SimpleOverlayRenderer MIXER_CASING;
    public static SimpleOverlayRenderer ENGRAVER_CASING;
    public static SimpleOverlayRenderer ATOMIC_CASING;
    public static SimpleOverlayRenderer STEAM_CASING;

    private GCYMTextures() {}

    public static void preInit() {
        // Multiblock Controllers
        LARGE_MACERATOR_OVERLAY = new OrientedOverlayRenderer("multiblock/large_macerator");
        ALLOY_BLAST_SMELTER_OVERLAY = new OrientedOverlayRenderer("multiblock/alloy_blast_smelter");
        LARGE_ARC_FURNACE_OVERLAY = new OrientedOverlayRenderer("multiblock/large_arc_furnace");
        LARGE_ASSEMBLER_OVERLAY = new OrientedOverlayRenderer("multiblock/large_assembler");
        LARGE_CIRCUIT_ASSEMBLER_OVERLAY = new OrientedOverlayRenderer("multiblock/large_circuit_assembler");
        LARGE_AUTOCLAVE_OVERLAY = new OrientedOverlayRenderer("multiblock/large_autoclave");
        LARGE_BENDER_OVERLAY = new OrientedOverlayRenderer("multiblock/large_bender");
        LARGE_BREWERY_OVERLAY = new OrientedOverlayRenderer("multiblock/large_brewery");
        LARGE_CENTRIFUGE_OVERLAY = new OrientedOverlayRenderer("multiblock/large_centrifuge");
        LARGE_CHEMICAL_BATH_OVERLAY = new OrientedOverlayRenderer("multiblock/large_chemical_bath");
        CHEMICAL_PLANT_OVERLAY = new OrientedOverlayRenderer("multiblock/chemical_plant");
        LARGE_EXTRACTOR_OVERLAY = new OrientedOverlayRenderer("multiblock/large_extractor");
        LARGE_CUTTER_OVERLAY = new OrientedOverlayRenderer("multiblock/large_cutter");
        LARGE_DISTILLERY_OVERLAY = new OrientedOverlayRenderer("multiblock/large_distillery");
        LARGE_ELECTROLYZER_OVERLAY = new OrientedOverlayRenderer("multiblock/large_electrolyzer");
        LARGE_POLARIZER_OVERLAY = new OrientedOverlayRenderer("multiblock/large_polarizer");
        LARGE_EXTRUDER_OVERLAY = new OrientedOverlayRenderer("multiblock/large_extruder");
        LARGE_SOLIDIFIER_OVERLAY = new OrientedOverlayRenderer("multiblock/large_solidifier");
        LARGE_MIXER_OVERLAY = new OrientedOverlayRenderer("multiblock/large_mixer");
        LARGE_PACKAGER_OVERLAY = new OrientedOverlayRenderer("multiblock/large_packager");
        LARGE_ENGRAVER_OVERLAY = new OrientedOverlayRenderer("multiblock/large_engraver");
        LARGE_SIFTER_OVERLAY = new OrientedOverlayRenderer("multiblock/large_sifter");
        LARGE_WIREMILL_OVERLAY = new OrientedOverlayRenderer("multiblock/large_wiremill");
        ELECTRIC_IMPLOSION_OVERLAY = new OrientedOverlayRenderer("multiblock/electric_implosion");
        LARGE_MASS_FABRICATOR_OVERLAY = new OrientedOverlayRenderer("multiblock/large_mass_fabricator");
        LARGE_REPLICATOR_OVERLAY = new OrientedOverlayRenderer("multiblock/large_replicator");
        MEGA_BLAST_FURNACE_OVERLAY = new OrientedOverlayRenderer("multiblock/mega_blast_furnace");
        MEGA_VACUUM_FREEZER_OVERLAY = new OrientedOverlayRenderer("multiblock/mega_vacuum_freezer");
        STEAM_ENGINE_OVERLAY = new OrientedOverlayRenderer("multiblock/steam_engine");

        // Hatches
        PARALLEL_HATCH_MK1_OVERLAY = new OrientedOverlayRenderer("hatches/parallel_hatch_mk1");
        PARALLEL_HATCH_MK2_OVERLAY = new OrientedOverlayRenderer("hatches/parallel_hatch_mk2");
        PARALLEL_HATCH_MK3_OVERLAY = new OrientedOverlayRenderer("hatches/parallel_hatch_mk3");
        PARALLEL_HATCH_MK4_OVERLAY = new OrientedOverlayRenderer("hatches/parallel_hatch_mk4");
        TIERED_HATCH_OVERLAY = new OrientedOverlayRenderer("hatches/tiered_hatch");

        // Casings
        MACERATOR_CASING = new SimpleOverlayRenderer("casings/large_multiblock_casing/macerator_casing");
        BLAST_CASING = new SimpleOverlayRenderer("casings/large_multiblock_casing/blast_casing");
        ASSEMBLING_CASING = new SimpleOverlayRenderer("casings/large_multiblock_casing/assembler_casing");
        STRESS_PROOF_CASING = new SimpleOverlayRenderer("casings/large_multiblock_casing/stress_proof_casing");
        CORROSION_PROOF_CASING = new SimpleOverlayRenderer("casings/large_multiblock_casing/corrosion_proof_casing");
        VIBRATION_SAFE_CASING = new SimpleOverlayRenderer("casings/large_multiblock_casing/vibration_safe_casing");
        WATERTIGHT_CASING = new SimpleOverlayRenderer("casings/large_multiblock_casing/watertight_casing");
        CUTTER_CASING = new SimpleOverlayRenderer("casings/large_multiblock_casing/cutter_casing");
        NONCONDUCTING_CASING = new SimpleOverlayRenderer("casings/large_multiblock_casing/nonconducting_casing");
        MIXER_CASING = new SimpleOverlayRenderer("casings/large_multiblock_casing/mixer_casing");
        ENGRAVER_CASING = new SimpleOverlayRenderer("casings/large_multiblock_casing/engraver_casing");
        ATOMIC_CASING = new SimpleOverlayRenderer("casings/large_multiblock_casing/atomic_casing");
        STEAM_CASING = new SimpleOverlayRenderer("casings/large_multiblock_casing/steam_casing");
    }
}
