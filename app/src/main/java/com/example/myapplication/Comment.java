package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Comment extends AppCompatActivity {
private EditText edit_text_cmt;

    DBHelper helper = new DBHelper(this);
    ListView list;
    ArrayList array_list;
    ArrayAdapter arrayAdapter;
    ImageButton btnsend;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    ZoomLayoutD zoomLayoutD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment);
        zoomLayoutD=(ZoomLayoutD) findViewById(R.id.drawerLayout);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("討論區");
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        AlertDialog.Builder builder2 =new AlertDialog.Builder(this);
        builder2.setTitle("您尚未擁有會員身分，無法新增評論")
                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        final AlertDialog dialog2 = builder2.create();
        array_list = helper.showComment(Contants.sid);
        arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1, array_list);
        list = (ListView)findViewById(R.id.LVc);
        list.setAdapter(arrayAdapter);
        edit_text_cmt = (EditText)findViewById(R.id.edit_text_cmt);
        btnsend = (ImageButton) findViewById(R.id.btnsend);
        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Contants.uid!="0") {
                    final String get_edittext = edit_text_cmt.getText().toString();
                    //helper.insertComment(Contants.uid,Contants.sid,get_edittext);
                    if (helper.insertComment(Contants.uid, Contants.sid, get_edittext)) {
                        Toast.makeText(Comment.this, "新增評論成功", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent();
                        i.setClass(Comment.this, Comment.class);
                        startActivity(i);
                    }
                }else{
                    dialog2.show();
                }

            }

        });


    }
}
