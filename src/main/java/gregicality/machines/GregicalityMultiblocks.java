package gregicality.machines;

import gregicality.machines.api.utils.GCYMLog;
import gregicality.machines.common.CommonProxy;
import gregicality.machines.common.GCYMMetaFluids;
import gregicality.machines.common.block.GCYMMetaBlocks;
import gregicality.machines.common.metatileentities.GCYMMetaTileEntities;
import gregtech.api.GTValues;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(   modid        = GregicalityMultiblocks.MODID,
        name         = GregicalityMultiblocks.NAME,
        version      = GregicalityMultiblocks.VERSION,
        dependencies = GTValues.MOD_VERSION_DEP)
public class GregicalityMultiblocks {

    public static final String MODID = "gcym";
    public static final String NAME = "Gregicality: Multiblocks";
    public static final String VERSION = "@VERSION@";

    @SidedProxy(modId = MODID, clientSide = "gregicality.machines.common.ClientProxy", serverSide = "gregicality.machines.common.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        GCYMLog.init(event.getModLog());

        GCYMMetaFluids.init();
        GCYMMetaBlocks.init();
        GCYMMetaTileEntities.init();

        proxy.preLoad();
    }
}
