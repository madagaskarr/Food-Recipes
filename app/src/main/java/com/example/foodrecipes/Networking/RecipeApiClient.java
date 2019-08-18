package com.example.foodrecipes.Networking;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.foodrecipes.AppExecutors;
import com.example.foodrecipes.Constants;
import com.example.foodrecipes.Model.Recipe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

// This is Api client responsible for fetching data from remote data source.
public class RecipeApiClient {

    // So far this is the lowest level and should be used to trigger LiveData.
    // That is why we are creating LiveData object and it will trigger the chain.
    private MutableLiveData<List<Recipe>> recipes;

    // Singleton pattern
    private static RecipeApiClient instance;

    private RetrieveRecipesRunnable retrieveRecipesRunnable;

    // Singleton pattern
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

    public void searchRecipesApi(String query, int pageNumber) {

        if (retrieveRecipesRunnable != null) {
            retrieveRecipesRunnable = null;
        }
        retrieveRecipesRunnable = new RetrieveRecipesRunnable(query, pageNumber);

        final Future handler = AppExecutors.getInstance().getNetworkInputOutput().submit(retrieveRecipesRunnable);

        AppExecutors.getInstance().getNetworkInputOutput().schedule(new Runnable() {
            @Override
            public void run() {
                handler.cancel(true);
            }
        }, Constants.NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);
    }

    public void cancelRequest() {
        if (retrieveRecipesRunnable != null) {
            retrieveRecipesRunnable.cancelRequest();
        }
    }

    // This is our runnable for searching recipes. It it implementing runnable interface.
    // It has overriten run method where all the logic should appear.
    private class RetrieveRecipesRunnable implements Runnable {

        // Query string
        private String query;
        // Query page number
        private int pageNumber;
        // Boolean to stoop/run the request
        boolean cancelRequest;

        // Public constructor with query parameters
        public RetrieveRecipesRunnable(String query, int pageNumber) {
            this.query = query;
            this.pageNumber = pageNumber;
            cancelRequest = false;
        }

        //Run method all the logic should be done in try/catch block.
        @Override
        public void run() {
            try {
                // Getting response objects from inner method.
                Response response = getRecipes(query, pageNumber).execute();
                // Checking if it is is cancelled or no
                if (cancelRequest == true) {
                    return;
                }
                // Checking if everything is OK
                if (response.code() == 200) {
                    // Getting a list of recipes.
                    List<Recipe> list = new ArrayList<>(((RecipeSearchResponse)response.body()).getRecipes());
                    // Checking ig it is first search request
                    if (pageNumber == 1) {
                        // If yes just post the value to LiveData in background thread
                        recipes.postValue(list);
                    } else {
                        // Else add recipes to the current list of recipes
                        List<Recipe> currentRecipes = recipes.getValue();
                        currentRecipes.addAll(list);
                        recipes.postValue(currentRecipes);
                    }
                } else {
                    // Null check for LiveData is a trigger to stop
                    recipes.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                // Null check for LiveData is a trigger to stop
                recipes.postValue(null);
            }

        }

        // Basically this is a call method with query string and page number
        private Call<RecipeSearchResponse> getRecipes(String query, int pageNumber) {
            // You can see the singleton chain from the highest layer to the lowest to
            // run searchRecipe method.
            return ServiceGenerator.getRecipeApi().searchRecipe(Constants.API_KEY,query,String.valueOf(pageNumber));
        }

        // This method is controlling cancel request logic
        private void cancelRequest() {
            // Boolean value changes to true and triggers the cancel request
            cancelRequest = true;
        }

    }
}
