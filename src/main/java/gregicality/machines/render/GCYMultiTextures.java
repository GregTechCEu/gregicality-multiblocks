package gregicality.machines.render;

import gregicality.machines.GregicalityMultiblocks;
import gregtech.api.render.OrientedOverlayRenderer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;

import static gregtech.api.render.OrientedOverlayRenderer.OverlayFace.*;

@Mod.EventBusSubscriber(modid = GregicalityMultiblocks.MODID, value = Side.CLIENT)
public class GCYMultiTextures {

    // Multiblock Controllers
    // Standard
    public static OrientedOverlayRenderer LARGE_MACERATOR_OVERLAY;
    public static OrientedOverlayRenderer LARGE_ARC_FURNACE_OVERLAY;
    public static OrientedOverlayRenderer LARGE_ASSEMBLER_OVERLAY;
    public static OrientedOverlayRenderer LARGE_AUTOCLAVE_OVERLAY;
    public static OrientedOverlayRenderer LARGE_BENDER_OVERLAY;
    public static OrientedOverlayRenderer LARGE_BREWERY_OVERLAY;
    public static OrientedOverlayRenderer LARGE_CENTRIFUGE_OVERLAY;
    public static OrientedOverlayRenderer LARGE_CHEMICAL_BATH_OVERLAY;
    public static OrientedOverlayRenderer LARGE_EXTRACTOR_OVERLAY;
    public static OrientedOverlayRenderer LARGE_COMPRESSOR_OVERLAY;
    public static OrientedOverlayRenderer LARGE_CUTTER_OVERLAY;
    public static OrientedOverlayRenderer LARGE_DISTILLERY_OVERLAY;
    public static OrientedOverlayRenderer LARGE_ELECTROLYZER_OVERLAY;

    // Unique
    public static OrientedOverlayRenderer CHEMICAL_PLANT_OVERLAY;


    public static void preInit() {
        // Multiblock Controllers
        // Standard
        LARGE_MACERATOR_OVERLAY = new OrientedOverlayRenderer("multiblock/large_macerator", FRONT);
        LARGE_ARC_FURNACE_OVERLAY = new OrientedOverlayRenderer("multiblock/large_arc_furnace", FRONT);
        LARGE_ASSEMBLER_OVERLAY = new OrientedOverlayRenderer("multiblock/large_assembler", FRONT);
        LARGE_AUTOCLAVE_OVERLAY = new OrientedOverlayRenderer("multiblock/large_autoclave", FRONT);
        LARGE_BENDER_OVERLAY = new OrientedOverlayRenderer("multiblock/large_bender", FRONT);
        LARGE_BREWERY_OVERLAY = new OrientedOverlayRenderer("multiblock/large_brewery", FRONT);
        LARGE_CENTRIFUGE_OVERLAY = new OrientedOverlayRenderer("multiblock/large_centrifuge", FRONT);
        LARGE_CHEMICAL_BATH_OVERLAY = new OrientedOverlayRenderer("multiblock/large_chemical_bath", FRONT);
        LARGE_EXTRACTOR_OVERLAY = new OrientedOverlayRenderer("multiblock/large_extractor", FRONT);
        LARGE_COMPRESSOR_OVERLAY = new OrientedOverlayRenderer("multiblock/large_compressor", FRONT);
        LARGE_CUTTER_OVERLAY = new OrientedOverlayRenderer("multiblock/large_cutter", FRONT);
        LARGE_DISTILLERY_OVERLAY = new OrientedOverlayRenderer("multiblock/large_distillery", FRONT);
        LARGE_ELECTROLYZER_OVERLAY = new OrientedOverlayRenderer("multiblock/large_electrolyzer", FRONT);

        // Unique
        CHEMICAL_PLANT_OVERLAY = new OrientedOverlayRenderer("multiblock/chemical_plant", FRONT);
    }
}
