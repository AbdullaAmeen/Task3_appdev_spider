package com.example.superheroes.ui;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.superheroes.Heroes.Heroes;
import com.example.superheroes.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> implements Filterable {

    private ArrayList<Heroes> heroesArrayList;
    private ArrayList<Heroes> backupArrayList;
    private Context context;
    OnCardListener onCardListener;

    public RecyclerAdapter(Context context, ArrayList<Heroes> heroesArrayList, OnCardListener onCardListener) {
        this.heroesArrayList = heroesArrayList;
        this.context = context;
        this.onCardListener = onCardListener;
        backupArrayList = new ArrayList<>(heroesArrayList);
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, onCardListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        Heroes Hero = heroesArrayList.get(position);
        if(!Hero.getName().isEmpty())
            holder.tv_name.setText(Hero.getName());
        if(Hero.getId() != null)
        holder.tv_id.setText("ID:"+Hero.getId().toString());
        Picasso.get().load(Hero.getImages().getSm()).into(holder.im_hero);

    }

    @Override
    public int getItemCount() {
        if(!heroesArrayList.isEmpty())
            return heroesArrayList.size();

        return 0;
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            Log.d("filter","filter");
            ArrayList<Heroes> filteredList = new ArrayList<>();
            if(constraint == null || constraint.length() == 0)
                filteredList.addAll(backupArrayList);
            else{
                String filterWord = constraint.toString().toLowerCase().trim();
                if(!filterWord.startsWith("#")) {
                    for (Heroes hero : backupArrayList) {
                        if (hero.getName().toLowerCase().startsWith(filterWord)) {
                            filteredList.add(hero);
                        }
                    }
                    for (Heroes hero : backupArrayList) {
                        if (hero.getName().toLowerCase().contains(filterWord) && !hero.getName().toLowerCase().startsWith(filterWord)) {
                            filteredList.add(hero);
                        }
                    }
                }
                else{
                    for (Heroes hero : backupArrayList) {
                        if (filterWord.contains(hero.getId().toString())) {
                            filteredList.add(hero);
                        }
                    }
                }
            }
            if(filteredList.isEmpty())
                Toast.makeText(context,"No Results Found", Toast.LENGTH_SHORT).show();
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            heroesArrayList.clear();
            heroesArrayList.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        OnCardListener onCardListener;
        ConstraintLayout lt_parent;
        TextView tv_name, tv_id;
        ImageView im_hero;
        public ViewHolder(View itemView, OnCardListener onCardListener) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_id = itemView.findViewById(R.id.tv_id);
            lt_parent = itemView.findViewById(R.id.lt_card);
            im_hero = itemView.findViewById(R.id.im_hero);
            this.onCardListener = onCardListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            onCardListener.onCardClick(getAdapterPosition(),im_hero);
        }
    }

    public interface OnCardListener{
        void onCardClick(int position, ImageView im_hero);
    }


}
