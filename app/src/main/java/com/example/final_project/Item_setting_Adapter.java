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

public class Item_setting_Adapter extends RecyclerView.Adapter<Item_setting_Adapter.ViewHolder> {
    private ArrayList<Item_food> listitems;
    private Context context;

    public Item_setting_Adapter(ArrayList<Item_food> items, Context context) {
        this.listitems = items;
        this.context = context;
    }

    @NonNull
    @Override
    public Item_setting_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.icon_menu_setting_design, parent, false);
        Item_setting_Adapter.ViewHolder viewHolder = new Item_setting_Adapter.ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Item_setting_Adapter.ViewHolder holder, int position) {
        Item_food item = listitems.get(position);
        String name = item.getmName();
        int im_item = item.getmImage();
        holder.tv_name.setText(item.getmName());
        holder.im_item.setImageResource(item.getmImage());

        holder.im_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position==0)
                {
                    Intent intent = new Intent(context, History_view_Screen.class);
                    context.startActivity(intent);
                }
                else if(position==1)
                {
                    Intent intent = new Intent(context, Money_bank_account_Screen.class);
                    context.startActivity(intent);
                }
                else if(position==2)
                {
                    Intent intent = new Intent(context, Change_password_Screen.class);
                    context.startActivity(intent);
                }
                else if(position==3)
                {
                    Toast.makeText(context,"Chức năng đang phát triển !",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listitems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private RelativeLayout layoutItem;
        ImageView im_item;
        TextView tv_name;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            im_item=itemView.findViewById(R.id.imageView_show_icon);
            tv_name=itemView.findViewById(R.id.textView_name_icon);
        }
    }
}
