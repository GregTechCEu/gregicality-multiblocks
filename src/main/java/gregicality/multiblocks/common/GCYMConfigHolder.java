package gregicality.multiblocks.common;

import gregicality.multiblocks.GregicalityMultiblocks;
import net.minecraftforge.common.config.Config;

@Config(modid = GregicalityMultiblocks.MODID)
public class GCYMConfigHolder {

    @Config.Comment("Config options applying to all GCYM Multiblocks")
    @Config.Name("Global Multiblock Options")
    public static GlobalMultiblocks globalMultiblocks = new GlobalMultiblocks();

    public static class GlobalMultiblocks {

        @Config.Comment({"Makes nearly every GCYM Multiblock require blocks which set their maximum voltages.", "Default: false"})
        public boolean enableTieredCasings = false;
    }
}
