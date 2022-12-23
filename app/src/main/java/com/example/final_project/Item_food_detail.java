package com.example.final_project;

import android.graphics.Bitmap;

public class Item_food_detail {
    private int id;
    private String mName;
    private Bitmap mImage;
    private String mNameStore;
    private String mCost;
    private int shop_id;
    public Item_food_detail(int id, String mName, Bitmap mImage, String mNameStore, String mCost, int shop_id)
    {
        this.id = id;
        this.mName=mName;
        this.mImage=mImage;
        this.mNameStore=mNameStore;
        this.mCost=mCost;
        this.shop_id=shop_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public String getmName()
    {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public Bitmap getmImage() {
        return mImage;
    }

    public void setmImage(Bitmap mImage) {
        this.mImage = mImage;
    }

    public String getmNameStore() {
        return mNameStore;
    }

    public void setmNameStore(String mNameStore) {
        this.mNameStore = mNameStore;
    }

    public String getmCost() {
        return mCost;
    }

    public void setmCost(String mCost) {
        this.mCost = mCost;
    }
}
