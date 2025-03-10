package edu.lewis.cs.adrian.databaserating;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RatingListAdapter extends RecyclerView.Adapter<RatingListAdapter.RatingHolder>{

    private final LayoutInflater inflater;
    private List<Rating> mRatingList;
    private Context context;

    private final RatingListAdapterOnClickHandler clickHandler;
    public interface RatingListAdapterOnClickHandler{
        void onClick(Rating rating);
    }

    //con method
    public RatingListAdapter(Context context, RatingListAdapterOnClickHandler clickHandler){
        this.context = context;
       inflater = LayoutInflater.from(context);
       this.clickHandler = clickHandler;

    }

    @Override
    public RatingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =  inflater.inflate(R.layout.list_row, parent, false);
        return new RatingHolder(view);
    }

    @Override
    public void onBindViewHolder(RatingHolder holder, int position) {
        //this will fill the row from <list_row.xml>
        if (mRatingList != null){
            Rating ratingObject = mRatingList.get(position);

            holder.mRatingNameTextView.setText(ratingObject.getRatingName());
            holder.mRatingReasonTextView.setText(ratingObject.getRatingReason());

            int num = ratingObject.getRatingNumber();
            String ratingNumToString = context.getResources()
                    .getQuantityString(R.plurals.star_rating, num, num);

            holder.mRatingNumberTextView.setText(ratingNumToString);
            holder.itemView.setTag(ratingObject.getId());
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

    public void setRatingList(List<Rating> mRatingList) {
        this.mRatingList = mRatingList;
        notifyDataSetChanged();
    }


    //  class   //
    class RatingHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView mRatingNameTextView;
        private final TextView mRatingReasonTextView;
        private final TextView mRatingNumberTextView;

        // constructor method
        public RatingHolder(@NonNull View itemView){
            super(itemView);
            mRatingNameTextView = itemView.findViewById(R.id.websiteNameTextView);
            mRatingReasonTextView = itemView.findViewById(R.id.websiteReasonTextView);
            mRatingNumberTextView = itemView.findViewById(R.id.ratingStarsTextView);

            itemView.setOnClickListener(this::onClick);

        }

        @Override
        public void onClick(View v) {
            int adapterPos = getAdapterPosition();
            Rating rating =  mRatingList.get(adapterPos);

            clickHandler.onClick(rating);
        }
    }// end of <RatingHolder> class



}//end of <RatingListAdapter> class
