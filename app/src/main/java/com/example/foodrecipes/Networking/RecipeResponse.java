package com.example.foodrecipes.Networking;

import com.example.foodrecipes.Model.Recipe;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// Each GET request will have some response back and in Retrofit we need
// to provide separate class for each response.
public class RecipeResponse {

    // SerializedName annotation is used for telling the key of the
    // response. So in this example we will get back a dictionary with
    // key of recipe and the values are all the field we wrote in the class.
    // Expose annotation is used for Gson converter factory to convert response
    // into our model class.
    @SerializedName("recipe")
    @Expose()
    private Recipe recipe;

    // Just a public constructor
    public Recipe getRecipe() {
        return recipe;
    }

    // ToString method is helpful for debugging
    @Override
    public String toString() {
        return "RecipeResponse{" +
                "recipe=" + recipe +
                '}';
    }
}
