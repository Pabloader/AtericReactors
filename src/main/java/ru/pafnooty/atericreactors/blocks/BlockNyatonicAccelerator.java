/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.pafnooty.atericreactors.blocks;

import java.util.ArrayList;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import ru.pafnooty.atericreactors.AtericReactors;
import ru.pafnooty.atericreactors.blocks.tileentity.TileNyatonicAccelerator;
import ru.pafnooty.atericreactors.gui.AtericGuiHandler;

/**
 *
 * @author pabloid
 */
public class BlockNyatonicAccelerator extends BlockMultiBlock {

    public static final int ICON_BASE = 0;
    public static final int ICON_NW = 1;
    public static final int ICON_NE = 2;
    public static final int ICON_SW = 3;
    public static final int ICON_SE = 4;
    public static final int ICON_VERTICAL = 5;
    public static final int ICON_HORIZONTAL = 6;

    public IIcon[] icons = new IIcon[7];

    public BlockNyatonicAccelerator() {
        super(Material.rock);
        setBlockName("nyatonicAccelerator");
        setBlockTextureName(AtericReactors.MODID + ":nyatonicAccelerator");
        setHardness(2.5f);
    }

    @Override
    public void registerBlockIcons(IIconRegister reg) {
        this.icons[ICON_BASE] = reg.registerIcon(this.textureName);
        this.icons[ICON_NW] = reg.registerIcon(this.textureName + "-nw");
        this.icons[ICON_NE] = reg.registerIcon(this.textureName + "-ne");
        this.icons[ICON_SW] = reg.registerIcon(this.textureName + "-sw");
        this.icons[ICON_SE] = reg.registerIcon(this.textureName + "-se");
        this.icons[ICON_VERTICAL] = reg.registerIcon(this.textureName + "-v");
        this.icons[ICON_HORIZONTAL] = reg.registerIcon(this.textureName + "-h");
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        if (side == 0) {
            return this.icons[ICON_BASE];
        }
        if (side != 1) {
            return this.icons[ICON_HORIZONTAL];
        }

        return this.icons[meta % icons.length];
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileNyatonicAccelerator();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            TileEntity tile = world.getTileEntity(x, y, z);
            if (tile != null && tile instanceof TileNyatonicAccelerator) {
                TileNyatonicAccelerator acceleratorTile = (TileNyatonicAccelerator) tile;
                if (!acceleratorTile.hasMaster()) {
                    return false;
                }
            }
            player.openGui(AtericReactors.instance, AtericGuiHandler.NYATONIC_ACCELERATOR_GUI, world, x, y, z);
        }
        return true;
    }
}
