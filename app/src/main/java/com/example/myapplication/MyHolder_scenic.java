package com.example.myapplication;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Hp on 3/17/2016.
 */
public class MyHolder_scenic extends RecyclerView.ViewHolder implements View.OnClickListener {

    ImageView imgV;
    TextView nameTxt,posTxt;
    ItemClickListener itemClickListener;


    public MyHolder_scenic(View itemView) {
        super(itemView);

        //ASSIGN

        nameTxt= (TextView) itemView.findViewById(R.id.nameTxt);
        posTxt= (TextView) itemView.findViewById(R.id.posTxt);
        imgV = (ImageView)itemView.findViewById(R.id.MainimageView);

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
