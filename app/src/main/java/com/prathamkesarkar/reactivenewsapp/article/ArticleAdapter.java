package com.prathamkesarkar.reactivenewsapp.article;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.prathamkesarkar.reactivenewsapp.R;
import com.prathamkesarkar.reactivenewsapp.data.source.remote.Article;
import com.prathamkesarkar.reactivenewsapp.di.module.GlideApp;

import java.util.List;

/**
 * Created by patty on 12/08/17.
 */

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {

    Context context;
    List<Article> articleList;

    public ArticleAdapter(Context context) {
        this.context = context;
    }


    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_article, parent, false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        Article article = articleList.get(position);
        holder.articleDescriptionTxtView.setText(article.getDescription());
        holder.articleTitleTxtView.setText(article.getTitle());
        GlideApp.with(context)
                .load(article.getUrlToImage())
                .placeholder(R.drawable.dark_gray_background)
                .into(holder.articleImageView);
    }

    @Override
    public int getItemCount() {
        if (articleList.isEmpty())
            return 0;
        else
            return articleList.size();
    }

    public void setArticleList(List<Article> articleList) {
        if (this.articleList == null) {
            this.articleList = articleList;
        } else
            this.articleList.addAll(articleList);
        notifyDataSetChanged();
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder {

        TextView articleDescriptionTxtView;
        TextView articleTitleTxtView;
        ImageView articleImageView;

        public ArticleViewHolder(View itemView) {
            super(itemView);
            articleDescriptionTxtView = (TextView) itemView.findViewById(R.id.article_description);
            articleTitleTxtView = (TextView) itemView.findViewById(R.id.article_title);
            articleImageView = (ImageView) itemView.findViewById(R.id.article_image);
        }
    }
}
