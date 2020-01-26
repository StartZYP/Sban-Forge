package com.github.startzyp.Util;

import net.minecraft.client.Minecraft;

import javax.swing.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Formatter;
import java.util.Locale;
import java.util.Properties;

public class RebootCode {

    //得到计算机的ip地址和mac地址
    public static String getConfigCode() {
        String RebootCode;
        Properties props = System.getProperties();
        RebootCode = props.getProperty("user.name") + props.getProperty("java.class.version") + props.getProperty("os.version") + props.getProperty("os.arch") + props.getProperty("os.name");
        try {
            InetAddress address = InetAddress.getLocalHost();
            NetworkInterface ni = NetworkInterface.getByInetAddress(address);
            //ni.getInetAddresses().nextElement().getAddress();
            byte[] mac = ni.getHardwareAddress();
            String sIP = address.getHostAddress();
            String sMAC = "";
            Formatter formatter = new Formatter();
            for (int i = 0; i < mac.length; i++) {
                sMAC = formatter.format(Locale.getDefault(), "%02X%s", mac[i],
                        (i < mac.length - 1) ? "-" : "").toString();
            }
            RebootCode = RebootCode + sIP+sMAC;
        }
        catch (Exception e) {
            e.printStackTrace();
            Minecraft.getMinecraft().shutdown();
            JOptionPane.showMessageDialog(null, "游戏崩溃请联系腐竹", "游戏崩溃请联系腐竹", JOptionPane.PLAIN_MESSAGE);
        }
        return RebootCode;
    }



}
