package com.example.superheroes.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.superheroes.Heroes.Heroes;


import java.util.List;

@Dao
public interface DAO {

    @Query("SELECT * FROM heroes" )
    List<Heroes> getFav();

    @Insert
    void insert(Heroes hero);

    @Delete
    void delete(Heroes hero);
}
