package com.example.final_project;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class Item_banhmi_Adapter extends RecyclerView.Adapter<Item_banhmi_Adapter.ViewHolder>{
    private ArrayList<Item_food_fragment> listitems;
    private Context context;

    public Item_banhmi_Adapter(ArrayList<Item_food_fragment> items, Context context)
    {
        this.listitems=items;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View itemView=inflater.inflate(R.layout.food_view_item_design,parent,false);
        ViewHolder viewHolder=new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       Item_food_fragment item=listitems.get(position);

       holder.tv_name.setText(item.getmName());
       holder.im_item.setImageBitmap(item.getmImage());
       holder.namestore.setText(item.getmNameStore());
       holder.cost.setText(item.getmCost());

       holder.im_item.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               onClickGoToDetail(item);
           }
       });
    }

    private void onClickGoToDetail(Item_food_fragment item) {
        Intent intent = new Intent(context, Detail_food.class);
        intent.putExtra("name", item.getmName());
        intent.putExtra("store", item.getmNameStore());
        intent.putExtra("price", item.getmCost());

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        item.getmImage().compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        intent.putExtra("img", byteArray);
        intent.putExtra("id", item.getId());
        intent.putExtra("shop_id", item.getShop_id());

//        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return listitems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView im_item;
        TextView tv_name;
        TextView namestore;
        TextView cost;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            tv_name=itemView.findViewById(R.id.textView_food_view_item_name);
            im_item=itemView.findViewById(R.id.imageView_food_view_item_img);
            namestore=itemView.findViewById(R.id.textView_food_view_item_store);
            cost=itemView.findViewById(R.id.textView_food_view_item_price);
        }
    }
}


