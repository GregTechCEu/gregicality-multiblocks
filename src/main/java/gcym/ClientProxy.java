package gcym;

import gcym.api.render.GCYMTextures;
import gcym.common.block.GCYMMetaBlocks;
import gcym.utils.GTMALog;
import net.minecraft.client.Minecraft;
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
