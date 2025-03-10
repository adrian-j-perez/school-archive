package edu.lewis.cs.adrian.firebasewebsiterating;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * this is where the user will be able to add data
 * */

public class MainActivity extends AppCompatActivity {

    private WebsiteRating mWebsiteRating; // the model
    private EditText mWebsiteNameEditText;
    private EditText mReasonEditText;
    private RadioGroup mWouldYouRadioGroup;
    private CheckBox mAgreeCheckBox;
    private RatingBar mRatingBar;
    private Button mSubmitButton;

    //for fire base
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebsiteRating = new WebsiteRating();

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("website_rating");
        // the name of the table

        mWebsiteNameEditText = findViewById(R.id.websiteNameEditText);
        mWebsiteNameEditText.addTextChangedListener(new NameTextListener(mWebsiteNameEditText));

        mReasonEditText = findViewById(R.id.reasonNameEditText);
        mReasonEditText.addTextChangedListener(new NameTextListener(mReasonEditText));

        mWouldYouRadioGroup = findViewById(R.id.wouldYouRadioGroup);
        mWouldYouRadioGroup.setOnCheckedChangeListener( new RadioGroupChangedListener());

        mAgreeCheckBox = findViewById(R.id.agreeCheckBox);

        mRatingBar = findViewById(R.id.ratingBar);
        mRatingBar.setOnRatingBarChangeListener(new RatingChangedListener());

        mSubmitButton = findViewById(R.id.submitButton);
        mSubmitButton.setOnClickListener(new SubmitClickListener());



    }//end of onCreate method







    // classes for the listeners //

    //class for editText (user input)
    private class NameTextListener implements TextWatcher {
        private final View mEditText;

        public NameTextListener (View mEditText){
            this.mEditText = mEditText;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if(mEditText == mWebsiteNameEditText){
                //s.toString will return what is in the textbox
                mWebsiteRating.setRatingName(s.toString());
            }else if(mEditText == mReasonEditText){
                mWebsiteRating.setRatingReason(s.toString());
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }//end of class <NameTextListener>


    private class SubmitClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            //user has to check the box to cont.
            if (mAgreeCheckBox.isChecked()) {
                mDatabaseReference.push().setValue(mWebsiteRating);
                finish();
            }else{
                Toast.makeText(getApplicationContext(), R.string.i_agree_checkbox, Toast.LENGTH_SHORT).show();
            }

        }
    }


    private class RadioGroupChangedListener implements RadioGroup.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.shareRadioButton) {
                mWebsiteRating.setRatingWouldYou("Share");
                mWebsiteRating.setRadioButtonId(R.id.shareRadioButton);
            } else if (checkedId == R.id.bookmarkRadioButton) {
                mWebsiteRating.setRatingWouldYou("Bookmark");
                mWebsiteRating.setRadioButtonId(R.id.bookmarkRadioButton);
            } else if (checkedId == R.id.notVisitRadioButton) {
                mWebsiteRating.setRatingWouldYou("Not visit again");
                mWebsiteRating.setRadioButtonId(R.id.notVisitRadioButton);

            }
            //i am save the radio button id so it be set when look back
            //think of a better way id # could change
        }
    }


    private class RatingChangedListener implements RatingBar.OnRatingBarChangeListener{
        @Override
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            mWebsiteRating.setRatingNumber((int)rating);
        }
    }


}//end of <MainActivity>