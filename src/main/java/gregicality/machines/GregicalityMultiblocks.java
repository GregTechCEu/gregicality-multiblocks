package gregicality.machines;

import gregicality.machines.api.utils.GTMALog;
import gregicality.machines.common.CommonProxy;
import gregicality.machines.common.block.GCYMultiMetaBlocks;
import gregicality.machines.common.metatileentities.GCYMultiMetaTileEntities;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(   modid        = GregicalityMultiblocks.MODID,
        name         = GregicalityMultiblocks.NAME,
        version      = GregicalityMultiblocks.VERSION,
        dependencies = "required-after:gregtech;")
public class GregicalityMultiblocks {

    public static final String MODID = "gcym";
    public static final String NAME = "Gregicality: Multiblocks";
    public static final String VERSION = "@VERSION@";

    @SidedProxy(modId = MODID, clientSide = "gregicality.machines.common.ClientProxy", serverSide = "gregicality.machines.common.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        GTMALog.init(event.getModLog());
        proxy.preLoad();

        GCYMultiMetaBlocks.init();
        GCYMultiMetaTileEntities.init();
    }
}
