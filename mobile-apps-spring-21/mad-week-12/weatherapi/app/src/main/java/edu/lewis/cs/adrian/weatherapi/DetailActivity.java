package edu.lewis.cs.adrian.weatherapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    private Weather mWeather;
    private String zipcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        mWeather = intent.getParcelableExtra("weather");
        zipcode = intent.getStringExtra("code");

        if(mWeather !=  null){
            TextView tempHigh = findViewById(R.id.temp_high);
            tempHigh.setText("Temperature High: " + mWeather.getTempHigh());

            TextView tempLow = findViewById(R.id.temp_low);
            tempLow.setText("Temperature Low: " + mWeather.getTempLow());

            TextView textViewZipcode = findViewById(R.id.zip_code);
            textViewZipcode.setText("Zip-Code: " + zipcode);

            TextView date = findViewById(R.id.day);
            String parseDate = mWeather.getDate().substring(0,10);

            date.setText("Date: " + parseDate);
            //i could add time I if want

            TextView link = findViewById(R.id.link);
            link.setText(mWeather.getLinkToTemp());
        }



    }//end of onCreate



}//end of class