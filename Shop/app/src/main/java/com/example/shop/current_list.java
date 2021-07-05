package com.example.shop;

import android.view.View;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class current_list {



    static Connection connection;
    static String ConnectionResult="";
    static Boolean isSuccess=false;
    public static Throwable ex;
    private int aaa;





    public interface ListBtnClickListener {
        void onListBtnClick(int position) ;



    };
    public class ItemData
    {
        public String strTitle;
        public String strDate;
        public View.OnClickListener onClickListener;
    }





    static final String Date = new SimpleDateFormat("yyy-MM-dd").format(new java.util.Date());
    final String Time = new SimpleDateFormat("HH:mm:ss").format( Calendar.getInstance( TimeZone.getTimeZone("Asia/Seoul")).getTime());




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
                query = "  select * from 라이더호출,매장,주문,사용자 where 주문일 = '"+ Date +"' and 현재상황 = '조리중' and 라이더호출.매장번호 = 매장.매장번호 and 라이더호출.호출번호 =주문.주문번호 and 주문.사용자ID =사용자.ID";
                Statement stmt =connection.createStatement();
                ResultSet rs =stmt.executeQuery(query);

                while (rs.next()){
                    Map<String,String> datanum= new HashMap<String,String>();
                    datanum.put("호출번호",rs.getString("호출번호"));
                    datanum.put("매장이름",rs.getString("매장이름"));


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