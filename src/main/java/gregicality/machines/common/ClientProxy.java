package gregicality.machines.common;

import gregicality.machines.api.render.GCYMTextures;
import gregicality.machines.common.block.GCYMMetaBlocks;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    @Override
    public void preLoad() {
        super.preLoad();
        GCYMTextures.preInit();
    }


    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        GCYMMetaBlocks.registerItemModels();
    }
}
