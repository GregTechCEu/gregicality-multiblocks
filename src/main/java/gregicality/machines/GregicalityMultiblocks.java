package gregicality.machines;

import gregicality.machines.api.utils.GTMALog;
import gregicality.machines.common.CommonProxy;
import gregicality.machines.common.block.GCYMultiMetaBlocks;
import gregicality.machines.common.metatileentities.GCYMultiMetaTileEntities;
import gregtech.api.worldgen.config.BedrockFluidDepositDefinition;
import gregtech.api.worldgen.config.OreDepositDefinition;
import gregtech.api.worldgen.config.WorldGenRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.IOException;

@Mod(   modid        = GregicalityMultiblocks.MODID,
        name         = GregicalityMultiblocks.NAME,
        version      = GregicalityMultiblocks.VERSION,
        dependencies = "required-after:gregtech;")
public class GregicalityMultiblocks {

    public static final String MODID = "gcy_multiblocks";
    public static final String NAME = "Gregicality: Multiblocks";
    public static final String VERSION = "@VERSION@";

    @SidedProxy(modId = MODID, clientSide = "gregicality.machines.common.ClientProxy", serverSide = "gregicality.machines.common.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        GTMALog.init(event.getModLog());
        proxy.preLoad();

        GCYMultiMetaBlocks.init();
        GCYMultiMetaTileEntities.init();
    }

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        GTMALog.logger.fatal("Vein Size Before Removals: " + WorldGenRegistry.getOreDeposits().size());
        for (int i = 0; i < WorldGenRegistry.getOreDeposits().size(); i++) {
            OreDepositDefinition definition = WorldGenRegistry.getOreDeposits().get(i);

            if (definition.getDepositName().equals("end/naquadah_vein.json")) {
                GTMALog.logger.fatal("end/naquadah_vein.json VEIN DETECTED!");

                WorldGenRegistry.INSTANCE.removeVeinDefinitions(definition);

                try {
                    WorldGenRegistry.INSTANCE.reinitializeRegisteredVeins();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        GTMALog.logger.fatal("Vein Size After Removals: " + WorldGenRegistry.getOreDeposits().size());

        GTMALog.logger.fatal("Deposit Size Before Removals: " + WorldGenRegistry.getBedrockVeinDeposits().size());
        for (int i = 0; i < WorldGenRegistry.getBedrockVeinDeposits().size(); i++) {
            BedrockFluidDepositDefinition definition = WorldGenRegistry.getBedrockVeinDeposits().get(i);

            if (definition.getDepositName().equals("nether/lava_deposit.json")) {
                GTMALog.logger.fatal("nether/lava_deposit.json DEPOSIT DETECTED!");

                WorldGenRegistry.INSTANCE.removeVeinDefinitions(definition);

                try {
                    WorldGenRegistry.INSTANCE.reinitializeRegisteredVeins();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        GTMALog.logger.fatal("Deposit Size After Removals: " + WorldGenRegistry.getBedrockVeinDeposits().size());



        GTMALog.logger.fatal("Vein Size Before Addition: " + WorldGenRegistry.getOreDeposits().size());

        WorldGenRegistry.INSTANCE.addVeinDefinitions(new OreDepositDefinition("end/custom_vein.json"));

        try {
            WorldGenRegistry.INSTANCE.reinitializeRegisteredVeins();
        } catch (IOException e) {
            e.printStackTrace();
        }

        GTMALog.logger.fatal("Vein Size After Addition: " + WorldGenRegistry.getBedrockVeinDeposits().size());

        GTMALog.logger.fatal("Fluid Vein Size Before Addition: " + WorldGenRegistry.getBedrockVeinDeposits().size());

        WorldGenRegistry.INSTANCE.addVeinDefinitions(new BedrockFluidDepositDefinition("nether/custom_deposit.json"));

        try {
            WorldGenRegistry.INSTANCE.reinitializeRegisteredVeins();
        } catch (IOException e) {
            e.printStackTrace();
        }

        GTMALog.logger.fatal("Fluid Vein Size After Addition: " + WorldGenRegistry.getBedrockVeinDeposits().size());
    }
}
