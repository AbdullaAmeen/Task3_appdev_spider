package com.example.superheroes;

import com.example.superheroes.Heroes.Heroes;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("all.json")
    Call<ArrayList<Heroes>> getHeroes(

    );
}
