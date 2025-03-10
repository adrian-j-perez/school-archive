package edu.lewisu.cs.howardcy.courserating;

import android.app.Application;

import java.util.List;

public class CourseRatingRepo {
    private  CourseRatingDao mCourseRatingDao;
    //using the interface class

    public CourseRatingRepo(Application application){
        CourseRatingDatabase database = CourseRatingDatabase.getInstance(application);
        mCourseRatingDao = database.courseRatingDao();
        // combo the two objects
    }

    List<CourseRating> getAllRating(){
        return mCourseRatingDao.getAllRating();
    }

    CourseRating getRating(int id){
        return mCourseRatingDao.getRating(id);
    }

    void insertRating(CourseRating rating){
        mCourseRatingDao.insertRating(rating);
    }

    void updateRating(CourseRating rating){
        mCourseRatingDao.updateRating(rating);
    }

    void deleteRating(int id){
        mCourseRatingDao.deleteRating(id);
    }

}//end of class
