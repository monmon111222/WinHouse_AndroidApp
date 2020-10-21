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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DetailActivity_date extends AppCompatActivity {
    DBHelper helper = new DBHelper(this);
    TextView timetv,eattv,pnumtv;
    Button btnjoin,btneditgroup,btnchancelgroup,btnoutgroup,btnalbum;
    ListView list;
    private NavigationView navigation_view;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private String  TAG="[ST]";
    LinearLayout tech;
    ZoomLayoutD zoomLayoutD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_info);
        navigation_view = (NavigationView) findViewById(R.id.navigation_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        zoomLayoutD=(ZoomLayoutD) findViewById(R.id.drawerLayout);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        toolbar.setTitle("揪團資訊");
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
                    Intent i = new Intent(DetailActivity_date.this, Menu.class);
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
                        AlertDialog.Builder builder =new AlertDialog.Builder(DetailActivity_date.this);
                        builder.setTitle("您尚未登入會員，無法瀏覽會員資訊")
                                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }else {
                        Intent i = new Intent(DetailActivity_date.this, User_info.class);
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
                    Intent i = new Intent(DetailActivity_date.this, Emulate_picture.class);
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
        AlertDialog.Builder builder2 =new AlertDialog.Builder(this);
        builder2.setTitle("您尚未擁有會員身分，無法執行此功能")
                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        final AlertDialog dialog2 = builder2.create();



        //RECEIVE DATA FROM MAIN ACTIVITY
        Intent i = getIntent();
        final String time = i.getExtras().getString("TIME");
        final String eat="飲食限制:"+i.getExtras().getString("EAT");
        final String gid = Integer.toString(i.getExtras().getInt("ID"));
        final String uid= i.getExtras().getString("UID");

        Contants.gid=gid;

        final ArrayList array_list = helper.GetUserNameInGroup(gid);
        final ArrayList array_list2 = helper.GetUserNameDetailInGroup(gid);
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1, array_list);
        list = (ListView)findViewById(R.id.LVmember);
        list.setAdapter(arrayAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
                // TODO Auto-generated method stub

                AlertDialog.Builder builder2 =new AlertDialog.Builder(DetailActivity_date.this,R.style.MyDialog);
                LayoutInflater factory = LayoutInflater.from(DetailActivity_date.this);
                final View view = factory.inflate(R.layout.dialog_model, null);
                builder2.setView(view);
                builder2.setTitle("成員資訊")
                        .setMessage(array_list2.get(arg2).toString())
                        .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                AlertDialog dialog2 = builder2.create();
                dialog2.show();
            }
        });
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setTitle("確定刪除此揪團嗎?")
                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                          Contants.check =i;
                        helper.deleteGroup(gid);
                        helper.deleteGroupInList(gid);
                        Intent intent = new Intent(DetailActivity_date.this, Group.class);
                        startActivity(intent);

                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                          Contants.check =i;

                    }
                });
        final AlertDialog dialog = builder.create();

        timetv = (TextView) findViewById(R.id.timetextView);
        eattv=(TextView)findViewById(R.id.eattextView);
        pnumtv=(TextView)findViewById(R.id.pnumtextView);
        btnjoin=(Button)findViewById(R.id.btnjoingroup);
        btneditgroup=(Button)findViewById(R.id.btneditgroup);
        btnchancelgroup=(Button)findViewById(R.id.btnchancelgroup);
        btnoutgroup=(Button)findViewById(R.id.btnoutgroup);
        btnalbum=(Button)findViewById(R.id.btnalbum);


        timetv.setText(time);
        eattv.setText(eat);
        if(i.getExtras().getString("PNUM")==null||i.getExtras().getString("PNUM").equals("未填寫")){
            pnumtv.setText("集合地點主揪尚未決定");

        }
        else {
            pnumtv.setText("集合地點:"+i.getExtras().getString("PNUM"));}
        //pnumtv.setText(pnum);
        if(uid.equals(Contants.uid)){
            btneditgroup.setVisibility(View.VISIBLE);
            btneditgroup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(DetailActivity_date.this, DetailActivity_date_edit.class);
                    intent.putExtra("ID",gid);
                    intent.putExtra("TIME", time);
                    intent.putExtra("EAT", eat);
                    Contants.gid=gid;
                    startActivity(intent);

                }
            });
            btnchancelgroup.setVisibility(View.VISIBLE);
            btnchancelgroup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.show();
                }
            });

        }else {
            btnjoin.setVisibility(View.VISIBLE);
            btnjoin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Contants.uid != "0") {
                        if (!helper.joinGroupcheck(gid, Contants.uid)) {
                            helper.joinGroup(gid, Contants.uid);
                            Intent i = new Intent(DetailActivity_date.this, Group.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(DetailActivity_date.this, "您已點選過參加此團", Toast.LENGTH_SHORT).show();
                        }
                    }else{dialog2.show();}
                }
            });
            btnoutgroup.setVisibility(View.VISIBLE);
            btnoutgroup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Contants.uid!="0") {
                        if (helper.joinGroupcheck(gid, Contants.uid)) {
                            helper.outGroup(gid, Contants.uid);
                            Intent i = new Intent(DetailActivity_date.this, Group.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(DetailActivity_date.this, "您未參加此團", Toast.LENGTH_SHORT).show();
                        }
                    }else {dialog2.show();}
                }
            });
        }
        btnalbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Contants.uid!="0") {
                    Intent i = new Intent(DetailActivity_date.this, Album.class);
                    i.putExtra("GID", gid);
                    startActivity(i);
                }else{dialog2.show();}
            }
        });


    }


}
