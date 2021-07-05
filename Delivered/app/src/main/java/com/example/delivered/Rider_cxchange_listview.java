package com.example.delivered;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rider_cxchange_listview {




    static Connection connection;
    static String ConnectionResult="";
    static Boolean isSuccess=false;
    public static Throwable ex;
    private int aaa;





    public interface ListBtnClickListener {
        void onListBtnClick(int position) ;
    }








    public static List<Map<String,String>> getdata(String reiderID){

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
              query=  "select  *from 급여 where 라이더ID = '"+ reiderID +"' and 환전 = 'X' ";



                Statement stmt =connection.createStatement();
                ResultSet rs =stmt.executeQuery(query);

                while (rs.next()){
                    Map<String,String> datanum= new HashMap<String,String>();
                    datanum.put("급여번호",rs.getString("급여번호"));
                    datanum.put("돈",rs.getString("돈"));
                   datanum.put("날짜",rs.getString("날짜"));
                    datanum.put("시간",rs.getString("시간"));
                    data.add(datanum);






                }
                ConnectionResult="Successful";
                isSuccess=true;
                connection.close();
            }
        } catch (Exception e) {
            isSuccess=false;
            ConnectionResult=ex.getMessage();
                    }
        return data;
    }



}