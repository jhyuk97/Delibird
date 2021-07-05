package com.example.rider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ceo_review_ListView {


    static Connection connection;
    static String ConnectionResult="";
    static Boolean isSuccess=false;
    public static Throwable ex;


    public interface ListBtnClickListener {
        void onListBtnClick(int position) ;
    }



    public static List<Map<String,String>> getdata(){

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
                query=  "select 리뷰번호,사용자ID,매장번호,제목,등록일,답변 from 리뷰";
                Statement stmt =connection.createStatement();
                ResultSet rs =stmt.executeQuery(query);

                while (rs.next()){
                    Map<String,String> datanum= new HashMap<String,String>();
                    datanum.put("리뷰번호",rs.getString("리뷰번호"));
                    datanum.put("사용자ID",rs.getString("사용자ID"));
                   datanum.put("매장번호",rs.getString("매장번호"));
                    datanum.put("제목",rs.getString("제목"));
                    datanum.put("등록일",rs.getString("등록일"));
                    datanum.put("답변",rs.getString("답변"));
                    data.add(datanum);


                }

            }
        } catch (Exception e) {
                    }
        return data;
    }



}