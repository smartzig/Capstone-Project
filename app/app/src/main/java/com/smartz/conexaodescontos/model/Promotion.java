package com.smartz.conexaodescontos.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.firebase.firestore.ServerTimestamp;
import com.google.gson.annotations.SerializedName;


import java.util.Calendar;
import java.util.Date;
@Entity
public class Promotion implements Parcelable {
    @NonNull
    @PrimaryKey
    @SerializedName("id")
    private Long id;

    @SerializedName("desc")
    private String desc;

    @SerializedName("detailDesc")
    private String detailDesc;

    @SerializedName("promotionName")
    private String promotionName;

    @SerializedName("price")
    private Double price;

    @SerializedName("companyRefId")
    private String companyRefId;

    @SerializedName("companyRefName")
    private String companyRefName;

    @SerializedName("imageUrl")
    private String imageUrl;

    @SerializedName("priceMessage")
    private String priceMessage;

    @SerializedName("discount")
    private String discount;

    @ServerTimestamp
    @SerializedName("validUntilDt")
    private Date validUntilDt;

    @Embedded
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

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    public String getCompanyRefId() {
        return companyRefId;
    }

    public void setCompanyRefId(String companyRefId) {
        this.companyRefId = companyRefId;
    }

    public String getCompanyRefName() {
        return companyRefName;
    }

    public void setCompanyRefName(String companyRefName) {
        this.companyRefName = companyRefName;
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
        promotionName = in.readString();


        validUntilDt = (Date) in.readSerializable();

        imageUrl = in.readString();

        companyRefId = in.readString();
        companyRefName = in.readString();
        price = in.readDouble();
        priceMessage = in.readString();
        discount = in.readString();

    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeLong(id);
        parcel.writeString(desc);
        parcel.writeString(detailDesc);

        parcel.writeString(promotionName);

        parcel.writeSerializable(validUntilDt);
        parcel.writeString(imageUrl);
        parcel.writeString(companyRefId);
        parcel.writeString(companyRefName);

        parcel.writeDouble(price);
        parcel.writeString(priceMessage);
        parcel.writeString(discount);

    }

}
