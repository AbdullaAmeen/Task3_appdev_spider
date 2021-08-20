package com.example.superheroes.Heroes;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class Heroes implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    @Expose
    private Integer id;
    @ColumnInfo(name = "name")
    @SerializedName("name")
    @Expose
    private String name;
    @ColumnInfo(name = "slug")
    @SerializedName("slug")
    @Expose
    private String slug;
    @Embedded
    @SerializedName("powerstats")
    @Expose
    private Powerstats powerstats;
    @Embedded
    @SerializedName("appearance")
    @Expose
    private Appearance appearance;
    @Embedded
    @SerializedName("biography")
    @Expose
    private Biography biography;
    @Embedded
    @SerializedName("work")
    @Expose
    private Work work;
    @Embedded
    @SerializedName("connections")
    @Expose
    private Connections connections;
    @Embedded
    @SerializedName("images")
    @Expose
    private Images images;

    public Heroes(Integer id, String name, String slug, Powerstats powerstats, Appearance appearance, Biography biography, Work work, Connections connections, Images images) {
        super();
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.powerstats = powerstats;
        this.appearance = appearance;
        this.biography = biography;
        this.work = work;
        this.connections = connections;
        this.images = images;
    }

    protected Heroes(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        name = in.readString();
        slug = in.readString();
        powerstats = in.readParcelable(Powerstats.class.getClassLoader());
        appearance = in.readParcelable(Appearance.class.getClassLoader());
        biography = in.readParcelable(Biography.class.getClassLoader());
        work = in.readParcelable(Work.class.getClassLoader());
        connections = in.readParcelable(Connections.class.getClassLoader());
        images = in.readParcelable(Images.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(name);
        dest.writeString(slug);
        dest.writeParcelable(powerstats, flags);
        dest.writeParcelable(appearance, flags);
        dest.writeParcelable(biography, flags);
        dest.writeParcelable(work, flags);
        dest.writeParcelable(connections, flags);
        dest.writeParcelable(images, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Heroes> CREATOR = new Creator<Heroes>() {
        @Override
        public Heroes createFromParcel(Parcel in) {
            return new Heroes(in);
        }

        @Override
        public Heroes[] newArray(int size) {
            return new Heroes[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Powerstats getPowerstats() {
        return powerstats;
    }

    public void setPowerstats(Powerstats powerstats) {
        this.powerstats = powerstats;
    }

    public Appearance getAppearance() {
        return appearance;
    }

    public void setAppearance(Appearance appearance) {
        this.appearance = appearance;
    }

    public Biography getBiography() {
        return biography;
    }

    public void setBiography(Biography biography) {
        this.biography = biography;
    }

    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
    }

    public Connections getConnections() {
        return connections;
    }

    public void setConnections(Connections connections) {
        this.connections = connections;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

}