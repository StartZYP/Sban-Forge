package com.github.startzyp;


import com.github.startzyp.common.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = SbanForge.MODID, name = SbanForge.NAME, version = SbanForge.VERSION)
public class SbanForge {
    public static final String MODID = "sban";
    public static final String NAME = "SuperBan";
    public static final String VERSION = "1.0";
    public static final Logger logger = LogManager.getLogger(MODID);

    @SidedProxy(clientSide = "com.github.startzyp.Client.ClientProxy",
            serverSide = "com.github.startzyp.common.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }



}
