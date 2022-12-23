package com.example.final_project;

import static android.content.Intent.getIntent;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomePage extends Fragment {
    private RecyclerView recyclerView;
    private Item_food mitem;
    private ArrayList<Item_food> listitem;
    private Item_food_Adapter mitemAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view_page = inflater.inflate(R.layout.home_page, container,false);

        recyclerView=view_page.findViewById(R.id.recyclerView);
        listitem=new ArrayList<>();
        data();

        mitemAdapter=new Item_food_Adapter(listitem,getContext());
        recyclerView.setAdapter(mitemAdapter);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));

        return view_page;
    }
    private void data(){
        listitem.add(new Item_food("Cơm",R.drawable.ic_rice));
        listitem.add(new Item_food("Bánh mì",R.drawable.ic_banhmi));
        listitem.add(new Item_food("Nước giải khát",R.drawable.ic_drink));
        listitem.add(new Item_food("Bún - Mì",R.drawable.ic_noodle));
    }

}
