package com.example.final_project;

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

public class SettingPage extends Fragment {
    private RecyclerView recyclerView;
    private Item_food mitem;
    private ArrayList<Item_food> listitem;
    private Item_setting_Adapter mitemAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view_page = inflater.inflate(R.layout.setting_page, container,false);

        recyclerView=view_page.findViewById(R.id.recyclerView_setting);
        listitem=new ArrayList<>();
        data();

        mitemAdapter=new Item_setting_Adapter(listitem,getContext());
        recyclerView.setAdapter(mitemAdapter);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));

        return view_page;
    }
    private void data(){
        listitem.add(new Item_food("Lịch sử",R.drawable.ic_lichsu_cam));
        listitem.add(new Item_food("Ví thanh toán",R.drawable.ic_vitaikhoan));
        listitem.add(new Item_food("Đổi mật khẩu",R.drawable.ic_changepassword_icon));
        listitem.add(new Item_food("Đăng bài",R.drawable.ic_postbai_cam));
    }
}
