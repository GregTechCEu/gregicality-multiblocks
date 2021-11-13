package gcym.api.render;

import gcym.GregicalityMultiblocks;
import gregtech.api.render.OrientedOverlayRenderer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;

import static gregtech.api.render.OrientedOverlayRenderer.OverlayFace.*;

@Mod.EventBusSubscriber(modid = GregicalityMultiblocks.MODID, value = Side.CLIENT)
public class GCYMTextures {

    // Multiblock Controllers
    public static OrientedOverlayRenderer LARGE_MACERATOR_OVERLAY;
    public static OrientedOverlayRenderer LARGE_ARC_FURNACE_OVERLAY;
    public static OrientedOverlayRenderer LARGE_ASSEMBLER_OVERLAY;
//    public static OrientedOverlayRenderer LARGE_ALLOY_SMELTER_OVERLAY;
    public static OrientedOverlayRenderer LARGE_AUTOCLAVE_OVERLAY;
    public static OrientedOverlayRenderer LARGE_BENDER_OVERLAY;
    public static OrientedOverlayRenderer LARGE_BREWERY_OVERLAY;
    public static OrientedOverlayRenderer LARGE_CENTRIFUGE_OVERLAY;
    public static OrientedOverlayRenderer LARGE_CHEMICAL_BATH_OVERLAY;


    public static void preInit() {
        LARGE_MACERATOR_OVERLAY = new OrientedOverlayRenderer("multiblock/large_macerator", FRONT);
        LARGE_ARC_FURNACE_OVERLAY = new OrientedOverlayRenderer("multiblock/large_arc_furnace", FRONT);
        LARGE_ASSEMBLER_OVERLAY = new OrientedOverlayRenderer("multiblock/large_assembler", FRONT);
//        LARGE_ALLOY_SMELTER_OVERLAY = new OrientedOverlayRenderer("multiblock/large_alloy_smelter", FRONT);
        LARGE_AUTOCLAVE_OVERLAY = new OrientedOverlayRenderer("multiblock/large_autoclave", FRONT);
        LARGE_BENDER_OVERLAY = new OrientedOverlayRenderer("multiblock/large_bender", FRONT);
        LARGE_BREWERY_OVERLAY = new OrientedOverlayRenderer("multiblock/large_brewery", FRONT);
        LARGE_CENTRIFUGE_OVERLAY = new OrientedOverlayRenderer("multiblock/large_centrifuge", FRONT);
        LARGE_CHEMICAL_BATH_OVERLAY = new OrientedOverlayRenderer("multiblock/large_chemical_bath", FRONT);
    }
}
