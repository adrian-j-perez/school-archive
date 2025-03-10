package edu.lewis.cs.adrian.databaserating;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RatingDao {

    @Insert
    void insertRatings(Rating ... ratings);

    @Update
    void updateRatings(Rating ... ratings);

    @Query("SELECT * FROM Rating WHERE id = :id")
    Rating getRating(int id);

    @Query("SELECT * FROM Rating ORDER BY ratingName, ratingReason")
    List<Rating> getAllRating();

    @Query("DELETE FROM Rating WHERE id = :id")
    void deleteRating(int id);

    @Query("DELETE FROM rating")
    void deleteAll();

}
