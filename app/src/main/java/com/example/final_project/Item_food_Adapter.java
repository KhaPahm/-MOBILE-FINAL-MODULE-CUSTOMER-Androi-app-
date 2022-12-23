package com.example.final_project;

import android.content.ClipData;
import android.content.Intent;
import android.view.LayoutInflater;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Item_food_Adapter extends RecyclerView.Adapter<Item_food_Adapter.ViewHolder>{
    private ArrayList<Item_food> listitems;
    private Context context;

    public Item_food_Adapter(ArrayList<Item_food> items,Context context)
    {
        this.listitems=items;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View itemView=inflater.inflate(R.layout.icon_menu_food_design,parent,false);
        ViewHolder viewHolder=new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       Item_food item=listitems.get(position);
       String name=item.getmName();
       int im_item=item.getmImage();
       holder.tv_name.setText(item.getmName());
       holder.im_item.setImageResource(item.getmImage());

       holder.im_item.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
                Intent intent=new Intent(context,Fragment_list_food_choice_Screen.class);
                intent.putExtra("loaifood",listitems.get(position).getmName());
                context.startActivity(intent);
           }
       });
    }

    @Override
    public int getItemCount() {
        return listitems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView im_item;
        TextView tv_name;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            im_item=itemView.findViewById(R.id.imageView_show_icon);
            tv_name=itemView.findViewById(R.id.textView_name_icon);
        }
    }
}


