package edu.lewisu.cs.tododb;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements ToDoListAdapter.TodoListAdapterOnClickHandler{
    private ToDoListAdapter mToDoListAdapter;
    private ToDoRepo mRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRepo =  new ToDoRepo(getApplication());

        RecyclerView recyclerView=findViewById(R.id.todo_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        mToDoListAdapter = new ToDoListAdapter(this);
        recyclerView.setAdapter(mToDoListAdapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailIntent = new Intent(getApplicationContext(), DetailActivity.class);
                startActivity(detailIntent);
            }
        });

    }// end of onCreate method

    @Override
    protected void onResume() {
        super.onResume();
        if(mToDoListAdapter != null){
            mToDoListAdapter.setToDos(mRepo.getAllToDos());
        }
    }

    @Override
    public void onClick(ToDo toDo) {
        Intent detailIntent = new Intent(this, DetailActivity.class);
        detailIntent.putExtra(DetailActivity.EXTRA_TODO_ID, toDo.getId());
        startActivity(detailIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.reset) {
            mRepo.initialize();
            //put that data into the recyler view
            mToDoListAdapter.setToDos(mRepo.getAllToDos());

            Toast.makeText(getApplicationContext(), "Reset DB", Toast.LENGTH_SHORT).show();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
