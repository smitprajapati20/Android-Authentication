package com.example.logsignjavadb;

public class SubCategoryList {
    int subId, catId, subImage;
    String subName;

    public int getsubId(){
        return subId;
    }
    public void setsubId(int subId) {
        this.subId = subId;
    }

    public int getcatId(){
        return catId;
    }
    public void setcatId(int catId) {
        this.catId = catId;
    }

    public int getSubImage(){
        return subImage;
    }
    public void setSubImage(int subImage) {
        this.subImage = subImage;
    }

    public String getSubName(){
        return subName;
    }
    public void setSubName(String subName) {
        this.subName = subName;
    }
}
