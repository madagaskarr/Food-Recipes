package com.example.foodrecipes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.foodrecipes.Model.Recipe;
import com.example.foodrecipes.Networking.RecipeApi;
import com.example.foodrecipes.Networking.RecipeResponse;
import com.example.foodrecipes.Networking.RecipeSearchResponse;
import com.example.foodrecipes.Networking.ServiceGenerator;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Testing response for RecipeSearch
    public void testingRecipeSearch(View view) {
        // Getting the reference to our RecipeApi
        final RecipeApi recipeApi = ServiceGenerator.getRecipeApi();
        // Call is object for responses and here we using our API interface to call searchRecipe method and passing parameters
        Call<RecipeSearchResponse> recipeSearchResponseCall = recipeApi.searchRecipe(Constants.API_KEY, "chicken", "1");
        // Enqueue method has two inner method onResponse and onFailure
        recipeSearchResponseCall.enqueue(new Callback<RecipeSearchResponse>() {
            // OnReponse return the response and then we can do our logic, but first
            // it is a good practise to check HTTP code. 200 means OK.
            @Override
            public void onResponse(Call<RecipeSearchResponse> call, Response<RecipeSearchResponse> response) {
                if (response.code() == 200) {
                    // So this is recipe search response which is returning list of recipes that
                    // is why we inserting it into list of recipes.
                    List<Recipe> recipes = new ArrayList<>(response.body().getRecipes());

                    // Just looping through and printing values
                    for (Recipe recipe : recipes) {
                        Log.d("Recipe", "onResponse: " + recipe.getTitle());
                    }
                } else {
                    try {
                        // This most likely shoudn't happen but what if happens lets print something
                        // for debugging.
                        Log.d("Not 200", "onResponse: " + response.body());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            // OnFailure return throwable which we can use to print error message.
            @Override
            public void onFailure(Call<RecipeSearchResponse> call, Throwable t) {
                Log.d("onFailure", "onResponse: " + t.toString());

            }
        });
    }

    public void testRetrofitResponseTwo(View view) {
        // Getting the reference to our RecipeApi
        final RecipeApi recipeApi = ServiceGenerator.getRecipeApi();
        // Call is object for responses and here we using our API interface to call RecipeResponse method and passing parameters
        Call<RecipeResponse> recipeResponseCall = recipeApi.getRecipe(Constants.API_KEY, "35382");
        // Enqueue method has two inner method onResponse and onFailure
        recipeResponseCall.enqueue(new Callback<RecipeResponse>() {
            // OnReponse return the response and then we can do our logic, but first
            // it is a good practise to check HTTP code. 200 means OK.
            @Override
            public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
                if (response.code() == 200) {
                    // This is just a reponse of single item so printing it.
                    Recipe recipes = response.body().getRecipe();
                    Log.d("recipes", "onResponse: " + recipes.getTitle());
                } else {
                    try {
                        // This most likely shoudn't happen but what if happens lets print something
                        // for debugging.
                        Log.d("Not 200", "onResponse: " + response.body());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            // OnFailure return throwable which we can use to print error message.
            @Override
            public void onFailure(Call<RecipeResponse> call, Throwable t) {
                Log.d("onFailure", "onResponse: " + t.toString());
            }
        });


    }
}
