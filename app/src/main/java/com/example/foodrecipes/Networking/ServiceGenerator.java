package com.example.foodrecipes.Networking;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// This is a common practise to start working with Retrofit. Basically you need
// ti implement singleton pattern so we will have only one object at a time.
public class ServiceGenerator {

    // We are instantiating retrofit using builder and passing two parameters: api key
    // and converter factory.
    public static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl(Constants.API_KEY)
                .addConverterFactory(GsonConverterFactory.create());

    // Then creating retrofit instance from retrofit builder.
    private static Retrofit retrofit = retrofitBuilder.build();

    // We need an interface to work with retrofit and run its networking
    // operations. For that we need to instantiate our interface and using
    // retrofit create method pass a reference to our interface.
    private static RecipeApi recipeApi = retrofit.create(RecipeApi.class);

    // As you noticed everything above is private so we need public method to get
    // our interface api.
    public static RecipeApi getRecipeApi() {
        return recipeApi;
    }
}
