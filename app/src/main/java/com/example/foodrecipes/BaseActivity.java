package com.example.foodrecipes;

import android.app.AppComponentFactory;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

// This is a base activity class which will be kind of a activity holder for
// other activates. We will never instantiate it, instead all the activities
// will be extended by base activity and that is why we added abstract keyword.
public abstract class BaseActivity extends AppCompatActivity {

    // We need to get progress bar reference and write method
    // to control its logic.
    private ProgressBar progressBar;


    // This method is used for setting all the views inside base activity.
    @Override
    public void setContentView(int layoutResID) {

        // First wee need to get our constraint layout, because our frame layout
        // is inside the constraint layout.
        ConstraintLayout constraintLayout = (ConstraintLayout) getLayoutInflater().inflate(R.layout.activity_base, null);

        // Getting frame layout from constraint layout.
        FrameLayout frameLayout = constraintLayout.findViewById(R.id.activity_content);

        // Progress bar is also inside constraints layout.
        progressBar = constraintLayout.findViewById(R.id.progress_bar);

        // This method will be responsible for frame layout to act as a container for other
        // activities that are extending base activity.
        getLayoutInflater().inflate(layoutResID, frameLayout, true);

        // Calling super method of the overridden method.
        super.setContentView(layoutResID);
    }

    // Logic to control progress bar visibility
    public void showProgressBar(boolean visibility) {
        if (visibility == false) {
            progressBar.setVisibility(View.INVISIBLE);
        } else {
            progressBar.setVisibility(View.VISIBLE);
        }
    }
}
