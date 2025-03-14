package edu.lewisu.cs.example.networklab;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = MainActivity.class.getSimpleName();
    private final static String  URL_STRING = "http://cs.lewisu.edu/<path-to-output-json>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goButtonClick(View v) {
        DonwloadData donwloadData = new DonwloadData(this); // going to be doing this on the backgroud thread
        donwloadData.execute(URL_STRING);


    }//end of method button

    //note the Async is being deprcated
    private static class DonwloadData extends AsyncTask<String, Void, String>{

        private WeakReference<MainActivity> activityWeakReference;// makeing this a weak reference is it can be garbage collected

        public DonwloadData(MainActivity context){
            activityWeakReference = new WeakReference<>(context);

        }

        @Override
        protected String doInBackground(String... strings) {

            String jsonData = "";

            HttpURLConnection urlConnection=null;

            try{
                URL url =  new URL(strings [0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                Scanner scan = new Scanner(in);
                scan.useDelimiter("\\A"); // end of file as dem

                boolean hasInput = scan.hasNext();
                if(hasInput){
                    jsonData = scan.next();
                    Log.d(TAG, jsonData.toString());
                }else{ //when there is a error
                    return activityWeakReference.get().getString(R.string.download_error);

                }

            }catch(Exception ex){
                Log.d(TAG, ex.toString());
            }
            finally{
                if(urlConnection!=null)
                    urlConnection.disconnect();
            }



            String title;
            String isbn;
            String row;
            StringBuilder results =  new StringBuilder();
            try{
                JSONArray jsonArray =  new JSONArray(jsonData);
                for (int i=0; i < jsonArray.length(); i++){
                    JSONObject book = jsonArray.getJSONObject(i);
                    title = book.getString("title");
                    isbn = book.getString("isbn");
                    row = isbn + "/t" + title + "\n";
                    results.append(row);

                }

            }catch(Exception ex){
                Log.d(TAG, ex.toString());
                return  activityWeakReference.get().getResources().getString(R.string.data_error);
            }

            return results.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            MainActivity mainActivity = activityWeakReference.get();
            if(mainActivity == null || mainActivity.isFinishing()){
                return;
            }
            TextView displayTextView = mainActivity.findViewById(R.id.displayTextView);
            displayTextView.setText(s); //display the data from the website

        }
    }//end of class download



}//end of class
