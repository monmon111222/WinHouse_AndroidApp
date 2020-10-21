package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import static android.view.Gravity.CENTER_HORIZONTAL;


public class User_edit extends AppCompatActivity {
    DBHelper helper = new DBHelper(this);
    EditText name,phonenumber1,phonenumber2,birthday,bodyhistory,re;
    Button checkeditbtn;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    ZoomLayoutD zoomLayoutD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_edit);
        zoomLayoutD=(ZoomLayoutD) findViewById(R.id.drawerLayout);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("編輯會員資訊");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Cursor c2 = helper.GetUserInfo(Contants.uid);
        c2.moveToNext();
        name = (EditText) findViewById((R.id.ueTFname));
        name.setText(c2.getString(1));
        phonenumber1 = (EditText) findViewById((R.id.ueTFphonenumber));
        phonenumber1.setText(c2.getString(2));
        phonenumber2 = (EditText) findViewById((R.id.ueTFphonenumber2));
        phonenumber2.setText(c2.getString(3));
        birthday = (EditText) findViewById((R.id.ueTFbirthday));
        birthday.setText(c2.getString(5));
        bodyhistory = (EditText) findViewById((R.id.ueTFbodyhistory));
        bodyhistory.setText(c2.getString(6));
        re = (EditText) findViewById((R.id.ueTFre));
        re.setText(c2.getString(4));
        checkeditbtn = (Button) findViewById(R.id.btncheckedit);
        checkeditbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String namestr = name.getText().toString();
                final String phonenumber1str = phonenumber1.getText().toString();
                final String phonenumber2tr = phonenumber2.getText().toString();
                final String rr = re.getText().toString();
                final String birthdaystr = birthday.getText().toString();
                final String bodyhistorystr = bodyhistory.getText().toString();
                helper.updateContact(Contants.uid,namestr,phonenumber1str,phonenumber2tr,rr,birthdaystr,bodyhistorystr);
                Intent i = new Intent();
                i.setClass(User_edit.this, User_info.class);
                startActivity(i);
            }

        });

    }
}