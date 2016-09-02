package com.example.dllo.vmovie.home;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dllo on 16/9/2.
 */
public class CarouselData implements Parcelable {

    //实体类用于传type和params
    private String type;
    private String params;

    public CarouselData() {
    }

    public CarouselData(String type, String params) {
        this.type = type;
        this.params = params;
    }

    protected CarouselData(Parcel in) {
        type = in.readString();
        params = in.readString();
    }

    public static final Creator<CarouselData> CREATOR = new Creator<CarouselData>() {
        @Override
        public CarouselData createFromParcel(Parcel in) {
            return new CarouselData(in);
        }

        @Override
        public CarouselData[] newArray(int size) {
            return new CarouselData[size];
        }
    };

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeString(params);
    }
}
