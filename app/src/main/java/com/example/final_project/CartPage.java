package com.example.final_project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.final_project.databinding.CartPageBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CartPage extends Fragment {
    private RecyclerView recyclerView;
    private Item_cacmonan_cartpage mitem;
    private ArrayList<Item_cacmonan_cartpage> listitem;
    private Item_cacmonan_cartpage_Adapter mitemAdapter;
    private String urlGetBillWait = "http://10.0.0.91/api/bill_api.php?bill_wait=";
    private String email = "";
    private static final String FILE_NAME = "myFile";
    private String billId = "";
    private TextView detail_food_price;
    private int total = 0;


    Button button;
    CartPageBinding cartPageBinding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view_page = inflater.inflate(R.layout.cart_page, container,false);

        detail_food_price = view_page.findViewById(R.id.detail_food_price);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        email = sharedPreferences.getString("email", "");
        urlGetBillWait+=email;
        Toast.makeText(getContext(), urlGetBillWait, Toast.LENGTH_SHORT).show();

        recyclerView=view_page.findViewById(R.id.recyclerView);
        listitem=new ArrayList<>();
        data(urlGetBillWait);

        mitemAdapter=new Item_cacmonan_cartpage_Adapter(listitem,getContext());
        recyclerView.setAdapter(mitemAdapter);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));


        button=view_page.findViewById(R.id.detail_button_pay);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),Complete_Screen.class); //Complete_Screen
                intent.putExtra("total", total);
                intent.putExtra("bill_id", billId);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });
        return view_page;
    }
    private void data(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
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
                                    byte[] bytes= Base64.decode(bill.getString("img"),Base64.DEFAULT);
                                    Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                                    String name = bill.getString("name");
                                    String amount = bill.getString("amount");
                                    int price = bill.getInt("price") * bill.getInt("amount") * 1000;
                                    String cost = String.valueOf(price);
                                    int bill_id = bill.getInt("bill_id");
                                    int dish_id = bill.getInt("dish_id");;
                                    listitem.add(new Item_cacmonan_cartpage(bitmap, name, amount, cost, bill_id, dish_id));
                                    billId = bill.getString("bill_id");
                                    total+=price;
                                }
                                detail_food_price.setText(String.valueOf(total));
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
