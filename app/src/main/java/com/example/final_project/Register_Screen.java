package com.example.final_project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Register_Screen extends AppCompatActivity {
    EditText editText_new_email, editText_new_phone, editText_password2, editText_name;
    Button btn_register;
    String urlInsert = "http://10.0.0.91/api/customer_api.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        init();
        onClick();
    }

    private void onClick() {
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText_name.getText().toString().trim();
                String phone = editText_new_phone.getText().toString().trim();
                String password = editText_password2.getText().toString().trim();
                String email = editText_new_email.getText().toString().trim();
                if(name.isEmpty() || phone.isEmpty() || password.isEmpty() || email.isEmpty()) {
                    Toast.makeText(Register_Screen.this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                } else {
                    addAccount(urlInsert);
                }
            }
        });
    }

    private void addAccount(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Boolean status = jsonObject.getBoolean("status");

                            if(status) {
                                Toast.makeText(Register_Screen.this, "Sign up success", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Register_Screen.this, Login_Screen.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(Register_Screen.this, "Can't sign up!", Toast.LENGTH_SHORT).show();

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Register_Screen.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Register_Screen.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
                ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name_signup", editText_name.getText().toString().trim());
                params.put("email_signup", editText_new_email.getText().toString().trim());
                params.put("phone_signup", editText_new_phone.getText().toString().trim());
                params.put("pass_signup", editText_password2.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void init() {
        editText_new_email = findViewById(R.id.editText_new_email);
        editText_new_phone = findViewById(R.id.editText_new_phone);
        editText_password2 = findViewById(R.id.editText_password2);
        editText_name= findViewById(R.id.editText_name);
        btn_register = findViewById(R.id.btn_register);
    }
}