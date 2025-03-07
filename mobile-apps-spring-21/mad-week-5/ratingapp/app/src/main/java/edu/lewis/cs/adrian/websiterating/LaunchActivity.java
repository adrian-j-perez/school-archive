package edu.lewis.cs.adrian.websiterating;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.PictureInPictureParams;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LaunchActivity extends AppCompatActivity {
private static final int RATING_INTENT_RESULT = 100;
// this is to check that we came back

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        Button mLaunchRatingButton = findViewById(R.id.startButton);
        mLaunchRatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ratingIntent =  new Intent(getApplicationContext(), MainActivity.class);
                startActivityForResult(ratingIntent, RATING_INTENT_RESULT);
            }
        });

    }

    //this is for the app bar icon
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_setting){
            //Toast.makeText(getApplicationContext(), item.getTitle, Toast.LENGTH_SHORT).show();
            Intent startSettingActivity =  new Intent(this, SettingsActivity.class);
            startActivity(startSettingActivity);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //once getting back from the <MainActivity> getting data to do a toast
        if(resultCode == RESULT_OK && requestCode == RATING_INTENT_RESULT){
            //----
            String websiteName = data.getStringExtra("websiteName");
            String reasonForUse = data.getStringExtra("reason");
            String wouldYou = data.getStringExtra("wouldYpu");
            int starsRatingNumber = data.getIntExtra("rating",1);
            //----

            //formatting var's
            String toastString = "Rating Entered:\n";
            toastString += "Website Name: " + websiteName + "\n";
            toastString += "Reason for use: " + reasonForUse+ "\n";
            toastString += "You Would: " + wouldYou + "\n";
            toastString += "Star Rating: " + starsRatingNumber + "\n";

            Toast.makeText(getApplicationContext(), toastString, Toast.LENGTH_SHORT).show();
        }

    }




}// end of class