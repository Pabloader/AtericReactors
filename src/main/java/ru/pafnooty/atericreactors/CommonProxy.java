package ru.pafnooty.atericreactors;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.common.MinecraftForge;
import ru.pafnooty.atericreactors.events.HugsEventHandler;
import ru.pafnooty.atericreactors.events.MonsterDeathEventHandler;
import ru.pafnooty.atericreactors.blocks.AtericBlocks;
import ru.pafnooty.atericreactors.blocks.tileentity.TileNyatonicAccelerator;
import ru.pafnooty.atericreactors.gui.AtericGuiHandler;
import ru.pafnooty.atericreactors.items.AtericItems;

/**
 *
 * @author pabloid
 */
public class CommonProxy {

    public void preInit(FMLPreInitializationEvent e) {
        AtericItems.init();
        AtericBlocks.init();
        MinecraftForge.EVENT_BUS.register(new HugsEventHandler());
        MinecraftForge.EVENT_BUS.register(new MonsterDeathEventHandler());
    }

    public void init(FMLInitializationEvent e) {
        GameRegistry.registerTileEntity(TileNyatonicAccelerator.class, "atericreactors.multiblock.accelerator");
        NetworkRegistry.INSTANCE.registerGuiHandler(AtericReactors.instance, new AtericGuiHandler());
    }

    public void postInit(FMLPostInitializationEvent e) {

    }
}
