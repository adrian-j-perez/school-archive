package edu.lewis.cs.adrian.fragmentrating;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class RatingDatabase {
    // static b/c we just need the one
    private static RatingDatabase sRatingDatabase;
    private List<Rating> mRatingsList;

    public static RatingDatabase getInstance(Context context){
        if (sRatingDatabase == null) {
            sRatingDatabase = new RatingDatabase(context);
        }
        return sRatingDatabase;
    }

    // a con method
    private RatingDatabase(Context context){
        mRatingsList =  new ArrayList<>();
        Resources res = context.getResources();

        String[] ratingNames = res.getStringArray(R.array.db_rating_name);
        // need to make sure I convert into a int later
        String[] ratingNums = res.getStringArray(R.array.db_rating_number);
        String[] ratingReasons = res.getStringArray(R.array.db_rating_reason);

        for (int i = 0; i < ratingNames.length; i++) {

            mRatingsList.add( new Rating((i+1), ratingNames[i], Integer.parseInt(ratingNums[i]),
                    ratingReasons[i]) );
            //Log.d("Database", (mRatingsList.get(i)).toString());
        }

    }

    //getters
    public List<Rating> getRatingsList() { return mRatingsList;}

    public Rating getRating (int ratingId){
        //for each loop to find a rating object we are looking for
        for (Rating rating : mRatingsList){
            if(rating.getId() == ratingId){
                return rating;
            }
        }
        return null;
    }

}//end of class
