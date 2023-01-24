package com.example.user;

public class ModelProducts {
    String product_name;
    String product_desc;
    String product_price;
    String product_image;
    String product_id;
    String uid;
    String user_id;

    public ModelProducts() {
    }

    public String getProductName() {
        return product_name;
    }

    public void setProductName(String product_name) {
        this.product_name = product_name;
    }


    public String getProductPrice() {
        return product_price;
    }

    public void setProductPrice(String product_price) {
        this.product_price = product_price;
    }


    public String getProductImg() {
        return product_image;
    }

    public void setProductImg(String image) {
        this.product_image = image;
    }

    public String getProductDescription() {
        return product_desc;
    }

    public void setProductDescription(String product_desc) {
        this.product_price = product_desc;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getProductId() {
        return product_id;
    }

    public void setProductId(String product_id) {
        this.product_id = product_id;
    }

    public String getUserID() {
        return user_id;
    }

    public void setUserID(String user_id) {
        this.user_id = user_id;
    }


    public ModelProducts(String product_name, String product_desc, String product_price, String product_image, String uid) {
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_desc = product_desc;
        this.product_image = product_image;
        this.uid = uid;
    }



}
