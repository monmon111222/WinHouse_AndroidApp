package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by Hp on 3/17/2016.
 */
public class MyAdapter_date extends RecyclerView.Adapter<MyHolder_date> {
    Context c;
    ArrayList<Date> dates;

    public MyAdapter_date(Context ctx, ArrayList<Date> dates)
    {
        //ASSIGN THEM LOCALLY
        this.c=ctx;
        this.dates = dates;
    }

    @Override
    public MyHolder_date onCreateViewHolder(ViewGroup parent, int viewType) {
        //VIEW OBJ FROM XML
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.date_model,null);

        //holder
        MyHolder_date holder=new MyHolder_date(v);

        return holder;
    }

    //BIND DATA TO VIEWS
    @Override
    public void onBindViewHolder(MyHolder_date holder, int position) {
           holder.dateTxt.setText(dates.get(position).getTime());
        //HANDLE ITEMCLICKS
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                //OPEN DETAIL ACTIVITY
                //PASS DATA
                //CREATE INTENT
                Intent i=new Intent(c, DetailActivity_date.class);

                //LOAD DATA
                i.putExtra("ID", dates.get(pos).getId());
                i.putExtra("TIME", dates.get(pos).getTime());
                i.putExtra("EAT", dates.get(pos).getEat());
                i.putExtra("PNUM", dates.get(pos).getPnum());
                i.putExtra("SID", dates.get(pos).getSid());
                i.putExtra("UID",dates.get(pos).getUid());
                //START ACTIVITY
                c.startActivity(i);


            }
        });

    }

    @Override
    public int getItemCount() {
        return dates.size();
    }
}
