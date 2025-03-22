package edu.lewisu.cs.example.dotty;

import android.content.Context;
import android.media.SoundPool;

import java.util.ArrayList;

class SoundEffects {
    private static SoundEffects mSoundEffects;

    private SoundPool mSoundPool;
    private ArrayList<Integer> mSelectSoundIds;
    private int mSoundIndex;
    private int mEndGameSoundId;


    private SoundEffects(Context context) {


    }

    public static SoundEffects getInstance(Context context) {
        if (mSoundEffects == null) {
            mSoundEffects = new SoundEffects(context);
        }
        return mSoundEffects;
    }

    public void resetTones() {
        mSoundIndex = -1;
    }

    public void playTone(boolean advance) {

    }

    public void playGameOver() {

    }
}
