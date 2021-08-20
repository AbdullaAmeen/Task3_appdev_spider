package com.example.superheroes.Database;

import androidx.room.TypeConverter;


import java.util.ArrayList;
import java.util.List;

public class ListStringTypeConverters {
    @TypeConverter
    public String fromArray(List<String> strings) {
        String string = "";
        for(String s : strings) string += (s + ",");

        return string;
    }

    @TypeConverter
    public List<String> toArray(String concatenatedStrings) {
        List<String> myStrings = new ArrayList<>();

        for(String s : concatenatedStrings.split(",")) {
            if(s!= null)
            myStrings.add(s);
        }
        return myStrings;
    }
}

