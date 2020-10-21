package com.example.myapplication;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Quoc Nguyen on 13-Dec-16.
 */

public class PhotoList extends AppCompatActivity {
    DBHelper helper = new DBHelper(this);
    GridView gridView;
    ArrayList<Photo> list;
    PhotoListAdapter adapter = null;
    ArrayList list2;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_list_activity);
        Intent i = getIntent();
        final String gid = i.getExtras().getString("GID");
        gridView = (GridView) findViewById(R.id.gridView);
        list = new ArrayList<>();
        adapter = new PhotoListAdapter(this, R.layout.photo_items,list);
        gridView.setAdapter(adapter);

        // get all data from sqlite
        Cursor cursor = helper.GetPhotos(gid);
        list.clear();
        final ArrayList<String> arrID = new ArrayList<String>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            //String name = cursor.getString(1);
            byte[] image = cursor.getBlob(1);
            String pgid = cursor.getString(2);
            list.add(new Photo(image,id,pgid));
            arrID.add(Integer.toString(id));
        }
        adapter.notifyDataSetChanged();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
                // TODO Auto-generated method stub
                String pid=arrID.get(arg2);
                Intent i=new Intent();
                i.putExtra("PID",pid);
                i.setClass(PhotoList.this, PhotoSingle.class);
                startActivity(i);
            }
        });
    }
}