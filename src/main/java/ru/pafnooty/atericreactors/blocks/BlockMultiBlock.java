package ru.pafnooty.atericreactors.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.pafnooty.atericreactors.blocks.tileentity.TileMultiBlock;

/**
 *
 * @author pabloid
 */
public class BlockMultiBlock extends BlockContainer {

    public BlockMultiBlock(Material material) {
        super(material);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        TileEntity tile = world.getTileEntity(x, y, z);
        if (tile != null && tile instanceof TileMultiBlock) {
            TileMultiBlock multiBlock = (TileMultiBlock) tile;
            if (multiBlock.hasMaster()) {
                TileMultiBlock master = multiBlock.getMaster();
                if (master != null && !master.checkMultiBlockForm()) {
                    master.resetStructure();
                }
            }
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return null;
    }
}
