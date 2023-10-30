package gregicality.multiblocks.api.utils;

import net.minecraft.util.ResourceLocation;

import org.jetbrains.annotations.NotNull;

import gregicality.multiblocks.GregicalityMultiblocks;

public final class GCYMUtil {

    public static @NotNull ResourceLocation gcymId(@NotNull String path) {
        return new ResourceLocation(GregicalityMultiblocks.MODID, path);
    }

    private GCYMUtil() {}
}
