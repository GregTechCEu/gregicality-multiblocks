package gregicality.machines.integration.jei.multiblock.info.standard;

import gregicality.machines.GregicalityMultiblocks;
import gregicality.machines.common.block.GCYMultiMetaBlocks;
import gregicality.machines.common.block.blocks.BlockLargeMultiblockCasing;
import gregicality.machines.common.metatileentities.GCYMultiMetaTileEntities;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.api.unification.material.Materials;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.BlockTurbineCasing;
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

public class LargeMixerInfo extends MultiblockInfoPage {

    @Override
    public MultiblockControllerBase getController() {
        return GCYMultiMetaTileEntities.LARGE_MIXER;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        return Collections.singletonList(MultiblockShapeInfo.builder()
                .aisle("#XXX#", "#XXX#", "#XXX#", "#XXX#", "#XXX#", "##F##")
                .aisle("XXXXX", "X#C#X", "X###X", "X#C#X", "X###X", "##F##")
                .aisle("XXXXX", "XCPCX", "X#P#X", "XCPCX", "X#P#X", "FFGFF")
                .aisle("XXXXX", "X#C#X", "X###X", "X#C#X", "X###X", "##F##")
                .aisle("#OMA#", "#ISf#", "#XEX#", "#XXX#", "#XXX#", "##F##")
                .where('S', getController(), EnumFacing.WEST)
                .where('X', GCYMultiMetaBlocks.LARGE_MULTIBLOCK_CASING.getState(BlockLargeMultiblockCasing.CasingType.MIXER_CASING))
                .where('P', MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.POLYTETRAFLUOROETHYLENE_PIPE))
                .where('C', MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.PTFE_INERT_CASING))
                .where('G', MetaBlocks.TURBINE_CASING.getState(BlockTurbineCasing.TurbineCasingType.STEEL_GEARBOX))
                .where('F', MetaBlocks.FRAMES.get(Materials.StainlessSteel).getDefaultState())
                .where('#', Blocks.AIR.getDefaultState())
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.HV], EnumFacing.WEST)
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[GTValues.HV], EnumFacing.WEST)
                .where('O', MetaTileEntities.ITEM_EXPORT_BUS[GTValues.HV], EnumFacing.WEST)
                .where('f', MetaTileEntities.FLUID_IMPORT_HATCH[GTValues.HV], EnumFacing.WEST)
                .where('A', MetaTileEntities.FLUID_EXPORT_HATCH[GTValues.HV], EnumFacing.WEST)
                .where('M', maintenanceIfEnabled(GCYMultiMetaBlocks.LARGE_MULTIBLOCK_CASING.getState(BlockLargeMultiblockCasing.CasingType.MIXER_CASING)), EnumFacing.WEST)
                .build());
    }

    @Override
    public String[] getDescription() {
        return new String[]{I18n.format(String.format("%s.multiblock.large_mixer.description", GregicalityMultiblocks.MODID))};
    }

    @Override
    public float getDefaultZoom() {
        return 0.8f;
    }

    @Override
    protected void generateBlockTooltips() {
        super.generateBlockTooltips();

        ITextComponent tooltip = new TextComponentTranslation("gregtech.multiblock.preview.limit", 45).setStyle(new Style().setColor(TextFormatting.AQUA));
        addBlockTooltip(GCYMultiMetaBlocks.LARGE_MULTIBLOCK_CASING.getItemVariant(BlockLargeMultiblockCasing.CasingType.MIXER_CASING), tooltip);
    }
}
