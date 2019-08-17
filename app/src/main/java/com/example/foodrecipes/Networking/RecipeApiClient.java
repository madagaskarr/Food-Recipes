package com.example.foodrecipes.Networking;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.foodrecipes.Model.Recipe;
import com.example.foodrecipes.Repository.RecipesRepository;

import java.util.List;

// This is Api client responsible for fetching data from remote data source.
public class RecipeApiClient {

    // So far this is the lowest level and should be used to trigger LiveData.
    // That is why we are creating LiveData object and it will trigger the chain.
    private MutableLiveData<List<Recipe>> recipes;

    // Singleton pattern
    private static RecipeApiClient instance;

    public static RecipeApiClient getInstance() {
        if (instance == null) {
            instance = new RecipeApiClient();
        }
        return instance;
    }

    // Public constructor, for instantiating LiveData when this class
    // is being instantiated first time. And it is a singleton that is why
    // it we always one object at a time and one LiveData.
    public RecipeApiClient() {
        recipes = new MutableLiveData<>();

    }

    // Method for getting all recipes.
    public LiveData<List<Recipe>> getRecipes() {
        return recipes;
    }
}
