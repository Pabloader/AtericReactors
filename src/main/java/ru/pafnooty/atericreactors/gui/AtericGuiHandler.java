package ru.pafnooty.atericreactors.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import ru.pafnooty.atericreactors.blocks.tileentity.TileNyatonicAccelerator;

public class AtericGuiHandler implements IGuiHandler {

    public static final int NYATONIC_ACCELERATOR_GUI = 0;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case NYATONIC_ACCELERATOR_GUI:
                return new ContainerNyatonicAccelerator(player.inventory, (TileNyatonicAccelerator) world.getTileEntity(x, y, z));
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case NYATONIC_ACCELERATOR_GUI:
                return new GuiNyatonicAccelerator(player.inventory, (TileNyatonicAccelerator) world.getTileEntity(x, y, z));
        }
        return null;
    }
}
