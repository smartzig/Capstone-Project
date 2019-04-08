package com.smartz.conexaodescontos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;



class Company implements Parcelable {

    @SerializedName("id")
    private Long id;


    @SerializedName("name")
    private String name;

    @SerializedName("locality")
    private Locality locality;

    public Long getId() {
        return id;
    }
    public Company(){}
    private Company(Parcel in) {
        id = in.readLong();
        name = in.readString();
        locality = in.readParcelable(Locality.class.getClassLoader());

    }

    public static final Creator<Company> CREATOR = new Creator<Company>() {
        @Override
        public Company createFromParcel(Parcel in) {
            return new Company(in);
        }

        @Override
        public Company[] newArray(int size) {
            return new Company[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(name);
        parcel.writeParcelable(locality,i);

    }
    public String getName() {
        return name;
    }

    public Locality getLocality() {
        return locality;
    }
}
