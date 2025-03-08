package edu.lewisu.cs.tododb;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_TODO_ID = "toDoId";
    private ToDo mToDo;
    private ToDoRepo mRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mRepo = new ToDoRepo(getApplication());


        EditText titleField = findViewById(R.id.title_field);
        titleField.addTextChangedListener(new TitleListener());

        Spinner prioritySpinner = findViewById(R.id.spinner);
        prioritySpinner.setOnItemSelectedListener(new PrioritySelect());

        CheckBox completeCheckBox = findViewById(R.id.complete_checkbox);
        completeCheckBox.setOnClickListener(new CompleteChangeListener());

        Button addEditButton = findViewById(R.id.add_edit_button);

        int id = getIntent().getIntExtra(EXTRA_TODO_ID, 0);

        if (id != 0) {
           //retrieve item from Database
            mToDo = mRepo.getToDo(id);
        }

        //when there is something, it will display the data
        if(mToDo != null) {
            //set components to display detail information
            titleField.setText(mToDo.getTitle());
            prioritySpinner.setSelection(mToDo.getPriority());
            completeCheckBox.setChecked(mToDo.isComplete());
            addEditButton.setText(R.string.update);
            addEditButton.setOnClickListener(new OnUpdateButtonClick());

        }else{
            mToDo = new ToDo();
            addEditButton.setOnClickListener(new OnAddButtonClick());
        }

    }


    private class TitleListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mToDo.setTitle(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }


    private class CompleteChangeListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            CheckBox checkBox = (CheckBox)v;
            if(checkBox.isChecked()){
                mToDo.setComplete(true);
            }else{
                mToDo.setComplete(false);
            }
        }
    }


    private class PrioritySelect implements AdapterView.OnItemSelectedListener{
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            mToDo.setPriority(position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }


    private class OnAddButtonClick implements View.OnClickListener{
        @Override
        public void onClick(View v){
            //add an item
            mRepo.insert(mToDo);
            finish();
        }
    }


    private class OnUpdateButtonClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            // update the item
            mRepo.update(mToDo);
            finish();
        }
      }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.delete_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.delete) {
            //delete an item
            mRepo.deleteToDo(mToDo);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }



}// end of calss
