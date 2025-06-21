package com.example.newsapp;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    private static Utils instance;
    private final List<NewsItem> newsList = new ArrayList<>();
    private RequestQueue requestQueue;

    private static final String API_KEY = "def8890eff66dbdb2f004bd43e4328d4";
    private static final String GNEWS_URL = "https://gnews.io/api/v4/top-headlines?lang=en&country=in&max=50&apikey=" + API_KEY;

    private Utils(Context context) {
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static synchronized Utils getInstance(Context context) {
        if (instance == null) {
            instance = new Utils(context);
        }
        return instance;
    }

    public List<NewsItem> getNewsList() {
        return newsList;
    }

    public void fetchNews(@NonNull Runnable onComplete, @NonNull Runnable onError) {
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, GNEWS_URL, null,
                response -> {
                    try {
                        JSONArray articles = response.getJSONArray("articles");
                        newsList.clear();

                        for (int i = 0; i < articles.length(); i++) {
                            JSONObject article = articles.getJSONObject(i);
                            String title = article.optString("title", "No Title");
                            String description = article.optString("content", "");
                            String imageUrl = article.optString("image", ""); // GNews uses "image"

                            newsList.add(new NewsItem(imageUrl, title, description));
                        }

                        onComplete.run();
                    } catch (Exception e) {
                        System.out.println("Parsing error");
                        onError.run();
                    }
                },
                error -> {
                    System.out.println("Parsing error");
                    onError.run();
                });

        requestQueue.add(jsonRequest);
    }
}