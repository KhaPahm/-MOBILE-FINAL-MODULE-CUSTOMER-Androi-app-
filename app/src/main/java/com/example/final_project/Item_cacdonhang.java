package com.example.final_project;

public class Item_cacdonhang {
    private String mTitle;
    private String mDate;
    private String mCost;
    public Item_cacdonhang(String mTitle,String mDate,String mCost)
    {
        this.mTitle=mTitle;
        this.mDate=mDate;
        this.mCost=mCost;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getmCost() {
        return mCost;
    }

    public void setmCost(String mCost) {
        this.mCost = mCost;
    }
}
