/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.pafnooty.atericreactors.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.pafnooty.atericreactors.AtericReactors;
import ru.pafnooty.atericreactors.blocks.tileentity.TileNyatonicAccelerator;

/**
 *
 * @author pabloid
 */
public class BlockNyatonicAccelerator extends BlockMultiBlock {

    public BlockNyatonicAccelerator() {
        super(Material.rock);
        setBlockName("nyatonicAccelerator");
        setBlockTextureName(AtericReactors.MODID + ":nyatonicAccelerator");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileNyatonicAccelerator();
    }
}
