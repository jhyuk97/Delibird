package com.example.rider;

import android.app.Activity;
import android.content.Context;
import android.net.IpSecManager;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapPOIItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapTapi;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class current_adapter extends BaseAdapter {
    ArrayList<current_data> data;
    LayoutInflater mLayoutInflater = null;
    Context mContext = null;
    TextView CallNumber;
    TextView marketName;
    TextView distance;
    ResultSet rs = null;
    Statement stmt = null;
    Connection conn;

    public current_adapter(ArrayList<current_data> data, Context mContext) {
        this.data = data;
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.currentstate_listview,null);
        CallNumber = (TextView)view.findViewById(R.id.textView458222);
        marketName = (TextView)view.findViewById(R.id.textView458);
        distance = (TextView)view.findViewById(R.id.current_distance);

        TMapTapi tmaptapi = new TMapTapi(mContext);
        tmaptapi.setSKTMapAuthentication ("l7xxf4938ed7b59f4928a5bd8de9bfeddcc4");

        CallNumber.setText(data.get(position).getCallNumber());
        marketName.setText(data.get(position).getMarketName());
        tryConnect(true);
        String sql = "select * from 주문,매장 where 주문.매장번호 = 매장.매장번호 and 주문번호 = " + data.get(position).getCallNumber();
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()){
                final Double marketX = rs.getDouble("x좌표");
                final Double marketY = rs.getDouble("y좌표");
                final TMapData tmapdata = new TMapData();
                TMapPoint tMapPointMarket = new TMapPoint(marketX,marketY); //매장좌표
                TMapPoint tMapPointEnd = new TMapPoint(rs.getDouble("배송지x좌표"), rs.getDouble("배송지y좌표")); // 배송지 좌표변환

                try {
                    Document doc = tmapdata.findPathDataAll(tMapPointMarket,tMapPointEnd);
                    NodeList nodeList = doc.getElementsByTagName("tmap:totalDistance");
                    int eee = nodeList.getLength();
                    Node node = nodeList.item(0);
                    String Distance = node.getChildNodes().item(0).getNodeValue();
                    final double km = Double.parseDouble(Distance)/1000;
                    distance.setText(km + "km");
                }catch (Exception e){
                    e.getMessage();
                }
            }
        }catch (Exception e){
            e.getMessage();
        }
        return view;
    }

    private boolean tryConnect(boolean showMessage) {
        try {
            app_loginDB connClass = new app_loginDB();
            conn = connClass.TestQuery();
            if (conn == null) {
                return false;
            } else if (conn.isClosed()) {
                return false;
            } else {
                //     Toast.makeText(getApplicationContext(),"데이터베이스 연결 성공",Toast.LENGTH_SHORT).show();
                return true;
            }
        } catch (SQLException e) {
            if (showMessage)
                e.printStackTrace();
            return false;
        }
    }
}
