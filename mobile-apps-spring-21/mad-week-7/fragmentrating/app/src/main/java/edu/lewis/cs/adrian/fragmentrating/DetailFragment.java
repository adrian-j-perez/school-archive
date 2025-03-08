package edu.lewis.cs.adrian.fragmentrating;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {

    private Rating mRating;
    private static final String ARG_RATING_ID = "Rating_Id";

    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment newInstance(int toDoId) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_RATING_ID, toDoId);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int toDoId = 1;

        if (getArguments() != null) {
            toDoId = getArguments().getInt(ARG_RATING_ID);
        }
        RatingDatabase ratingDatabase = RatingDatabase.getInstance(getContext());
        mRating = ratingDatabase.getRating(toDoId);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //finding by id
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        TextView ratingName = view.findViewById(R.id.rating_name);
        RatingBar ratingNum = view.findViewById(R.id.rating_bar);
        TextView ratingReason = view.findViewById(R.id.reason);

        //setting
        ratingName.setText(mRating.getRatingName());
        ratingNum.setRating(mRating.getRatingNumber()); //TODO double check to see if this is right
        ratingReason.setText(mRating.getRatingReason());
        return view;
    }


}