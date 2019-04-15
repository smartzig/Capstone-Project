package com.smartz.conexaodescontos.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.firestore.ServerTimestamp;
import com.google.gson.annotations.SerializedName;


import java.util.Calendar;
import java.util.Date;

public class Promotion implements Parcelable {

    @SerializedName("id")
    private Long id;

    @SerializedName("desc")
    private String desc;

    @SerializedName("detailDesc")
    private String detailDesc;

    @SerializedName("name")
    private String name;

    @SerializedName("price")
    private Double price;

    @SerializedName("companyId")
    private Long companyId;

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

    @SerializedName("company")
    private Company company;


    public Promotion(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDetailDesc() {
        return detailDesc;
    }

    public void setDetailDesc(String detailDesc) {
        this.detailDesc = detailDesc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public Date getValidUntilDt() {
        return validUntilDt;
    }

    public void setValidUntilDt(Date validUntilDt) {
        this.validUntilDt = validUntilDt;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
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

    private Promotion(Parcel in) {
        id = in.readLong();
        desc = in.readString();
        detailDesc = in.readString();
        name = in.readString();


        validUntilDt = (Date) in.readSerializable();

        imageUrl = in.readString();

        companyName = in.readString();
        companyId = in.readLong();

        price = in.readDouble();
        priceMessage = in.readString();
        discount = in.readString();

    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeLong(id);
        parcel.writeString(desc);
        parcel.writeString(detailDesc);

        parcel.writeString(name);

        parcel.writeSerializable(validUntilDt);
        parcel.writeString(imageUrl);
        parcel.writeLong(companyId);
        parcel.writeString(companyName);

        parcel.writeDouble(price);
        parcel.writeString(priceMessage);
        parcel.writeString(discount);

    }

}
