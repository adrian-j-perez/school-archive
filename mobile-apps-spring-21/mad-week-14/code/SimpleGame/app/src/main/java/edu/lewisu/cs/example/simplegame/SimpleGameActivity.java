package edu.lewisu.cs.example.simplegame;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SimpleGameActivity extends AppCompatActivity {

    private GameView gameView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_game);

    }

    @Override
    protected void onPause(){
        super.onPause();
        gameView.stopGame();
    }

    public void gameOver(int score){
        Intent mIntent = new Intent();
        mIntent.putExtra("score", score);
        setResult(RESULT_OK, mIntent);
        finish();
    }
}