package edu.lewis.cs.adrian.databaserating;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_RATING_ID = "ratingid";
    private Rating mWebsiteRating; // the model
    private EditText mWebsiteNameEditText;
    private EditText mReasonEditText;
    private RadioGroup mWouldYouRadioGroup;
    private CheckBox mAgreeCheckBox;
    private RatingBar mRatingBar;
    private Button mSubmitButton;

    private SharedPreferences mPreferences;
    private RatingRepo mRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRepo = new RatingRepo(getApplication());

        mWebsiteRating =  new Rating();

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


        //todo the preferences .. add more ??
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        //going tin the file
        String num = getString(R.string.pref_rating_key);
        String ratingString = mPreferences.getString(num,"0");
        int default_rating = Integer.parseInt(ratingString);

        mRatingBar.setRating(default_rating);

        //checking to see if there is a rating object being sent
        int id = getIntent().getIntExtra(EXTRA_RATING_ID,0);
        if(id != 0){
            mWebsiteRating = mRepo.getRating(id);
        }


        if(mWebsiteRating.getId() != 0) {
            mWebsiteNameEditText.setText(mWebsiteRating.getRatingName());
            mReasonEditText.setText(mWebsiteRating.getRatingReason());
            mWouldYouRadioGroup.check(mWebsiteRating.getRadioButtonId());
            mAgreeCheckBox.setChecked(true);
            mRatingBar.setRating(mWebsiteRating.getRatingNumber());
            mSubmitButton.setText(R.string.update);
            mSubmitButton.setOnClickListener(new onUpdateButtonClick());
        }else{
            mWebsiteRating =  new Rating();
           // mAgreeCheckBox.setChecked(false);
            mSubmitButton.setText(R.string.add);
            mSubmitButton.setOnClickListener( new SubmitClickListener());
        }

    }// end of onCreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if( item.getItemId() == R.id.action_delete ){
            mRepo.deleteRating(mWebsiteRating.getId());
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

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
                mRepo.insertRatings(mWebsiteRating);
                Intent intent =  new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }else{
                Toast.makeText(getApplicationContext(), R.string.i_agree_checkbox, Toast.LENGTH_SHORT).show();
            }

        }
    }


    private class onUpdateButtonClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            // update the item
            mRepo.updateRating(mWebsiteRating);
            finish();
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
        }
    }


    private class RatingChangedListener implements RatingBar.OnRatingBarChangeListener{
        @Override
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            mWebsiteRating.setRatingNumber((int)rating);
        }
    }


}//end of <MainActivity> class