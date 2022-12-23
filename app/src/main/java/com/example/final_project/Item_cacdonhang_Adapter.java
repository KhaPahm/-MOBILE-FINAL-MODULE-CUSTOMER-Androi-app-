package com.example.final_project;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Item_cacdonhang_Adapter extends RecyclerView.Adapter<Item_cacdonhang_Adapter.ViewHolder>{
    private ArrayList<Item_cacdonhang> listitems;
    private Context context;

    public Item_cacdonhang_Adapter(ArrayList<Item_cacdonhang> items, Context context)
    {
        this.listitems=items;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View itemView=inflater.inflate(R.layout.history_view_item_design,parent,false);
        ViewHolder viewHolder=new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       Item_cacdonhang item=listitems.get(position);
       String title=item.getmTitle();
       String date=item.getmDate();
       String cost=item.getmCost();

       holder.title.setText(item.getmTitle());
       holder.date.setText(item.getmDate());
       holder.cost.setText(item.getmCost());

       holder.chitietdonhang.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
                //Intent intent=new Intent(context,Fragment_list_food_choice_Screen.class);
                //context.startActivity(intent);
               Toast.makeText(context,"Chi tiết đơn hàng !",Toast.LENGTH_SHORT).show();
           }
       });
       holder.danhgiadonhang.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(context,History_Danhgiadonhang_Screen.class);
               context.startActivity(intent);
           }
       });
       holder.giaothanhcong.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               //Intent intent=new Intent(context,Fragment_list_food_choice_Screen.class);
               //context.startActivity(intent);
               Toast.makeText(context,"Giao thành công !",Toast.LENGTH_SHORT).show();
           }
       });
    }

    @Override
    public int getItemCount() {
        return listitems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView date;
        TextView cost;
        Button chitietdonhang,danhgiadonhang,giaothanhcong;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            title=itemView.findViewById(R.id.title_history_food);
            date=itemView.findViewById(R.id.date_history_food);
            cost=itemView.findViewById(R.id.price_history_food);
            chitietdonhang=itemView.findViewById(R.id.button_history_detail);
            danhgiadonhang=itemView.findViewById(R.id.button_history_delete);
            giaothanhcong=itemView.findViewById(R.id.button_history_thanhcong);
        }
    }
}


