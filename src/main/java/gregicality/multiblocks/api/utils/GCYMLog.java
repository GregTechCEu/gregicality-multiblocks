package gregicality.multiblocks.api.utils;

import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public final class GCYMLog {

    public static Logger logger;

    private GCYMLog() {}

    public static void init(@NotNull Logger modLogger) {
        logger = modLogger;
    }
}
