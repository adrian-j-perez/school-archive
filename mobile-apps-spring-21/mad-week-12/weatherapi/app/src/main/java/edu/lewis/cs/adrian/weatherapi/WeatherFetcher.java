package edu.lewis.cs.adrian.weatherapi;

import android.app.DownloadManager;
import android.app.WallpaperColors;
import android.content.Context;
import android.net.Uri;
import android.renderscript.Sampler;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 *  main website: https://developer.accuweather.com/
 *  <enter-api-key>  - api kry
 *  "http://dataservice.accuweather.com/forecasts/v1/daily/1day/<enter-zip-code>>?apikey=%09<enter-api-key>&details=false&metric=false"
 *
 *  */

public class WeatherFetcher {

    public interface OnWeatherReceivedListener{
        void onWeatherReceived(List<Weather> weathers);
        void onErrorResponse(VolleyError error);
    }

   //private String mZipCode; //= "<test-zip-code>>" ; //user date getting from EditText
    private String BASE_URL; // = "http://dataservice.accuweather.com/forecasts/v1/daily/1day/" + mZipCode + "?";
    private final String API_KEY = "<enter-api-key>";
    private final String TAG = WeatherFetcher.class.getSimpleName();
    private final RequestQueue mRequestQueue;

    public WeatherFetcher(Context context, String zipCode) {
        mRequestQueue = Volley.newRequestQueue(context);
        //mZipCode = zipCode; // to pass the zip code that the user is typing
        BASE_URL = "http://dataservice.accuweather.com/forecasts/v1/daily/1day/" + zipCode + "?" ;
    }


    public void fetchWeather (final OnWeatherReceivedListener listener){
        String url = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter("apikey", API_KEY)
                .appendQueryParameter("details", "false")
                .appendQueryParameter("metric", "false")
                .build().toString();

        Log.d(TAG, url.toString());

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    //Thread.sleep(2000);
                }catch(Exception e){
                    Log.d(TAG, e.toString());
                }

                List<Weather> weatherList = parseJsonWeather(response);
                listener.onWeatherReceived(weatherList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onErrorResponse(error);
            }
        });
        mRequestQueue.add(request);

    }//end of fetchWeather class


    List<Weather> parseJsonWeather(JSONObject jsonObject){
        // i am only getting data for one day //
        int tempHigh;
        int tempLow;
        String date;
        String linkToTemp;
        ArrayList<Weather> weatherArrayList = new ArrayList<>();

        //can add the 5 day forcast here by doing a loop
        try{
            JSONArray weatherList = jsonObject.getJSONArray("DailyForecasts");
            JSONObject weatherObject = weatherList.getJSONObject(0);
            date = weatherObject.getString("Date");

            JSONObject tempList = weatherObject.getJSONObject("Temperature");
            JSONObject min = tempList.getJSONObject("Minimum");

            tempLow = min.getInt("Value");

            JSONObject max =tempList.getJSONObject("Maximum");

            tempHigh = max.getInt("Value");

            linkToTemp = weatherObject.getString("MobileLink");

            Weather weather = new Weather(tempHigh, tempLow, date, linkToTemp);
            weatherArrayList.add(weather);

        }catch(Exception e){
            Log.d(TAG, e.toString());

        }
        return weatherArrayList;

    }

}//end of class
