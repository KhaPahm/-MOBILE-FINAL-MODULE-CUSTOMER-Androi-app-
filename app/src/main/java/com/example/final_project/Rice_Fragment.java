package com.example.final_project;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Rice_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Rice_Fragment extends Fragment {
    private RecyclerView recyclerView;
    private Item_food_fragment mitem;
    private ArrayList<Item_food_fragment> listitem;
    private Item_rice_Adapter mitemAdapter;
    private String urlRice = "http://10.0.0.91/api/dish_api.php?dishes_rice";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Rice_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Rice_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Rice_Fragment newInstance(String param1, String param2) {
        Rice_Fragment fragment = new Rice_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view_page = inflater.inflate(R.layout.fragment_rice_, container, false);

        recyclerView=view_page.findViewById(R.id.recyclerView_rice);
        listitem=new ArrayList<>();
        data(urlRice);

        mitemAdapter=new Item_rice_Adapter(listitem,getContext());
        recyclerView.setAdapter(mitemAdapter);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));

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
                                    JSONObject dish = jsonArray.getJSONObject(i);
                                    byte[] bytes= Base64.decode(dish.getString("img"),Base64.DEFAULT);
                                    Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                                    listitem.add(new Item_food_fragment(dish.getInt("dish_id"), dish.getString("name"), bitmap, dish.getString("shop_name"), String.valueOf((dish.getDouble("price")*1000)), dish.getInt("shop_id")));
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
//        JsonArrayRequest jsonArrayRequest1 = new JsonArrayRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        try {
//                            JSONObject t = response.getJSONObject(0);
//                            Boolean status = t.getBoolean("status");
//                            if (status) {
//                                JSONArray jsonArray = t.getJSONArray("data");
//                                for (int i = 0; i < jsonArray.length(); i++) {
//                                    JSONObject dish = jsonArray.getJSONObject(i);
//                                    listitem.add(new Item_food_fragment(dish.getInt("dish_id"), dish.getString("name"), R.drawable.ic_rice, dish.getString("shop_name"), String.valueOf((dish.getDouble("price") * 1000))));
//                                }
//                                mitemAdapter.notifyDataSetChanged();
//                            }
//                        } catch (JSONException e) {
//                            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
//
//                    }
//                });
//        requestQueue.add(jsonArrayRequest1);

    }
}