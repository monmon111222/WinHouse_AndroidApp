package com.example.myapplication;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import java.util.Calendar;
import java.util.Date;

public class GroupSet extends AppCompatActivity {
    private Button show,enter;
    private DatePickerDialog datePickerDialog;
    private Calendar calendar;
    private EditText day;
    private EditText num;
    RadioButton vegbutton,unvegbutton,nolimitbutton;
    RadioGroup rg;
    DBHelper helper = new DBHelper(this);
    String eat,daystr;
    private NavigationView navigation_view;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private String  TAG="[ST]";
    LinearLayout tech;
    ZoomLayoutD zoomLayoutD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_set);
        navigation_view = (NavigationView) findViewById(R.id.navigation_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        zoomLayoutD=(ZoomLayoutD) findViewById(R.id.drawerLayout);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        toolbar.setTitle("全部景點");
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
                    Intent i = new Intent(GroupSet.this, Menu.class);
                    startActivity(i);
                    return true;
                }
                else if (id == R.id.action_help) {
                    // 按下「介面說明」要做的事
                    Toast.makeText(GroupSet.this, "請在此填寫揪團資訊，集合地點可日後再新增", Toast.LENGTH_LONG).show();

                    return true;
                }
                else if (id == R.id.action_user) {
                    // 按下「會員資訊」要做的事
                    if (Contants.uid=="0"){
                        AlertDialog.Builder builder =new AlertDialog.Builder(GroupSet.this);
                        builder.setTitle("您尚未登入會員，無法瀏覽會員資訊")
                                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }else {
                        Intent i = new Intent(GroupSet.this, User_info.class);
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
                    Intent i = new Intent(GroupSet.this, Emulate_picture.class);
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

        Intent intent= getIntent();
        day = (EditText) findViewById(R.id.day);
        show = (Button) findViewById(R.id.showdailog);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDailog();
            }
        });
        calendar = Calendar.getInstance();
        daystr = day.getText().toString();
        rg = (RadioGroup) findViewById(R.id.eatgroup);
        vegbutton = (RadioButton) findViewById(R.id.veg);
        vegbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 eat ="素食";
            }
        });
        unvegbutton = (RadioButton) findViewById(R.id.unveg);
        unvegbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  eat ="葷食";
            }
        });
        nolimitbutton = (RadioButton) findViewById(R.id.nolimit);
        nolimitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 eat ="無限制";
            }
        });

        //int Num = Integer.parseInt(numstr);
        enter = (Button) findViewById(R.id.enter);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText num=(EditText)findViewById(R.id.numText);
                  String numstr = num.getText().toString();
                if(numstr==null){
                    numstr="未填寫";
                }
                daystr = day.getText().toString();
                if(helper.insertGroup(daystr,eat,numstr,Contants.sid,Contants.uid)){
                    Cursor c=helper.getGroup(Contants.sid);
                    c.moveToLast();
                    String newgid=c.getString(c.getColumnIndex("gid"));
                    helper.insertGroupInList(Contants.uid,newgid);
                    Intent intent = new Intent();
                    intent.setClass(GroupSet.this, Group.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(GroupSet.this, "建立失敗", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }


    private void showDailog() {
        datePickerDialog = new DatePickerDialog(
                this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //monthOfYear 得到的月份会减1所以我们要加1
                String time = String.valueOf(year) + "-" + String.valueOf(monthOfYear + 1) + "-" + Integer.toString(dayOfMonth);
                day.setText(time);
                // Log.d("测试", time);
            }
        },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
        //自动弹出键盘问题解决
        datePickerDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }
}

