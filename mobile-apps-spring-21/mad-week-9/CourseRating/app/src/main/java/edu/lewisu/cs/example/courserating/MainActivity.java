package edu.lewisu.cs.example.courserating;

import android.app.DownloadManager;
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
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private FirebaseDatabase mFirebaseDatabase;
    private Query query; // make sure this is using the firebase import
    private FirebaseRecyclerAdapter<CourseRating, CourseRatingAdapter.RatingHolder> mAdapter;
    private static final int RC_SIGN_IN = 1; // this should be any number we want


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);

        FloatingActionButton fab =  findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RatingActivity.class);
                startActivity(intent);
            }
        });

        //we set us the data base where they need a email ans password
        //? does it need to be a valied email to work
        mFirebaseAuth = FirebaseAuth.getInstance();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = mFirebaseAuth.getCurrentUser();
                // make sure we are just getting user data and not other user data^^
                if (user == null){
                    startActivityForResult(AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setIsSmartLockEnabled(false)//after testing it is good to set to true
                            .setTheme(R.style.Theme_CourseRating) //setting to same theme
                            .setAvailableProviders(Arrays.asList(new AuthUI.IdpConfig.EmailBuilder().build()))
                            .build(), RC_SIGN_IN );
                }

            }
        };
        //got to firebase website to see users

        //this is show we can see the list in he app from the database
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseDatabase.setPersistenceEnabled(true);// when we have a internet connection going in and out
        query = mFirebaseDatabase.getReference().child("course_rating");
        // i can also do a . orderByChild('instrutoName').equalTo("name") or limitByChilid(int)
        //so we can filiter out the data set
        // get data from the database ^^
        FirebaseRecyclerOptions<CourseRating> options =
                new FirebaseRecyclerOptions.Builder<CourseRating>().
                        setQuery(query,CourseRating.class).build();
        mAdapter = new CourseRatingAdapter(options, this);
        recyclerView.setAdapter(mAdapter);


    }//end of onCreate


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.sign_out){
            AuthUI.getInstance().signOut(this);
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
        mAdapter.startListening();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
        mAdapter.stopListening();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //when the user tries to sign but backs out
        if(resultCode == RESULT_CANCELED && requestCode == RC_SIGN_IN ){
            finish(); // will kill app

        }


    }
}







