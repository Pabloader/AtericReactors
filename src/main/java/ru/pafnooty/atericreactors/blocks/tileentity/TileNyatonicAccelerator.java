package ru.pafnooty.atericreactors.blocks.tileentity;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import ru.pafnooty.atericreactors.blocks.BlockNyatonicAccelerator;
import ru.pafnooty.atericreactors.items.AtericItems;

/**
 *
 * @author pabloid
 */
public class TileNyatonicAccelerator extends TileMultiBlock implements ISidedInventory {

    public static final int SLOT_INPUT = 0;
    public static final int SLOT_FUEL = 1;
    public static final int SLOT_OUTPUT = 2;

    private ItemStack[] inventory = new ItemStack[this.getSizeInventory()];

    @Override
    public void doMultiBlockStuff() {
    }

    @Override
    public boolean checkMultiBlockForm() {
        int count = 0;
        for (int x = xCoord; x < xCoord + 3; x++) {
            for (int z = zCoord; z < zCoord + 3; z++) {
                TileEntity tile = worldObj.getTileEntity(x, yCoord, z);
                if (tile != null && (tile instanceof TileNyatonicAccelerator)) {
                    TileNyatonicAccelerator acceleratorTile = (TileNyatonicAccelerator) tile;
                    if (this.isMaster()) {
                        if (acceleratorTile.hasMaster()) {
                            count++;
                        }
                    } else if (!acceleratorTile.hasMaster()) {
                        count++;
                    }
                }

            }
        }
        return count == 8 && worldObj.isAirBlock(xCoord + 1, yCoord, zCoord + 1);
    }

    @Override
    public void setupStructure() {
        for (int x = xCoord; x < xCoord + 3; x++) {
            for (int z = zCoord; z < zCoord + 3; z++) {
                TileEntity tile = worldObj.getTileEntity(x, yCoord, z);
                boolean master = (x == xCoord && z == zCoord);
                if (tile != null && (tile instanceof TileNyatonicAccelerator)) {
                    TileNyatonicAccelerator acceleratorTile = (TileNyatonicAccelerator) tile;
                    acceleratorTile.setMasterCoords(xCoord, yCoord, zCoord);
                    acceleratorTile.setHasMaster(true);
                    acceleratorTile.setIsMaster(master);
                    acceleratorTile.inventory = inventory;
                    int metadata;
                    if (master) {
                        metadata = BlockNyatonicAccelerator.ICON_NW;
                    } else if (x == xCoord + 2 && z == zCoord) {
                        metadata = BlockNyatonicAccelerator.ICON_NE;
                    } else if (x == xCoord && z == zCoord + 2) {
                        metadata = BlockNyatonicAccelerator.ICON_SW;
                    } else if (x == xCoord + 2 && z == zCoord + 2) {
                        metadata = BlockNyatonicAccelerator.ICON_SE;
                    } else if (z == zCoord + 1) {
                        metadata = BlockNyatonicAccelerator.ICON_VERTICAL;
                    } else {
                        metadata = BlockNyatonicAccelerator.ICON_HORIZONTAL;
                    }
                    worldObj.setBlockMetadataWithNotify(x, yCoord, z, metadata, 2);
                }
            }
        }
    }

    @Override
    public void resetStructure() {
        for (int i = 0; i < this.getSizeInventory(); i++) {
            ItemStack stack = this.getStackInSlot(i);
            if (stack != null) {
                worldObj.spawnEntityInWorld(new EntityItem(worldObj, xCoord + 1.5, yCoord, zCoord + 1.5, stack));
            }
        }
        for (int x = xCoord; x < xCoord + 3; x++) {
            for (int z = zCoord; z < zCoord + 3; z++) {
                TileEntity tile = worldObj.getTileEntity(x, yCoord, z);
                if (tile != null && (tile instanceof TileNyatonicAccelerator)) {
                    TileNyatonicAccelerator acceleratorTile = (TileNyatonicAccelerator) tile;
                    acceleratorTile.reset();
                }
            }
        }
    }

    @Override
    public void reset() {
        super.reset();
        worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 2);
        this.inventory = new ItemStack[this.getSizeInventory()];
    }

    @Override
    public void masterWriteToNBT(NBTTagCompound tag) {
        NBTTagList list = new NBTTagList();
        for (int i = 0; i < this.getSizeInventory(); ++i) {
            if (this.getStackInSlot(i) != null) {
                NBTTagCompound stackTag = new NBTTagCompound();
                stackTag.setByte("Slot", (byte) i);
                this.getStackInSlot(i).writeToNBT(stackTag);
                list.appendTag(stackTag);
            }
        }
        tag.setTag("Items", list);
    }

    @Override
    public void masterReadFromNBT(NBTTagCompound tag) {
        NBTTagList list = tag.getTagList("Items", 10);
        for (int i = 0; i < list.tagCount(); ++i) {
            NBTTagCompound stackTag = list.getCompoundTagAt(i);
            int slot = stackTag.getByte("Slot") & 255;
            this.setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(stackTag));
        }
    }

    @Override
    public int getSizeInventory() {
        return 3; // input 0, nyaton fuel 1, output
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        if (index < 0 || index >= this.getSizeInventory()) {
            return null;
        }
        return this.inventory[index];
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        if (this.getStackInSlot(index) != null) {
            ItemStack itemstack;

            if (this.getStackInSlot(index).stackSize <= count) {
                itemstack = this.getStackInSlot(index);
                this.setInventorySlotContents(index, null);
                this.markDirty();
                return itemstack;
            } else {
                itemstack = this.getStackInSlot(index).splitStack(count);

                if (this.getStackInSlot(index).stackSize <= 0) {
                    this.setInventorySlotContents(index, null);
                } else {
                    //Just to show that changes happened
                    this.setInventorySlotContents(index, this.getStackInSlot(index));
                }

                this.markDirty();
                return itemstack;
            }
        }
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int index) {
        ItemStack stack = this.getStackInSlot(index);
        this.setInventorySlotContents(index, null);
        return stack;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        if (index < 0 || index >= this.getSizeInventory()) {
            return;
        }

        if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
            stack.stackSize = this.getInventoryStackLimit();
        }

        if (stack != null && stack.stackSize == 0) {
            stack = null;
        }

        this.inventory[index] = stack;
        this.markDirty();
    }

    @Override
    public String getInventoryName() {
        return "container.atericreactors.nyatonic_accelerator";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return this.worldObj.getTileEntity(xCoord, yCoord, zCoord) == this
                && player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) <= 64;
    }

    public static boolean isFuel(ItemStack stack) {
        return stack.getItem() == AtericItems.nyaton || stack.getItem() == AtericItems.ateron;
    }

    public static boolean isAcceleratable(ItemStack stack) {
        return stack.getItem() == AtericItems.nyatoniumIngot || stack.getItem() == AtericItems.ateroniumIngot;
    }

    @Override
    public void openInventory() {
    }

    @Override
    public void closeInventory() {
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack itemStack) {
        switch (index) {
            case SLOT_INPUT:
                return isAcceleratable(itemStack);
            case SLOT_FUEL:
                return isFuel(itemStack);
        }
        return false;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side) {
        switch (side) {
            case 0:
                return new int[]{SLOT_OUTPUT, SLOT_FUEL};
            case 1:
                return new int[]{SLOT_INPUT};
        }
        return new int[]{SLOT_OUTPUT};
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack stack, int side) {
        return this.isItemValidForSlot(slot, stack);
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack stack, int side) {
        return slot == SLOT_OUTPUT;
    }
}
