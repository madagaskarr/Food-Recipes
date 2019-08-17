package com.example.foodrecipes.Repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.foodrecipes.Model.Recipe;
import com.example.foodrecipes.Networking.RecipeApiClient;

import java.util.List;

// Repository is the gateway for all the data sources. Here data is being
// transferred to ViewModel layer. That is why it is a good idea to make
// this class singleton.
public class RecipesRepository {

    // This is a reference to APi Client to get data.
    RecipeApiClient recipeApiClient;

    // Implementing singleton pattern.
    private static RecipesRepository instance;

    // Implementing singleton pattern.
    public static RecipesRepository getInstance() {
        if (instance == null) {
            instance = new RecipesRepository();
        }
        return instance;
    }

    // When instantiating the class we getting reference to Api Client.
    public RecipesRepository() {
        recipeApiClient = RecipeApiClient.getInstance();
    }

    // Method for getting all recipes.
    public LiveData<List<Recipe>> getRecipes() {
        return recipeApiClient.getRecipes();
    }



}