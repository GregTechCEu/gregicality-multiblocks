package gcym;

import gcym.common.block.GCYMMetaBlocks;
import gcym.common.metatileentities.GCYMMetaTileEntities;
import gcym.utils.GTMALog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;

@Mod(   modid        = GregicalityMultiblocks.MODID,
        name         = GregicalityMultiblocks.NAME,
        version      = GregicalityMultiblocks.VERSION,
        dependencies = "required-after:gregtech@[2.0,);")
public class GregicalityMultiblocks {

    public static final String MODID = "gcym";
    public static final String NAME = "Gregicality: Multiblocks";
    public static final String VERSION = "@VERSION@";

    @SidedProxy(modId = MODID, clientSide = "gcym.ClientProxy", serverSide = "gcym.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        GTMALog.init(event.getModLog());
        proxy.preLoad();

        GCYMMetaBlocks.init();
        GCYMMetaTileEntities.init();
    }
}
