package com.example.rider;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPOIItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapPolyLine;
import com.skt.Tmap.TMapTapi;
import com.skt.Tmap.TMapView;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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
    TextView distance;





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




    public static List<Map<String,String>> getdata(String ID, Context context){


        final List<Map<String,String>> data = new ArrayList<Map<String,String>>();
        try{

            app_loginDB app_loginDB=new app_loginDB();
            connection=app_loginDB.TestQuery();
            TMapTapi tmaptapi = new TMapTapi(context);
            tmaptapi.setSKTMapAuthentication ("l7xxf4938ed7b59f4928a5bd8de9bfeddcc4");



            if(connection==null)
            {
                ConnectionResult="Check yse" ;


            }
            else{

                String query ;
                query = "  select * from 라이더호출,매장,주문 where 주문일 = '"+ Date +"' and 현재상황 = '조리중' and 라이더호출.매장번호 = 매장.매장번호 and 라이더호출.호출번호 =주문.주문번호";
                Statement stmt =connection.createStatement();
                final ResultSet rs =stmt.executeQuery(query);

                while (rs.next()){
                    final Map<String,String> datanum= new HashMap<String,String>();
                    final TMapData tmapdata = new TMapData();
                    String aaa = rs.getString("배송지").substring(5);
                    final Double marketX = rs.getDouble("x좌표");
                    final Double marketY = rs.getDouble("y좌표");

                    tmapdata.findAddressPOI(aaa, new TMapData.FindAddressPOIListenerCallback() {

                        @Override
                        public void onFindAddressPOI(ArrayList<TMapPOIItem> items) {
                            TMapPoint tMapPointMarket;
                            TMapPoint tMapPointEnd;
                            TMapPOIItem item = items.get(0);
                            String aaa = item.getPOIPoint().toString();
                            String [] z = aaa.split(" ");
                            String a2,a4;
                            a2 = z[1]; a4 = z[3];
                            double xx = Double.parseDouble(a2);
                            double yy = Double.parseDouble(a4);


                            tMapPointMarket = new TMapPoint(marketX,marketY); //매장좌표
                            tMapPointEnd = new TMapPoint(xx, yy); // 배송지 좌표변환

                            try {
                                Document doc = tmapdata.findPathDataAll(tMapPointMarket,tMapPointEnd);
                                NodeList nodeList = doc.getElementsByTagName("tmap:totalDistance");
                                int eee = nodeList.getLength();
                                Node node = nodeList.item(0);
                                String distance = node.getChildNodes().item(0).getNodeValue();
                                double km = Double.parseDouble(distance)/1000;
                                datanum.put("호출번호",rs.getString("호출번호"));
                                datanum.put("매장이름",rs.getString("매장이름"));
                                datanum.put("거리", km + "km");
                                data.add(datanum);
                            }catch (Exception e){
                                e.getMessage();
                            }

                        }
                    });

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