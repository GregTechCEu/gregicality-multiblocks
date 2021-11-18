package gregicality.machines.common.metatileentities.multiblock.standard;

import gregicality.machines.common.block.GCYMultiMetaBlocks;
import gregicality.machines.common.block.blocks.BlockLargeMultiblockCasing;
import gregicality.machines.common.block.blocks.BlockUniqueCasing;
import gregicality.machines.render.GCYMultiTextures;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.BlockWorldState;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.multiblock.PatternMatchContext;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.recipeproperties.BlastTemperatureProperty;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.render.OrientedOverlayRenderer;
import gregtech.api.util.GTUtility;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.BlockWireCoil;
import gregtech.common.blocks.BlockWireCoil2;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.Predicate;

public class MetaTileEntityAlloyBlastSmelter extends RecipeMapMultiblockController {

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = new MultiblockAbility[]{
            MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_FLUIDS,
            MultiblockAbility.IMPORT_FLUIDS, MultiblockAbility.INPUT_ENERGY
    };

    private int blastFurnaceTemperature;

    public MetaTileEntityAlloyBlastSmelter(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, RecipeMaps.ALLOY_SMELTER_RECIPES);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder metaTileEntityHolder) {
        return new MetaTileEntityAlloyBlastSmelter(this.metaTileEntityId);
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        if (isStructureFormed()) {
            textList.add(new TextComponentTranslation("gregtech.multiblock.blast_furnace.max_temperature", blastFurnaceTemperature)
                    .setStyle(new Style().setColor(TextFormatting.RED)));
        }
        super.addDisplayText(textList);
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        Object type = context.get("CoilType");
        if (type instanceof BlockWireCoil.CoilType) {
            this.blastFurnaceTemperature = ((BlockWireCoil.CoilType) type).getCoilTemperature();
        } else if(type instanceof BlockWireCoil2.CoilType2) {
            this.blastFurnaceTemperature = ((BlockWireCoil2.CoilType2) type).getCoilTemperature();
        } else {
            this.blastFurnaceTemperature = BlockWireCoil.CoilType.CUPRONICKEL.getCoilTemperature();
        }

        if (ConfigHolder.U.GT5u.ebfTemperatureBonuses)
            this.blastFurnaceTemperature += 100 * Math.max(0, GTUtility.getTierByVoltage(getEnergyContainer().getInputVoltage()) - GTValues.MV);
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        this.blastFurnaceTemperature = 0;
    }

    @Override
    public boolean checkRecipe(Recipe recipe, boolean consumeIfSuccess) {
        int recipeRequiredTemp = recipe.getProperty(BlastTemperatureProperty.getInstance(), 0);
        return this.blastFurnaceTemperature >= recipeRequiredTemp;
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("#XXX#", "#CCC#", "#GGG#", "#CCC#", "#XXX#")
                .aisle("XXXXX", "CAAAC", "GAAAG", "CAAAC", "XXXXX")
                .aisle("XXXXX", "CAAAC", "GAAAG", "CAAAC", "XXMXX")
                .aisle("XXXXX", "CAAAC", "GAAAG", "CAAAC", "XXXXX")
                .aisle("#XSX#", "#CCC#", "#GGG#", "#CCC#", "#XXX#")
                .setAmountAtLeast('L', 30)
                .where('S', selfPredicate())
                .where('X', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES))
                        .or(maintenancePredicate(getCasingState())))
                .where('C', heatingCoilPredicate())
                .where('G', statePredicate(getCasingState2()))
                .where('M', abilityPartPredicate(MultiblockAbility.MUFFLER_HATCH))
                .where('A', isAirPredicate())
                .where('#', (tile) -> true)
                .where('L', statePredicate(getCasingState()))
                .build();
    }

    private IBlockState getCasingState() {
        return GCYMultiMetaBlocks.LARGE_MULTIBLOCK_CASING.getState(BlockLargeMultiblockCasing.CasingType.BLAST_CASING);
    }

    public static Predicate<BlockWorldState> heatingCoilPredicate() {
        return blockWorldState -> {
            IBlockState blockState = blockWorldState.getBlockState();
            if ((blockState.getBlock() instanceof BlockWireCoil)) {
                BlockWireCoil blockWireCoil = (BlockWireCoil) blockState.getBlock();
                BlockWireCoil.CoilType coilType = blockWireCoil.getState(blockState);
                Object currentCoilType = blockWorldState.getMatchContext().getOrPut("CoilType", coilType);
                return currentCoilType.toString().equals(coilType.getName());
            } else if ((blockState.getBlock() instanceof BlockWireCoil2)) {
                BlockWireCoil2 blockWireCoil = (BlockWireCoil2) blockState.getBlock();
                BlockWireCoil2.CoilType2 coilType = blockWireCoil.getState(blockState);
                Object currentCoilType = blockWorldState.getMatchContext().getOrPut("CoilType", coilType);
                return currentCoilType.toString().equals(coilType.getName());
            }
            return false;
        };
    }

    private IBlockState getCasingState2() {
        return GCYMultiMetaBlocks.UNIQUE_CASING.getState(BlockUniqueCasing.UniqueCasingType.HEAT_VENT);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return GCYMultiTextures.BLAST_CASING;
    }

    @Nonnull
    @Override
    protected OrientedOverlayRenderer getFrontOverlay() {
        return GCYMultiTextures.ALLOY_BLAST_SMELTER_OVERLAY;
    }

    @Override
    public boolean hasMufflerMechanics() {
        return true;
    }

    @Override
    public boolean canBeDistinct() {
        return true;
    }
}
