package ru.pafnooty.atericreactors;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = AtericReactors.MODID, version = AtericReactors.VERSION)
public class AtericReactors {

    public static final String MODID = "atericReactors";
    public static final String VERSION = "0.0.1";

    @SidedProxy(clientSide = "ru.pafnooty.atericreactors.ClientProxy", serverSide = "ru.pafnooty.atericreactors.ServerProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        proxy.preInit(e);
    }

    @EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }
}
