package edu.lewis.cs.adrian.fragmentrating;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_RATINGS_ID = "RatingId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        int ratingId = getIntent().getIntExtra(EXTRA_RATINGS_ID,1);

        FragmentManager fragmentManager =  getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.detail_container);

        if(fragment ==  null){
            fragment =  DetailFragment.newInstance(ratingId);
            fragmentManager.beginTransaction()
                    .add(R.id.detail_container, fragment)
                    .commit();
        }
    }//end of onCreate

}//end of class


