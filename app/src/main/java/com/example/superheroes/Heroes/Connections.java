package com.example.superheroes.Heroes;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Connections implements Parcelable {
    @ColumnInfo(name = "groupAffiliation")
    @SerializedName("groupAffiliation")
    @Expose
    private String groupAffiliation;
    @ColumnInfo(name = "relatives")
    @SerializedName("relatives")
    @Expose
    private String relatives;

    /**
     * No args constructor for use in serialization
     *
     */
    public Connections() {
    }

    /**
     *
     * @param groupAffiliation
     * @param relatives
     */
    public Connections(String groupAffiliation, String relatives) {
        super();
        this.groupAffiliation = groupAffiliation;
        this.relatives = relatives;
    }

    protected Connections(Parcel in) {
        groupAffiliation = in.readString();
        relatives = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(groupAffiliation);
        dest.writeString(relatives);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Connections> CREATOR = new Creator<Connections>() {
        @Override
        public Connections createFromParcel(Parcel in) {
            return new Connections(in);
        }

        @Override
        public Connections[] newArray(int size) {
            return new Connections[size];
        }
    };

    public String getGroupAffiliation() {
        return groupAffiliation;
    }

    public void setGroupAffiliation(String groupAffiliation) {
        this.groupAffiliation = groupAffiliation;
    }

    public String getRelatives() {
        return relatives;
    }

    public void setRelatives(String relatives) {
        this.relatives = relatives;
    }

}