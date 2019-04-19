package com.smartz.conexaodescontos.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;
import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

@Entity
public class QrCode implements Parcelable {

    @NonNull
    @PrimaryKey
    private Long qrCodeId;

    @Ignore
    @SerializedName("generatedBitMap")
    private Bitmap generatedBitMap;

    @Embedded
    @SerializedName("promotion")
    private Promotion promotion;

    @SerializedName("userID")
    private String userID;

    @SerializedName("usedHash")
    private String usedHash;

    public QrCode(){}

    public static final Creator<QrCode> CREATOR = new Creator<QrCode>() {
        @Override
        public QrCode createFromParcel(Parcel in) {
            return new QrCode(in);
        }

        @Override
        public QrCode[] newArray(int size) {
            return new QrCode[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    private QrCode(Parcel in) {

        qrCodeId = in.readLong();
        generatedBitMap = in.readParcelable(Bitmap.class.getClassLoader());
        promotion = in.readParcelable(Promotion.class.getClassLoader());
        userID = in.readString();
        usedHash = in.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeLong(qrCodeId);
        parcel.writeValue(generatedBitMap);
        parcel.writeValue(promotion);
        parcel.writeString(userID);
        parcel.writeString(usedHash);


    }

    @NonNull
    public Long getQrCodeId() {
        return qrCodeId;
    }

    public void setQrCodeId(@NonNull Long qrCodeId) {
        this.qrCodeId = qrCodeId;
    }

    public Bitmap getGeneratedBitMap() {
        return generatedBitMap;
    }

    public void setGeneratedBitMap(Bitmap generatedBitMap) {
        this.generatedBitMap = generatedBitMap;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUsedHash() {
        return usedHash;
    }

    public void setUsedHash(String usedHash) {
        this.usedHash = usedHash;
    }
}
