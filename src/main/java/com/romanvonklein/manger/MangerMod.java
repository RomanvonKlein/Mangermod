package com.romanvonklein.manger;


import com.romanvonklein.manger.init.MangerBlocks;
import com.romanvonklein.manger.proxy.IProxy;

import org.apache.logging.log4j.Logger;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MangerMod.MODID, version = MangerMod.VERSION, name = MangerMod.NAME)
public class MangerMod
{

    @Mod.Instance
    public static MangerMod instance;

    public static final String CLIENT = "com.romanvonklein.manger.proxy.ClientProxy";
    public static final String SERVER = "com.romanvonklein.manger.proxy.ServerProxy";

    public static Logger logger;

    @SidedProxy(clientSide = MangerMod.CLIENT, serverSide = MangerMod.SERVER)
    public static IProxy proxy;

    public static final String MODID = "manger";
    public static final String VERSION = "1.0";
    public static final String NAME = "MangerMod";
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.preInit(event);
        MinecraftForge.EVENT_BUS.register(MangerBlocks.class);
    }
 
    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }
 
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
}
