package com.example.user;


import android.os.Parcel;
import android.os.Parcelable;

public class CartRVModal implements Parcelable {
    // creating variables for our different fields.
    private String productName;
    private String productDescription;
    private String productPrice;
    private String productImg;
    private String productId;


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }


    // creating an empty constructor.
    public CartRVModal() {

    }

    protected CartRVModal(Parcel in) {
        productName = in.readString();
        productId = in.readString();
        productDescription = in.readString();
        productPrice = in.readString();
        productImg = in.readString();
    }

    public static final Creator<CartRVModal> CREATOR = new Creator<CartRVModal>() {
        @Override
        public CartRVModal createFromParcel(Parcel in) {
            return new CartRVModal(in);
        }

        @Override
        public CartRVModal[] newArray(int size) {
            return new CartRVModal[size];
        }
    };

    // creating getter and setter methods.
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public CartRVModal(String productId, String productName, String productDescription, String productPrice, String productImg) {
        this.productName = productName;
        this.productId = productId;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productImg = productImg;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(productName);
        dest.writeString(productId);
        dest.writeString(productDescription);
        dest.writeString(productPrice);
        dest.writeString(productImg);
    }
}