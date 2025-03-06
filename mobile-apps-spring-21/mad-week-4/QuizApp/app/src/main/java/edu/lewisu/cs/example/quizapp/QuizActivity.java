package edu.lewisu.cs.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class QuizActivity extends AppCompatActivity {
    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private Button gameOverButton;
    private TextView questionTextView;
    private TextView answerTextView;
    private TextView gameScoreTextView;
    private TextView lifetimeScoreTextView;
    private static final String TAG = "QuizActivity";


    private Question[] questions = new Question[]{
            new Question(R.string.question1, true),
            new Question(R.string.question2, false),
            new Question(R.string.question3, true),
    };

    private int currentIndex;
    private int score;
    private int lifetimeScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Log.d(TAG, "onCreate");


        Intent sender = getIntent();
        lifetimeScore = sender.getIntExtra("lifetime",0);


        trueButton = findViewById(R.id.trueButton);
        falseButton = findViewById(R.id.falseButton);

        trueButton.setOnClickListener(new TrueButtonClickListener());
        falseButton.setOnClickListener(new FalseButtonClickListener());

        nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new NextButtonClickListener());

        questionTextView = findViewById(R.id.questionTextView);
        answerTextView = findViewById(R.id.answerTextView);
        gameScoreTextView = findViewById(R.id.gameScoreTextView);
        lifetimeScoreTextView = findViewById(R.id.lifetimeScoreTextView);

        gameOverButton = findViewById(R.id.gameOverButton);
        gameOverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // this is sending data back and forward
                Intent returnIntent = new Intent();
                returnIntent.putExtra("score",score);
                //this is to send back the  game score
                setResult(RESULT_OK, returnIntent);

                finish();
                //this will close the activity and make t come of the stack and eunte turn back
            }
        });//end of class

        if (savedInstanceState != null){
            // the spelling of what we name matters or else it can't  find it
            currentIndex = savedInstanceState.getInt("question");
            score = savedInstanceState.getInt("gamesScore");
        }

        updateQuestion();
        updateScore();
    }

    //this method will help save the var when it restarts the app
    // example is when we rotate the layout it clears the data
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.d(TAG,"onSaveInstanceState");
        outState.putInt("question",currentIndex);
        outState.putInt("gameScore", score);
        outState.putInt("lifestime", lifetimeScore);

    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG,"onStart");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG,"onResume");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d(TAG,"onRestart");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d(TAG,"onStop");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAG,"onDestroy");
    }

    private void updateQuestion(){
        int question = questions[currentIndex].getTextResId();
        questionTextView.setText(question);
    }

    private void updateScore(){
        String scoreString = getResources().getString(R.string.game_score_string, score);
        gameScoreTextView.setText(scoreString);
        scoreString = getResources().getString(R.string.lifetime_score_string, lifetimeScore);
        lifetimeScoreTextView.setText(scoreString);
    }

    private void checkAnswer(Boolean userPress){
        boolean correctAnswer = questions[currentIndex].isAnswerTrue();


        if(userPress == correctAnswer){
            answerTextView.setTextColor(ContextCompat.getColor(getApplicationContext(),
                    R.color.colorPrimaryDark));
            answerTextView.setText(getResources().getText(R.string.correct));
            score ++;
            lifetimeScore++;


        }else{
            answerTextView.setTextColor(ContextCompat.getColor(getApplicationContext(),
                    R.color.colorIncorrect));//setting the color
            answerTextView.setText(getResources().getText(R.string.incorrect));
        }

        updateScore();
        answerTextView.setVisibility(View.VISIBLE);

        currentIndex++;
        if(currentIndex < questions.length) {
            nextButton.setVisibility(View.VISIBLE);
        }else{
            gameOverButton.setVisibility(View.VISIBLE);
        }


    }

    private class TrueButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            checkAnswer(true);
            v.setEnabled(false);
            falseButton.setVisibility(View.GONE);
            /// this right is is how to hide th ebutton and disable a buton

        }
    }

    private class FalseButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            checkAnswer(false);
            v.setEnabled(false);
            trueButton.setVisibility(View.GONE);

        }
    }

    private class NextButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            updateQuestion();
            answerTextView.setVisibility(View.INVISIBLE);
            nextButton.setVisibility(View.GONE);
            trueButton.setVisibility(View.VISIBLE);
            falseButton.setVisibility(View.VISIBLE);
            trueButton.setEnabled(true);
            falseButton.setEnabled(true);
        }
    }
} //end of <QuizActivity>
