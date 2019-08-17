package com.example.foodrecipes;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.foodrecipes.Model.Recipe;
import com.example.foodrecipes.Networking.RecipeApi;
import com.example.foodrecipes.Networking.RecipeResponse;
import com.example.foodrecipes.Networking.RecipeSearchResponse;
import com.example.foodrecipes.Networking.ServiceGenerator;
import com.example.foodrecipes.ViewModel.MainActivelyViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    private MainActivelyViewModel mainActivelyViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivelyViewModel = ViewModelProviders.of(this).get(MainActivelyViewModel.class);
        subscribeObservers();
    }

    // LiveData work with subscribing. This method will subscribe the observers.
    private void subscribeObservers() {
        // ViewModel class has method observe which triggers onChanged method when some
        // changes happens to LiveData.
        mainActivelyViewModel.getRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipes) {
                for (Recipe recipe : recipes) {
                    Log.d("onChanged", recipe.getTitle());
                }
            }
        });
    }

    // Testing response for RecipeSearch
    public void testingRecipeSearch(View view) {
        searchRecipes("chicken", 1);
    }

    // Initial method that triggers all the layers up to the last layer in repository
    // that is triggering search reqeust from API client and that triggers LiveData
    private void searchRecipes(String query, int pageNumber) {
        mainActivelyViewModel.searchRecipes(query, pageNumber);
    }


}
