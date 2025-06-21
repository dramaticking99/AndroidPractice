package com.example.newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class ArticleDetailsActivity extends AppCompatActivity {

    ImageView detailImage;
    TextView detailTitle, detailArticle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_details);

        detailImage = findViewById(R.id.detailImage);
        detailTitle = findViewById(R.id.detailTitle);
        detailArticle = findViewById(R.id.detailArticle);

        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra("image");
        String title = intent.getStringExtra("title");
        String article = intent.getStringExtra("article");

        Glide.with(this)
                .load(imageUrl)
                .centerCrop()
                .into(detailImage);

        detailTitle.setText(title);
        detailArticle.setText(article);
    }
}
