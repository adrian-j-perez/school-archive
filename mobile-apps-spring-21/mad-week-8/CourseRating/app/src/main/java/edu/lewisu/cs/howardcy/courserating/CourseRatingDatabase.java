package edu.lewisu.cs.howardcy.courserating;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;


@Database(entities = {CourseRating.class}, version=1, exportSchema = false)
abstract class CourseRatingDatabase extends RoomDatabase {

    public abstract CourseRatingDao courseRatingDao();

    private static CourseRatingDatabase sCourseRatingDatabase;

    static CourseRatingDatabase getInstance(final Context context){
        if(sCourseRatingDatabase == null){
            synchronized (CourseRatingDatabase.class){
                if (sCourseRatingDatabase == null){
                    sCourseRatingDatabase = Room.databaseBuilder(context.getApplicationContext(),
                    CourseRatingDatabase.class, "rating_db")
                            .allowMainThreadQueries()
                            .build();
                    //NOTE: it would be better to make a thread then to put it on the main
                }//end of if

            }//sync

        }//if
        return sCourseRatingDatabase;

    }




}
