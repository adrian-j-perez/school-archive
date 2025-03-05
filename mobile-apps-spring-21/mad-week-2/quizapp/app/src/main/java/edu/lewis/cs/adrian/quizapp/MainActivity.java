package edu.lewis.cs.adrian.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //variable
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mBackButton;
    private TextView mQuestionTextView;

    //making a array of questions
    //Question is the new class hat we made and we are make a array objects
    private final Question[] mQuestions = new Question  [] {
            new Question(R.string.question_1, true),
            new Question(R.string.question_2, true),
            new Question(R.string.question_3, false)
    };

    private int mCurrentIndex = 0 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //finding the button that we made in .xml
        mTrueButton = findViewById(R.id.true_button);
        //setting a listener
        mTrueButton.setOnClickListener( new TrueButtonClickListener());

        mFalseButton = findViewById(R.id.false_button);
        mFalseButton.setOnClickListener( new FalseButtonClickListener());

        mNextButton = findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new NextButtonClickListener());

        mBackButton = findViewById(R.id.back_button);
        mBackButton.setOnClickListener(new BackButtonCLickListener());

        mQuestionTextView = findViewById(R.id.question_text_view);
        updateQuestion();
    }

    private void updateQuestion(){
        int question = mQuestions[mCurrentIndex].getTestResId();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer (boolean userPress){
        boolean correctAnswer = mQuestions[mCurrentIndex].isAnswer();
        int toastTextId;

        if (userPress == correctAnswer){
            toastTextId = R.string.correct;
        }else{ toastTextId = R.string.incorrect ; }

        Toast.makeText(getApplicationContext(), toastTextId, Toast.LENGTH_SHORT).show();
    }//end of checkAnswer


    //////////////////////adding classes////////////////////////

    //make new classes for the button actions when
    private class TrueButtonClickListener implements View.OnClickListener {
        // do ctrl + i to implement the needed methods
        @Override
        public void onClick(View v) {
            checkAnswer(true);
            //Toast.makeText(MainActivity.this, R.string.correct, Toast.LENGTH_SHORT).show();
            //the toasting is going to be handle in the <checkAnswer> method

            //Log.d("MainActivity", "True"); // debugging
        }
    }

    private class FalseButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            checkAnswer(false);
            //Toast.makeText(MainActivity.this, R.string.incorrect, Toast.LENGTH_SHORT).show();
        }
    }

    private class NextButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            mCurrentIndex = mCurrentIndex + 1;
            if (mCurrentIndex >= mQuestions.length){
                mCurrentIndex = 0;
            }
            updateQuestion();
        }
    }//end of next class

    private class BackButtonCLickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            mCurrentIndex = mCurrentIndex - 1;
            if (mCurrentIndex < 0){
                mCurrentIndex = mQuestions.length - 1;
            }
            updateQuestion();
        }
    }//end of back class


}//end of main class