package edu.lewis.cs.adrian.databaserating;

import android.app.Application;
import java.util.List;

public class RatingRepo {
    private RatingDao mRatingDao;

    public RatingRepo(Application application){
        RatingDatabase database =  RatingDatabase.getInstance(application);
        mRatingDao = database.RatingDao();
    }

    void insertRatings(Rating rating){
        mRatingDao.insertRatings(rating);
    }

    void updateRating(Rating rating){
        mRatingDao.updateRatings(rating);
    }

    Rating getRating(int id){
        return mRatingDao.getRating(id);
    }

    List<Rating> getAllRating(){
        return mRatingDao.getAllRating();
    }

    void deleteRating(int id){
        mRatingDao.deleteRating(id);
    }

    public void initialize(){
        //have some fun here
        mRatingDao.deleteAll();

        Rating rating =  new Rating("Google.com", 5 ,
                "for search things", R.id.bookmarkRadioButton);
        mRatingDao.insertRatings(rating);

        rating = new Rating("Bing.com", 3 ,
                "for find google", R.id.notVisitRadioButton);
        mRatingDao.insertRatings(rating);

        rating = new Rating("Netflix.com", 4 ,
                "Good for watching movies and tv shows", R.id.bookmarkRadioButton);
        mRatingDao.insertRatings(rating);

        rating = new Rating("Pizzahut.com", 5 ,
                "Because no one can out pizza the hut!!", R.id.shareRadioButton);
        mRatingDao.insertRatings(rating);

    }


}
