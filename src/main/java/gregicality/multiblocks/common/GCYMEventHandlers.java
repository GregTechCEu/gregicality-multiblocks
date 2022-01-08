package gregicality.multiblocks.common;

import gregicality.multiblocks.GregicalityMultiblocks;
import gregicality.multiblocks.api.unification.GCYMMaterials;
import gregicality.multiblocks.api.unification.properties.GCYMPropertyAddition;
import gregtech.api.GregTechAPI;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = GregicalityMultiblocks.MODID)
public class GCYMEventHandlers {

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void registerMaterials(GregTechAPI.MaterialEvent event) {
        GCYMMaterials.init();
        GCYMPropertyAddition.init();
    }
}
