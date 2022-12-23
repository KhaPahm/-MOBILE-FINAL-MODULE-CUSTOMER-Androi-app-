package com.example.final_project;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.AccessController;
import java.util.ArrayList;

public class History_view_Screen extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Item_cacdonhang mitem;
    private ArrayList<Item_cacdonhang> listitem;
    private Item_cacdonhang_Adapter mitemAdapter;
    String urlHistory ="http://10.0.0.91/api/bill_api.php?email_history=";
    String email = "";
    private static final String FILE_NAME = "myFile";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_cacdonhang);

        recyclerView=findViewById(R.id.recyclerView_cacdonhang);
        listitem=new ArrayList<>();
        SharedPreferences sharedPreferences = History_view_Screen.this.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        email = sharedPreferences.getString("email", "");
        urlHistory+=email;
        data(urlHistory);

        mitemAdapter=new Item_cacdonhang_Adapter(listitem,this);
        recyclerView.setAdapter(mitemAdapter);

        recyclerView.setLayoutManager(new GridLayoutManager(this,1));

    }
    private void data(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(History_view_Screen.this);
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Boolean status = response.getBoolean("status");
                            if(status) {
                                JSONArray jsonArray = response.getJSONArray("data");
                                for(int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject bill = jsonArray.getJSONObject(i);
                                    listitem.add(new Item_cacdonhang("Ma Don Hang "+bill.getString("bill_id"),"Ngày giao: " + bill.getString("date"),"Giá: " + bill.getString("total")));
                                }
                                mitemAdapter.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }
}