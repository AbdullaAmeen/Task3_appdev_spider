package com.example.superheroes.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.superheroes.Heroes.Heroes;

@Database(entities = Heroes.class, version = 1)
public abstract class HeroDatabase extends RoomDatabase {

    public abstract DAO dao();

    private static HeroDatabase INSTANCE;

    public static HeroDatabase getINSTANCE(Context context){

        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), HeroDatabase.class, "Hero Database")
                    .allowMainThreadQueries().build();

        }

        return INSTANCE;
    }

}
