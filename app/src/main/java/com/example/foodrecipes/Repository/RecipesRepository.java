package com.example.foodrecipes.Repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.foodrecipes.Model.Recipe;

import java.util.List;

// Repository is the gateway for all the data sources. Here data is being
// transferred to ViewModel layer. That is why it is a good idea to make
// this class singleton.
public class RecipesRepository {


    // Each ViewModel has LiveData object.
    private MutableLiveData<List<Recipe>> recipes;

    // Implementing singleton pattern.
    private static RecipesRepository instance;

    // Implementing singleton pattern.
    public static RecipesRepository getInstance() {
        if (instance == null) {
            instance = new RecipesRepository();
        }
        return instance;
    }

    // Public constructor, for instantiating LiveData when this class
    // is being instantiated first time. And it is a singleton that is why
    // it we always one object at a time and one LiveData.
    public RecipesRepository() {
        recipes = new MutableLiveData<>();
    }

    // Method for getting all recipes.
    public LiveData<List<Recipe>> getRecipes() {
        return recipes;
    }

}
