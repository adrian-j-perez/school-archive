package edu.lewis.cs.adrian.databaserating;

import android.content.Context;
import android.content.res.Resources;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

@Database(entities = {Rating.class}, version=1, exportSchema = false)
abstract class RatingDatabase extends RoomDatabase {

    public abstract RatingDao RatingDao();

    private static RatingDatabase sRatingDatabase;

    static RatingDatabase getInstance(final Context context){

        if(sRatingDatabase == null){
            synchronized (Rating.class){
                if(sRatingDatabase == null){
                    sRatingDatabase = Room.databaseBuilder(context.getApplicationContext(),
                    RatingDatabase.class, "rating_database")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return sRatingDatabase;
    }

}//end of class
