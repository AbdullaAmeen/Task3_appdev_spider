package com.example.superheroes.ui.Heroes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.superheroes.ApiService;
import com.example.superheroes.Database.AccessDatabase;
import com.example.superheroes.Database.HeroDatabase;
import com.example.superheroes.Heroes.Heroes;
import com.example.superheroes.R;
import com.example.superheroes.ui.RecyclerAdapter;
import com.example.superheroes.databinding.FragmentHeroesBinding;
import com.example.superheroes.ui.MoreInfoActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HeroesFragment extends Fragment implements RecyclerAdapter.OnCardListener {

    private FragmentHeroesBinding binding;
    Activity context;
    AccessDatabase accessDatabase;
    RecyclerView rv_heroes;
    ProgressBar pb_loading;
    RecyclerAdapter recyclerAdapter;
    private ArrayList<Heroes> heroes;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentHeroesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        context = getActivity();


        Retrofit retrofit =  new Retrofit.Builder().baseUrl("https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/").addConverterFactory(GsonConverterFactory.create()).build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<ArrayList<Heroes>> call = apiService.getHeroes();
        call.enqueue(new Callback<ArrayList<Heroes>>() {
            @Override
            public void onResponse(@NotNull Call<ArrayList<Heroes>> call, @NotNull Response<ArrayList<Heroes>> response) {

                heroes = response.body();
                init_recycler();
            }

            @Override
            public void onFailure(@NotNull Call<ArrayList<Heroes>> call, @NotNull Throwable t) {

            }
        });
        setHasOptionsMenu(true);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onStart() {
        super.onStart();
        pb_loading = context.findViewById(R.id.pb_loading);
    }



    private void init_recycler(){
        pb_loading.setVisibility(View.INVISIBLE);
        rv_heroes = (RecyclerView) context.findViewById(R.id.rv_heroes);
        recyclerAdapter = new RecyclerAdapter(context,heroes, this);
        rv_heroes.setAdapter(recyclerAdapter);
        rv_heroes.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    public void onCardClick(int position, ImageView im_hero) {

        Heroes hero = heroes.get(position);
                if(hero == null)
            return;
        Intent itGoToMoreInfo = new Intent(context, MoreInfoActivity.class);
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(context,im_hero, ViewCompat.getTransitionName(im_hero));
        itGoToMoreInfo.putExtra("HeroData", hero);
        startActivity(itGoToMoreInfo, optionsCompat.toBundle());

    }




    @Override
    public void onCreateOptionsMenu(@NotNull Menu menu, @NotNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();

        inflater.inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItem.SHOW_AS_ACTION_IF_ROOM);
        androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView ) item.getActionView();
        searchView.setQueryHint("Search Name or '# <enter id>' ");
        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(recyclerAdapter != null) {
                    recyclerAdapter.getFilter().filter(newText);
                    return true;
                }
                return false;
            }

        });
    }



  /*  private void saveFav(Heroes hero, Context context){
        /*HeroDatabase heroDatabase = HeroDatabase.getINSTANCE(context);
        heroDatabase.dao().insert(hero);
    }*/
}
