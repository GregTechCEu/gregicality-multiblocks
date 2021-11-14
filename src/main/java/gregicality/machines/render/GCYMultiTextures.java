package gregicality.machines.render;

import gregicality.machines.GregicalityMultiblocks;
import gregtech.api.render.OrientedOverlayRenderer;
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
    public static OrientedOverlayRenderer LARGE_COMPRESSOR_OVERLAY;
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
        LARGE_COMPRESSOR_OVERLAY = new OrientedOverlayRenderer("multiblock/large_compressor", FRONT);
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
    }
}
