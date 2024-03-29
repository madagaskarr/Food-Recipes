package com.example.foodrecipes.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.foodrecipes.Model.Recipe;
import com.example.foodrecipes.Repository.RecipesRepository;

import java.util.List;
// Each View has its ViewModel. We should extend ViewModel class or
// AndroidViewModel class if we need Application context.
public class MainActivelyViewModel extends ViewModel {

    private boolean isPerformingQuery;

    // ViewModel get all its data from Repository layer, which is singleton class.
    private RecipesRepository recipesRepository;


    // Constructor
    public MainActivelyViewModel() {
        isPerformingQuery = false;
        recipesRepository = RecipesRepository.getInstance();
    }

    // And method to return LiveData
    public LiveData<List<Recipe>> getRecipes() {
        return recipesRepository.getRecipes();
    }

    // Second search method in a chain.
    public void searchRecipes(String query, int pageNumber) {
        recipesRepository.searchRecipes(query, pageNumber);
        isPerformingQuery = true;
    }

    public boolean isPerformingQuery() {
        return isPerformingQuery;
    }

    public void setPerformingQuery(boolean performingQuery) {
        isPerformingQuery = performingQuery;
    }

    public boolean onCancelMenuItemClicked() {
        if (isPerformingQuery) {
            isPerformingQuery = false;
            recipesRepository.cancelRequest();
        }
        return true;
    }

    public void requestNextPage(String query) {
        recipesRepository.requestNextPage(query);
    }
}
