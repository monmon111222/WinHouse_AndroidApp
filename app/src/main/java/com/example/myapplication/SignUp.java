package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static android.view.Gravity.CENTER_HORIZONTAL;


public class SignUp extends AppCompatActivity {
    DBHelper helper = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        Button checksinupbtn = (Button) findViewById(R.id.Bsignupbutton);
        checksinupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText name = (EditText) findViewById((R.id.TFname));
                EditText phonenumber1 = (EditText) findViewById((R.id.TFphonenumber));
                EditText phonenumber2 = (EditText) findViewById((R.id.TFphonenumber2));
                EditText re = (EditText) findViewById((R.id.TFr));

                EditText birthday = (EditText) findViewById((R.id.TFbirthday));
                EditText bodyhistory = (EditText) findViewById((R.id.TFbodyhistory));
                EditText uname = (EditText) findViewById((R.id.TFuname));
                EditText pass1 = (EditText) findViewById((R.id.TFpass));
                EditText pass2 = (EditText) findViewById((R.id.TFpass2));

                String namestr = name.getText().toString();
                String phonenumber1str = phonenumber1.getText().toString();
                String phonenumber2tr = phonenumber2.getText().toString();
                String rr = re.getText().toString();
                String birthdaystr = birthday.getText().toString();
                String bodyhistorystr = bodyhistory.getText().toString();
                String unamestr = uname.getText().toString();
                String pass1str = pass1.getText().toString();
                String pass2str = pass2.getText().toString();

                if (!pass1str.equals(pass2str)) {
                    //popup massage
                    Toast pass = Toast.makeText(SignUp.this, "密碼不一致!請再試一次!", Toast.LENGTH_SHORT);
                    pass.show();
                } else {
                    //insert the details in database
                    Contact c = new Contact();
                    c.setName(namestr);
                    c.setPhonenumber1(phonenumber1str);
                    c.setPhonenumber2(phonenumber2tr);
                    c.setRe(rr);
                    c.setBday(birthdaystr);
                    c.setBhistory(bodyhistorystr);
                    c.setUname(unamestr);
                    c.setPass(pass1str);

                    helper.insertContact2(namestr, phonenumber1str, phonenumber2tr,rr, birthdaystr, bodyhistorystr, unamestr, pass1str);
                    if (helper.insertContact2(namestr, phonenumber1str, phonenumber2tr,rr, birthdaystr, bodyhistorystr, unamestr, pass1str)) {
                        Toast.makeText(SignUp.this, "註冊成功", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(SignUp.this, SignIn.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(SignUp.this, "註冊失敗", Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });
        Button Checkunbtn = (Button) findViewById(R.id.Checkunbtn);
        Checkunbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText n = (EditText) findViewById((R.id.TFuname));
                String strn = n.getText().toString();
                TextView checkunTV = (TextView) findViewById(R.id.checkunTV);
                Boolean check1 = helper.checkdoubleUser(strn);
                if (check1) {
                    checkunTV.setText("此帳號已存在!請使用其他名稱!");
                    checkunTV.setGravity(CENTER_HORIZONTAL);
                    checkunTV.setTextSize(18);

                } else {
                    checkunTV.setText("可以使用此名稱!");
                    checkunTV.setGravity(CENTER_HORIZONTAL);
                    checkunTV.setTextSize(18);
                }
            }
        });
    }
}