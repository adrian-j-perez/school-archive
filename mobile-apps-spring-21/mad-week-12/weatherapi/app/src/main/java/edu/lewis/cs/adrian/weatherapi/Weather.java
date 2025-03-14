package edu.lewis.cs.adrian.weatherapi;

import android.os.Parcel;
import android.os.Parcelable;

//model for forecast for one day
public class Weather implements Parcelable {

    private int tempHigh;
    private int tempLow;
    private String date;
    private String linkToTemp; // get the mobile link

    public Weather(int tempHigh, int tempLow, String date, String linkToTemp) {
        this.tempHigh = tempHigh;
        this.tempLow = tempLow;
        this.date = date;
        this.linkToTemp = linkToTemp;
    }

    //getters and setters//

    public int getTempHigh() {
        return tempHigh;
    }

    public void setTempHigh(int tempHigh) {
        this.tempHigh = tempHigh;
    }

    public int getTempLow() {
        return tempLow;
    }

    public void setTempLow(int tempLow) {
        this.tempLow = tempLow;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLinkToTemp() {
        return linkToTemp;
    }

    public void setLinkToTemp(String linkToTemp) {
        this.linkToTemp = linkToTemp;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "tempHigh=" + tempHigh +
                ", tempLow=" + tempLow +
                ", date='" + date + '\'' +
                ", linkToTemp='" + linkToTemp + '\'' +
                '}';
    }

    protected Weather(Parcel in) {
        tempHigh = in.readInt();
        tempLow = in.readInt();
        date = in.readString();
        linkToTemp = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(tempHigh);
        dest.writeInt(tempLow);
        dest.writeString(date);
        dest.writeString(linkToTemp);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Weather> CREATOR = new Parcelable.Creator<Weather>() {
        @Override
        public Weather createFromParcel(Parcel in) {
            return new Weather(in);
        }

        @Override
        public Weather[] newArray(int size) {
            return new Weather[size];
        }
    };
}//end of class