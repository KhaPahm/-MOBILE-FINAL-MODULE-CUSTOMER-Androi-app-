package com.example.final_project;

public class Item_food_detail_comment {
    private String mUser;
    private String mComment;
    public Item_food_detail_comment(String mUser, String mComment)
    {
        this.mUser=mUser;
        this.mComment=mComment;
    }

    public String getmUser() {
        return mUser;
    }

    public void setmUser(String mUser) {
        this.mUser = mUser;
    }

    public String getmComment() {
        return mComment;
    }

    public void setmComment(String mComment) {
        this.mComment = mComment;
    }
}
