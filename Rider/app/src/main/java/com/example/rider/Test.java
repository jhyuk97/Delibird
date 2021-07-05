package com.example.rider;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Test extends AppCompatActivity {
    ListView listview;
    ArrayAdapter<String> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                Connection conn = null;
                try {
                    Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();

                    String connString = "jdbc:jtds:sqlserver://localhost:1433/2-C ";
                    conn = DriverManager.getConnection(connString, "sa", "system");

                    String queryString = "select * from user";
                    Statement stmt = conn.createStatement();
                    ResultSet reset = stmt.executeQuery(queryString);

                    while (reset.next()) {
                        Log.e("Data:", reset.getString(1));
                    }

                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }).start();

    }
}