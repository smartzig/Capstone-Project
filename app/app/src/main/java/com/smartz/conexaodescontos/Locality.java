package com.smartz.conexaodescontos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

class Locality implements Parcelable {

    @SerializedName("address")
    private String address;

        @SerializedName("city")
    private String city;

    @SerializedName("country")
    private String country;


    @SerializedName("latitude")
    private String latitude;


    @SerializedName("longitude")
    private String longitude;

    @SerializedName("state")
    private String state;
    @SerializedName("zipcode")
    private String zipcode;

    private Locality(Parcel in) {
        address = in.readString();
        city = in.readString();
        country = in.readString();
        latitude = in.readString();
        longitude = in.readString();
        state = in.readString();
        zipcode = in.readString();



    }
    public Locality(){}
    public static final Parcelable.Creator<Locality> CREATOR = new Parcelable.Creator<Locality>() {
        @Override
        public Locality createFromParcel(Parcel in) {
            return new Locality(in);
        }

        @Override
        public Locality[] newArray(int size) {
            return new Locality[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(address);
        parcel.writeString(city);
        parcel.writeString(country);
        parcel.writeString(latitude);
        parcel.writeString(longitude);
        parcel.writeString(state);
        parcel.writeString(zipcode);

    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getState() {
        return state;
    }

    public String getZipcode() {
        return zipcode;
    }
}
