package com.example.superheroes.ui.MaleHeroes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.superheroes.ApiService;
import com.example.superheroes.Heroes.Heroes;
import com.example.superheroes.R;
import com.example.superheroes.ui.RecyclerAdapter;
import com.example.superheroes.databinding.FragmentMaleHeroesBinding;
import com.example.superheroes.ui.MoreInfoActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MaleHeroesFragment extends Fragment implements RecyclerAdapter.OnCardListener  {


    private FragmentMaleHeroesBinding binding;
    Activity context;

    RecyclerView rv_heroes;
    ProgressBar pb_loading;
    RecyclerAdapter recyclerAdapter;
    private ArrayList<Heroes> heroes = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentMaleHeroesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Log.v("hellomale","yes");
        context = getActivity();
        Retrofit retrofit =  new Retrofit.Builder().baseUrl("https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/").addConverterFactory(GsonConverterFactory.create()).build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<ArrayList<Heroes>> call = apiService.getHeroes();
        call.enqueue(new Callback<ArrayList<Heroes>>() {
        @Override
        public void onResponse(Call<ArrayList<Heroes>> call, Response<ArrayList<Heroes>> response) {
            for(Heroes hero: response.body())
                if (hero.getAppearance().getGender().equals("Male")){
                    Log.v("yesMale","yesMale "+hero.getName());
                    heroes.add(hero);
            }
            init_recycler();
        }

        @Override
        public void onFailure(Call<ArrayList<Heroes>> call, Throwable t) {

        }
    });
    setHasOptionsMenu(true);
    return root;
}

    @Override
    public void onStart() {
        super.onStart();
        pb_loading = context.findViewById(R.id.pb_male_loading);
    }



    private void init_recycler(){
        pb_loading.setVisibility(View.INVISIBLE);
        rv_heroes = (RecyclerView) context.findViewById(R.id.rv_male_heroes);
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();

        inflater.inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItem.SHOW_AS_ACTION_IF_ROOM);
        androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView ) item.getActionView();
        searchView.setQueryHint("Search Name or '#id <enter id>' ");
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}