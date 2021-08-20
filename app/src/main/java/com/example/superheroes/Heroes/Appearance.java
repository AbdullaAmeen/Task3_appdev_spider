package com.example.superheroes.Heroes;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.TypeConverters;

import java.util.List;

import com.example.superheroes.Database.ListStringTypeConverters;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Appearance implements Parcelable {
    @ColumnInfo(name = "gender")
    @SerializedName("gender")
    @Expose
    private String gender;
    @ColumnInfo(name = "race")
    @SerializedName("race")
    @Expose
    private String race;

    @SerializedName("height")
    @Expose
    @TypeConverters(ListStringTypeConverters.class)
    private List<String> height = null;
    @TypeConverters(ListStringTypeConverters.class)
    @SerializedName("weight")
    @Expose
    private List<String> weight = null;
    @ColumnInfo(name = "eyeColor")
    @SerializedName("eyeColor")
    @Expose
    private String eyeColor;
    @ColumnInfo(name = "hairColor")
    @SerializedName("hairColor")
    @Expose
    private String hairColor;

    /**
     * No args constructor for use in serialization
     *
     */
    public Appearance() {
    }

    /**
     *
     * @param gender
     * @param race
     * @param eyeColor
     * @param weight
     * @param hairColor
     * @param height
     */
    public Appearance(String gender, String race, List<String> height, List<String> weight, String eyeColor, String hairColor) {
        super();
        this.gender = gender;
        this.race = race;
        this.height = height;
        this.weight = weight;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
    }

    protected Appearance(Parcel in) {
        gender = in.readString();
        race = in.readString();
        height = in.createStringArrayList();
        weight = in.createStringArrayList();
        eyeColor = in.readString();
        hairColor = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(gender);
        dest.writeString(race);
        dest.writeStringList(height);
        dest.writeStringList(weight);
        dest.writeString(eyeColor);
        dest.writeString(hairColor);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Appearance> CREATOR = new Creator<Appearance>() {
        @Override
        public Appearance createFromParcel(Parcel in) {
            return new Appearance(in);
        }

        @Override
        public Appearance[] newArray(int size) {
            return new Appearance[size];
        }
    };

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public List<String> getHeight() {
        return height;
    }

    public void setHeight(List<String> height) {
        this.height = height;
    }

    public List<String> getWeight() {
        return weight;
    }

    public void setWeight(List<String> weight) {
        this.weight = weight;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

}