package edu.lewis.cs.adrian.fragmentrating;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity
        implements RatingListAdapter.RatingListAdapterOnClickHandler{

    private boolean twoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(findViewById(R.id.detail_container) !=null){
            twoPane = true;
        }

        RecyclerView recyclerView = findViewById(R.id.rating_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        RatingListAdapter ratingListAdapter = new RatingListAdapter(this);
        recyclerView.setAdapter(ratingListAdapter);
        ratingListAdapter.setRatingList(RatingDatabase.getInstance(this).getRatingsList());

    }

    @Override
    public void onClick(Rating rating) {
        if(twoPane){
            FragmentManager fragmentManager = getSupportFragmentManager();
            Fragment fragment = DetailFragment.newInstance(rating.getId());
            fragmentManager.beginTransaction()
                    .replace(R.id.detail_container, fragment)
                    .commit();
        }else{
            Intent detailIntent = new Intent(getApplicationContext(), DetailActivity.class);
            detailIntent.putExtra(DetailActivity.EXTRA_RATINGS_ID, rating.getId());

            startActivity(detailIntent);
        }
    }


}//end class