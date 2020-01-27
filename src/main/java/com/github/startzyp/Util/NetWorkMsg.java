package com.github.startzyp.Util;

import com.github.startzyp.SbanForge;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.FMLEventChannel;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;

public class NetWorkMsg {

    public static FMLEventChannel channel;


    public NetWorkMsg() {
        MinecraftForge.EVENT_BUS.register(this);
        FMLCommonHandler.instance().bus().register(this);
        NetWorkMsg.channel = NetworkRegistry.INSTANCE.newEventDrivenChannel(SbanForge.MODID);
        NetWorkMsg.channel.register(this);
    }
    public static void sendMessage(byte[] array) {
        // .getBytes(Charset.forName("UTF-8"));
        ByteBuf buf = Unpooled.wrappedBuffer(array);
        FMLProxyPacket packet = new FMLProxyPacket(new PacketBuffer(buf), SbanForge.MODID);
        channel.sendToServer(packet);
    }


}
