package com.example.delivered;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class user09_menu_one_pick extends AppCompatActivity {
    Button button1;
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    TextView name;
    TextView Endwon;
    TextView title;
    TextView won;
    TextView  tnfid;
    TextView  rkrur;
    TextView  chdrkrur;
    Button plus;
    Button mi;
    EditText Editcount;
    String koreanName;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user09_menu_one_pick);
        name = (TextView)findViewById(R.id.Menu_name);
        won = (TextView)findViewById(R.id.menu_won);
        Endwon = (TextView)findViewById(R.id.menu_Endwon);
        title = (TextView)findViewById(R.id.user09_title);
        Editcount = (EditText)findViewById(R.id.user09_count);
        rkrur= (TextView)findViewById(R.id.rkrur);
        tnfid= (TextView)findViewById(R.id.tnfid);
        chdrkrur= (TextView)findViewById(R.id.chdrkrur);
        img = (ImageView)findViewById(R.id.user09_img);

        if(tryConnect(true))
          //Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
            getApplicationContext();
        Intent intent = getIntent();
        String menu = intent.getExtras().getString("menu_name");
        final String num = intent.getExtras().getString("phoneNum");
        String pic;
        String endwon = "";
        koreanName = menu;
        try{
            stmt = conn.createStatement();
            String sql = "select * from 메뉴,매장 where 매장.매장번호 = 메뉴.매장번호 and 매장전화번호 = "+ num +" and 메뉴명 = '" + menu + "'";
            rs = stmt.executeQuery(sql);
            while (rs.next()){
                endwon = rs.getString("금액");
                Endwon.setText(endwon);
                name.setText(rs.getString("메뉴명"));
                won.setText(rs.getString("금액"));
                title.setText(rs.getString("매장이름"));
                pic = rs.getString("사진");
                Bitmap bitmap = BitmapFactory.decodeFile(rs.getString("사진"));
                img.setImageBitmap(bitmap);
            }
        }catch (Exception e){
            e.getMessage();
        }

        final int endWon = Integer.parseInt(endwon);

        button1 = (Button) findViewById(R.id.btn_order_add);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                global data = (global)getApplication();
                final ArrayList<user08_basket_data> arr = data.getArr();
                String sql;
                final Intent intent = new Intent(user09_menu_one_pick.this,user8_shopping_basket.class);
                intent.putExtra("number1",num);
                sql = "select 메뉴번호 from 메뉴, 매장 where 매장.매장번호 = 메뉴.매장번호 and 메뉴명 = '" + koreanName + "' and 매장전화번호 = " + num;
                String number = "";
                try{
                    stmt = conn.createStatement();
                    rs = stmt.executeQuery(sql);
                    while (rs.next()){
                        number = rs.getString("메뉴번호");
                    }
                }catch (Exception e){
                    e.getMessage();
                }
                final String number3 = number;
                int bbbbb = arr.size();
                for(int i = 0; i < arr.size(); i++){
                    sql = "select distinct 매장번호 from 메뉴 where 메뉴번호 = " + arr.get(i).getNumber() + " or 메뉴번호 = " + number;
                    try {
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sql);
                        int count = 0;
                        while (rs.next()){
                            ++count;
                        }


                        if(count == 1){
                            arr.add(new user08_basket_data(name.getText().toString(),"",won.getText().toString(),Editcount.getText().toString(),number));
                            startActivity(intent);
                        //    Toast. makeText (getApplicationContext(), "메뉴가 담겼습니다.", Toast. LENGTH_LONG ).show();
                            break;
                        }
                        else{
                            AlertDialog.Builder builder = new AlertDialog.Builder(user09_menu_one_pick.this);
                            builder.setTitle("주의");
                            builder.setMessage("장바구니에는 같은 가게의 메뉴만 담을\n 수 있습니다.\n이전에 담은 메뉴가 삭제됩니다.");
                            builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    arr.clear();
                                    arr.add(new user08_basket_data(name.getText().toString(),"",won.getText().toString(),Editcount.getText().toString(),number3));
                                    startActivity(intent);
                                //    Toast. makeText (getApplicationContext(), "메뉴가 담겼습니다.", Toast. LENGTH_LONG ).show();
                                }
                            });
                            builder.setNeutralButton("취소",null);
                            builder.create().show();
                        }
                    }catch (Exception e){
                        e.getMessage();
                    }

                }
                if(arr.size() == 0) {
                    arr.add(new user08_basket_data(name.getText().toString(), "", won.getText().toString(), Editcount.getText().toString(), number));
                    startActivity(intent);
                    finish();
                //    Toast.makeText(getApplicationContext(), "메뉴가 담겼습니다.", Toast.LENGTH_LONG).show();
                }
            }
        });

        plus = (Button)findViewById(R.id.user09_plus);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt(Editcount.getText().toString());
                String endWon2 = Endwon.getText().toString();
                count = count + 1;
                Editcount.setText(Integer.toString(count));
                Endwon.setText("" + (Integer.parseInt(endWon2) + endWon));
            }
        });

        mi = (Button)findViewById(R.id.user09_mi);
        mi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt(Editcount.getText().toString());
                if(count == 1){
                    count = count;
                }
                else {
                    count = count - 1;
                    Editcount.setText(Integer.toString(count));
                    String endWon2 = Endwon.getText().toString();
                    Endwon.setText("" + (Integer.parseInt(endWon2) - endWon));
                }
            }

        });




        SharedPreferences pref = getSharedPreferences( "pref", MODE_PRIVATE );

        papago papago = new papago();
        String language = pref.getString("language","");
        button1.setText(papago.papago(button1.getText().toString(),language)); //장바구니로


tnfid.setText(papago.papago(tnfid.getText().toString(),language)); //수량

        name.setText(papago.papago(name.getText().toString(),language)); //매뉴이름
        rkrur.setText(papago.papago(rkrur.getText().toString(),language)); //가격
chdrkrur.setText(papago.papago(chdrkrur.getText().toString(),language)); //총가격


    }

    private boolean tryConnect(boolean showMessage) {
        try {
            app_loginDB connClass = new app_loginDB();
            conn = connClass.TestQuery();
            if (conn == null) {
                return false;
            } else if (conn.isClosed()) {
                Toast.makeText(getApplicationContext(),"데이터베이스 연결 실패",Toast.LENGTH_SHORT).show();
                return false;
            } else {
           //     Toast.makeText(getApplicationContext(),"데이터베이스 연결 성공",Toast.LENGTH_SHORT).show();
                return true;
            }
        } catch (SQLException e) {
            if (showMessage)
             //   Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            return false;
        }
    }
}
