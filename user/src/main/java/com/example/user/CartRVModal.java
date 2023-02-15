package com.example.user;


import android.os.Parcel;
import android.os.Parcelable;

public class CartRVModal implements Parcelable {
    // creating variables for our different fields.
    private String name;
    private String productDescription;
    private String price;
    private String image;
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
        name = in.readString();
        productId = in.readString();
        productDescription = in.readString();
        price = in.readString();
        image = in.readString();
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
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public CartRVModal(String productId, String name, String productDescription, String price, String image) {
        this.name = name;
        this.productId = productId;
        this.productDescription = productDescription;
        this.price = price;
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(productId);
        dest.writeString(productDescription);
        dest.writeString(price);
        dest.writeString(image);
    }
}