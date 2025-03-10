package edu.lewis.cs.adrian.databaserating;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class LaunchActivity extends AppCompatActivity
        implements RatingListAdapter.RatingListAdapterOnClickHandler {

    private static final int RATING_INTENT_RESULT = 10;
    private RatingListAdapter mRatingListAdapter;
    private RatingRepo mRepo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRepo = new RatingRepo(getApplication());

        setContentView(R.layout.activity_launch);

        FloatingActionButton fab =  findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivityForResult(intent, RATING_INTENT_RESULT);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        mRatingListAdapter = new RatingListAdapter(this, this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mRatingListAdapter);
        mRepo.initialize();

    }// end onCreate method

    @Override
    protected void onResume() {
        super.onResume();
        if(mRatingListAdapter != null ){
            mRatingListAdapter.setRatingList(mRepo.getAllRating());
        }
    }

    //method come from the implements
    @Override
    public void onClick(Rating rating) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(MainActivity.EXTRA_RATING_ID, rating.getId());
        startActivity(intent);
    }

    //for the icon
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu,menu); //the setting icon
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings){
             Intent startSettingsActivity = new Intent(this, SettingActivity.class);
             startActivity(startSettingsActivity);
        }

        if(id ==  R.id.action_reset){
            mRepo.initialize();
            mRatingListAdapter.setRatingList(mRepo.getAllRating());
            Toast.makeText(getApplicationContext(), "Database reset", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == RATING_INTENT_RESULT ){
            String toastString = getString(R.string.rating_added);
            Toast.makeText(getApplicationContext(), toastString, Toast.LENGTH_SHORT).show();
        }
    }


}//end of class