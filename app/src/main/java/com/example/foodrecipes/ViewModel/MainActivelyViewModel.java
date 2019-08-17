package com.example.foodrecipes.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.foodrecipes.Model.Recipe;

import java.util.List;
// Each View has its viewmodel. We should extend ViewModel class or
// AndroidViewModel class if we need Application context.
public class MainActivelyViewModel extends ViewModel {

    // Each ViewModel has LiveData object
    private MutableLiveData<List<Recipe>> recipes = new MutableLiveData<>();

    // Constructor
    public MainActivelyViewModel() {

    }

    // And method to return LiveData
    public LiveData<List<Recipe>> getRecipes() {
        return recipes;
    }
}
