package edu.lewisu.cs.example.bestseller;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

class BooksFetcher {

    public interface OnBooksReceivedListener{
        void onBooksReceived(List<Book> books);
        void onErrorResponse(VolleyError error);
    }

    //https://developer.nytimes.com/docs/books-product/1/routes/lists.json/get
    private final String BASE_URL = "http://api.nytimes.com/svc/books/v3/lists.json";
    private final String API_KEY = "<enter-api-key>"; // this was given from the service
    private final String TAG = BooksFetcher.class.getSimpleName();
    private final RequestQueue mRequestQueue;

    public BooksFetcher(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);
    }

    public void fetchBooks(final OnBooksReceivedListener listener){
        String url = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter("api-key", API_KEY)
                .appendQueryParameter("list", "hardcover-fiction") // woking as  key pairs
                .build().toString();

        Log.d(TAG, url.toString());

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                // this is some code that we do not really need, but it can show me the process bar//
                try{
                    Thread.sleep(4000);
                }catch (Exception e ){
                    Log.d(TAG, e.toString());
                }

                List<Book> books = parseJsonBooks(response);
                listener.onBooksReceived(books);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onErrorResponse(error);
            }
        });

        mRequestQueue.add(request);
    }

    List<Book> parseJsonBooks(JSONObject jsonObject){
        String title;
        String author;
        String description;
        String amazonUrl;
        int rank;
        int numBooks = 10;
        ArrayList<Book> books = new ArrayList<>();

        try{
            JSONArray bookList = jsonObject.getJSONArray("results");
            if(bookList.length()< 10){
                numBooks = bookList.length();
            }
            for (int i = 0; i<numBooks; i++){
                JSONObject bookObject = bookList.getJSONObject(i);
                amazonUrl = bookObject.getString("amazon_product_url");
                rank = bookObject.getInt("rank");
                JSONObject bookDetail = bookObject.getJSONArray("book_details").getJSONObject(0);

                title = bookDetail.getString("title");
                author = bookDetail.getString("author");
                description = bookDetail.getString("description");
                Book book = new Book(title, author, amazonUrl, description, rank);
                books.add(book);
            }

        } catch (Exception e) {
             Log.d(TAG, e.toString());
        }
        return books;
    }
}
