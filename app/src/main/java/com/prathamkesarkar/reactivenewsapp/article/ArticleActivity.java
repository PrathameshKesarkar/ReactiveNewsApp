package com.prathamkesarkar.reactivenewsapp.article;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.prathamkesarkar.reactivenewsapp.R;
import com.prathamkesarkar.reactivenewsapp.ReactiveNewsApplication;
import com.prathamkesarkar.reactivenewsapp.data.source.NewsRepository;
import com.prathamkesarkar.reactivenewsapp.data.source.remote.Article;

import java.util.List;

import javax.inject.Inject;

public class ArticleActivity extends AppCompatActivity implements ArticleContract.View,SwipeRefreshLayout.OnRefreshListener {


    private ArticleContract.Presenter presenter;

    @Inject
    NewsRepository repository;

    private SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ReactiveNewsApplication)getApplication()).getComponent().inject(this);
        setContentView(R.layout.activity_main);

        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
        refreshLayout.setOnRefreshListener(this);

        presenter = new ArticlePresenter(repository);
        presenter.bind(this);
        refreshLayout.setRefreshing(true);
        presenter.loadArticles();

    }

    @Override
    public void setPresenter(ArticleContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showArticles(List<Article> articles) {
        if(refreshLayout.isRefreshing()){
            refreshLayout.setRefreshing(false);
        }

        for(Article article:articles)
        Log.d(Article.class.getSimpleName(),article.getTitle());
    }


    @Override
    public void onRefresh() {
        refreshLayout.setRefreshing(true);
        presenter.loadArticles();
    }


}
