package gregicality.machines.common;

import gregicality.machines.api.render.GCYMultiTextures;
import gregicality.machines.common.block.GCYMultiMetaBlocks;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    @Override
    public void preLoad() {
        super.preLoad();
        GCYMultiTextures.preInit();
    }


    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        GCYMultiMetaBlocks.registerItemModels();
    }
}
