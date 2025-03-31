package edu.lewisu.cs.example.simplegame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private int score;
    private TextView scoreBox;
    private final int SIMPLE_GAME = 12345;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scoreBox = (TextView)findViewById(R.id.scoreBox);
        scoreBox.setText(getResources().getString(R.string.score_format, score));
    }

    //button click method
    public void startGame(View v){
        Intent launchGame = new Intent(this, SimpleGameActivity.class);
        startActivityForResult(launchGame, SIMPLE_GAME);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SIMPLE_GAME && resultCode == RESULT_OK) {
            int newScore = data.getExtras().getInt("score");
            score += newScore;
            scoreBox.setText(getResources().getString(R.string.score_format, score));
        }
    }
}
