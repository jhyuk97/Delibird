package com.example.delivered;

import android.content.SharedPreferences;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class user27_ListView {


    static Connection connection;
    static String ConnectionResult="";
    static Boolean isSuccess=false;
    public static Throwable ex;


    public interface ListBtnClickListener {
        void onListBtnClick(int position) ;
    }



    public static List<Map<String,String>> getdata(String userid){

        List<Map<String,String>> data =null;
        data=new ArrayList<Map<String,String>>();
        try{

            app_loginDB app_loginDB=new app_loginDB();
            connection=app_loginDB.TestQuery();




            if(connection==null)
            {
                ConnectionResult="Check yse" ;
            }
            else{
                String query ;
                query=  "select  문의번호,제목,내용,답변 from 문의 where 작성자 = '" + userid +"'  ";
                Statement stmt =connection.createStatement();
                ResultSet rs =stmt.executeQuery(query);

                while (rs.next()){
                    Map<String,String> datanum= new HashMap<String,String>();
                    datanum.put("문의번호",rs.getString("문의번호"));
                    datanum.put("제목",rs.getString("제목"));
                   datanum.put("내용",rs.getString("내용"));
                    datanum.put("답변",rs.getString("답변"));
                    data.add(datanum);


                }

            }
        } catch (Exception e) {
                    }
        return data;
    }



}