package gregicality.machines;

import gregicality.machines.common.block.GCYMultiMetaBlocks;
import gregicality.machines.common.metatileentities.GCYMultiMetaTileEntities;
import gregicality.machines.utils.GTMALog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;

@Mod(   modid        = GregicalityMultiblocks.MODID,
        name         = GregicalityMultiblocks.NAME,
        version      = GregicalityMultiblocks.VERSION,
        dependencies = "required-after:gregtech@[2.0,);")
public class GregicalityMultiblocks {

    public static final String MODID = "gcy_multiblocks";
    public static final String NAME = "Gregicality: Multiblocks";
    public static final String VERSION = "@VERSION@";

    @SidedProxy(modId = MODID, clientSide = "gregicality.machines.ClientProxy", serverSide = "gregicality.machines.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        GTMALog.init(event.getModLog());
        proxy.preLoad();

        GCYMultiMetaBlocks.init();
        GCYMultiMetaTileEntities.init();
    }
}
