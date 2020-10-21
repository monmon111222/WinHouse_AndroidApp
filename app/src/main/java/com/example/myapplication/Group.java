package com.example.myapplication;



import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Group extends AppCompatActivity {
    public Button makebtn;
    RecyclerView rv2;
    MyAdapter_date adapter;
    ArrayList<Date> dates =new ArrayList<>();
    DBHelper helper = new DBHelper(this);
    private NavigationView navigation_view;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private String  TAG="[ST]";
    LinearLayout tech;
    ZoomLayoutD zoomLayoutD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group);

        navigation_view = (NavigationView) findViewById(R.id.navigation_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        zoomLayoutD=(ZoomLayoutD) findViewById(R.id.drawerLayout);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        toolbar.setTitle("該景點揪團清單");
        // 用toolbar做為APP的ActionBar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
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
                    Intent i = new Intent(Group.this, Menu.class);
                    startActivity(i);
                    return true;
                }
                else if (id == R.id.action_help) {
                    // 按下「介面說明」要做的事
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
                        AlertDialog.Builder builder =new AlertDialog.Builder(Group.this);
                        builder.setTitle("您尚未登入會員，無法瀏覽會員資訊")
                                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }else {
                        Intent i = new Intent(Group.this, User_info.class);
                        startActivity(i);
                    }
                    return true;
                }
                else if (id == R.id.action_sos) {
                    // 按下「求救」要做的事
                    // TextView textView = (TextView) findViewById(R.id.number_to_call);
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
                        java.util.Date d2 = new java.util.Date();
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
                }
                else if (id == R.id.action_ar) {
                    // 按下「推薦拍照」要做的事
                    Intent i = new Intent(Group.this, Emulate_picture.class);
                    startActivity(i);
                    return true;
                }
                else if (id == R.id.action_back) {
                    // 按下「返回」要做的事
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                }
                return false;
            }
        });
        //Intent intent= getIntent();
        //final String sid = intent.getExtras().getString("sid");
        //recycler
        rv2= (RecyclerView) findViewById(R.id.myRecycler2);
        //SET ITS PROPS
        rv2.setLayoutManager(new LinearLayoutManager(this));
        rv2.setItemAnimator(new DefaultItemAnimator());
        adapter=new MyAdapter_date(this, dates);
        AlertDialog.Builder builder2 =new AlertDialog.Builder(this);
        builder2.setTitle("您尚未擁有會員身分，無法建立揪團")
                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        final AlertDialog dialog2 = builder2.create();
        retrieve();


        makebtn = (Button) findViewById(R.id.btnmakegroup);
        makebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Contants.uid!="0") {
                    Intent i = new Intent();
                    i.setClass(Group.this, GroupSet.class);
                    startActivity(i);
                }else{
                    dialog2.show();
                }
            }
        });
    }
    //RETRIEVE
    private void retrieve()
    {
        Intent intent= getIntent();
        //final String fsid = intent.getExtras().getString("sid");
        //OPEN
        //db.openDB();
        dates.clear();
        //SELECT
        Cursor c=helper.getGroup(Contants.sid);
        //LOOP THRU THE DATA ADDING TO ARRAYLIST
        while (c.moveToNext())
        {
            int id=c.getInt(0);
            String time=c.getString(1);
            String eat=c.getString(2);
            String pnum=c.getString(3);
            String sid=c.getString(4);
            String uid=c.getString(5);
            String check=c.getString(6);

            //CREATE Date
            Date p=new Date(id,time,eat,pnum,sid,uid,check);

            //ADD TO PLAYERS
            dates.add(p);
        }

        //SET ADAPTER TO RV
        if(!(dates.size()<1))
        {
            rv2.setAdapter(adapter);
        }

    }
}
