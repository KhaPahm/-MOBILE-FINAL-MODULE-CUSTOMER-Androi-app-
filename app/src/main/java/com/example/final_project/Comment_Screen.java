package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class Comment_Screen extends AppCompatActivity {
    private RecyclerView recyclerView1;
    private Item_food_detail_comment mitem1;
    private ArrayList<Item_food_detail_comment> listitem1;
    private Item_food_detail_comment_Adapter mitemAdapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_comment);

        recyclerView1=findViewById(R.id.recyclerView_comment);
        listitem1=new ArrayList<>();
        data();

        mitemAdapter1=new Item_food_detail_comment_Adapter(listitem1,this);
        recyclerView1.setAdapter(mitemAdapter1);
        recyclerView1.setLayoutManager(new GridLayoutManager(this,1));
    }
    private void data(){
        listitem1.add(new Item_food_detail_comment("Phúc Hậu","Cơm ngon quá! Lần sau sẽ ủng hộ"));
        listitem1.add(new Item_food_detail_comment("Phúc Hậu","Cơm ngon quá! Lần sau sẽ ủng hộ"));
        listitem1.add(new Item_food_detail_comment("Phúc Hậu","Cơm ngon quá! Lần sau sẽ ủng hộ"));
        listitem1.add(new Item_food_detail_comment("Phúc Hậu","Cơm ngon quá! Lần sau sẽ ủng hộ"));
        listitem1.add(new Item_food_detail_comment("Phúc Hậu","Cơm ngon quá! Lần sau sẽ ủng hộ"));
        listitem1.add(new Item_food_detail_comment("Phúc Hậu","Cơm ngon quá! Lần sau sẽ ủng hộ"));
        listitem1.add(new Item_food_detail_comment("Phúc Hậu","Cơm ngon quá! Lần sau sẽ ủng hộ"));
        listitem1.add(new Item_food_detail_comment("Phúc Hậu","Cơm ngon quá! Lần sau sẽ ủng hộ"));
        listitem1.add(new Item_food_detail_comment("Phúc Hậu","Cơm ngon quá! Lần sau sẽ ủng hộ"));
        listitem1.add(new Item_food_detail_comment("Phúc Hậu","Cơm ngon quá! Lần sau sẽ ủng hộ"));
        listitem1.add(new Item_food_detail_comment("Phúc Hậu","Cơm ngon quá! Lần sau sẽ ủng hộ"));
        listitem1.add(new Item_food_detail_comment("Phúc Hậu","Cơm ngon quá! Lần sau sẽ ủng hộ"));
        listitem1.add(new Item_food_detail_comment("Phúc Hậu","Cơm ngon quá! Lần sau sẽ ủng hộ"));
    }
}