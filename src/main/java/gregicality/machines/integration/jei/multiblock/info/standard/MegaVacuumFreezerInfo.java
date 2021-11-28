package gregicality.machines.integration.jei.multiblock.info.standard;

import gregicality.machines.GregicalityMultiblocks;
import gregicality.machines.common.block.GCYMultiMetaBlocks;
import gregicality.machines.common.block.blocks.BlockUniqueCasing;
import gregicality.machines.common.metatileentities.GCYMultiMetaTileEntities;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.BlockTransparentCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

import java.util.Collections;
import java.util.List;

public class MegaVacuumFreezerInfo extends MultiblockInfoPage {

    @Override
    public MultiblockControllerBase getController() {
        return GCYMultiMetaTileEntities.MEGA_VACUUM_FREEZER;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        return Collections.singletonList(MultiblockShapeInfo.builder()
                .aisle("XXXXXXX#KKK", "XXXXXXX#KVK", "XXXXXXX#KVK", "XXXXXXX#KVK", "XXXXXXX#KKK", "XXXXXXX####", "XXXXXXX####")
                .aisle("XXXXXXX#KVK", "XPPPPPPPPPV", "XP#P#PX#VPV", "XPPPPPPPPPV", "XP#P#PX#KVK", "XPPPPPX####", "XXXXXXX####")
                .aisle("XXXXXXX#KVK", "XP#P#PX#VPV", "X#####X#VPV", "XP###PX#VPV", "X#####X#KVK", "XP#P#PX####", "XXXXXXX####")
                .aisle("XXXXXXX#KVK", "XP#P#PPPPPV", "X#####X#VPV", "XP###PPPPPV", "X#####X#KVK", "XP#P#PX####", "XXXXXXX####")
                .aisle("XXXXXXX#KKK", "XPPPPPX#KVK", "XP###PX#KVK", "XP###PX#KVK", "XP###PX#KKK", "XPPPPPX####", "XXXXXXX####")
                .aisle("#OMAEX#####", "#XISFX#####", "#XGGGX#####", "#XGGGX#####", "#XGGGX#####", "#XXXXX#####", "###########")
                .where('S', getController(), EnumFacing.WEST)
                .where('X', MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.ALUMINIUM_FROSTPROOF))
                .where('G', MetaBlocks.TRANSPARENT_CASING.getState(BlockTransparentCasing.CasingType.TEMPERED_GLASS))
                .where('K', MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STAINLESS_CLEAN))
                .where('V', GCYMultiMetaBlocks.UNIQUE_CASING.getState(BlockUniqueCasing.UniqueCasingType.HEAT_VENT))
                .where('P', MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TUNGSTENSTEEL_PIPE))
                .where('#', Blocks.AIR.getDefaultState())
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.HV], EnumFacing.WEST)
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[GTValues.HV], EnumFacing.WEST)
                .where('O', MetaTileEntities.ITEM_EXPORT_BUS[GTValues.HV], EnumFacing.WEST)
                .where('F', MetaTileEntities.FLUID_IMPORT_HATCH[GTValues.HV], EnumFacing.WEST)
                .where('A', MetaTileEntities.FLUID_EXPORT_HATCH[GTValues.HV], EnumFacing.WEST)
                .where('M', maintenanceIfEnabled(MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.ALUMINIUM_FROSTPROOF)), EnumFacing.WEST)
                .build());
    }


    @Override
    public String[] getDescription() {
        return new String[]{I18n.format(String.format("%s.multiblock.mega_vacuum_freezer.description", GregicalityMultiblocks.MODID))};
    }

    @Override
    public float getDefaultZoom() {
        return 0.8f;
    }

    @Override
    protected void generateBlockTooltips() {
        super.generateBlockTooltips();

        ITextComponent tooltip = new TextComponentTranslation("gregtech.multiblock.preview.limit", 140).setStyle(new Style().setColor(TextFormatting.AQUA));
        addBlockTooltip(MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.ALUMINIUM_FROSTPROOF), tooltip);
    }
}
