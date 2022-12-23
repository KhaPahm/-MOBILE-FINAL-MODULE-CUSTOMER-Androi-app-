package com.example.final_project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login_Screen extends AppCompatActivity {
    private static final String FILE_NAME = "myFile";
    Button button;
    TextView editText_username,editText_password,text_register,text_forgot;
    String urlLogin = "http://10.0.0.91/api/customer_api.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        editText_username = findViewById(R.id.editText_username);
        editText_password = findViewById(R.id.editText_password);
        button = findViewById(R.id.button);
        //Đăng nhập
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strU = editText_username.getText().toString();
                String strP = editText_password.getText().toString();
                if(TextUtils.isEmpty(strU) || TextUtils.isEmpty(strP))
                {
                    Toast.makeText(getApplicationContext(),"Không được để trống username hoặc password!",Toast.LENGTH_SHORT).show();
                }
                else
                {
//                    if(strU.equals("admin") && strP.equals("admin"))
//                    {
//                        Toast.makeText(getApplicationContext(),"Đăng nhập thành công !",Toast.LENGTH_SHORT).show();
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                startActivity(new Intent(Login_Screen.this,HomePage_Screen.class));
//                            }
//                        },500);
//                    }
//                    else
//                    {
//                        Toast.makeText(getApplicationContext(),"Username hoặc password sai",Toast.LENGTH_SHORT).show();
//                    }

                    Login(urlLogin);
                }
            }
        });



        //Đăng kí
        text_register = findViewById(R.id.text_register);
        text_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login_Screen.this,Register_Screen.class));
            }
        });

        //Quên mật khẩu
        text_forgot = findViewById(R.id.text_forgot);
        text_forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login_Screen.this,ForgotPassword_Screen.class));
            }
        });

    }

    private void Login(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Boolean status = jsonObject.getBoolean("status");


                            if(status) {
                                JSONArray cutomerArray = jsonObject.getJSONArray("customer");
                                JSONObject customer = cutomerArray.getJSONObject(0);
                                User user = new User(customer.getString("name"), customer.getString("email"), customer.getString("phone"));
                                Toast.makeText(Login_Screen.this, "Login success", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Login_Screen.this,HomePage_Screen.class);
                                StoredDataUsingSharedPref(customer.getString("name"), customer.getString("email"), customer.getString("phone"));
                                startActivity(intent);
                            } else {
                                Toast.makeText(Login_Screen.this, "Sai email hoac mat khau!", Toast.LENGTH_SHORT).show();

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Login_Screen.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("eamil_login", editText_username.getText().toString().trim());
                params.put("password_login", editText_password.getText().toString().trim());
                return  params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void StoredDataUsingSharedPref(String name, String email, String phone) {
        SharedPreferences.Editor editor = getSharedPreferences(FILE_NAME,MODE_PRIVATE).edit();
        editor.putString("name", name);
        editor.putString("email", email);
        editor.putString("phone", phone);
        editor.apply();

    }
}