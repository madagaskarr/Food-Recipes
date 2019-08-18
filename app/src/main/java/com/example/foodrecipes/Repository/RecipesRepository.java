package com.example.foodrecipes.Repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.foodrecipes.Model.Recipe;
import com.example.foodrecipes.Networking.RecipeApiClient;

import java.util.List;

// Repository is the gateway for all the data sources. Here data is being
// transferred to ViewModel layer. That is why it is a good idea to make
// this class singleton.
public class RecipesRepository {

    private int initialPageNumber = 1;

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

    // Search recipes last chain which will call RecipeAPiClient method
    public void searchRecipes(String query, int pageNumber) {

        // Api specific checking
        if (pageNumber == 0) {
            pageNumber = 1;
        }

        // Calling search method.
        recipeApiClient.searchRecipesApi(query, pageNumber);
    }

    public void cancelRequest() {
        recipeApiClient.cancelRequest();
    }

    public void requestNextPage(String query) {
        initialPageNumber++;
        searchRecipes(query, initialPageNumber);
    }



}
