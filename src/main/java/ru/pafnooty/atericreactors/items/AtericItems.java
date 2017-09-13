package ru.pafnooty.atericreactors.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSimpleFoiled;
import net.minecraft.item.ItemStack;
import ru.pafnooty.atericreactors.AtericReactors;

/**
 *
 * @author pabloid
 */
public final class AtericItems {

    public static Item paw;
    
    public static Item nyaton;
    public static Item rawNyatonium;
    public static Item nyatoniumIngot;
    public static Item chargedNyatoniumIngot;
    
    public static Item ateron;
    public static Item rawAteronium;
    public static Item ateroniumIngot;
    public static Item chargedAteroniumIngot;

    public static final void init() {
        initItems();
        initRecipes();
    }

    private static void initItems() {
        paw = new Item()
                .setUnlocalizedName("paw")
                .setCreativeTab(CreativeTabs.tabTools)
                .setTextureName(AtericReactors.MODID + ":paw")
                .setMaxStackSize(1);
        GameRegistry.registerItem(paw, "paw");

        nyaton = new Item()
                .setUnlocalizedName("nyaton")
                .setCreativeTab(CreativeTabs.tabMaterials)
                .setTextureName(AtericReactors.MODID + ":nyaton");
        GameRegistry.registerItem(nyaton, "nyaton");

        rawNyatonium = new Item()
                .setUnlocalizedName("rawNyatonium")
                .setCreativeTab(CreativeTabs.tabMaterials)
                .setTextureName(AtericReactors.MODID + ":rawNyatonium");
        GameRegistry.registerItem(rawNyatonium, "rawNyatonium");

        nyatoniumIngot = new Item()
                .setUnlocalizedName("nyatoniumIngot")
                .setCreativeTab(CreativeTabs.tabMaterials)
                .setTextureName(AtericReactors.MODID + ":nyatoniumIngot");
        GameRegistry.registerItem(nyatoniumIngot, "nyatoniumIngot");

        chargedNyatoniumIngot = new ItemSimpleFoiled()
                .setUnlocalizedName("chargedNyatoniumIngot")
                .setCreativeTab(CreativeTabs.tabMaterials)
                .setTextureName(AtericReactors.MODID + ":nyatoniumIngot");
        GameRegistry.registerItem(chargedNyatoniumIngot, "chargedNyatoniumIngot");

        ateron = new Item()
                .setUnlocalizedName("ateron")
                .setCreativeTab(CreativeTabs.tabMaterials)
                .setTextureName(AtericReactors.MODID + ":ateron");
        GameRegistry.registerItem(ateron, "ateron");

        rawAteronium = new Item()
                .setUnlocalizedName("rawAteronium")
                .setCreativeTab(CreativeTabs.tabMaterials)
                .setTextureName(AtericReactors.MODID + ":rawAteronium");
        GameRegistry.registerItem(rawAteronium, "rawAteronium");

        ateroniumIngot = new Item()
                .setUnlocalizedName("ateroniumIngot")
                .setCreativeTab(CreativeTabs.tabMaterials)
                .setTextureName(AtericReactors.MODID + ":ateroniumIngot");
        GameRegistry.registerItem(ateroniumIngot, "ateroniumIngot");

        chargedAteroniumIngot = new ItemSimpleFoiled()
                .setUnlocalizedName("chargedAteroniumIngot")
                .setCreativeTab(CreativeTabs.tabMaterials)
                .setTextureName(AtericReactors.MODID + ":ateroniumIngot");
        GameRegistry.registerItem(chargedAteroniumIngot, "chargedAteroniumIngot");

    }

    private static void initRecipes() {
        GameRegistry.addRecipe(new ItemStack(paw), " # ", "# #", '#', Blocks.wool);
        
        GameRegistry.addRecipe(new ItemStack(rawNyatonium), "###", "###", "###", '#', nyaton);
        GameRegistry.addSmelting(rawNyatonium, new ItemStack(nyatoniumIngot), 1.0f);
        
        GameRegistry.addRecipe(new ItemStack(rawAteronium), "###", "###", "###", '#', ateron);
        GameRegistry.addSmelting(rawAteronium, new ItemStack(ateroniumIngot), 1.0f); 
    }
}
