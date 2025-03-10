package edu.lewisu.cs.howardcy.courserating;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class CourseRatingListAdapter extends RecyclerView.Adapter<CourseRatingListAdapter.RatingHolder>{

    public interface ListAdapterOnClickHandler{
        void onClick(CourseRating courseRating);
    }

    private final ListAdapterOnClickHandler click;

    CourseRatingListAdapter(Context context, ListAdapterOnClickHandler clickHandler){
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.click = clickHandler;

    }

    class RatingHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView courseNameTextView;
        private final TextView courseRatingTextView;
        private final TextView instructorTextView;

        RatingHolder(View itemView) {
            super(itemView);
            courseNameTextView = itemView.findViewById(R.id.courseNameTextView);
            courseRatingTextView = itemView.findViewById(R.id.courseRatingTextView);
            instructorTextView = itemView.findViewById(R.id.courseInstructorTextView);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();

            CourseRating rating = ratings.get(adapterPosition);
            click.onClick(rating);

        }


    }// end of class <RatingHolder>

    private final LayoutInflater inflater;
    private List<CourseRating> ratings;
    private Context context;



    @Override
    public RatingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =  inflater.inflate(R.layout.list_row, parent, false);
        return new RatingHolder(view);
    }

    @Override
    public void onBindViewHolder(RatingHolder holder, int position) {
        if (ratings != null){
            CourseRating ratingObject = ratings.get(position);

            holder.courseNameTextView.setText(ratingObject.getCourseName());
            holder.instructorTextView.setText(ratingObject.getInstructorName());

            int rating = ratingObject.getRating();
            String ratingString = context.getResources().getQuantityString(R.plurals.star_rating, rating, rating);
            holder.courseRatingTextView.setText(ratingString);
            holder.itemView.setTag(ratingObject.getId()); // ths will let us be able to do swap to del
        }

    }

    @Override
    public int getItemCount() {
        if(ratings != null){
            return ratings.size();
        }
        return 0;
    }

    void setRatings(List<CourseRating> ratings){
        this.ratings = ratings;
        notifyDataSetChanged();
    }
}
