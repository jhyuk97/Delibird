package com.example.shop;

import android.os.StrictMode;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

public class app_loginDB {
    public app_loginDB(){

    }
    private Connection conn = null;
    private String URL = null;
    public Connection TestQuery(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            DriverManager.registerDriver((Driver)Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance());
            //URL = "jdbc:jtds:sqlserver://10.0.2.2:1433/DeliBird;user=sa;password=system;";
            String ip = getLocalIpAddress();
            conn = DriverManager.getConnection("jdbc:jtds:sqlserver://10.0.2.2:1433/DeilBird","sa","system");

        }catch (Exception e){
            e.getMessage();
        }
        return  conn;
    }

    public static String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }
}
