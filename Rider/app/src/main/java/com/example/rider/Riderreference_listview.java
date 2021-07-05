package com.example.rider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Riderreference_listview {




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
                //       query=  "select   *from 급여 where 라이더ID = '"+ reiderID +"' and 날짜 = '"+day+ "' ";

                query="  select * from 라이더호출,매장,주문,사용자 where 라이더호출.라이더ID = '"+reiderID+"' and 현재상황='배달완료'  and 라이더호출.매장번호 = 매장.매장번호 and 라이더호출.호출번호 =주문.주문번호 and 주문.사용자ID =사용자.ID";

                Statement stmt =connection.createStatement();
                ResultSet rs =stmt.executeQuery(query);

                while (rs.next()){
                    Map<String,String> datanum= new HashMap<String,String>();
                    datanum.put("날짜",rs.getString("호출날짜"));
                    datanum.put("시간",rs.getString("호출시간"));
                    datanum.put("매장주소",rs.getString("매장주소"));
                    datanum.put("매장이름",rs.getString("매장이름"));
                    datanum.put("배달지",rs.getString("도로명주소"));


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





    public static List<Map<String,String>> getdata(String reiderID,String day){

        List<Map<String,String>> data =null;
        data=new ArrayList<Map<String,String>>();
        try{

            app_loginDB app_loginDB =new app_loginDB();
            connection=app_loginDB.TestQuery();


            if(connection==null)
            {
                ConnectionResult="Check yse" ;


            }
            else{

                String query ;
       //       query=  "select   *from 급여 where 라이더ID = '"+ reiderID +"' and 날짜 = '"+day+ "' ";

                query="  select * from 라이더호출,매장,주문,사용자 where 라이더호출.라이더ID = '"+reiderID+"' and 현재상황='배달완료' and 주문일 = '"+day+ "' and 라이더호출.매장번호 = 매장.매장번호 and 라이더호출.호출번호 =주문.주문번호 and 주문.사용자ID =사용자.ID";

                Statement stmt =connection.createStatement();
                ResultSet rs =stmt.executeQuery(query);

                while (rs.next()){
                    Map<String,String> datanum= new HashMap<String,String>();
                    datanum.put("날짜",rs.getString("호출날짜"));
                    datanum.put("시간",rs.getString("호출시간"));
                    datanum.put("매장주소",rs.getString("매장주소"));
                    datanum.put("매장이름",rs.getString("매장이름"));
                    datanum.put("배달지",rs.getString("도로명주소"));


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