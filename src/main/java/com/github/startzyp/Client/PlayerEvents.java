package com.github.startzyp.Client;

import com.github.startzyp.Entity.PlayerData;
import com.github.startzyp.SbanForge;
import com.github.startzyp.Util.NetWorkMsg;
import com.github.startzyp.Util.RebootCode;
import com.google.gson.Gson;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;

import java.nio.charset.Charset;

public class PlayerEvents {
    public PlayerEvents(){
        MinecraftForge.EVENT_BUS.register(this);
        FMLCommonHandler.instance().bus().register(this);
    }

//    @SubscribeEvent
//    public void onClientPacket(FMLNetworkEvent.ClientCustomPacketEvent evt) {
//        evt.getPacket().channel();
//        byte [] strbyte = {};
//        evt.getPacket().payload().readBytes(strbyte);
//
//        SbanForge.logger.info(new String(evt.getPacket().payload().array()));
//    }

    @SubscribeEvent
    public void PlayerJoinGame(FMLNetworkEvent.ClientConnectedToServerEvent event){
        Gson gson = new Gson();
        String rebootcode = RebootCode.getConfigCode();
        String playername = Minecraft.getMinecraft().getSession().getUsername();
        PlayerData playerData = new PlayerData(playername,rebootcode);
        byte[] bytes = gson.toJson(playerData).getBytes(Charset.forName("UTF-8"));
        SbanForge.logger.info(playerData.toString());
        //JOptionPane.showMessageDialog(null, rebootcode+"|"+playername, "游戏崩溃请联系腐竹", JOptionPane.PLAIN_MESSAGE);
        //PlayerData playerData1 = gson.fromJson(new String(bytes, StandardCharsets.UTF_8), PlayerData.class);
        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                NetWorkMsg.sendMessage(bytes);
                super.run();
            }
        }.start();
    }
}
