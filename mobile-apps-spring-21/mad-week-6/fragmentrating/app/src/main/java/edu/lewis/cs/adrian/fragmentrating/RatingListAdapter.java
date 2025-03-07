package edu.lewis.cs.adrian.fragmentrating;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RatingListAdapter extends RecyclerView.Adapter<RatingListAdapter.RatingHolder>{
    private List<Rating> mRatingList;

    public interface RatingListAdapterOnClickHandler{
        void onClick(Rating rating);
    }

    private final RatingListAdapterOnClickHandler mClickHandler;

    // con method
    public RatingListAdapter (RatingListAdapterOnClickHandler mClickHandler){
        this.mClickHandler = mClickHandler;
    }

    //  method here b/c of extends
    @NonNull
    @Override
    public RatingListAdapter.RatingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return  new RatingHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RatingHolder holder, int position) {
        if(mRatingList != null){
            Rating current = mRatingList.get(position);
            holder.mTitleTextView.setText(current.getRatingName());
        }

    }

    //getter and setter
    @Override
    public int getItemCount() {
        if(mRatingList != null){
            return mRatingList.size();
        }
        return 0;
    }

    public void setRatingList (List<Rating> ratingList){ mRatingList = ratingList;}


    class RatingHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        private final TextView mTitleTextView;

        // constructor method
        public RatingHolder(@NonNull View itemView){
            super(itemView);
            mTitleTextView = itemView.findViewById(R.id.title_text_view);
            itemView.setOnClickListener(this);
        }

        public void onClick(View v){
            int adapterPosition  =  getAdapterPosition();
            Rating rating  = mRatingList.get(adapterPosition);
            mClickHandler.onClick(rating);
        }
    }// end of <RatingHolder> class


}//end of class
