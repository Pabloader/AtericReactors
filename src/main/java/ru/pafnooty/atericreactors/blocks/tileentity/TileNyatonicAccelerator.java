package ru.pafnooty.atericreactors.blocks.tileentity;

import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import ru.pafnooty.atericreactors.blocks.BlockNyatonicAccelerator;

/**
 *
 * @author pabloid
 */
public class TileNyatonicAccelerator extends TileMultiBlock {

    private boolean activated = false;

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
    }

    @Override
    public void masterWriteToNBT(NBTTagCompound tag) {
        tag.setBoolean("Activated", activated);
    }

    @Override
    public void masterReadFromNBT(NBTTagCompound tag) {
        activated = tag.getBoolean("Activated");
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

}
