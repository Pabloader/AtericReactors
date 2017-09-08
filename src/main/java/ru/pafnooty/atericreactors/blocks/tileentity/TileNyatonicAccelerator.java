package ru.pafnooty.atericreactors.blocks.tileentity;

import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

/**
 *
 * @author pabloid
 */
public class TileNyatonicAccelerator extends TileMultiBlock {

    @Override
    public void doMultiBlockStuff() {
        if (worldObj.isAirBlock(xCoord + 1, yCoord, zCoord + 1)) {
            worldObj.setBlock(xCoord + 1, yCoord, zCoord + 1, Blocks.fire);
        }
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
        if (worldObj.isAirBlock(xCoord + 1, yCoord, zCoord + 1)) {
            worldObj.setBlock(xCoord + 1, yCoord, zCoord + 1, Blocks.fire);
        }
    }

    @Override
    public void masterWriteToNBT(NBTTagCompound tag) {
    }

    @Override
    public void masterReadFromNBT(NBTTagCompound tag) {
    }

}
