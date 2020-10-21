package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

/**
 * Created by Quoc Nguyen on 13-Dec-16.
 */

public class PhotoSingle extends AppCompatActivity {
    DBHelper helper = new DBHelper(this);
    PhotoListAdapter adapter = null;
    TouchImageView imgv;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_item);
        Intent i = getIntent();
        final String pid = i.getExtras().getString("PID");
        final byte[] img = helper.GetPhoto(pid);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(img);
        Bitmap bitmapimg = BitmapFactory.decodeStream(inputStream);
        imgv = (TouchImageView)findViewById(R.id.imgPhotos);
        imgv.setImageBitmap(bitmapimg);
    }
}