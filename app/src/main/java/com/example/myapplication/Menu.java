package com.example.myapplication;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.provider.Settings.EXTRA_APP_PACKAGE;
import static android.provider.Settings.EXTRA_CHANNEL_ID;

public class Menu extends AppCompatActivity {
    private NavigationView navigation_view;
    DBHelper helper = new DBHelper(this);
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private TextView Menu_Textview;
    Button btnp,btnp1,btnp2,btnp3,btnp4,btnp5;
    LinearLayout tech;
    ZoomLayoutD zoomlayout;
    private String  TAG="[ST]";
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        zoomlayout = (ZoomLayoutD) findViewById(R.id.drawerLayout);
        navigation_view = (NavigationView) findViewById(R.id.navigation_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        toolbar.setTitle("景點清單");
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
                    Toast.makeText(Menu.this, "此為分類景點頁", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (id == R.id.action_help) {
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
                } else if (id == R.id.action_user) {
                    // 按下「會員資訊」要做的事
                    if (Contants.uid == "0") {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Menu.this);
                        builder.setTitle("您尚未登入會員，無法瀏覽會員資訊")
                                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    } else {
                        Intent i = new Intent(Menu.this, User_info.class);
                        startActivity(i);
                    }
                    return true;
                } else if (id == R.id.action_user) {
                    // 按下「會員資訊」要做的事
                    if (Contants.uid == "0") {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Menu.this);
                        builder.setTitle("您尚未登入會員，無法瀏覽會員資訊")
                                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    } else {
                        Intent i = new Intent(Menu.this, User_info.class);
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
                } else if (id == R.id.action_ar) {
                    // 按下「推薦拍照」要做的事
                    Intent i = new Intent(Menu.this, Emulate_picture.class);
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
            long diff = d1.getTime() - d2.getTime();
            String relation = null;
            String relation2 = null;
            if (d1.equals(d2)) {
                relation = "the same date as" + cursor.getString(1);
            } else if (d1.after(d2)) {
                relation2 = (diff / (1000 * 60 * 60 * 24)) + "";
                if (relation2.equals("1")) {
                    relation = cursor.getString(0) + cursor.getString(1);
                    Contants.gid=cursor.getString(2);
                    final int notifyID = 1; // 通知的識別號碼
                    final int requestCode = notifyID; // PendingIntent的Request Code
                    final Intent intent = new Intent(getApplicationContext(), DetailActivity_date2.class); // 開啟另一個Activity的Intent
                    final int flags = PendingIntent.FLAG_UPDATE_CURRENT; // ONE_SHOT：PendingIntent只使用一次；CANCEL_CURRENT：PendingIntent執行前會先結束掉之前的；NO_CREATE：沿用先前的PendingIntent，不建立新的PendingIntent；UPDATE_CURRENT：更新先前PendingIntent所帶的額外資料，並繼續沿用
                    final TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext()); // 建立TaskStackBuilder
                    stackBuilder.addParentStack(DetailActivity_date2.class); // 加入目前要啟動的Activity，這個方法會將這個Activity的所有上層的Activity(Parents)都加到堆疊中
                    stackBuilder.addNextIntent(intent); // 加入啟動Activity的Intent
                    final PendingIntent pendingIntent = stackBuilder.getPendingIntent(requestCode, flags); // 取得PendingIntent
                    final NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE); // 取得系統的通知服務
                    String show = "show";
                    NotificationChannel notificationChannel = new NotificationChannel("show","mesg", NotificationManager.IMPORTANCE_DEFAULT);

                    final Notification notification = new Notification.Builder(this).setSmallIcon(R.drawable.grandparents).setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.grandparents)).setContentTitle("出團提醒").setContentText("您所建立的團:"+relation+"將於兩天後出發，請您至揪團資訊頁編輯是否出團及集合地點").setContentIntent(pendingIntent).build();
                    final Notification notification2 = new Notification.Builder(this,"show").setSmallIcon(R.drawable.grandparents).setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.grandparents)).setContentTitle("出團提醒").setContentText("您所建立的團:"+relation+"將於兩天後出發，請您至揪團資訊頁編輯是否出團及集合地點").setContentIntent(pendingIntent).build();


                    notificationChannel.enableLights(true);
                    notificationChannel.setLightColor(Color.RED);
                    notificationChannel.enableVibration(true);
                    notificationChannel.setVibrationPattern(new long[]{1000, 2000});
                    notificationManager.createNotificationChannel(notificationChannel);
                    notificationManager.notify(notifyID, notification2); // 發送通知
                    AlertDialog.Builder builder = new AlertDialog.Builder(Menu.this);
                    builder.setTitle("出團提醒")
                            .setMessage("您所建立的團:"+"\n"+relation+"\n"+"將於兩天後出發，請您至揪團資訊頁編輯是否出團及集合地點")
                            .setPositiveButton("前往編輯", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent = new Intent(getApplicationContext(), DetailActivity_date2.class);
                                    startActivity(intent);
                                }

                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    Log.e(TAG, relation2);

                }
            }
        }


        btnp = (Button) findViewById(R.id.btnP);
        btnp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Menu.this, ScenicMainActivity.class);
                startActivity(i);
            }
        });
        btnp1 = (Button) findViewById(R.id.btnP1);
        btnp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Menu.this, ScenicMainActivity1.class);
                startActivity(i);
            }
        });
        btnp2 = (Button) findViewById(R.id.btnP2);
        btnp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Menu.this, ScenicMainActivity2.class);
                startActivity(i);
            }
        });
        btnp3 = (Button) findViewById(R.id.btnP3);
        btnp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Menu.this, ScenicMainActivity3.class);
                startActivity(i);
            }
        });
        btnp4 = (Button) findViewById(R.id.btnP4);
        btnp4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Menu.this, ScenicMainActivity4.class);
                startActivity(i);
            }
        });
        btnp5 = (Button) findViewById(R.id.btnP5);
        btnp5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Menu.this, ScenicMainActivity5.class);
                startActivity(i);
            }
        });
    }
    public void smsSendMessage(View view) {
//        TextView textView = (TextView) findViewById(R.id.number_to_call);
        // Use format with "smsto:" and phone number to create smsNumber.
//        String smsNumber = String.format("smsto: %s",
//                textView.getText().toString());
        String smsNumber = String.format("smsto: %s","0929122219");
        // Find the sms_message view.
//        EditText smsEditText = (EditText) findViewById(R.id.sms_message);
        // Get the text of the SMS message.
//        String sms = smsEditText.getText().toString();
        String sms = "HELP!";

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

    }
}
