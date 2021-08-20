package com.example.superheroes.Heroes;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Work implements Parcelable {
    @ColumnInfo(name = "occupation")
    @SerializedName("occupation")
    @Expose
    private String occupation;
    @ColumnInfo(name = "base")
    @SerializedName("base")
    @Expose
    private String base;

    public Work(String occupation, String base) {
        super();
        this.occupation = occupation;
        this.base = base;
    }

    protected Work(Parcel in) {
        occupation = in.readString();
        base = in.readString();
    }

    public static final Creator<Work> CREATOR = new Creator<Work>() {
        @Override
        public Work createFromParcel(Parcel in) {
            return new Work(in);
        }

        @Override
        public Work[] newArray(int size) {
            return new Work[size];
        }
    };

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(occupation);
        dest.writeString(base);
    }
}

