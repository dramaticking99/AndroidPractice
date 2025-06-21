package com.example.newsapp;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView newsRecyclerView;
    private NewsRelViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        newsRecyclerView = findViewById(R.id.newsRelLayout);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Utils.getInstance(this).fetchNews(
                () -> runOnUiThread(() -> {
                    adapter = new NewsRelViewAdapter(Utils.getInstance(this).getNewsList());
                    newsRecyclerView.setAdapter(adapter);
                    System.out.println("News Displayed");
                }),
                () -> System.out.println("Error fetching the News")
        );
    }
}