package edu.lewisu.cs.example.todofragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_TODO_ID = "ToDoId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        int toDoId = getIntent().getIntExtra(EXTRA_TODO_ID,1);

        FragmentManager fragmentManager =  getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.detail_container);

        if(fragment ==  null){
            fragment =  DetailFragment.newInstance(toDoId);
            //newInstance is a static method
            fragmentManager.beginTransaction()
                    .add(R.id.detail_container, fragment)
                    .commit();
        }

    }


}// end of class