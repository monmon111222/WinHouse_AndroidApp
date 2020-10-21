package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.io.ByteArrayInputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailActivity_scenic extends AppCompatActivity {
    DBHelper helper = new DBHelper(this);
    private NavigationView navigation_view;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private String  TAG="[ST]";
    Button btngroup,btnMonth,btnPhone,btnOpen,btnDevice,btnPark,btnAddress,btnTrans,btndisscution;
    ImageView imgv,imgv2,imgv3,imgv4;
    TextView nametv,showtv,infotv;
    LinearLayout tech;
    ZoomLayoutL zoomlayoutl1;
    ZoomLayoutD zoomLayoutD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailactivity_scenic);
        navigation_view = (NavigationView) findViewById(R.id.navigation_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        zoomlayoutl1=(ZoomLayoutL) findViewById(R.id.Ltext1);
        //zoomlayoutl2=(ZoomLayoutL) findViewById(R.id.Ltext2);
        //zoomlayoutl3=(ZoomLayoutL) findViewById(R.id.Ltext3);
        zoomLayoutD=(ZoomLayoutD) findViewById(R.id.drawerLayout);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        toolbar.setTitle("景點介紹");
        // 用toolbar做為APP的ActionBar
        setSupportActionBar(toolbar);

        // 將drawerLayout和toolbar整合，會出現「三」按鈕
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        // 選單點擊事件
        navigation_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                // 點選時收起選單
                drawerLayout.closeDrawer(GravityCompat.START);

                // 取得選項id
                int id = item.getItemId();

                // 依照id判斷點了哪個項目並做相應事件
                if (id == R.id.action_home) {
                    // 按下「景點頁」要做的事
                    Intent i = new Intent(DetailActivity_scenic.this, Menu.class);
                    startActivity(i);
                    return true;
                }
                else if (id == R.id.action_help) {
                    // 按下「介面介紹」要做的事
                    tech = (LinearLayout)findViewById(R.id.tech);
                    tech.setVisibility(View.VISIBLE);
                    tech.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            tech.setVisibility(View.INVISIBLE);
                        }
                    });
                    return true;
                }
                else if (id == R.id.action_user) {
                    // 按下「會員資訊」要做的事
                    if (Contants.uid=="0"){
                        AlertDialog.Builder builder =new AlertDialog.Builder(DetailActivity_scenic.this);
                        builder.setTitle("您尚未登入會員，無法瀏覽會員資訊")
                                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }else {
                        Intent i = new Intent(DetailActivity_scenic.this, User_info.class);
                        startActivity(i);
                    }
                    return true;
                }
                else if (id == R.id.action_sos) {
                    // 按下「求救」要做的事
                    //TextView textView = (TextView) findViewById(R.id.number_to_call);
                    //textView.getText().toString());
                    Cursor c2 = helper.GetUserInfo(Contants.uid);
                    c2.moveToNext();
                    String phonenum=c2.getString(3);
                    String smsNumber = String.format("smsto: %s",phonenum);
                    // Find the sms_message view.
                    //EditText smsEditText = (EditText) findViewById(R.id.sms_message);
                    // Get the text of the SMS message.
                    //String sms = smsEditText.getText().toString();
                    Cursor cursor = helper.getGroupName(Contants.uid);
                    while (cursor.moveToNext()) {
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        java.util.Date d1 = null;
                        try {
                            d1 = df.parse(cursor.getString(0));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        java.util.Date d2 = new Date();
                        String place;
                        long diff = d2.getTime() - d1.getTime();
                        String relation = null;
                        relation = (diff / (1000 * 60 * 60 * 24)) + "";
                        if (relation.equals("0")) {
                            Contants.place =cursor.getString(1);
                        }
                    }
                    String sms = "我在"+Contants.place+"需要幫助!";

                    // Create the intent.
                    Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
                    // Set the data for the intent as the phone number.
                    smsIntent.setData(Uri.parse(smsNumber));
                    // Add the message (sms) with the key ("sms_body").
                    smsIntent.putExtra("sms_body", sms);

                    // If package resolves (target app installed), send intent.
                    if (smsIntent.resolveActivity(getPackageManager()) != null) {
                        startActivity(smsIntent);
                    } else {
                        Log.e(TAG, "Can't resolve app for ACTION_SENDTO Intent");
                    }
                    return true;
                }else if (id == R.id.action_back) {

                    return true;
                }

                return false;
            }
        });



        //RECEIVE DATA FROM MAIN ACTIVITY

        Intent i = getIntent();
        final String name = i.getExtras().getString("NAME");
        final  String month="推薦月份:"+i.getExtras().getString("MONTH");
        final  String phone="連絡電話:"+i.getExtras().getString("PHONE");
        final  String device="設施:"+i.getExtras().getString("DEVICE");
        final  String open="開放時間:"+i.getExtras().getString("OPEN");
        final  String trans="交通方式:\n"+i.getExtras().getString("TRANS");
        final  String par="停車資訊:\n"+i.getExtras().getString("PAR");
        final String pos = "地址:\n"+i.getExtras().getString("POSITION");
        final String info = "簡介:\n"+i.getExtras().getString("INFO");
        final int id = i.getExtras().getInt("ID");
        final String sid=Integer.toString(id);
        final byte[] img = i.getExtras().getByteArray("IMG");
        final byte[] img1 = i.getExtras().getByteArray("IMG1");
        final byte[] img2 = i.getExtras().getByteArray("IMG2");
        final byte[] img3 = i.getExtras().getByteArray("IMG3");
        ByteArrayInputStream inputStream = new ByteArrayInputStream(img);
        Bitmap bitmapimg = BitmapFactory.decodeStream(inputStream);
        ByteArrayInputStream inputStream1 = new ByteArrayInputStream(img1);
        Bitmap bitmapimg1 = BitmapFactory.decodeStream(inputStream1);
        ByteArrayInputStream inputStream2 = new ByteArrayInputStream(img2);
        Bitmap bitmapimg2 = BitmapFactory.decodeStream(inputStream2);
        ByteArrayInputStream inputStream3 = new ByteArrayInputStream(img3);
        Bitmap bitmapimg3 = BitmapFactory.decodeStream(inputStream3);
        nametv = (TextView) findViewById(R.id.nametextView);
        //showtv=(TextView)findViewById(R.id.showtextView);
        //infotv = (TextView)findViewById(R.id.info);
        imgv = (ImageView)findViewById(R.id.image);
        imgv2 = (ImageView)findViewById(R.id.image2);
        imgv3 = (ImageView)findViewById(R.id.image3);
        imgv4 = (ImageView)findViewById(R.id.image4);
        nametv.setText(name+"\n"+info);
        zoomlayoutl1=(ZoomLayoutL) findViewById(R.id.Ltext1);
        btnMonth = (Button)findViewById(R.id.btnMonth);
        btnMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nametv.setText(name+"\n"+month+"\n"+info);
            }
        });

        btnPhone = (Button)findViewById(R.id.btnPhone);
        btnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nametv.setText(name+"\n"+phone+"\n"+info);
            }
        });

        btnDevice = (Button)findViewById(R.id.btnDevice);
        btnDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nametv.setText(name+"\n"+device+"\n"+info);
            }
        });

        btnOpen = (Button)findViewById(R.id.btnOpen);
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nametv.setText(name+"\n"+open+"\n"+info);            }
        });

        btnTrans = (Button)findViewById(R.id.btnTrans);
        btnTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nametv.setText(name+"\n"+trans+"\n"+info);            }
        });

        btnPark = (Button)findViewById(R.id.btnPark);
        btnPark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nametv.setText(name+"\n"+par+"\n"+info);            }
        });

        btnAddress = (Button)findViewById(R.id.btnAddress);
        btnAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nametv.setText(name+"\n"+pos+"\n"+info);            }
        });

        //infotv.setText(info);
        imgv.setImageBitmap(bitmapimg);
        imgv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contants.sid=sid;
                Intent i = new Intent(DetailActivity_scenic.this, DetailActivity_scenic_photoitem.class);
                i.putExtra("INDEX",10);
                startActivity(i);

            }
        });
        imgv2.setImageBitmap(bitmapimg1);
        imgv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contants.sid=sid;
                Intent i = new Intent(DetailActivity_scenic.this, DetailActivity_scenic_photoitem.class);
                i.putExtra("INDEX",11);
                startActivity(i);

            }
        });
        imgv3.setImageBitmap(bitmapimg2);
        imgv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contants.sid=sid;
                Intent i = new Intent(DetailActivity_scenic.this, DetailActivity_scenic_photoitem.class);
                i.putExtra("INDEX",12);
                startActivity(i);

            }
        });
        imgv4.setImageBitmap(bitmapimg3);
        imgv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contants.sid=sid;
                Intent i = new Intent(DetailActivity_scenic.this, DetailActivity_scenic_photoitem.class);
                i.putExtra("INDEX",13);
                startActivity(i);

            }
        });
        btngroup = (Button) findViewById(R.id.btngroup);
        btngroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contants.sid=sid;
                Intent i = new Intent(DetailActivity_scenic.this, Group.class);
                startActivity(i);

            }
        });
        btndisscution = (Button)findViewById(R.id.btndisscution);
        btndisscution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contants.sid=sid;
                Intent i = new Intent(DetailActivity_scenic.this, Comment.class);
                startActivity(i);
            }
        });

    }


}
