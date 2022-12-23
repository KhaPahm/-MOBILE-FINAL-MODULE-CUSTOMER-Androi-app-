package com.example.final_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Item_food_detail_comment_Adapter extends RecyclerView.Adapter<Item_food_detail_comment_Adapter.ViewHolder>{
    private ArrayList<Item_food_detail_comment> listitems1;
    private Context context;

    public Item_food_detail_comment_Adapter(ArrayList<Item_food_detail_comment> items, Context context)
    {
        this.listitems1=items;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View itemView=inflater.inflate(R.layout.comment_view_detail_see,parent,false);
        ViewHolder viewHolder=new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       Item_food_detail_comment item=listitems1.get(position);
       String mUser=item.getmUser();
       String mComment=item.getmComment();

       holder.mUser.setText(item.getmUser());
       holder.mComment.setText(item.getmComment());

       /*holder.im_item.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
                Intent intent=new Intent(context,Fragment_list_food_choice_Screen.class);
                intent.putExtra("loaifood",listitems.get(position).getmName());
                context.startActivity(intent);
           }
       });*/
    }

    @Override
    public int getItemCount() {
        return listitems1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView mUser;
        TextView mComment;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            mUser=itemView.findViewById(R.id.client_comment);
            mComment=itemView.findViewById(R.id.message_comment);
        }
    }
}


