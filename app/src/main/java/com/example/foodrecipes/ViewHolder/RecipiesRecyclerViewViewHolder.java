package com.example.foodrecipes.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodrecipes.Listener.OnRecipeClickListener;
import com.example.foodrecipes.R;

public class RecipiesRecyclerViewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView title;
    private TextView publisher;
    private TextView socialRank;
    private ImageView imageView;
    private OnRecipeClickListener onRecipeClickListener;

    public RecipiesRecyclerViewViewHolder(@NonNull View itemView, OnRecipeClickListener onRecipeClickListener) {
        super(itemView);
        this.onRecipeClickListener = onRecipeClickListener;
        title = itemView.findViewById(R.id.recipe_item_title_text_view);
        publisher = itemView.findViewById(R.id.recipe_item_publisher_text_view);
        socialRank = itemView.findViewById(R.id.recipe_item_social_rank_text_view);
        imageView = itemView.findViewById(R.id.recipe_item_image_view);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        onRecipeClickListener.onRecipeItemClicked(getAdapterPosition());
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public String getTitle() {
        return title.getText().toString();
    }

    public void setPublisher(String publisher) {
        this.publisher.setText(publisher);
    }

    public String getPublisher() {
        return publisher.getText().toString();
    }

    public void setSocialRank(String socialRank) {
        this.socialRank.setText(socialRank);
    }

    public String getSocialRank() {
        return socialRank.getText().toString();
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
