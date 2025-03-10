package edu.lewis.cs.adrian.firebasewebsiterating;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


public class RatingAdapter extends FirebaseRecyclerAdapter<WebsiteRating, RatingAdapter.RatingHolder> {
    private Context context; // this is using the android  context

    //default con
    public RatingAdapter(@NonNull FirebaseRecyclerOptions<WebsiteRating> options, Context context) {
        super(options);
    }

    //setting the values to the view
    @Override
    protected void onBindViewHolder(@NonNull RatingHolder holder, int position, @NonNull WebsiteRating model) {
        holder.ratingNameTextView.setText(model.getRatingName());
        holder.buttonTextView.setText(model.getRatingWouldYou());

        int rating = model.getRatingNumber();
        String ratingString = context.getResources().getQuantityString(R.plurals.star_rating, rating, rating);
        holder.starsTextView.setText(ratingString);
     }

    @NonNull
    @Override
    public RatingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_row, parent, false);
        return new RatingHolder(view);
    }


    class RatingHolder extends RecyclerView.ViewHolder{
        private final TextView ratingNameTextView;
        private final TextView buttonTextView;
        private final TextView starsTextView;

        RatingHolder(View itemView) {
            super(itemView);
            ratingNameTextView = itemView.findViewById(R.id.websiteNameTextView);
            buttonTextView = itemView.findViewById(R.id.websiteReasonTextView);
            starsTextView = itemView.findViewById(R.id.ratingStarsTextView);

        }
    }// end of class


}// end of main class