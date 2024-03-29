package gregicality.multiblocks.common;

import net.minecraftforge.common.config.Config;

import gregicality.multiblocks.GregicalityMultiblocks;

@Config(modid = GregicalityMultiblocks.MODID)
public class GCYMConfigHolder {

    @Config.Comment("Config options applying to all GCYM Multiblocks")
    @Config.Name("Global Multiblock Options")
    public static GlobalMultiblocks globalMultiblocks = new GlobalMultiblocks();

    @Config.Comment("Config settings applying to the Linear Forging Furnace")
    @Config.Name("Linear Forging Furnace Settings")
    public static LinearForgingFurnaceSettings linearForgingFurnaceSettings = new LinearForgingFurnaceSettings();

    public static class GlobalMultiblocks {

        @Config.Comment({ "Makes nearly every GCYM Multiblock require blocks which set their maximum voltages.",
                "Default: false" })
        public boolean enableTieredCasings = false;
    }

    public static class LinearForgingFurnaceSettings {

        @Config.Comment({ "Sets the multiplier to duration applied for cooling recipes.",
                "Default: 1" })
        @Config.RequiresMcRestart
        @Config.RangeDouble(
                            min = 0.1,
                            max = 2.0)
        public double coolingDurationModifier = 1.0;

        @Config.Comment({ "Sets the flat temperature penalty applied for cooling recipes.",
                "Default: 0" })
        @Config.RequiresMcRestart
        @Config.RangeInt(
                         min = 0,
                         max = 2000)
        public int coolingTemperaturePenalty = 0;

        @Config.Comment({ "Sets the multiplier to duration applied for forging recipes.",
                "Default: 1.05" })
        @Config.RequiresMcRestart
        @Config.RangeDouble(
                            min = 0.1,
                            max = 2.0)
        public double forgingDurationModifier = 1.05;

        @Config.Comment({ "Sets the flat temperature penalty applied for forging recipes.",
                "Default: 200" })
        @Config.RequiresMcRestart
        @Config.RangeInt(
                         min = 0,
                         max = 2000)
        public int forgingTemperaturePenalty = 200;
    }
}
