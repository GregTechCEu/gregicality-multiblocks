package gregicality.multiblocks.common.metatileentities.multiblock.standard;

import static gregtech.api.util.RelativeDirection.*;

import java.util.List;
import java.util.function.Function;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.IItemHandlerModifiable;

import org.jetbrains.annotations.NotNull;

import gregtech.api.capability.IDistillationTower;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.impl.DistillationTowerLogicHandler;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.util.*;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.core.sound.GTSoundEvents;

import gregicality.multiblocks.api.capability.impl.GCYMMultiblockRecipeLogic;
import gregicality.multiblocks.api.metatileentity.GCYMMultiblockAbility;
import gregicality.multiblocks.api.metatileentity.GCYMRecipeMapMultiblockController;
import gregicality.multiblocks.api.render.GCYMTextures;
import gregicality.multiblocks.common.block.GCYMMetaBlocks;
import gregicality.multiblocks.common.block.blocks.BlockLargeMultiblockCasing;

/**
 * Requires an unfortunate amount of copy-pasted logic from
 * {@link gregtech.common.metatileentities.multi.electric.MetaTileEntityDistillationTower}
 */
public class MetaTileEntityLargeDistillery extends GCYMRecipeMapMultiblockController implements IDistillationTower {

    protected final DistillationTowerLogicHandler handler;

    public MetaTileEntityLargeDistillery(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, new RecipeMap[] { RecipeMaps.DISTILLATION_RECIPES, RecipeMaps.DISTILLERY_RECIPES });
        this.recipeMapWorkable = new LargeDistilleryRecipeLogic(this);
        this.handler = new DistillationTowerLogicHandler(this);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity metaTileEntityHolder) {
        return new MetaTileEntityLargeDistillery(this.metaTileEntityId);
    }

    /**
     * Used if MultiblockPart Abilities need to be sorted a certain way, like
     * Distillation Tower and Assembly Line. <br>
     * <br>
     * There will be <i>consequences</i> if this is changed. Make sure to set the logic handler to one with
     * a properly overriden {@link DistillationTowerLogicHandler#determineOrderedFluidOutputs()}
     */
    @Override
    protected Function<BlockPos, Integer> multiblockPartSorter() {
        return RelativeDirection.UP.getSorter(getFrontFacing(), getUpwardsFacing(), isFlipped());
    }

    /**
     * Whether this multi can be rotated or face upwards. <br>
     * <br>
     * There will be <i>consequences</i> if this returns true. Make sure to set the logic handler to one with
     * a properly overriden {@link DistillationTowerLogicHandler#determineOrderedFluidOutputs()}
     */
    @Override
    public boolean allowsExtendedFacing() {
        return false;
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        if (isStructureFormed()) {
            FluidStack stackInTank = importFluids.drain(Integer.MAX_VALUE, false);
            if (stackInTank != null && stackInTank.amount > 0) {
                ITextComponent fluidName = TextComponentUtil.setColor(GTUtility.getFluidTranslation(stackInTank),
                        TextFormatting.AQUA);
                textList.add(TextComponentUtil.translationWithColor(
                        TextFormatting.GRAY,
                        "gregtech.multiblock.distillation_tower.distilling_fluid",
                        fluidName));
            }
        }
        super.addDisplayText(textList);
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        if (!usesAdvHatchLogic() || this.structurePattern == null) return;
        handler.determineLayerCount(this.structurePattern);
        handler.determineOrderedFluidOutputs();
    }

    protected boolean usesAdvHatchLogic() {
        return getCurrentRecipeMap() == RecipeMaps.DISTILLATION_RECIPES;
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        if (usesAdvHatchLogic())
            this.handler.invalidate();
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        // Different characters use common constraints
        TraceabilityPredicate casingPredicate = states(getCasingState()).setMinGlobalLimited(40);
        TraceabilityPredicate maintenancePredicate = this.hasMaintenanceMechanics() &&
                ConfigHolder.machines.enableMaintenance ?
                        abilities(MultiblockAbility.MAINTENANCE_HATCH).setMinGlobalLimited(1).setMaxGlobalLimited(1) :
                        casingPredicate;
        return FactoryBlockPattern.start(RIGHT, FRONT, DOWN)
                .aisle("#####", "#ZZZ#", "#ZCZ#", "#ZZZ#", "#####")
                .aisle("##X##", "#XAX#", "XAPAX", "#XAX#", "##X##").setRepeatable(1, 12)
                .aisle("#YSY#", "YAAAY", "YATAY", "YAAAY", "#YYY#")
                .aisle("#YYY#", "YYYYY", "YYYYY", "YYYYY", "#YYY#")
                .where('S', selfPredicate())
                .where('Y', casingPredicate.or(abilities(MultiblockAbility.IMPORT_ITEMS))
                        .or(abilities(MultiblockAbility.INPUT_ENERGY).setMinGlobalLimited(1).setMaxGlobalLimited(2))
                        .or(abilities(MultiblockAbility.IMPORT_FLUIDS).setMinGlobalLimited(1))
                        .or(abilities(MultiblockAbility.EXPORT_ITEMS))
                        .or(abilities(GCYMMultiblockAbility.PARALLEL_HATCH).setMaxGlobalLimited(1).setPreviewCount(1))
                        .or(maintenancePredicate))
                .where('X', casingPredicate
                        .or(abilities(MultiblockAbility.EXPORT_FLUIDS)
                                .setMinLayerLimited(1) // TODO parallel logic doesn't support hatch omission without
                                                       // global voiding enabled
                                .setMaxLayerLimited(1, 1)))
                .where('Z', casingPredicate)
                .where('P', states(getCasingState2()))
                .where('C', abilities(MultiblockAbility.MUFFLER_HATCH))
                .where('T', tieredCasing().or(states(getCasingState2())))
                .where('A', air())
                .where('#', any())
                .build();
    }

    @Override
    public boolean allowSameFluidFillForOutputs() {
        return !usesAdvHatchLogic();
    }

    private static IBlockState getCasingState() {
        return GCYMMetaBlocks.LARGE_MULTIBLOCK_CASING.getState(BlockLargeMultiblockCasing.CasingType.WATERTIGHT_CASING);
    }

    private static IBlockState getCasingState2() {
        return MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.STEEL_PIPE);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return GCYMTextures.WATERTIGHT_CASING;
    }

    @Override
    public SoundEvent getBreakdownSound() {
        return GTSoundEvents.BREAKDOWN_ELECTRICAL;
    }

    @Override
    protected @NotNull OrientedOverlayRenderer getFrontOverlay() {
        return GCYMTextures.LARGE_DISTILLERY_OVERLAY;
    }

    @Override
    public int getFluidOutputLimit() {
        if (usesAdvHatchLogic()) return this.handler.getLayerCount();
        else return super.getFluidOutputLimit();
    }

    @Override
    public boolean hasMufflerMechanics() {
        return true;
    }

    @Override
    public boolean isTiered() {
        return false;
    }

    private class LargeDistilleryRecipeLogic extends GCYMMultiblockRecipeLogic {

        public LargeDistilleryRecipeLogic(RecipeMapMultiblockController tileEntity) {
            super(tileEntity);
        }

        @Override
        protected void outputRecipeOutputs() {
            if (usesAdvHatchLogic()) {
                GTTransferUtils.addItemsToItemHandler(getOutputInventory(), false, itemOutputs);
                handler.applyFluidToOutputs(fluidOutputs, true);
            } else {
                super.outputRecipeOutputs();
            }
        }

        @Override
        protected boolean setupAndConsumeRecipeInputs(@NotNull Recipe recipe,
                                                      @NotNull IItemHandlerModifiable importInventory,
                                                      @NotNull IMultipleTankHandler importFluids) {
            if (!usesAdvHatchLogic()) {
                return super.setupAndConsumeRecipeInputs(recipe, importInventory, importFluids);
            }

            this.overclockResults = calculateOverclock(recipe);

            modifyOverclockPost(overclockResults, recipe.getRecipePropertyStorage());

            if (!hasEnoughPower(overclockResults)) {
                return false;
            }

            IItemHandlerModifiable exportInventory = getOutputInventory();

            // We have already trimmed outputs and chanced outputs at this time
            // Attempt to merge all outputs + chanced outputs into the output bus, to prevent voiding chanced outputs
            if (!metaTileEntity.canVoidRecipeItemOutputs() &&
                    !GTTransferUtils.addItemsToItemHandler(exportInventory, true, recipe.getAllItemOutputs())) {
                this.isOutputsFull = true;
                return false;
            }

            // Perform layerwise fluid checks
            if (!metaTileEntity.canVoidRecipeFluidOutputs() &&
                    !handler.applyFluidToOutputs(recipe.getAllFluidOutputs(), false)) {
                this.isOutputsFull = true;
                return false;
            }

            this.isOutputsFull = false;
            if (recipe.matches(true, importInventory, importFluids)) {
                this.metaTileEntity.addNotifiedInput(importInventory);
                return true;
            }
            return false;
        }

        @Override
        protected IMultipleTankHandler getOutputTank() {
            if (usesAdvHatchLogic())
                return handler.getFluidTanks();

            return super.getOutputTank();
        }
    }
}
