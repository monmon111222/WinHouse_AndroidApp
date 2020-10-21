package com.example.myapplication;



import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class SignIn extends AppCompatActivity {

    DBHelper helper = new DBHelper(this);
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);
        Button sinupbtn = (Button) findViewById(R.id.Bsignup);
        sinupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignIn.this, SignUp.class);
                startActivity(i);
            }
        });
        Button loginbtn = (Button) findViewById(R.id.Blogin);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText a = (EditText) findViewById(R.id.TFuname);
                String str = a.getText().toString();
                EditText b = (EditText) findViewById(R.id.TFpassword);
                String pass = b.getText().toString();

                if(helper.checkUser(str,pass)){
                    Cursor c =helper.GetUser(str);
                    c.moveToFirst();
                        Contants.uid = Integer.toString(c.getInt(0));
                        Contants.uaname = c.getString(1);
                        //int uid = c.getInt(0);
                        //Toast.makeText(SignIn.this, show, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(SignIn.this, Menu.class);
                    //i.putExtra("uid",uid);
                    startActivity(i);
                }else{
                    Toast.makeText(SignIn.this,"找不到此用戶", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
