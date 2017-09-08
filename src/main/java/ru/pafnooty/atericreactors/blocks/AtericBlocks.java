package ru.pafnooty.atericreactors.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import ru.pafnooty.atericreactors.items.AtericItems;

/**
 *
 * @author pabloid
 */
public class AtericBlocks {

    public static Block nyatonicAccelerator;

    public static final void init() {
        initBlocks();
        initRecipes();
    }

    private static void initBlocks() {
        nyatonicAccelerator = new BlockNyatonicAccelerator();
        GameRegistry.registerBlock(nyatonicAccelerator, ItemBlock.class, "nyatonicAccelerator");
    }

    private static void initRecipes() {
        GameRegistry.addRecipe(new ItemStack(nyatonicAccelerator), "*#*", "# #", "*#*", '*', AtericItems.nyatoniumIngot, '#', AtericItems.ateroniumIngot);
        GameRegistry.addRecipe(new ItemStack(nyatonicAccelerator), "*#*", "# #", "*#*", '*', AtericItems.ateroniumIngot, '#', AtericItems.nyatoniumIngot);
    }
}
