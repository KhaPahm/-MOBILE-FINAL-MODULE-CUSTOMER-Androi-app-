package com.example.final_project;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class InformationPage extends Fragment {
    private static final String FILE_NAME = "myFile";
    private EditText email_infor,phone_infor, edname_infor;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view_page = inflater.inflate(R.layout.information_page, container,false);
        email_infor = view_page.findViewById(R.id.email_infor);
        phone_infor = view_page.findViewById(R.id.phone_infor);
        edname_infor = view_page.findViewById(R.id.edname_infor);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);

        email_infor.setText(sharedPreferences.getString("email", ""));
        phone_infor.setText(sharedPreferences.getString("phone", ""));
        edname_infor.setText(sharedPreferences.getString("name", ""));
        return view_page;
    }
}
