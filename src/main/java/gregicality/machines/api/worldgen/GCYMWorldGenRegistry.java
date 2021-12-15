package gregicality.machines.api.worldgen;

import gregicality.machines.api.utils.GTMALog;
import gregtech.api.GTValues;
import gregtech.api.worldgen.config.BedrockFluidDepositDefinition;
import gregtech.api.worldgen.config.OreDepositDefinition;
import gregtech.api.worldgen.config.WorldGenRegistry;
import net.minecraftforge.fml.common.Loader;
import org.apache.commons.io.IOUtils;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class GCYMWorldGenRegistry {

    public static final GCYMWorldGenRegistry INSTANCE = new GCYMWorldGenRegistry();

    private final Map<Path, List<String>> oreVeinsToAdd = new HashMap<>();
    private final Map<Path, List<String>> fluidVeinsToAdd = new HashMap<>();

    private GCYMWorldGenRegistry() {

    }

    public void addRemoveVeins() throws IOException {
        Path configPath = Loader.instance().getConfigDir().toPath().resolve(GTValues.MODID);
        // The folder where worldgen definitions are stored
        Path worldgenRootPath = configPath.resolve("worldgen");

        // The folder where gcym worldgen definitions are stored
        Path gcymWorldgenRootPath = worldgenRootPath.resolve("gcym");

        // The folder where all physical veins are stored
        Path veinPath = gcymWorldgenRootPath.resolve("vein");
        if (!Files.exists(veinPath))
            Files.createDirectories(veinPath);

        // The folder where all bedrock fluid veins are stored
        Path bedrockVeinPath = gcymWorldgenRootPath.resolve("fluid");
        if (!Files.exists(bedrockVeinPath))
            Files.createDirectories(bedrockVeinPath);

        // Retrieve the defaults from the mod jar
        extractJarVeinDefinitions(configPath, veinPath, oreVeinsToAdd);
        extractJarVeinDefinitions(configPath, bedrockVeinPath, fluidVeinsToAdd);

//        GTMALog.logger.info("Vein Size Before Addition: " + WorldGenRegistry.getOreDeposits().size());
//        GTMALog.logger.info("Fluid Vein Size Before Addition: " + WorldGenRegistry.getBedrockVeinDeposits().size());
        addVeins();
        addFluidVeins();

        try {
            WorldGenRegistry.INSTANCE.reinitializeRegisteredVeins();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        GTMALog.logger.info("Vein Size After Addition: " + WorldGenRegistry.getOreDeposits().size());
//        GTMALog.logger.info("Fluid Vein Size After Addition: " + WorldGenRegistry.getBedrockVeinDeposits().size());

//        GTMALog.logger.info("Vein Size Before Removals: " + WorldGenRegistry.getOreDeposits().size());
//        GTMALog.logger.info("Fluid Vein Size Before Removals: " + WorldGenRegistry.getBedrockVeinDeposits().size());
        removeVeins();
        removeFluidVeins();

        try {
            WorldGenRegistry.INSTANCE.reinitializeRegisteredVeins();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        GTMALog.logger.info("Vein Size After Removals: " + WorldGenRegistry.getOreDeposits().size());
//        GTMALog.logger.info("Fluid Vein Size After Removals: " + WorldGenRegistry.getBedrockVeinDeposits().size());
    }

    public void addVeins() {

        for (List<String> folder : oreVeinsToAdd.values()) {
            for (String vein : folder) {
                WorldGenRegistry.INSTANCE.addVeinDefinitions(new OreDepositDefinition(vein));
            }
        }
    }

    public void addFluidVeins() {
        for (List<String> folder : fluidVeinsToAdd.values()) {
            for (String vein : folder) {
                WorldGenRegistry.INSTANCE.addVeinDefinitions(new BedrockFluidDepositDefinition(vein));
            }
        }
    }

    public void removeVeins() {
        for (int i = 0; i < WorldGenRegistry.getOreDeposits().size(); i++) {
            OreDepositDefinition definition = WorldGenRegistry.getOreDeposits().get(i);

            if (definition.getDepositName().equals("end/naquadah_vein.json")) {
                GTMALog.logger.info("end/naquadah_vein.json VEIN DETECTED!");

                WorldGenRegistry.INSTANCE.removeVeinDefinitions(definition);
            }
        }
    }

    public void removeFluidVeins() {
        for (int i = 0; i < WorldGenRegistry.getBedrockVeinDeposits().size(); i++) {
            BedrockFluidDepositDefinition definition = WorldGenRegistry.getBedrockVeinDeposits().get(i);

            if (definition.getDepositName().equals("nether/lava_deposit.json")) {
                GTMALog.logger.info("nether/lava_deposit.json DEPOSIT DETECTED!");

                WorldGenRegistry.INSTANCE.removeVeinDefinitions(definition);
            }
        }
    }

    /**
     * Extracts files from the GCYM jar and places them in the specified location
     *
     * @param configPath The path of the config root for the Gregtech mod
     * @param targetPath The path of the target location where the files will be initialized
     * @throws IOException
     */
    private static void extractJarVeinDefinitions(@Nonnull Path configPath, Path targetPath, Map<Path, List<String>> oreVeinsToAdd) throws IOException {
        // The path of the worldgen folder in the config folder
        Path worldgenRootPath = configPath.resolve("worldgen");
        // The path of the worldgen folder in the config folder
        Path gcymWorldgenRootPath = worldgenRootPath.resolve("gcym");
        // The path of the physical vein folder in the config folder
        Path oreVeinRootPath = gcymWorldgenRootPath.resolve("vein");
        // The path of the bedrock fluid vein folder in the config folder
        Path bedrockFluidVeinRootPath = gcymWorldgenRootPath.resolve("fluid");
        FileSystem zipFileSystem = null;
        try {
            URI sampleUri = GCYMWorldGenRegistry.class.getResource("/assets/gcy_multiblocks/.gcymassetsroot").toURI();
            // The Path for representing the vein folder in the vein folder in the assets folder in the Gregtech resources folder in the jar
            Path oreVeinJarRootPath;
            // The Path for representing the fluid folder in the vein folder in the assets folder in the Gregtech resources folder in the jar
            Path bedrockFluidJarRootPath;
            if (sampleUri.getScheme().equals("jar") || sampleUri.getScheme().equals("zip")) {
                zipFileSystem = FileSystems.newFileSystem(sampleUri, Collections.emptyMap());
                oreVeinJarRootPath = zipFileSystem.getPath("/assets/gcy_multiblocks/worldgen/vein");
                bedrockFluidJarRootPath = zipFileSystem.getPath("/assets/gcy_multiblocks/worldgen/fluid");
            } else if (sampleUri.getScheme().equals("file")) {
                oreVeinJarRootPath = Paths.get(GCYMWorldGenRegistry.class.getResource("/assets/gcy_multiblocks/worldgen/vein").toURI());
                bedrockFluidJarRootPath = Paths.get(GCYMWorldGenRegistry.class.getResource("/assets/gcy_multiblocks/worldgen/fluid").toURI());
            } else {
                throw new IllegalStateException("Unable to locate absolute path to GCYM worldgen root directory: " + sampleUri);
            }

            // Attempts to extract the worldgen definition jsons
            if (targetPath.compareTo(oreVeinRootPath) == 0) {
                GTMALog.logger.info("Attempting retrieval of standard GCYM worldgen definitions from {} to {}",
                        oreVeinJarRootPath, oreVeinRootPath);
                // Find all the default worldgen files in the assets folder
                List<Path> jarFiles = Files.walk(oreVeinJarRootPath)
                        .filter(Files::isRegularFile)
                        .collect(Collectors.toList());

                // Retrieves the GCYM worldgen files
                for (Path jarFile : jarFiles) {
                    oreVeinsToAdd.computeIfAbsent(jarFile.getParent(), k -> new ArrayList<>());
                    String name = (getActualVeinName(jarFile));
                    oreVeinsToAdd.get(jarFile.getParent()).add(name);
                    Path path = worldgenRootPath.resolve(name);
                    Files.createDirectories(path.getParent());
                    Files.copy(jarFile, path, StandardCopyOption.REPLACE_EXISTING);
                }
                GTMALog.logger.info("Retrieved {} builtin GCYM worldgen vein definitions", jarFiles.size());
            } else if (targetPath.compareTo(bedrockFluidVeinRootPath) == 0) {
                GTMALog.logger.info("Attempting retrieval of standard GCYM worldgen definitions from {} to {}",
                        bedrockFluidJarRootPath, bedrockFluidVeinRootPath);
                // Find all the default worldgen files in the assets folder
                List<Path> jarFiles = Files.walk(bedrockFluidJarRootPath)
                        .filter(Files::isRegularFile)
                        .collect(Collectors.toList());

                // Replaces or creates the default worldgen files
                for (Path jarFile : jarFiles) {
                    oreVeinsToAdd.computeIfAbsent(jarFile.getParent(), k -> new ArrayList<>());
                    String name = (getActualVeinName(jarFile));
                    oreVeinsToAdd.get(jarFile.getParent()).add(name);
                    Path path = worldgenRootPath.resolve(name);
                    Files.createDirectories(path.getParent());
                    Files.copy(jarFile, path, StandardCopyOption.REPLACE_EXISTING);
                }
                GTMALog.logger.info("Retrieved {} builtin worldgen bedrock fluid definitions into fluid folder", jarFiles.size());
            }

        } catch (URISyntaxException impossible) {
            //this is impossible, since getResource always returns valid URI
            throw new RuntimeException(impossible);
        } finally {
            if (zipFileSystem != null) {
                //close zip file system to avoid issues
                IOUtils.closeQuietly(zipFileSystem);
            }
        }
    }

    @Nonnull
    private static String getActualVeinName(@Nonnull Path path) {
        int count = path.getNameCount();
        String separator = FileSystems.getDefault().getSeparator();
        String[] split = path.toString().split("\\\\" + separator);
        return "gcym" + separator + split[count - 2] + separator + split[count - 1] + separator + split[count];
    }
}
