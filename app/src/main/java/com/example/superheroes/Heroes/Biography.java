package com.example.superheroes.Heroes;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.TypeConverters;

import java.util.List;

import com.example.superheroes.Database.ListStringTypeConverters;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Biography implements Parcelable {
    @ColumnInfo(name = "fullName")
    @SerializedName("fullName")
    @Expose
    private String fullName;
    @ColumnInfo(name = "alterEgos")
    @SerializedName("alterEgos")
    @Expose
    private String alterEgos;
    @TypeConverters(ListStringTypeConverters.class)
    @SerializedName("aliases")
    @Expose
    private List<String> aliases = null;
    @ColumnInfo(name = "placeOfBirth")
    @SerializedName("placeOfBirth")
    @Expose
    private String placeOfBirth;
    @ColumnInfo(name = "firstAppearance")
    @SerializedName("firstAppearance")
    @Expose
    private String firstAppearance;
    @ColumnInfo(name = "publisher")
    @SerializedName("publisher")
    @Expose
    private String publisher;
    @ColumnInfo(name = "alignment")
    @SerializedName("alignment")
    @Expose
    private String alignment;

    /**
     * No args constructor for use in serialization
     *
     */
    public Biography() {
    }

    /**
     *
     * @param firstAppearance
     * @param placeOfBirth
     * @param aliases
     * @param fullName
     * @param publisher
     * @param alterEgos
     * @param alignment
     */
    public Biography(String fullName, String alterEgos, List<String> aliases, String placeOfBirth, String firstAppearance, String publisher, String alignment) {
        super();
        this.fullName = fullName;
        this.alterEgos = alterEgos;
        this.aliases = aliases;
        this.placeOfBirth = placeOfBirth;
        this.firstAppearance = firstAppearance;
        this.publisher = publisher;
        this.alignment = alignment;
    }

    protected Biography(Parcel in) {
        fullName = in.readString();
        alterEgos = in.readString();
        aliases = in.createStringArrayList();
        placeOfBirth = in.readString();
        firstAppearance = in.readString();
        publisher = in.readString();
        alignment = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fullName);
        dest.writeString(alterEgos);
        dest.writeStringList(aliases);
        dest.writeString(placeOfBirth);
        dest.writeString(firstAppearance);
        dest.writeString(publisher);
        dest.writeString(alignment);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Biography> CREATOR = new Creator<Biography>() {
        @Override
        public Biography createFromParcel(Parcel in) {
            return new Biography(in);
        }

        @Override
        public Biography[] newArray(int size) {
            return new Biography[size];
        }
    };

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAlterEgos() {
        return alterEgos;
    }

    public void setAlterEgos(String alterEgos) {
        this.alterEgos = alterEgos;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public void setAliases(List<String> aliases) {
        this.aliases = aliases;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getFirstAppearance() {
        return firstAppearance;
    }

    public void setFirstAppearance(String firstAppearance) {
        this.firstAppearance = firstAppearance;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

}
