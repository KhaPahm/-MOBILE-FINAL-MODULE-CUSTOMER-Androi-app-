package com.example.final_project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Change_password_Screen extends AppCompatActivity {
    EditText editText_old_password, editText_new_password, editText_password4;
    Button btn_changePass;
    String urlChangePassWord = "http://10.0.0.91/api/customer_api.php";
    private static final String FILE_NAME = "myFile";
    String email = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password);

        editText_old_password = findViewById(R.id.editText_old_password);
        editText_new_password = findViewById(R.id.editText_new_password);
        editText_password4 = findViewById(R.id.editText_password4);
        btn_changePass = findViewById(R.id.btn_changePass);

        SharedPreferences sharedPreferences =getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        email = sharedPreferences.getString("email", "");



        btn_changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText_old_password.getText().toString().isEmpty() || editText_new_password.getText().toString().isEmpty()
                        || editText_password4.getText().toString() .isEmpty()) {
                    Toast.makeText(Change_password_Screen.this, "Điền đầy đủ thông tin để thay đổi mật khẩu", Toast.LENGTH_SHORT).show();
                } else if (!editText_new_password.getText().toString().equals(editText_password4.getText().toString()) ) {
                    Toast.makeText(Change_password_Screen.this, "Mật khẩu mới chưa khớp!", Toast.LENGTH_SHORT).show();
                } else {
                    changePassWord(urlChangePassWord);
                }
            }
        });

    }

    private void changePassWord(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Boolean status = jsonObject.getBoolean("status");

                            if(status) {
                                Toast.makeText(Change_password_Screen.this, "Password was updated", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(Change_password_Screen.this, "Wrong password!", Toast.LENGTH_SHORT).show();

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Change_password_Screen.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Change_password_Screen.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("change_email", email);
                params.put("old_pass", editText_old_password.getText().toString().trim());
                params.put("new_pass", editText_new_password.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}