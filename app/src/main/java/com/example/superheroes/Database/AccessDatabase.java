package com.example.superheroes.Database;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.example.superheroes.Heroes.Heroes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AccessDatabase {
    private HeroDatabase database;
    private List<Heroes> favList;

    public AccessDatabase(Context context) {
        database = HeroDatabase.getINSTANCE(context);
        favList = new ArrayList<>();
    }

    public void saveNewUser(Heroes hero) {


        if(isFav(hero) == false)
            database.dao().insert(hero);
        else
            Log.v("isFavno","is fav no");
    }

    public List<Heroes> getAllUser() {

        return database.dao().getFav();
    }

    public boolean isFav(Heroes hero) {
        favList.clear();
        favList.addAll(getAllUser());
        if(!favList.isEmpty())
            for(Iterator<Heroes>iterator = favList.iterator(); iterator.hasNext(); )
                if(hero.getId() == iterator.next().getId()){
                    Log.v("isfav","true");
                    return true;

            }
        return false;
    }

    public void deleteNewUser(Heroes hero) {


        if(isFav(hero))
            database.dao().delete(hero);
    }


}
