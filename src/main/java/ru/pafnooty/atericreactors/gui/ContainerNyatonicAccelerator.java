package ru.pafnooty.atericreactors.gui;

/**
 *
 * @author pabloid
 */
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import ru.pafnooty.atericreactors.blocks.tileentity.TileNyatonicAccelerator;

public class ContainerNyatonicAccelerator extends Container {

    private final TileNyatonicAccelerator tileAccelerator;
    private boolean lastFuelLoaded;
    private int lastAcceleratingTicks;

    public ContainerNyatonicAccelerator(InventoryPlayer inventory, TileNyatonicAccelerator accelerator) {
        this.tileAccelerator = accelerator;
        this.addSlotToContainer(new Slot(accelerator, 0, 56, 17));
        this.addSlotToContainer(new Slot(accelerator, 1, 56, 53));
        this.addSlotToContainer(new SlotResult(accelerator, 2, 116, 35));
        int i;

        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return this.tileAccelerator.isUseableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        ItemStack previous = null;
        Slot slot = (Slot) this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack current = slot.getStack();
            previous = current.copy();

            if (index == TileNyatonicAccelerator.SLOT_OUTPUT) {
                if (!this.mergeItemStack(current, 3, 39, true)) {
                    return null;
                }

                slot.onSlotChange(current, previous);
            } else if (index != TileNyatonicAccelerator.SLOT_FUEL && index != TileNyatonicAccelerator.SLOT_INPUT) {
                if (TileNyatonicAccelerator.isAcceleratable(current)) {
                    if (!this.mergeItemStack(current, 0, 1, false)) {
                        return null;
                    }
                } else if (TileNyatonicAccelerator.isFuel(current)) {
                    if (!this.mergeItemStack(current, 1, 2, false)) {
                        return null;
                    }
                } else if (index >= 3 && index < 30) { // from inventory to hotbar
                    if (!this.mergeItemStack(current, 30, 39, false)) {
                        return null;
                    }
                } else if (index >= 30 && index < 39 && !this.mergeItemStack(current, 3, 30, false)) { // from hotbar to inventory
                    return null;
                }
            } else if (!this.mergeItemStack(current, 3, 39, false)) {
                return null;
            }

            if (current.stackSize == 0) {
                slot.putStack((ItemStack) null);
            } else {
                slot.onSlotChanged();
            }

            if (current.stackSize == previous.stackSize) {
                return null;
            }

            slot.onPickupFromSlot(player, current);
        }

        return previous;
    }

    @Override
    public void addCraftingToCrafters(ICrafting iCraft) {
        super.addCraftingToCrafters(iCraft);
        iCraft.sendProgressBarUpdate(this, 0, this.tileAccelerator.isFuelLoaded() ? 1 : 0);
        iCraft.sendProgressBarUpdate(this, 1, this.tileAccelerator.getAcceleratingTicks());
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i) {
            ICrafting icrafting = (ICrafting) this.crafters.get(i);

            if (this.lastFuelLoaded != this.tileAccelerator.isFuelLoaded()) {
                icrafting.sendProgressBarUpdate(this, 0, lastFuelLoaded ? 1 : 0);
            }

            if (this.lastAcceleratingTicks != this.tileAccelerator.getAcceleratingTicks()) {
                icrafting.sendProgressBarUpdate(this, 1, this.tileAccelerator.getAcceleratingTicks());
            }
        }

        this.lastFuelLoaded = this.tileAccelerator.isFuelLoaded();
        this.lastAcceleratingTicks = this.tileAccelerator.getAcceleratingTicks();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void updateProgressBar(int param, int value) {
        if (param == 0) {
            this.tileAccelerator.setFuelLoaded(value != 0);
        }

        if (param == 1) {
            this.tileAccelerator.setAcceleratingTicks(value);
        }
    }
}
