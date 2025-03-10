package edu.lewisu.cs.howardcy.courserating;


import android.graphics.CornerPathEffect;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CourseRatingDao {
    @Insert
    void insertRating(CourseRating ... ratings);

    @Update
    void updateRating(CourseRating ... ratings);

    @Query("SELECT * FROM CourseRating WHERE id = :id")
    CourseRating getRating(int id);

    @Query("SELECT * FROM CourseRating ORDER BY instructorName, courseName")
    List<CourseRating> getAllRating();

    @Query("DELETE FROM CourseRating WHERE id = :id")
    void deleteRating(int id);



}
