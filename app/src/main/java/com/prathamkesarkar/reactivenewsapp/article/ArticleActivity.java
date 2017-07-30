package com.prathamkesarkar.reactivenewsapp.article;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.prathamkesarkar.reactivenewsapp.R;
import com.prathamkesarkar.reactivenewsapp.ReactiveNewsApplication;
import com.prathamkesarkar.reactivenewsapp.data.source.remote.Article;
import com.prathamkesarkar.reactivenewsapp.data.source.NewsRepository;

import java.util.List;

import javax.inject.Inject;

public class ArticleActivity extends AppCompatActivity implements ArticleContract.View {


    private ArticleContract.Presenter presenter;

    @Inject
    NewsRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ReactiveNewsApplication)getApplication()).getComponent().inject(this);

        setContentView(R.layout.activity_main);
        presenter = new ArticlePresenter(repository);
        presenter.bind(this);
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
        if(articles.isEmpty()){
            Log.d(Article.class.getSimpleName(),"List is empty");
        }

        for(Article article:articles)
        Log.d(Article.class.getSimpleName(),article.getTitle());
    }
}
