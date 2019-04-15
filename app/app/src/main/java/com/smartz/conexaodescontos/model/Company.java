package com.smartz.conexaodescontos.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class Company implements Parcelable {

    @SerializedName("id")
    private Long id;


    @SerializedName("name")
    private String name;


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

    public Company(){}
    private Company(Parcel in) {
        id = in.readLong();
        name = in.readString();
        address = in.readString();
        city = in.readString();
        country = in.readString();
        latitude = in.readString();
        longitude = in.readString();
        state = in.readString();
        zipcode = in.readString();

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
        parcel.writeString(address);
        parcel.writeString(city);
        parcel.writeString(country);
        parcel.writeString(latitude);
        parcel.writeString(longitude);
        parcel.writeString(state);
        parcel.writeString(zipcode);

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
