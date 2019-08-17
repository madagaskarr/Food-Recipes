package com.example.foodrecipes.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodrecipes.Listener.OnRecipeClickListener;
import com.example.foodrecipes.Model.Recipe;
import com.example.foodrecipes.R;
import com.example.foodrecipes.ViewHolder.RecipiesRecyclerViewViewHolder;

import java.util.List;

public class RecipiesRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Recipe> recipes;
    private OnRecipeClickListener onRecipeClickListener;

    public RecipiesRecyclerViewAdapter(OnRecipeClickListener onRecipeClickListener) {
        this.onRecipeClickListener = onRecipeClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item_view_layout, parent, false);
        return new RecipiesRecyclerViewViewHolder(view, onRecipeClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ((RecipiesRecyclerViewViewHolder) holder).setTitle(recipes.get(position).getTitle());
        ((RecipiesRecyclerViewViewHolder) holder).setPublisher(recipes.get(position).getPublisher());
        String socialRank = String.valueOf(recipes.get(position).getSocial_rank());
        ((RecipiesRecyclerViewViewHolder) holder).setSocialRank(socialRank);

        // For default if something goes wrong or slow connection
        RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.ic_launcher_background);
        // Setting Images
        Glide.with(holder.itemView.getContext()).setDefaultRequestOptions(requestOptions).load(recipes.get(position).getImage_url()).into(((RecipiesRecyclerViewViewHolder) holder).getImageView());

    }

    @Override
    public int getItemCount() {
        if (recipes == null) {
            return 0;
        } else {
            return recipes.size();
        }
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
        notifyDataSetChanged();
    }

}
