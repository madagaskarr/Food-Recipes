package com.example.foodrecipes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.foodrecipes.Adapter.RecipiesRecyclerViewAdapter;
import com.example.foodrecipes.Listener.OnRecipeClickListener;
import com.example.foodrecipes.Model.Recipe;
import com.example.foodrecipes.ViewModel.MainActivelyViewModel;
import java.util.List;

import javax.xml.datatype.Duration;


public class MainActivity extends AppCompatActivity implements OnRecipeClickListener {

    private MainActivelyViewModel mainActivelyViewModel;
    private RecyclerView recipiesRecyclerView;
    private RecipiesRecyclerViewAdapter recipiesRecyclerViewAdapter;
    private SearchView searchView;
    private ProgressBar mainProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainProgressBar = findViewById(R.id.main_progress_bar);
        mainActivelyViewModel = ViewModelProviders.of(this).get(MainActivelyViewModel.class);
        subscribeObservers();
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        recipiesRecyclerView = findViewById(R.id.recipies_recycler_view);
        recipiesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        recipiesRecyclerViewAdapter = new RecipiesRecyclerViewAdapter(this);
        recipiesRecyclerView.setAdapter(recipiesRecyclerViewAdapter);

        searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                mainActivelyViewModel.setPerformingQuery(true);
                mainProgressBar.setVisibility(View.VISIBLE);
                mainActivelyViewModel.searchRecipes(s, 1);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        recipiesRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {

                if (!recyclerView.canScrollVertically(0)) {
                    Log.d("TIGRAN", "onScrollStateChanged: get to the end!");
                    mainActivelyViewModel.requestNextPage(searchView.getQuery().toString());
                    Toast.makeText(getApplicationContext(),"New query started.",Toast.LENGTH_LONG).show();
                }

                super.onScrollStateChanged(recyclerView, newState);
            }
        });



    }



    // LiveData work with subscribing. This method will subscribe the observers.
    private void subscribeObservers() {
        // ViewModel class has method observe which triggers onChanged method when some
        // changes happens to LiveData.
        mainActivelyViewModel.getRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipes) {
                if (recipes != null) {
                    recipiesRecyclerViewAdapter.setRecipes(recipes);
                    mainProgressBar.setVisibility(View.GONE);
                    mainActivelyViewModel.setPerformingQuery(false);
                }
            }
        });
    }

    @Override
    public void onRecipeItemClicked(int position) {

    }

    @Override
    public void onCategoryClicked(String category) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.recipe_search_meanu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.action_categories) {
            mainActivelyViewModel.onCancelMenuItemClicked();
            Toast.makeText(getApplicationContext(),"Request has been cancelled.",Toast.LENGTH_LONG).show();

        }

        return super.onOptionsItemSelected(item);
    }
}
