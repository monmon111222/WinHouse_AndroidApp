package com.example.myapplication;


import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
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
import java.util.Date;

public class ScenicMainActivity5 extends AppCompatActivity {
    private NavigationView navigation_view;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private String  TAG="[ST]";
    RecyclerView rv;
    MyAdapter_scenic adapter;
    ArrayList<Scenic> scenics =new ArrayList<>();
    SearchView searchView;
    DBHelper helper = new DBHelper(this);
    LinearLayout tech;
    ZoomLayoutD zoomLayoutD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scenic_main);
        navigation_view = (NavigationView) findViewById(R.id.navigation_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        zoomLayoutD=(ZoomLayoutD) findViewById(R.id.drawerLayout);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        toolbar.setTitle("單車景點");
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
                    Intent i = new Intent(ScenicMainActivity5.this, Menu.class);
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
                        AlertDialog.Builder builder =new AlertDialog.Builder(ScenicMainActivity5.this);
                        builder.setTitle("您尚未登入會員，無法瀏覽會員資訊")
                                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }else {
                        Intent i = new Intent(ScenicMainActivity5.this, User_info.class);
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
                }
                else if (id == R.id.action_ar) {
                    // 按下「推薦拍照」要做的事
                    Intent i = new Intent(ScenicMainActivity5.this, Emulate_picture.class);
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
        //recycler
        rv= (RecyclerView) findViewById(R.id.myRecycler);
        //SET ITS PROPS
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());
        //ADAPTER
        adapter=new MyAdapter_scenic(this, scenics);
        searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setQueryHint("Search Here");
        searchView.setQueryRefinementEnabled(true);
        retrieve();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {return  false; }

            @Override
            public boolean onQueryTextChange(String newText) {
                search(newText);
                return false;
            }
        });
    }


    //RETRIEVE
    private void retrieve()
    {
        //DBAdapter db=new DBAdapter(this);
        //OPEN
        //db.openDB();
        scenics.clear();
        //SELECT
        Cursor c=helper.getAllScenicP5();
        //LOOP THRU THE DATA ADDING TO ARRAYLIST
        while (c.moveToNext())
        {
            int id=c.getInt(0);
            String name=c.getString(1);
            String month=c.getString(2);
            String phone=c.getString(3);
            String device=c.getString(4);
            String open=c.getString(5);
            String trans=c.getString(6);
            String par=c.getString(7);
            String pos=c.getString(8);
            String info = c.getString(9);
            byte[] imgc = c.getBlob(10);
            byte[] img1 = c.getBlob(11);
            byte[] img2 = c.getBlob(12);
            byte[] img3 = c.getBlob(13);


            //CREATE Scenic
            Scenic p=new Scenic(name,month,phone,device,open,trans,par,pos,info,id,imgc,img1,img2,img3);

            //ADD TO PLAYERS
            scenics.add(p);
        }

        //SET ADAPTER TO RV
        if(!(scenics.size()<1))
        {
            rv.setAdapter(adapter);
        }

    }

    private void search(String newtext)
    {
        //DBAdapter db2=new DBAdapter(this);
        //OPEN
        //db2.openDB();
        scenics.clear();
        //SELECT
        Cursor c=helper.selectScenicP5(newtext);
        //LOOP THRU THE DATA ADDING TO ARRAYLIST
        while (c.moveToNext())
        {
            int id=c.getInt(0);
            String name=c.getString(1);
            String month=c.getString(2);
            String phone=c.getString(3);
            String device=c.getString(4);
            String open=c.getString(5);
            String trans=c.getString(6);
            String par=c.getString(7);
            String pos=c.getString(8);
            String info = c.getString(9);
            byte[] imgc = c.getBlob(c.getColumnIndex("cover"));
            byte[] img1 = c.getBlob(c.getColumnIndex("pic1"));
            byte[] img2 = c.getBlob(c.getColumnIndex("pic2"));
            byte[] img3 = c.getBlob(c.getColumnIndex("pic3"));
            //CREATE PLAYER
            Scenic p=new Scenic(name,month,phone,device,open,trans,par,pos,info,id,imgc,img1,img2,img3);

            //ADD TO PLAYERS
            scenics.add(p);
        }

        //SET ADAPTER TO RV
        if(!(scenics.size()<1))
        {
            rv.setAdapter(adapter);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        retrieve();
    }
}
