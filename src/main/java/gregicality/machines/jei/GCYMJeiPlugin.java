package gregicality.machines.jei;

import gregicality.machines.jei.multiblock.info.standard.*;
import gregicality.machines.jei.multiblock.info.unique.ChemicalPlantInfo;
import gregtech.integration.jei.multiblock.MultiInfoPageEvent;
import gregtech.integration.jei.multiblock.MultiblockInfoRecipeWrapper;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JEIPlugin;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@JEIPlugin
@Mod.EventBusSubscriber
public class GCYMJeiPlugin implements IModPlugin {

    @SubscribeEvent
    public static void registerInfoPages(MultiInfoPageEvent event) {
        // Standard
        event.register("large_macerator", new MultiblockInfoRecipeWrapper(new LargeMaceratorInfo()));
        event.register("large_arc_furnace", new MultiblockInfoRecipeWrapper(new LargeArcFurnaceInfo()));
        event.register("large_assembler", new MultiblockInfoRecipeWrapper(new LargeAssemblerInfo()));
        // event.register("large_alloy_smelter", new MultiblockInfoRecipeWrapper(new LargeAlloySmelterInfo()));
        event.register("large_autoclave", new MultiblockInfoRecipeWrapper(new LargeAutoclaveInfo()));
        event.register("large_bender", new MultiblockInfoRecipeWrapper(new LargeBenderInfo()));
        event.register("large_brewery", new MultiblockInfoRecipeWrapper(new LargeBreweryInfo()));
        event.register("large_centrifuge", new MultiblockInfoRecipeWrapper(new LargeCentrifugeInfo()));
        event.register("large_chemical_bath", new MultiblockInfoRecipeWrapper(new LargeChemicalBathInfo()));
        event.register("large_extractor", new MultiblockInfoRecipeWrapper(new LargeExtractorInfo()));

        // Unique
        event.register("chemical_plant", new MultiblockInfoRecipeWrapper(new ChemicalPlantInfo()));
    }
}
