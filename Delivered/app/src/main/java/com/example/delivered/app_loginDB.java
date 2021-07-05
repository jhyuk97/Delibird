package com.example.delivered;

import android.os.StrictMode;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

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
            conn = DriverManager.getConnection("jdbc:jtds:sqlserver://10.0.2.2:1433/DeilBird","sa","system");

        }catch (Exception e){
            e.getMessage();
        }
        return  conn;
    }
}
