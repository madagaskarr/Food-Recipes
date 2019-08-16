package com.example.foodrecipes.Networking;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

// This is the interface through which we are going to make our requests.
public interface RecipeApi {

    // The first request will be GET request for searching recipe. We don't need to pass the base url
    // because we have it in our constants we will pass it from there. Next we need to maintain the
    // same structure as we have in PostMan. So after base url comes api/search and then ? and Retrofit
    // will put that automatically wit first @Query annotation, then we have &q and starting from second
    // Query annotation instead of ? retrofit will append &, so we need to write only q, then we need to
    // pass the page we want to get. Again remember that after the first Query annotation retrofit appending
    // & character instead of ? character so we don't need to write it.
    @GET("api/search")
    Call<RecipeSearchResponse> searchRecipe(@Query("key") String key, @Query("q") String query, @Query("page") String page);

    // This request is for getting particular recipe and the difference is that it has api/get instead of
    // api/search and again imitate the url from Postman. In this case we have only two parameters for
    // our request: rID and key. ALso the type is RecipeResponse.
    @GET("api/get")
    Call<RecipeResponse> getRecipe(@Query("key") String key, @Query("rId") String rId);


}
