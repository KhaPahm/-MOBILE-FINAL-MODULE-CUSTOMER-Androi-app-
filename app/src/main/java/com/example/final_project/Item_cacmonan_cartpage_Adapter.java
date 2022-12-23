package com.example.final_project;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

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

public class Item_cacmonan_cartpage_Adapter extends RecyclerView.Adapter<Item_cacmonan_cartpage_Adapter.ViewHolder>{
    private ArrayList<Item_cacmonan_cartpage> listitems;
    private Context context;
    private String urlDelete = "http://10.0.0.91/api/bill_api.php";

    public Item_cacmonan_cartpage_Adapter(ArrayList<Item_cacmonan_cartpage> items, Context context)
    {
        this.listitems=items;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View itemView=inflater.inflate(R.layout.cart_view_item_design,parent,false);
        ViewHolder viewHolder=new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       Item_cacmonan_cartpage item=listitems.get(position);
       Bitmap mImage=item.getmImage();
       String mName=item.getmName();
       String mSoluong=item.getmSoluong();
       String mCost=item.getmCost();

       holder.mImage.setImageBitmap(item.getmImage());
       holder.mName.setText(item.getmName());
       holder.mSoluong.setText(item.getmSoluong());
       holder.mCost.setText(item.getmCost());

        holder.huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteDish(urlDelete, item, position);
            }
        });
    }

    private void deleteDish(String url, Item_cacmonan_cartpage item, int position) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Boolean status = jsonObject.getBoolean("status");

                            if(status) {
                                Toast.makeText(context, "Was deleted dish!", Toast.LENGTH_SHORT).show();
                                listitems.remove(position);
                                notifyItemRemoved(position);
                            } else {
                                Toast.makeText(context, "Can't deleted dish!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("bill_id_de", String.valueOf(item.getBill_id()));
                params.put("dish_id_de", String.valueOf(item.getDish_id()));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    @Override
    public int getItemCount() {
        return listitems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView mImage;
        TextView mName;
        TextView mSoluong;
        TextView mCost;
        Button huy;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            mImage=itemView.findViewById(R.id.imageView_cart_detal_img);
            mName=itemView.findViewById(R.id.cart_detal_name);
            mSoluong=itemView.findViewById(R.id.title_cart_detail_soluong);
            mCost=itemView.findViewById(R.id.title_cart_detail_price);
            huy=itemView.findViewById(R.id.cart_button_detail_delete);
        }
    }
}


