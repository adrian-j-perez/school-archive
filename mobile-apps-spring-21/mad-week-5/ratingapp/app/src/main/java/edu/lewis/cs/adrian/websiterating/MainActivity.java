package edu.lewis.cs.adrian.websiterating;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private WebsiteRating mWebsiteRating; // the model that  will hold data
    private EditText mWebsiteNameEditText;
    private EditText mReasonEditText;
    private RadioGroup mWouldYouRadioGroup;
    private CheckBox mAgreeCheckBox;
    private RatingBar mRatingBar;
    private Button mSubmitButton;


    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //this is the the model object <WebstieRating.java>
        mWebsiteRating = new WebsiteRating();

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
        mSubmitButton.setOnClickListener( new SubmitClickListener());


        // also i think i want to do the radio button to to set a default
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        //going tin the file
        String num = getString(R.string.pref_rating_key);
        String ratingString = mPreferences.getString(num,"0");
        int default_rating = Integer.parseInt(ratingString);

        mRatingBar.setRating(default_rating); // user can set a default rating for the star


    }

    //class for editText (user input)
    private class NameTextListener implements TextWatcher{
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
                mWebsiteRating.setWebsiteName(s.toString());
            }else if(mEditText == mReasonEditText){
                mWebsiteRating.setReasonForUse(s.toString());
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }//end of class <NameTextListener>


    private class SubmitClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            //a example of how to add things into the preference
                //writing directly
            String nameOfWebsite = mWebsiteRating.getWebsiteName();
            SharedPreferences.Editor editor = mPreferences.edit();
            editor.putString("websiteName", nameOfWebsite);
            editor.apply();
            //end of example // this will write it in the shared_prefs

            //user has to check the box to cont.
            if (mAgreeCheckBox.isChecked()) {

                // sending date from model to welcome screen
                // so is can a toast in that screen
                String websiteName = mWebsiteRating.getWebsiteName();
                String reasonForUse = mWebsiteRating.getReasonForUse();
                String wouldYou = mWebsiteRating.getWouldYou();
                int starsRatingNumber = mWebsiteRating.getStarsRatingNumber();

                Intent returnIntent = new Intent();
                returnIntent.putExtra("websiteName", websiteName);
                returnIntent.putExtra("reason", reasonForUse);
                returnIntent.putExtra("wouldYpu", wouldYou);
                returnIntent.putExtra("rating", starsRatingNumber);

                setResult(RESULT_OK, returnIntent);
                finish();
            }else{
                Toast.makeText(getApplicationContext(), R.string.check_box, Toast.LENGTH_SHORT).show();
            }

        }
    }//end submit

    private class RadioGroupChangedListener implements RadioGroup.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.shareRadioButton) {
                mWebsiteRating.setWouldYou("Share");
            } else if (checkedId == R.id.bookmarkRadioButton) {
                mWebsiteRating.setWouldYou("Bookmark");
            } else if (checkedId == R.id.notVisitRadioButton) {
                mWebsiteRating.setWouldYou("Not visit again");
            }
        }
    }

    private class RatingChangedListener implements RatingBar.OnRatingBarChangeListener{
        @Override
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            mWebsiteRating.setStarsRatingNumber((int)rating);
        }
    }

}//end of MainActivity class