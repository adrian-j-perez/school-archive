package edu.lewisu.cs.example.quizapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int mLifetimeScore = 21;
    private TextView mScoreTextView;
    private static final int QUIZ_INTENT_RESULT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = findViewById(R.id.startButton);
        //making class
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // this is to launch the quiz screen
                Intent quizIntent = new Intent(getApplicationContext(), QuizActivity.class);
                quizIntent.putExtra("lifetime", mLifetimeScore);
                startActivityForResult(quizIntent, QUIZ_INTENT_RESULT);
                // this is to make sure that we are expection to get a number back


            }
        }//end of class
        );

        mScoreTextView = findViewById(R.id.mainScoreTextView);// set a id for thi in the xml
        String scoreString = getResources().getString(R.string.lifetime_score_string,mLifetimeScore);
        mScoreTextView.setText(scoreString);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == QUIZ_INTENT_RESULT){
            int gameScore = data.getIntExtra("score", 0);
            mLifetimeScore += gameScore;
            String scoreString =  getResources().getString(R.string.lifetime_score_string, mLifetimeScore);
            mScoreTextView.setText(scoreString);
        }


    }



}//end of class