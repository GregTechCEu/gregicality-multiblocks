package gregicality.multiblocks.common;

import gregicality.multiblocks.GregicalityMultiblocks;
import gregtech.api.GTValues;
import net.minecraftforge.common.config.Config;

@Config(modid = GregicalityMultiblocks.MODID)
public class GCYMConfigHolder {

    @Config.Comment("Config options applying to all GCYM Multiblocks")
    @Config.Name("Global Multiblock Options")
    public static GlobalMultiblocks globalMultiblocks = new GlobalMultiblocks();

    public static class GlobalMultiblocks {

        @Config.Comment({"Makes nearly every GCYM Multiblock require specialized hatches which set their maximum voltages.", "Default: true"})
        public boolean enableTieredCasings = true;

        @Config.Comment({"The base voltage tier of tiered GCYM Multiblocks.", "Default: 5"})
        @Config.RangeInt(min = GTValues.ULV, max = GTValues.MAX)
        public int baseMultiblockTier = GTValues.IV;
    }
}
