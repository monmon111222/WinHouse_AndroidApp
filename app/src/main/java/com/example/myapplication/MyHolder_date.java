package com.example.myapplication;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Hp on 3/17/2016.
 */
public class MyHolder_date extends RecyclerView.ViewHolder implements View.OnClickListener {


    TextView dateTxt;
    ItemClickListener itemClickListener;


    public MyHolder_date(View itemView) {
        super(itemView);

        //ASSIGN

        dateTxt= (TextView) itemView.findViewById(R.id.dateTxt);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(v,getLayoutPosition());
    }

    public void setItemClickListener(ItemClickListener ic)
    {
        this.itemClickListener=ic;
    }
}
