package edu.lewisu.cs.examples.implicitintents;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
/**
 * this is using implicit intent
 * down below are examples of how to use it
 *
 * from searching the web to launching a map
 * */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickOpenWebsite(View v){
        Uri uri = Uri.parse("https://www.pizzahut.com/index.php#/home");// this can be anything
        // change this to something fun
        Intent myIntent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(myIntent);
    }

    public void onClickSearchWeb(View v){
        Intent searchIntent = new Intent(Intent.ACTION_WEB_SEARCH);
        searchIntent.putExtra(SearchManager.QUERY,"pizza hut vs dominos");
        //Pizza-hut is better
        startActivity(searchIntent);
    }

    public void onClickViewContacts(View v){
        Intent contactsIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("content://contacts//people"));//TODO recheck this part
        startActivity(contactsIntent); // this will bring up the contact app
    }

    public void onClickCallPizza(View v){
        Intent callIntent = new Intent(Intent.ACTION_DIAL,
                Uri.parse("tel:8158382294"));
        // the pizza hut on  460 Summit Dr Suite 4-A, Lockport, IL 60441
        startActivity(callIntent);
    }

    public void onClickShareText(View v){
        String textToShare = "Pizza hut is the best kind of pizza to have!";
        String mineType = "text/plain";
        String title = "Sharing Pizza";

        ShareCompat.IntentBuilder
                .from(this)
                .setType(mineType)
                .setChooserTitle(title)
                .setText(textToShare)
                .startChooser();
    }

    public void onClickOpenAddress(View v){
        Uri addressUri = Uri.parse("geo:0,0?q=Pizza+Place+IL");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW);
        mapIntent.setData(addressUri);
        startActivity(mapIntent);
    }

}//end of class