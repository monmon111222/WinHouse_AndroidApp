package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailActivity_date_edit extends AppCompatActivity {
    DBHelper helper = new DBHelper(this);
    TextView timetv,eattv,gatheretv;
    Button btneditgroupcheck,btnchancelgroupcheck;
    LinearLayout checklv;
    RadioGroup checkgp;
    RadioButton rbtnyes,rbtnno;
    String check;
    private NavigationView navigation_view;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private String  TAG="[ST]";
    LinearLayout tech;
    ZoomLayoutD zoomLayoutD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_info_u);
        zoomLayoutD=(ZoomLayoutD) findViewById(R.id.drawerLayout);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("編輯揪團資訊");
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        Cursor c=helper.getGroupFromUser(Contants.gid);
        c.moveToFirst();
        //RECEIVE DATA FROM MAIN ACTIVITY
        Intent i = getIntent();
        //final String time = i.getExtras().getString("TIME");
        //final  String eat="飲食限制:"+i.getExtras().getString("EAT");
        final String gid=""+i.getExtras().get("ID");
        final String time = c.getString(1);
        final String eat="飲食限制:"+c.getString(2);
        String g = c.getString(3);



        timetv = (TextView) findViewById(R.id.timetextView2);
        eattv=(TextView)findViewById(R.id.eattextView2);
        gatheretv=(EditText)findViewById(R.id.gatheretv);

        btneditgroupcheck=(Button)findViewById(R.id.btneditgroupcheck);
        btnchancelgroupcheck=(Button)findViewById(R.id.btnchancelgroupchancel);
        checkgp = (RadioGroup)findViewById(R.id.checkgroup);
        rbtnno=(RadioButton)findViewById(R.id.no);
        rbtnyes=(RadioButton)findViewById(R.id.yes);

        rbtnno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check ="2";
            }
        });
        rbtnyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check ="1";
            }
        });
        if(check!="1"&&check!="2"){
            check="3";
        }

        timetv.setText(time);
        eattv.setText(eat);
        gatheretv.setText(g.replace("未填寫",""));
        //inal  String gather=gatheretv.getText().toString();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date d1 = null;
        try {
            d1 = df.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.util.Date d2 = new Date();
        long diff = d1.getTime() - d2.getTime();
        String relation = null;
        if (d1.after(d2)) {
            relation = (diff / (1000 * 60 * 60 * 24)) + "";
            if (relation.equals("1")) {
                checklv=(LinearLayout)findViewById(R.id.checkLV);
                checklv.setVisibility(View.VISIBLE);
            }
        }
            btneditgroupcheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String gather=gatheretv.getText().toString();
                    helper.updateGroup(Contants.gid,gather+"",check);
                    Intent intent = new Intent(DetailActivity_date_edit.this, Group.class);
                    startActivity(intent);


                }
            });
            btnchancelgroupcheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        Intent intent = new Intent(DetailActivity_date_edit.this, DetailActivity_date.class);
                        startActivity(intent);

                }
            });


        }


    }



