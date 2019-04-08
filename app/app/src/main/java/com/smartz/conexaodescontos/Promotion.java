package com.smartz.conexaodescontos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.ServerTimestamp;
import com.google.gson.annotations.SerializedName;


import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Promotion implements Parcelable {

    @SerializedName("id")
    private Long id;

    @SerializedName("desc")
    private String desc;

    @SerializedName("name")
    private String name;

    @SerializedName("price")
    private Double price;

    @SerializedName("companyName")
    private String companyName;

    @SerializedName("imageUrl")
    private String imageUrl;

    @SerializedName("priceMessage")
    private String priceMessage;

    @SerializedName("discount")
    private String discount;

    @ServerTimestamp
    @SerializedName("validUntilDt")
    private Date validUntilDt;

    @SerializedName("companyId")
    private Long companyId;


    private Promotion(Parcel in) {
        id = in.readLong();
        desc = in.readString();
        name = in.readString();
        price = in.readDouble();
        validUntilDt = (Date) in.readSerializable();
        imageUrl = in.readString();
        companyName = in.readString();
        priceMessage = in.readString();
        discount = in.readString();
        companyId = in.readLong();
    }
    public Promotion(Long id, String desc, String name, Double price,
                     Date validUntilDt,String companyName,  String imageUrl,  String priceMessage, String discount) {
        this.id = id;
        this.desc = desc;
        this.name = name;
        this.price = price;
        this.validUntilDt = validUntilDt;
        this.imageUrl = imageUrl;
        this.companyName = companyName;
        this.priceMessage = priceMessage;
        this.discount = discount;
    }

    public Promotion(){}

    public Long getId() {
        return id;
    }
    public void setId(Long id)
    {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }


    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Date getValidUntilDt() {
        return validUntilDt;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPriceMessage() {
        return priceMessage;
    }

    public void setPriceMessage(String priceMessage) {
        this.priceMessage = priceMessage;
    }

    public String getDiscount() {
        return discount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public boolean isValid()
    {
        Calendar now = Calendar.getInstance();
        boolean treta = now.after(validUntilDt);
        return treta;
    }

    public static final Creator<Promotion> CREATOR = new Creator<Promotion>() {
        @Override
        public Promotion createFromParcel(Parcel in) {
            return new Promotion(in);
        }

        @Override
        public Promotion[] newArray(int size) {
            return new Promotion[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(desc);
        parcel.writeString(name);
        parcel.writeDouble(price);
        parcel.writeSerializable(validUntilDt);
        parcel.writeString(imageUrl);
        parcel.writeString(companyName);
        parcel.writeString(priceMessage);
        parcel.writeString(discount);
        parcel.writeLong(companyId);
    }

}
