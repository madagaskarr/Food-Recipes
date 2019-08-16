package com.example.foodrecipes.Networking;

import com.example.foodrecipes.Model.Recipe;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

// Each GET request will have some response back and in Retrofit we need
// to provide separate class for each response.
public class RecipeSearchResponse {

    // SerializedName annotation is used for telling the key of the
    // response. So in this example we will get back a dictionary with
    // key of count and the value is the number of recipes Expose annotation
    // is used for Gson converter factory to convert response into our model
    // class.
    @SerializedName("count")
    @Expose()
    private int count;

    // In this example we will get back a dictionary with
    // key of recipes and the value is a list of recipes.
    @SerializedName("recipes")
    @Expose()
    private List<Recipe> recipes;

    // Getter for count.
    public int getCount() {
        return count;
    }

    // Getter for recipes.
    public List<Recipe> getRecipes() {
        return recipes;
    }

    // This is helpful for debugging.
    @Override
    public String toString() {
        return "RecipeSearchResponse{" +
                "count=" + count +
                ", recipes=" + recipes +
                '}';
    }
}
