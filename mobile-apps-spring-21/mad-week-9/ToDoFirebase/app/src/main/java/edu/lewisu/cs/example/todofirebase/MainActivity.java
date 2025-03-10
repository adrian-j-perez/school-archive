package edu.lewisu.cs.example.todofirebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements ToDoListAdapter.TodoListAdapterOnClickHandler {

    private final static int RC_SIGN_IN = 1;
    private ToDoListAdapter adapter;
    private RecyclerView recyclerView;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mFirebaseListener;
    private String mUserId;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FloatingActionButton fab =  findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("uid",mUserId);// sending user id
                startActivity(intent);
            }
        });

        FirebaseApp.initializeApp(this);
        mFirebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mFirebaseAuth.getCurrentUser();
        if (user != null){
            mUserId = user.getUid();
        }

        setAdapter();


        mFirebaseListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    mUserId = user.getUid();
                }else{
                    startActivityForResult(
                            AuthUI.getInstance().createSignInIntentBuilder().setIsSmartLockEnabled().setTheme(R.style.Theme_ToDoFirebase).setAvailableProviders(Arrays.asList(new AuthUI.IdpConfig.EmailBuilder().build())))
                    ;/// TODO FIXME finsihs the line f code
                }
            }
        };

    }//end of onCreate

    @Override
    public void onClick(int position) {
        Intent detailIntent = new Intent(this, DetailActivity.class);
        detailIntent.putExtra("uid",mUserId);

        DatabaseReference ref = adapter.getRef(position);
        String id =; //TODO last 11 min of class vode // for all thid
        startActivity(detailIntent);
    }


    @Override
    public void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mFirebaseListener);
        adapter.startListening();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mFirebaseAuth.removeAuthStateListener(mFirebaseListener);
        adapter.stopListening();







    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.sign_out){
            AuthUI.getInstance().signOut()
            return true;
        }else{
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_SIGN_IN){
            if(resultCode == RESULT_OK){ // TODO fixm e
                FirebaseUser user = mFirebaseAuth.getCurrentUser();

                if(user != null){
                    mUserId = user.getUid();//TODO  rechecl if this id reight
                    setAdapter();
                }

            }
            if (resultCode == )//FIXME
        }

    }


    private  void setAdapter(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        Query query = firebaseDatabase.getReference().child("to_do").orderByChild("uid").equalTo(mUserId);
        //^^ this will get the user is object only

        FirebaseRecyclerOptions<ToDo> options = new FirebaseRecyclerOptions.Builder<ToDo>()
                .setQuery(query,ToDo.class).build();
        adapter = new ToDoListAdapter(options,this);
        recyclerView.setAdapter(adapter);
    }

}//end of class