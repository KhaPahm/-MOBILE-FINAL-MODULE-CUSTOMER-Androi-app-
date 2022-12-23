package com.example.final_project;

import android.graphics.Bitmap;

public class Item_cacmonan_cartpage {
    private Bitmap mImage;
    private String mName;
    private String mSoluong;
    private String mCost;
    private int bill_id;
    private int dish_id;
    public Item_cacmonan_cartpage(Bitmap mImage, String mName, String mSoluong, String mCost, int bill_id, int dish_id)
    {
        this.mImage=mImage;
        this.mName=mName;
        this.mSoluong=mSoluong;
        this.mCost=mCost;
        this.bill_id=bill_id;
        this.dish_id=dish_id;
    }

    public int getBill_id() {
        return bill_id;
    }

    public void setBill_id(int bill_id) {
        this.bill_id = bill_id;
    }

    public int getDish_id() {
        return dish_id;
    }

    public void setDish_id(int dish_id) {
        this.dish_id = dish_id;
    }

    public Bitmap getmImage() {
        return mImage;
    }

    public void setmImage(Bitmap mImage) {
        this.mImage = mImage;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmSoluong() {
        return mSoluong;
    }

    public void setmSoluong(String mSoluong) {
        this.mSoluong = mSoluong;
    }

    public String getmCost() {
        return mCost;
    }

    public void setmCost(String mCost) {
        this.mCost = mCost;
    }
}
