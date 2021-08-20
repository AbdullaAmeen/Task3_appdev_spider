package com.example.superheroes.Heroes;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Images implements Parcelable {
    @ColumnInfo(name = "xs")
    @SerializedName("xs")
    @Expose
    private String xs;
    @ColumnInfo(name = "sm")
    @SerializedName("sm")
    @Expose
    private String sm;
    @ColumnInfo(name = "md")
    @SerializedName("md")
    @Expose
    private String md;
    @ColumnInfo(name = "lg")
    @SerializedName("lg")
    @Expose
    private String lg;

    public Images(String xs, String sm, String md, String lg) {
        super();
        this.xs = xs;
        this.sm = sm;
        this.md = md;
        this.lg = lg;
    }

    protected Images(Parcel in) {
        xs = in.readString();
        sm = in.readString();
        md = in.readString();
        lg = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(xs);
        dest.writeString(sm);
        dest.writeString(md);
        dest.writeString(lg);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Images> CREATOR = new Creator<Images>() {
        @Override
        public Images createFromParcel(Parcel in) {
            return new Images(in);
        }

        @Override
        public Images[] newArray(int size) {
            return new Images[size];
        }
    };

    public String getXs() {
        return xs;
    }

    public void setXs(String xs) {
        this.xs = xs;
    }

    public String getSm() {
        return sm;
    }

    public void setSm(String sm) {
        this.sm = sm;
    }

    public String getMd() {
        return md;
    }

    public void setMd(String md) {
        this.md = md;
    }

    public String getLg() {
        return lg;
    }

    public void setLg(String lg) {
        this.lg = lg;
    }

}