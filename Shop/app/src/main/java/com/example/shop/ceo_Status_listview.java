package com.example.shop;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ceo_Status_listview {
    static Connection connection;
    static String ConnectionResult="";
    static Boolean isSuccess=false;
    public static Throwable ex;

    public interface ListBtnClickListener {
        void onListBtnClick(int position) ;
    }

    public static List<Map<String,String>> getdata(String ceoid){




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


                final String Date = new SimpleDateFormat("yyy-MM-dd").format(new java.util.Date());
                String query ;

                query = "SELECT DISTINCT a.주문번호, 배송지, STUFF(( SELECT ',' + d.메뉴명 FROM 메뉴 d, 주문한음식 e where d.메뉴번호 = e.메뉴번호 and e.주문번호 = a.주문번호 ";
                query = query + "FOR XML PATH('') ),1,1,'') AS 메뉴, a.금액, d.호출시간,현재상황 FROM 주문 a, 메뉴 b, 주문한음식 c, 라이더호출 d ";
                query = query + "where a.주문번호 = d.호출번호 and a.주문번호 = c.주문번호 and 현재상황 != '배달완료' and 현재상황 != '리뷰작성완료' and c.메뉴번호 = b.메뉴번호 and 주문일 ='"+Date+"' and a.매장번호 = (select 매장번호 from 점주 where ID = '" + ceoid +"')";
                Statement stmt =connection.createStatement();
                ResultSet rs =stmt.executeQuery(query);

                while (rs.next()){
                    Map<String,String> datanum= new HashMap<String,String>();
                    datanum.put("배송지",rs.getString("배송지"));
                   datanum.put("메뉴",rs.getString("메뉴"));
                    datanum.put("금액",rs.getString("금액"));
                    datanum.put("현재상황",rs.getString("현재상황"));



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