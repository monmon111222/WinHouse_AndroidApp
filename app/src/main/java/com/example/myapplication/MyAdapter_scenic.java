package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

/**
 * Created by Hp on 3/17/2016.
 */
public class MyAdapter_scenic extends RecyclerView.Adapter<MyHolder_scenic> {
    Context c;
    ArrayList<Scenic> scenics;

    public MyAdapter_scenic(Context ctx, ArrayList<Scenic> scenics)
    {
        //ASSIGN THEM LOCALLY
        this.c=ctx;
        this.scenics = scenics;
    }

    @Override
    public MyHolder_scenic onCreateViewHolder(ViewGroup parent, int viewType) {
        //VIEW OBJ FROM XML
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.scenic_model,null);

        //holder
        MyHolder_scenic holder=new MyHolder_scenic(v);

        return holder;
    }

    //BIND DATA TO VIEWS
    @Override
    public void onBindViewHolder(MyHolder_scenic holder, int position) {
           holder.posTxt.setText(scenics.get(position).getPosition());
           holder.nameTxt.setText(scenics.get(position).getName());
           byte[] getimg= scenics.get(position).getImg();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(getimg);
        Bitmap bitmapimg = BitmapFactory.decodeStream(inputStream);
        holder.imgV.setImageBitmap(bitmapimg);
        //HANDLE ITEMCLICKS
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                //OPEN DETAIL ACTIVITY
                //PASS DATA
                //CREATE INTENT
                Intent i=new Intent(c, DetailActivity_scenic.class);

                //LOAD DATA
                i.putExtra("NAME", scenics.get(pos).getName());
                i.putExtra("MONTH", scenics.get(pos).getMonth());
                i.putExtra("PHONE", scenics.get(pos).getPhone());
                i.putExtra("DEVICE", scenics.get(pos).getDevice());
                i.putExtra("OPEN", scenics.get(pos).getOpen());
                i.putExtra("TRANS", scenics.get(pos).getTransportation());
                i.putExtra("PAR", scenics.get(pos).getParking());
                i.putExtra("POSITION", scenics.get(pos).getPosition());
                i.putExtra("INFO", scenics.get(pos).getInfo());
                i.putExtra("ID", scenics.get(pos).getId());
                i.putExtra("IMG", scenics.get(pos).getImg());
                i.putExtra("IMG1", scenics.get(pos).getImg1());
                i.putExtra("IMG2", scenics.get(pos).getImg2());
                i.putExtra("IMG3", scenics.get(pos).getImg3());

                //START ACTIVITY
                c.startActivity(i);


            }
        });

    }

    @Override
    public int getItemCount() {
        return scenics.size();
    }
}
