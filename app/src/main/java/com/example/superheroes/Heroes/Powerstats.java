package com.example.superheroes.Heroes;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Powerstats implements Parcelable {
    @ColumnInfo(name = "intelligence")
    @SerializedName("intelligence")
    @Expose
    private Integer intelligence;
    @ColumnInfo(name = "strength")
    @SerializedName("strength")
    @Expose
    private Integer strength;
    @ColumnInfo(name = "speed")
    @SerializedName("speed")
    @Expose
    private Integer speed;
    @ColumnInfo(name = "durability")
    @SerializedName("durability")
    @Expose
    private Integer durability;
    @ColumnInfo(name = "power")
    @SerializedName("power")
    @Expose
    private Integer power;
    @ColumnInfo(name = "combat")
    @SerializedName("combat")
    @Expose
    private Integer combat;

    public Powerstats(Integer intelligence, Integer strength, Integer speed, Integer durability, Integer power, Integer combat) {
        super();
        this.intelligence = intelligence;
        this.strength = strength;
        this.speed = speed;
        this.durability = durability;
        this.power = power;
        this.combat = combat;
    }

    protected Powerstats(Parcel in) {
        if (in.readByte() == 0) {
            intelligence = null;
        } else {
            intelligence = in.readInt();
        }
        if (in.readByte() == 0) {
            strength = null;
        } else {
            strength = in.readInt();
        }
        if (in.readByte() == 0) {
            speed = null;
        } else {
            speed = in.readInt();
        }
        if (in.readByte() == 0) {
            durability = null;
        } else {
            durability = in.readInt();
        }
        if (in.readByte() == 0) {
            power = null;
        } else {
            power = in.readInt();
        }
        if (in.readByte() == 0) {
            combat = null;
        } else {
            combat = in.readInt();
        }
    }

    public static final Creator<Powerstats> CREATOR = new Creator<Powerstats>() {
        @Override
        public Powerstats createFromParcel(Parcel in) {
            return new Powerstats(in);
        }

        @Override
        public Powerstats[] newArray(int size) {
            return new Powerstats[size];
        }
    };

    public Integer getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(Integer intelligence) {
        this.intelligence = intelligence;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getDurability() {
        return durability;
    }

    public void setDurability(Integer durability) {
        this.durability = durability;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public Integer getCombat() {
        return combat;
    }

    public void setCombat(Integer combat) {
        this.combat = combat;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (intelligence == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(intelligence);
        }
        if (strength == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(strength);
        }
        if (speed == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(speed);
        }
        if (durability == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(durability);
        }
        if (power == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(power);
        }
        if (combat == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(combat);
        }
    }
}

