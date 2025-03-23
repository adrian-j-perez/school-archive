package edu.lewisu.cs.example.graphics;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void buttonClick(View v){
        Intent animationIntent = new Intent(this, AnimationActivity.class);
        startActivity(animationIntent);
    }
}