package com.example.final_project;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Item_food_detail_Adapter extends RecyclerView.Adapter<Item_food_detail_Adapter.ViewHolder>{
    private ArrayList<Item_food_detail> listitems;
    private Context context;

    public Item_food_detail_Adapter(ArrayList<Item_food_detail> items, Context context)
    {
        this.listitems=items;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View itemView=inflater.inflate(R.layout.food_detail,parent,false);
        ViewHolder viewHolder=new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       Item_food_detail item=listitems.get(position);
       String mName=item.getmName();
       Bitmap mImage=item.getmImage();
       String mNameStore=item.getmNameStore();
       String mCost=item.getmCost();
       holder.mName.setText(item.getmName());
       holder.mImage.setImageBitmap(item.getmImage());
       holder.mNameStore.setText(item.getmNameStore());
       holder.mCost.setText(item.getmCost());

       holder.mNameStore.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
                Intent intent=new Intent(context,Comment_Screen.class);
                context.startActivity(intent);
           }
       });
    }

    @Override
    public int getItemCount() {
        return listitems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView mImage;
        TextView mName;
        TextView mNameStore;
        TextView mCost;
        RatingBar mRating;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            mImage=itemView.findViewById(R.id.imageView_food_view_item_img);
            mName=itemView.findViewById(R.id.textView_food_view_item_name);
            mNameStore=itemView.findViewById(R.id.textView_food_view_item_store);
            mCost=itemView.findViewById(R.id.textView_food_view_item_price);
            mRating=itemView.findViewById(R.id.ratingBar_food_detail);
        }
    }
}


