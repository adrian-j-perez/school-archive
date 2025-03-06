package edu.lewis.cs.adrian.websiterating;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebsiteRating mWebsiteRating;
    private EditText mWebsiteNameEditText;
    private EditText mReasonEditText;
    private RadioGroup mWouldYouRadioGroup;
    private CheckBox mAgreeCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init the obj  so it can hold the user data
        mWebsiteRating = new WebsiteRating();

        mWebsiteNameEditText = findViewById(R.id.websiteNameEditText);
        mWebsiteNameEditText.addTextChangedListener(new NameTextListener(mWebsiteNameEditText));

        mReasonEditText = findViewById(R.id.reasonNameEditText);
        mReasonEditText.addTextChangedListener(new NameTextListener(mReasonEditText));

        mWouldYouRadioGroup = findViewById(R.id.wouldYouRadioGroup);

        mAgreeCheckBox = (CheckBox) findViewById(R.id.agreeCheckBox);

    }

    public void submitClickButton(View v){
        if (mAgreeCheckBox.isChecked()) {
            //user has to check the box to 'submit'
            int checkId = mWouldYouRadioGroup.getCheckedRadioButtonId();
            if (checkId == R.id.shareRadioButton) {
                mWebsiteRating.setWouldYou("Share");
            } else if (checkId == R.id.bookmarkRadioButton) {
                mWebsiteRating.setWouldYou("Bookmark");
            } else if (checkId == R.id.notVisitRadioButton) {
                mWebsiteRating.setWouldYou("Not visit again");
            }
            Toast.makeText(getApplicationContext(), mWebsiteRating.toString(), Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(getApplicationContext(), R.string.check_the_box, Toast.LENGTH_SHORT).show();
        }
    }//end <submitClickButton>


    //adding class for editText (user input)
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


}//end of mainActivity class