package com.mithilank.main;

import com.mithilank.module.*;
import com.mithilank.module.modules.*;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Main.MODID, version = Main.VERSION)
public class Main
{
    public static final String Name = "Ghost Client of Christmas Yet to Come";
    public static final String MODID = "YTC";
    public static final String VERSION = "Beta";
    public static Configuration config;
    public static ClickGui gui = new ClickGui();

    public ModuleListener listener = new ModuleListener();


    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        config = new Configuration(event.getSuggestedConfigurationFile());
    }
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        ModuleManager.Setup();

    }

    //TODO
    //Make Old Animations
    //Make Config System
    //Scaffold
    //Minimap


}
