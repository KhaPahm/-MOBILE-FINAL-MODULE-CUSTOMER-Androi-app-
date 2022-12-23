package com.example.final_project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Detail_food extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Item_food_detail mitem;
    private ArrayList<Item_food_detail> listitem;
    private Item_food_detail_Adapter mitemAdapter;
    private Button btn_add_bill;
    private String urlAddbill = "http://10.0.0.91/api/bill_api.php";
    private static final String FILE_NAME = "myFile";
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_food);
        btn_add_bill = findViewById(R.id.btn_add_bill);
        recyclerView = findViewById(R.id.recyclerView_detailfood);
        listitem=new ArrayList<>();
        Intent intent = getIntent();
        if(!intent.getStringExtra("name").isEmpty()) {
            byte[] byteArray = getIntent().getByteArrayExtra("img");
            Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            mitem = new Item_food_detail(intent.getIntExtra("id", 0), intent.getStringExtra("name"), bmp, intent.getStringExtra("store"), intent.getStringExtra("price"), intent.getIntExtra("shop_id", 0));
            listitem.add(mitem);
        }

        mitemAdapter=new Item_food_detail_Adapter(listitem,this);
        recyclerView.setAdapter(mitemAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));

        SharedPreferences sharedPreferences =getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        email = sharedPreferences.getString("email", "");

        btn_add_bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNew(urlAddbill);
                finish();
            }
        });
    }
    


    private void addNew(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Boolean status = jsonObject.getBoolean("status");
                            if(status) {
                                Toast.makeText(Detail_food.this, "Added!", Toast.LENGTH_SHORT).show();
                            } else {
                                String msg = jsonObject.getString("msg");
                                Toast.makeText(Detail_food.this, msg, Toast.LENGTH_SHORT).show();
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Detail_food.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Detail_food.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("addbill_email", email);
                params.put("addbill_price", String.valueOf((int) Math.round(Double.parseDouble(mitem.getmCost())/1000.0)));
                params.put("addbill_shopID", String.valueOf(mitem.getShop_id()));
                params.put("addbill_amount", "1");
                params.put("addbill_dish_id", String.valueOf(mitem.getId()));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

}