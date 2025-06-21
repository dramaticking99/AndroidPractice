package com.example.newsapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class NewsRelViewAdapter extends RecyclerView.Adapter<NewsRelViewAdapter.ViewHolder> {

    private List<NewsItem> newsList;

    public NewsRelViewAdapter(List<NewsItem> newsList) {
        this.newsList = newsList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgViewNews;
        TextView txtHeadline;
        TextView txtArticle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgViewNews = itemView.findViewById(R.id.imgNewsItem);
            txtHeadline = itemView.findViewById(R.id.txtHeadline);
            txtArticle = itemView.findViewById(R.id.txtArticleNews);
        }
    }

    @NonNull
    @Override
    public NewsRelViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_item, parent, false); // your layout filename
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsRelViewAdapter.ViewHolder holder, int position) {
        NewsItem item = newsList.get(position);

        Glide.with(holder.itemView.getContext())
                .load(item.getImageResUrl())
                .centerCrop()
                .into(holder.imgViewNews);

        holder.txtHeadline.setText(item.getHeadline());
        holder.txtArticle.setText(item.getArticle());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), ArticleDetailsActivity.class);
            intent.putExtra("image", item.getImageResUrl());
            intent.putExtra("title", item.getHeadline());
            intent.putExtra("article", item.getArticle());
            v.getContext().startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }
}
