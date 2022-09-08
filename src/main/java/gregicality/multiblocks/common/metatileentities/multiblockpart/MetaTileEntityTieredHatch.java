package gregicality.multiblocks.common.metatileentities.multiblockpart;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregicality.multiblocks.api.metatileentity.GCYMMultiblockAbility;
import gregicality.multiblocks.api.metatileentity.IGCYMMultiBlock;
import gregicality.multiblocks.api.metatileentity.ITieredHatch;
import gregicality.multiblocks.api.render.GCYMTextures;
import gregicality.multiblocks.common.GCYMConfigHolder;
import gregtech.api.GTValues;
import gregtech.api.capability.impl.NotifiableItemStackHandler;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.widgets.AdvancedTextWidget;
import gregtech.api.gui.widgets.SlotWidget;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockAbilityPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityMultiblockPart;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MetaTileEntityTieredHatch extends MetaTileEntityMultiblockPart implements IMultiblockAbilityPart<ITieredHatch>, ITieredHatch {

    private int cachedMaxVoltage = -1;

    public MetaTileEntityTieredHatch(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GCYMConfigHolder.globalMultiblocks.baseMultiblockTier);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity metaTileEntityHolder) {
        return new MetaTileEntityTieredHatch(metaTileEntityId);
    }

    @Override
    protected IItemHandlerModifiable createImportItemHandler() {
        return new NotifiableItemStackHandler(6, this, false) {
            @Override
            public void onContentsChanged(int slot) {
                super.onContentsChanged(slot);
                updateMaxVoltageTier(true);
            }
        };
    }

    @Override
    public void update() {
        super.update();
        if (!getWorld().isRemote && !this.notifiedItemInputList.isEmpty()) {
            MultiblockControllerBase controllerBase = getController();
            updateMaxVoltageTier(controllerBase != null && !controllerBase.isActive());
        }
    }

    @Override
    protected ModularUI createUI(EntityPlayer entityPlayer) {
        ModularUI.Builder builder = ModularUI.builder(GuiTextures.BACKGROUND, 176, 18 * 5 + 82)
                .label(6, 6, getMetaFullName());

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 2; y++) {
                int index = x * 2 + y;
                builder.widget(new SlotWidget(importItems, index,
                        (79 - 2 * 9 + x * 18), 18 + y * 18, true, true)
                        .setBackgroundTexture(GuiTextures.SLOT));
            }
        }
        builder.image(68, 18 * 4 - 12, 18 * 2 + 4, 22, GuiTextures.DISPLAY);
        builder.widget(new AdvancedTextWidget(82, 18 * 4 - 4, this::addDisplayText, 0xFFFFFF));

        return builder.bindPlayerInventory(entityPlayer.inventory, GuiTextures.SLOT, 7, 18 * 5)
                .build(getHolder(), entityPlayer);
    }

    protected void addDisplayText(@Nonnull List<ITextComponent> list) {
        int tier = this.cachedMaxVoltage;
        if (tier < 0) tier = GCYMConfigHolder.globalMultiblocks.baseMultiblockTier;
        list.add(new TextComponentString(GTValues.VNF[tier]).setStyle(new Style()
                .setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new TextComponentTranslation("gcym.machine.tiered_hatch.info")))));
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        GCYMTextures.TIERED_HATCH_OVERLAY.renderOrientedState(renderState, translation, pipeline, getFrontFacing(), false, false);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gcym.machine.tiered_hatch.tooltip.1", GTValues.VNF[getTier()]));
    }

    @Override
    public MultiblockAbility<ITieredHatch> getAbility() {
        return GCYMMultiblockAbility.TIERED_HATCH;
    }

    @Override
    public void registerAbilities(@Nonnull List<ITieredHatch> list) {
        list.add(this);
    }

    protected void updateMaxVoltageTier(boolean forceRecheck) {
        MultiblockControllerBase controllerBase = getController();
        if (controllerBase instanceof IGCYMMultiBlock) {
            Map<Set<ItemStack>, Integer> tierMap = TIERED_COMPONENTS.get(controllerBase.metaTileEntityId);
            if (tierMap == null) {
                // if this multiblock doesn't have tiered requirements, the tier is the default
                this.cachedMaxVoltage = this.getTier();
                if (forceRecheck) forceControllerRecheck(controllerBase);
                return;
            }

            // convert the hatch inventory into a list
            List<ItemStack> inventory = new ArrayList<>();
            for (int i = 0; i < this.itemInventory.getSlots(); i++) {
                ItemStack stack = this.itemInventory.getStackInSlot(i);
                if (!stack.isEmpty()) inventory.add(stack);
            }

            // try every tier entry for this multi
            for (Map.Entry<Set<ItemStack>, Integer> entry : tierMap.entrySet()) {
                boolean matchedStacks = false;
                for (ItemStack stack : entry.getKey()) {
                    if (inventory.isEmpty()) {
                        // if we have nothing in the hatch, and there are still items to check, fail
                        if (matchedStacks) {
                            matchedStacks = false;
                        }
                        break;
                    }

                    // check every item in stored against the current required item
                    boolean matched = false;
                    for (int i = 0; i < inventory.size(); i++) {
                        ItemStack itemStack = inventory.get(i);
                        // match item and stack
                        if (stack.isItemEqual(itemStack) && stack.getCount() <= itemStack.getCount()) {
                            // if the item in the inventory is satisfactory, remove it from the possibilities to check
                            inventory.remove(i);
                            matched = true;
                            matchedStacks = true;
                            break;
                        }
                    }

                    // if we did not have the required item, fail
                    if (!matched) {
                        matchedStacks = false;
                        break;
                    }
                }
                if (matchedStacks) {
                    // if everything is matched, cache the new max voltage and check for new recipes
                    this.cachedMaxVoltage = entry.getValue();
                    if (forceRecheck) forceControllerRecheck(controllerBase);
                    return;
                }
            }
            // if no tier was found, revert to the minimum provided
            this.cachedMaxVoltage = this.getTier();
            if (forceRecheck) forceControllerRecheck(controllerBase);
        }
    }

    protected void forceControllerRecheck(MultiblockControllerBase controllerBase) {
        if (controllerBase instanceof RecipeMapMultiblockController) {
            RecipeMapMultiblockController controller = (RecipeMapMultiblockController) controllerBase;
            if (!controller.isActive()) {
                controller.getRecipeMapWorkable().forceRecipeRecheck();
            }
        }
    }

    @Override
    public int getMaxVoltageTier() {
        if (this.cachedMaxVoltage > -1) {
            // if there is a current cached voltage, use it
            return this.cachedMaxVoltage;
        }

        if (!this.notifiedItemInputList.isEmpty()) {
            // if the inventory changes, update the voltage and attempt to use it
            updateMaxVoltageTier(false);
            if (this.cachedMaxVoltage > -1) {
                return this.cachedMaxVoltage;
            }
        }

        // default to the minimum voltage
        return this.getTier();
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        data.setInteger("maxVoltage", this.cachedMaxVoltage);
        return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        if (data.hasKey("maxVoltage")) {
            this.cachedMaxVoltage = data.getInteger("maxVoltage");
        } else {
            this.cachedMaxVoltage = GCYMConfigHolder.globalMultiblocks.baseMultiblockTier;
        }
    }

    @Override
    public void writeInitialSyncData(PacketBuffer buf) {
        super.writeInitialSyncData(buf);
        buf.writeInt(this.cachedMaxVoltage);
    }

    @Override
    public void receiveInitialSyncData(PacketBuffer buf) {
        super.receiveInitialSyncData(buf);
        this.cachedMaxVoltage = buf.readInt();
    }
}
