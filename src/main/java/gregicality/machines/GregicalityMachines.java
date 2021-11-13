package gregicality.machines;

import gregicality.machines.common.block.GCYMMetaBlocks;
import gregicality.machines.common.metatileentities.GCYMMetaTileEntities;
import gregicality.machines.utils.GTMALog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;

@Mod(   modid        = GregicalityMachines.MODID,
        name         = GregicalityMachines.NAME,
        version      = GregicalityMachines.VERSION,
        dependencies = "required-after:gregtech@[2.0,);")
public class GregicalityMachines {

    public static final String MODID = "gcy_machines";
    public static final String NAME = "Gregicality: Machines";
    public static final String VERSION = "@VERSION@";

    @SidedProxy(modId = MODID, clientSide = "gregicality.machines.ClientProxy", serverSide = "gregicality.machines.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        GTMALog.init(event.getModLog());
        proxy.preLoad();

        GCYMMetaBlocks.init();
        GCYMMetaTileEntities.init();
    }
}
