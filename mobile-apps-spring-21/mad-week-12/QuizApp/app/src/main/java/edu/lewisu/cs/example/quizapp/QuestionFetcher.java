package edu.lewisu.cs.example.quizapp;

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

public class QuestionFetcher {

    public interface OnQuestionReceivedListener {
        void onQuestionReceived(Question question);
        void onErrorResponse(VolleyError error);
    }

    //using a api to help generate this link for the triva question
    private static final String BASE_URL = "https://opentdb.com/api.php?amount=1&category=18&type=boolean";
    private final String TAG = QuestionFetcher.class.getSimpleName();

    private final RequestQueue mRequestQueue;

    public QuestionFetcher(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);
    }

    public void fetchQuestion(final OnQuestionReceivedListener listener) {
        String url = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter("amount", "1")
                .appendQueryParameter("category", "18")
                .appendQueryParameter("type", "boolean")
                .build().toString();
        Log.d("QuizFetcher", url);

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Question question = parseJsonQuestion(response);
                listener.onQuestionReceived(question);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onErrorResponse(error);
            }
        });

        mRequestQueue.add(objectRequest);

    }//end of method

    //the Question class came from us that is our model class
    private Question parseJsonQuestion(JSONObject json) {
        Question question = null;
        try {
            JSONArray questions = json.getJSONArray("results");
            JSONObject questionObject = questions.getJSONObject(0);
            String questionText  = questionObject.getString("question");
            boolean correctAnswer = Boolean.parseBoolean(questionObject.getString("correct_answer"));
            question = new Question(questionText, correctAnswer);

        }
        catch (Exception e) {
            Log.e(TAG, "One or more fields not found in the JSON data");
        }

        return question;
    }

}
