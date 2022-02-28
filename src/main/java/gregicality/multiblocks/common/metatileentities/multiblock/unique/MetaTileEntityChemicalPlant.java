package gregicality.multiblocks.common.metatileentities.multiblock.unique;

import gregicality.multiblocks.api.GCYMValues;
import gregicality.multiblocks.api.capability.impl.GCYMMultiblockRecipeLogic;
import gregicality.multiblocks.api.metatileentity.GCYMRecipeMapMultiblockController;
import gregicality.multiblocks.api.render.GCYMTextures;
import gregicality.multiblocks.api.unification.GCYMMaterials;
import gregicality.multiblocks.common.block.GCYMMetaBlocks;
import gregicality.multiblocks.common.block.blocks.BlockUniqueCasing;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.BlockGlassCasing;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.items.MetaItems;
import gregtech.common.metatileentities.MetaTileEntities;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class MetaTileEntityChemicalPlant extends GCYMRecipeMapMultiblockController {

    public MetaTileEntityChemicalPlant(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, determineRecipeMaps());
        if (determineRecipeMaps().length > 1)
            this.recipeMapWorkable = new ChemicalPlantRecipeLogic(this);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder metaTileEntityHolder) {
        return new MetaTileEntityChemicalPlant(this.metaTileEntityId);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("X###X", "XXXXX", "XGGGX", "XXXXX", "X###X")
                .aisle("X###X", "XXXXX", "XAAAX", "XXXXX", "X###X")
                .aisle("XPPPX", "XAAAX", "XCCCX", "XAAAX", "XPPPX")
                .aisle("X###X", "XXXXX", "XAAAX", "XXXXX", "X###X")
                .aisle("X###X", "XXSXX", "XGGGX", "XXXXX", "X###X")
                .where('S', selfPredicate())
                .where('X', states(getCasingState()).setMinGlobalLimited(50).or(autoAbilities()))
                .where('G', states(getCasingState2()))
                .where('P', states(getCasingState3()))
                .where('C', states(getCasingState4()))
                .where('A', air())
                .where('#', any())
                .build();
    }

    private IBlockState getCasingState() {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.PTFE_INERT_CASING);
    }

    private IBlockState getCasingState2() {
        return MetaBlocks.TRANSPARENT_CASING.getState(BlockGlassCasing.CasingType.TEMPERED_GLASS);
    }

    private IBlockState getCasingState3() {
        return MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.POLYTETRAFLUOROETHYLENE_PIPE);
    }

    private IBlockState getCasingState4() {
        return GCYMMetaBlocks.UNIQUE_CASING.getState(BlockUniqueCasing.UniqueCasingType.MOLYBDENUM_DISILICIDE_COIL);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return Textures.INERT_PTFE_CASING;
    }

    @Nonnull
    @Override
    protected OrientedOverlayRenderer getFrontOverlay() {
        return GCYMTextures.CHEMICAL_PLANT_OVERLAY;
    }

    @Override
    public boolean canBeDistinct() {
        return true;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, @Nonnull List<String> tooltip, boolean advanced) {
        tooltip.add(I18n.format("gcym.deprecation.tooltip"));
        super.addInformation(stack, player, tooltip, advanced);
        if (determineRecipeMaps().length > 1)
            tooltip.add(I18n.format("gcym.machine.chemical_plant.tooltip.1"));
    }

    @Nonnull
    private static RecipeMap<?>[] determineRecipeMaps() {
        RecipeMap<?> chemical_plant = RecipeMap.getByName("chemical_plant");
        if (Loader.isModLoaded(GCYMValues.GCYS_MODID) && chemical_plant != null) {
            return new RecipeMap<?>[]{RecipeMaps.LARGE_CHEMICAL_RECIPES, chemical_plant};
        }
        return new RecipeMap<?>[]{RecipeMaps.LARGE_CHEMICAL_RECIPES};
    }

    @Override
    public void getDrops(@Nonnull NonNullList<ItemStack> dropsList, @Nullable EntityPlayer harvester) {
        dropsList.clear();
        dropsList.add(OreDictUnifier.get(OrePrefix.pipeLargeFluid, Materials.Polybenzimidazole, 2));
        dropsList.add(OreDictUnifier.get(OrePrefix.spring, GCYMMaterials.MolybdenumDisilicide, 2));
        dropsList.add(MetaItems.QUANTUM_MAINFRAME_ZPM.getStackForm(2));
        dropsList.add(OreDictUnifier.get(OrePrefix.rotor, Materials.Iridium));
        dropsList.add(MetaItems.ELECTRIC_MOTOR_IV.getStackForm());
        dropsList.add(MetaTileEntities.LARGE_CHEMICAL_REACTOR.getStackForm());
    }

    @SuppressWarnings("InnerClassMayBeStatic")
    private class ChemicalPlantRecipeLogic extends GCYMMultiblockRecipeLogic {

        public ChemicalPlantRecipeLogic(RecipeMapMultiblockController tileEntity) {
            super(tileEntity);
        }

        @Override
        public int getParallelLimit() {
            return getRecipeMap().equals(RecipeMaps.LARGE_CHEMICAL_RECIPES) ? super.getParallelLimit() : 1;
        }
    }
}
