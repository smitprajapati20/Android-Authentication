package com.example.logsignjavadb;

public class ProductList {
    int productid, subcategoryid, productimage, originalPrice, discountPrice;
    String productname;
    public int getProductid(){
        return productid;
    }
    public void setProductid(int productid) {
        this.productid = productid;
    }
    public int getSubcategoryid(){
        return subcategoryid;
    }
    public void setSubcategoryid(int subcategoryid) {
        this.subcategoryid = subcategoryid;
    }

    public String getProductname(){
        return productname;
    }
    public void setProductname(String productname) {
        this.productname = productname;
    }
    public int getProductimage(){
        return productimage;
    }
    public void setProductimage(int productimage) {
        this.productimage = productimage;
    }
    public int getOriginalPrice(){
        return originalPrice;
    }
    public void setOriginalPrice(int originalPrice) {
        this.originalPrice = originalPrice;
    }
    public int getDiscountPrice(){
        return discountPrice;
    }
    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }

}
