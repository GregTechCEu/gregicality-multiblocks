package gregicality.machines.render;

import gregicality.machines.GregicalityMultiblocks;
import gregtech.api.render.OrientedOverlayRenderer;
import gregtech.api.render.SimpleCubeRenderer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;

import static gregtech.api.render.OrientedOverlayRenderer.OverlayFace.*;

@Mod.EventBusSubscriber(modid = GregicalityMultiblocks.MODID, value = Side.CLIENT)
public class GCYMultiTextures {

    // Multiblock Controllers
    public static OrientedOverlayRenderer LARGE_MACERATOR_OVERLAY;
    public static OrientedOverlayRenderer ALLOY_BLAST_SMELTER_OVERLAY;
    public static OrientedOverlayRenderer LARGE_ARC_FURNACE_OVERLAY;
    public static OrientedOverlayRenderer LARGE_ASSEMBLER_OVERLAY;
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


    // Casings
    public static SimpleCubeRenderer MACERATOR_CASING;
    public static SimpleCubeRenderer BLAST_CASING;
    public static SimpleCubeRenderer ASSEMBLING_CASING;
    public static SimpleCubeRenderer AUTOCLAVE_CASING;
    public static SimpleCubeRenderer BENDER_CASING;
    public static SimpleCubeRenderer BREWERY_CASING;
    public static SimpleCubeRenderer CENTRIFUGE_CASING;
    public static SimpleCubeRenderer BATH_CASING;
    public static SimpleCubeRenderer FLUID_SAFE_CASING;
    public static SimpleCubeRenderer CUTTER_CASING;
    public static SimpleCubeRenderer DISTILLlERY_CASING;
    public static SimpleCubeRenderer ELECTROLYZER_CASING;
    public static SimpleCubeRenderer ELECTROMAGNET_CASING;
    public static SimpleCubeRenderer EXTRUDER_CASING;
    public static SimpleCubeRenderer MIXER_CASING;
    public static SimpleCubeRenderer ENGRAVER_CASING;
    public static SimpleCubeRenderer SIFTER_CASING;
    public static SimpleCubeRenderer WIREMILL_CASING;
    public static SimpleCubeRenderer ATOMIC_CASING;


    public static void preInit() {
        // Multiblock Controllers
        LARGE_MACERATOR_OVERLAY = new OrientedOverlayRenderer("multiblock/large_macerator", FRONT);
        ALLOY_BLAST_SMELTER_OVERLAY = new OrientedOverlayRenderer("multiblock/alloy_blast_smelter", FRONT);
        LARGE_ARC_FURNACE_OVERLAY = new OrientedOverlayRenderer("multiblock/large_arc_furnace", FRONT);
        LARGE_ASSEMBLER_OVERLAY = new OrientedOverlayRenderer("multiblock/large_assembler", FRONT);
        LARGE_AUTOCLAVE_OVERLAY = new OrientedOverlayRenderer("multiblock/large_autoclave", FRONT);
        LARGE_BENDER_OVERLAY = new OrientedOverlayRenderer("multiblock/large_bender", FRONT);
        LARGE_BREWERY_OVERLAY = new OrientedOverlayRenderer("multiblock/large_brewery", FRONT);
        LARGE_CENTRIFUGE_OVERLAY = new OrientedOverlayRenderer("multiblock/large_centrifuge", FRONT);
        LARGE_CHEMICAL_BATH_OVERLAY = new OrientedOverlayRenderer("multiblock/large_chemical_bath", FRONT);
        CHEMICAL_PLANT_OVERLAY = new OrientedOverlayRenderer("multiblock/chemical_plant", FRONT);
        LARGE_EXTRACTOR_OVERLAY = new OrientedOverlayRenderer("multiblock/large_extractor", FRONT);
        LARGE_CUTTER_OVERLAY = new OrientedOverlayRenderer("multiblock/large_cutter", FRONT);
        LARGE_DISTILLERY_OVERLAY = new OrientedOverlayRenderer("multiblock/large_distillery", FRONT);
        LARGE_ELECTROLYZER_OVERLAY = new OrientedOverlayRenderer("multiblock/large_electrolyzer", FRONT);
        LARGE_POLARIZER_OVERLAY = new OrientedOverlayRenderer("multiblock/large_polarizer", FRONT);
        LARGE_EXTRUDER_OVERLAY = new OrientedOverlayRenderer("multiblock/large_extruder", FRONT);
        LARGE_SOLIDIFIER_OVERLAY = new OrientedOverlayRenderer("multiblock/large_solidifier", FRONT);
        LARGE_MIXER_OVERLAY = new OrientedOverlayRenderer("multiblock/large_mixer", FRONT);
        LARGE_PACKAGER_OVERLAY = new OrientedOverlayRenderer("multiblock/large_packager", FRONT);
        LARGE_ENGRAVER_OVERLAY = new OrientedOverlayRenderer("multiblock/large_engraver", FRONT);
        LARGE_SIFTER_OVERLAY = new OrientedOverlayRenderer("multiblock/large_sifter", FRONT);
        LARGE_WIREMILL_OVERLAY = new OrientedOverlayRenderer("multiblock/large_wiremill", FRONT);
        ELECTRIC_IMPLOSION_OVERLAY = new OrientedOverlayRenderer("multiblock/electric_implosion", FRONT);
        LARGE_MASS_FABRICATOR_OVERLAY = new OrientedOverlayRenderer("multiblock/large_mass_fabricator", FRONT);
        LARGE_REPLICATOR_OVERLAY = new OrientedOverlayRenderer("multiblock/large_replicator", FRONT);
        MEGA_BLAST_FURNACE_OVERLAY = new OrientedOverlayRenderer("multiblock/mega_blast_furnace", FRONT);
        MEGA_VACUUM_FREEZER_OVERLAY = new OrientedOverlayRenderer("multiblock/mega_vacuum_freezer", FRONT);

        // Casings
        MACERATOR_CASING = new SimpleCubeRenderer("casings/large_multiblock_casing/macerator_casing");
        BLAST_CASING = new SimpleCubeRenderer("casings/large_multiblock_casing/blast_casing");
        ASSEMBLING_CASING = new SimpleCubeRenderer("casings/large_multiblock_casing/assembler_casing");
        AUTOCLAVE_CASING = new SimpleCubeRenderer("casings/large_multiblock_casing/autoclave_casing");
        BENDER_CASING = new SimpleCubeRenderer("casings/large_multiblock_casing/bender_casing");
        BREWERY_CASING = new SimpleCubeRenderer("casings/large_multiblock_casing/brewing_casing");
        CENTRIFUGE_CASING = new SimpleCubeRenderer("casings/large_multiblock_casing/centrifuge_casing");
        BATH_CASING = new SimpleCubeRenderer("casings/large_multiblock_casing/bath_casing");
        FLUID_SAFE_CASING = new SimpleCubeRenderer("casings/large_multiblock_casing/fluid_safe_casing");
        CUTTER_CASING = new SimpleCubeRenderer("casings/large_multiblock_casing/cutter_casing");
        DISTILLlERY_CASING = new SimpleCubeRenderer("casings/large_multiblock_casing/distillery_casing");
        ELECTROLYZER_CASING = new SimpleCubeRenderer("casings/large_multiblock_casing/electrolyzer_casing");
        ELECTROMAGNET_CASING = new SimpleCubeRenderer("casings/large_multiblock_casing/electromagnet_casing");
        EXTRUDER_CASING = new SimpleCubeRenderer("casings/large_multiblock_casing/extruder_casing");
        MIXER_CASING = new SimpleCubeRenderer("casings/large_multiblock_casing/mixer_casing");
        ENGRAVER_CASING = new SimpleCubeRenderer("casings/large_multiblock_casing/engraver_casing");
        SIFTER_CASING = new SimpleCubeRenderer("casings/large_multiblock_casing_2/sifter_casing");
        WIREMILL_CASING = new SimpleCubeRenderer("casings/large_multiblock_casing_2/wiremill_casing");
        ATOMIC_CASING = new SimpleCubeRenderer("casings/large_multiblock_casing_2/atomic_casing");
    }
}
