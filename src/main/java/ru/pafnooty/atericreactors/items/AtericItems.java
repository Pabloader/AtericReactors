package ru.pafnooty.atericreactors.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import ru.pafnooty.atericreactors.AtericReactors;

/**
 *
 * @author pabloid
 */
public final class AtericItems {

    public static Item nyaton;
    public static Item nyatoniumIngot;

    public static final void init() {
        nyaton = new Item()
                .setUnlocalizedName("nyaton")
                .setCreativeTab(CreativeTabs.tabMaterials)
                .setTextureName(AtericReactors.MODID + ":nyaton");
        GameRegistry.registerItem(nyaton, "nyaton");
        nyatoniumIngot = new Item()
                .setUnlocalizedName("nyatoniumIngot")
                .setCreativeTab(CreativeTabs.tabMaterials)
                .setTextureName(AtericReactors.MODID + ":nyatoniumIngot");
        GameRegistry.registerItem(nyatoniumIngot, "nyatoniumIngot");
        GameRegistry.addSmelting(nyaton, new ItemStack(nyatoniumIngot), 1.0f);
    }

}
