package gregicality.multiblocks.common.metatileentities.multiblock.generator;

import gregicality.multiblocks.api.render.GCYMTextures;
import gregicality.multiblocks.common.block.GCYMMetaBlocks;
import gregicality.multiblocks.common.block.blocks.BlockLargeMultiblockCasing;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.FuelMultiblockController;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.recipes.RecipeMaps;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.common.blocks.BlockTurbineCasing;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class MetaTileEntitySteamEngine extends FuelMultiblockController {

    public MetaTileEntitySteamEngine(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, RecipeMaps.STEAM_TURBINE_FUELS, GTValues.MV);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity metaTileEntityHolder) {
        return new MetaTileEntitySteamEngine(metaTileEntityId);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("#XX", "XEX", "#XX")
                .aisle("XXX", "XGX", "XMX")
                .aisle("#XX", "XGX", "#XX")
                .aisle("#XX", "#SX", "#XX")
                .where('S', selfPredicate())
                .where('X', states(getCasingState()).setMinGlobalLimited(18)
                        .or(autoAbilities(false, true, true, true, true, true, false)))
                .where('G', states(getCasingState2()))
                .where('E', abilities(MultiblockAbility.OUTPUT_ENERGY))
                .where('M', abilities(MultiblockAbility.MUFFLER_HATCH))
                .where('#', any())
                .build();
    }

    private IBlockState getCasingState() {
        return GCYMMetaBlocks.LARGE_MULTIBLOCK_CASING.getState(BlockLargeMultiblockCasing.CasingType.STEAM_CASING);
    }

    private IBlockState getCasingState2() {
        return MetaBlocks.TURBINE_CASING.getState(BlockTurbineCasing.TurbineCasingType.BRONZE_GEARBOX);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gcym.machine.steam_engine.tooltip.1", GTValues.VNF[GTValues.MV]));
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return GCYMTextures.STEAM_CASING;
    }

    @Nonnull
    @Override
    protected ICubeRenderer getFrontOverlay() {
        return GCYMTextures.STEAM_ENGINE_OVERLAY;
    }

    @Override
    public boolean hasMufflerMechanics() {
        return true;
    }

    @Override
    public void runMufflerEffect(float xPos, float yPos, float zPos, float xSpd, float ySpd, float zSpd) {
        this.getWorld().spawnParticle(EnumParticleTypes.CLOUD, xPos, yPos, zPos, xSpd, ySpd, zSpd);
    }
}
