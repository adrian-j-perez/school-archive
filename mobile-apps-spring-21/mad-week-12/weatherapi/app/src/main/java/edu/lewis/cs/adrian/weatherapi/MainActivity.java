 package edu.lewis.cs.adrian.weatherapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.android.volley.VolleyError;

import java.util.List;

 public class MainActivity extends AppCompatActivity {

    private final static String TAG = MainActivity.class.getSimpleName();
    private EditText mEditTextZipCode;
    private Button mButtonEnter;
    private List<Weather> weatherList;
    private String zipcode;

    private final WeatherFetcher.OnWeatherReceivedListener mFetchWeatherListener = new WeatherFetcher.OnWeatherReceivedListener() {
        @Override
        public void onWeatherReceived(List<Weather> weathers) {
            weatherList = weathers;//getting data

        }

        @Override
        public void onErrorResponse(VolleyError error) {
             Log.d(TAG, error.toString());
         }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mEditTextZipCode = findViewById(R.id.edit_text_zipcode);

        mEditTextZipCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                zipcode = s.toString();
                Log.d(TAG, "onTextChanged: " + zipcode);

            }

            @Override
            public void afterTextChanged(Editable s) {
                //after the user puts zip-code it will start building the link-json
                startAPI();

            }

        });

        mButtonEnter = findViewById(R.id.button_enter);
        mButtonEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent detailIntent = new Intent( MainActivity.this, DetailActivity.class);
                detailIntent.putExtra("code", zipcode);
                detailIntent.putExtra("weather", weatherList.get(0));
                startActivity(detailIntent);

            }
        });

        //zipcode = "zipcode"; // testing
        //WeatherFetcher weatherFetcher = new WeatherFetcher(MainActivity.this, zipcode);
        //weatherFetcher.fetchWeather(mFetchWeatherListener);


    }//end of onCreate

     public void startAPI(){
        //Log.d(TAG, "zipcode---------> " + zipcode ); //debugging ouput
        WeatherFetcher weatherFetcher = new WeatherFetcher(MainActivity.this, zipcode);
        weatherFetcher.fetchWeather(mFetchWeatherListener);

     }


}//end of class