package com.example.final_project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

import java.util.HashMap;
import java.util.Map;

public class Complete_Screen extends AppCompatActivity {
    String toal, bill_id, email;
    TextView complete_price;
    Button complete_button;
    String urlOrderbill = "http://10.0.0.91/api/bill_api.php";
    EditText complete_choice_address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complete);
        complete_price = findViewById(R.id.complete_price);
        complete_button = findViewById(R.id.complete_button);
        complete_choice_address = findViewById(R.id.complete_choice_address);
        Intent intent = getIntent();
        toal = String.valueOf(intent.getIntExtra("total", 0));
        bill_id = intent.getStringExtra("bill_id");
        email = intent.getStringExtra("email");
        complete_price.setText(toal);

        complete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order(urlOrderbill);
            }
        });
    }

    private void order(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Boolean status = jsonObject.getBoolean("status");

                            if(status) {
                                Toast.makeText(Complete_Screen.this, "Order success", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Complete_Screen.this, HomePage_Screen.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(Complete_Screen.this, "Order erro!", Toast.LENGTH_SHORT).show();

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Complete_Screen.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Complete_Screen.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("order_bill_id", bill_id);
                params.put("address_order", complete_choice_address.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}