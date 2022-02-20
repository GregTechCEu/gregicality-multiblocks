package gregicality.multiblocks;

import gregicality.multiblocks.api.fluids.GCYMMetaFluids;
import gregicality.multiblocks.api.utils.GCYMLog;
import gregicality.multiblocks.common.CommonProxy;
import gregicality.multiblocks.common.block.GCYMMetaBlocks;
import gregicality.multiblocks.common.metatileentities.GCYMMetaTileEntities;
import gregtech.api.GTValues;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(   modid        = GregicalityMultiblocks.MODID,
        name         = GregicalityMultiblocks.NAME,
        version      = GregicalityMultiblocks.VERSION,
        dependencies = GTValues.MOD_VERSION_DEP)
public class GregicalityMultiblocks {

    public static final String MODID = "gcym";
    public static final String NAME = "Gregicality Multiblocks";
    public static final String VERSION = "@VERSION@";

    @SidedProxy(modId = MODID, clientSide = "gregicality.multiblocks.common.ClientProxy", serverSide = "gregicality.multiblocks.common.CommonProxy")
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
