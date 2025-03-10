package edu.lewisu.cs.howardcy.courserating;

import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class LaunchActivity extends AppCompatActivity implements CourseRatingListAdapter.ListAdapterOnClickHandler {
    private static final int RATING_INTENT_RESULT = 100;
    private CourseRatingListAdapter mCourseRatingListAdapter;
    private CourseRatingRepo repo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //user default
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean darkTheme = sharedPreferences.getBoolean("dark", false);
        if(darkTheme){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        repo = new CourseRatingRepo(getApplication());

        setContentView(R.layout.activity_launch);
        FloatingActionButton fab =  findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivityForResult(intent, RATING_INTENT_RESULT);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        mCourseRatingListAdapter = new CourseRatingListAdapter(this, this);
        recyclerView.setAdapter(mCourseRatingListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //this will let us be able to swip to delete
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int id  = (int) viewHolder.itemView.getTag(); // we set a tag in the listAdapter onbind method
                repo.deleteRating(id);
                mCourseRatingListAdapter.setRatings(repo.getAllRating());
            }
        }).attachToRecyclerView(recyclerView);


    }//end of onCreate()


    @Override
    protected void onResume() {
        super.onResume();
        if(mCourseRatingListAdapter != null){
            mCourseRatingListAdapter.setRatings(repo.getAllRating());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_settings){
            //Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
            Intent startSettingsActivity = new Intent(this, SettingsActivity.class);
            startActivity(startSettingsActivity);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode==RATING_INTENT_RESULT){
            String toastString = getString(R.string.added_rating);
            Toast.makeText(getApplicationContext(), toastString, Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onClick(CourseRating courseRating) {
        Intent detailIntent = new Intent(this, MainActivity.class);
        detailIntent.putExtra("id", courseRating.getId());
        startActivity(detailIntent);
    }
}//end of class