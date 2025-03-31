package edu.lewisu.cs.example.simplegame;

import android.content.Context;
import android.graphics.Paint;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

import java.util.ArrayList;

class GameView  {
    private final int WIN_SCORE = 5;
    private final int MAX_TIME = 5000;

    protected Ball mBall;
    private Paint mTextPaint;
    private double mTotalElapsedTime;
    private int mScore;
    private GameThread mGameThread;

    private SimpleGameActivity mParent;
    private SoundPool mSoundPool;
    private ArrayList<Integer> mSoundArray;


    public GameView(Context context) {

        mParent = (SimpleGameActivity) context;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes attributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            mSoundPool = new SoundPool.Builder()
                    .setAudioAttributes(attributes)
                    .build();
        }
        else {
            mSoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        }

        mSoundArray = new ArrayList<Integer>();
        mSoundArray.add(mSoundPool.load(mParent, R.raw.blocker_hit,1));
        mSoundArray.add(mSoundPool.load(mParent, R.raw.cannon_fire,1));
        mSoundArray.add(mSoundPool.load(mParent, R.raw.target_hit,1));
    }

    void stopGame(){

    }




    private class GameThread extends Thread {


    }

}
