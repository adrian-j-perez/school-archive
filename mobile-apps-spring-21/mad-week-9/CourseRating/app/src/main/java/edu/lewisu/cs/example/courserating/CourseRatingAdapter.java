package edu.lewisu.cs.example.courserating;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class CourseRatingAdapter extends FirebaseRecyclerAdapter<CourseRating, CourseRatingAdapter.RatingHolder> {
    private Context context;
    //default con
    public CourseRatingAdapter(@NonNull FirebaseRecyclerOptions<CourseRating> options, Context context) {
        super(options);
    }


    //setting the values to the view
    @Override
    protected void onBindViewHolder(@NonNull RatingHolder holder, int position, @NonNull CourseRating model) {
        holder.courseNameTextView.setText(model.getCourseName());
        holder.instructorTextView.setText(model.getInstructorName());
        int rating = model.getRating();
        String ratingString = context.getResources().getQuantityString(R.plurals.star_rating, rating, rating);
        holder.courseRatingTextView.setText(ratingString);
    }

    @NonNull
    @Override
    public RatingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new RatingHolder(view);
    }


    class RatingHolder extends RecyclerView.ViewHolder{
        private final TextView courseNameTextView;
        private final TextView courseRatingTextView;
        private final TextView instructorTextView;

        RatingHolder(View itemView) {
            super(itemView);
            courseNameTextView = itemView.findViewById(R.id.courseNameTextView);
            courseRatingTextView = itemView.findViewById(R.id.courseRatingTextView);
            instructorTextView = itemView.findViewById(R.id.courseInstructorTextView);

        }
    }// end of class


}// end of main class
