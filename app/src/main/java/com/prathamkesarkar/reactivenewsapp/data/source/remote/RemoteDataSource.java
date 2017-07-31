package com.prathamkesarkar.reactivenewsapp.data.source.remote;

import android.util.Log;

import com.prathamkesarkar.reactivenewsapp.data.source.NewsDataSource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by patty on 21/07/17.
 */

public class RemoteDataSource implements NewsDataSource {

    NewsApiService apiService;
    @Inject
    public RemoteDataSource(NewsApiService service){
        apiService=service;
    }

    @Override
    public Observable<List<Article>> getArticles(String source) {
        Log.d("RemoteSource","Network Started");
        return apiService.getArticles(source)
                .map(ArticleResponse::getArticleList)
                .map(this::addDate);

    }


    public List<Article> addDate(List<Article> articles) {
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

        for (Article article :articles) {
            try {
                article.setPublishedDate(sdf.parse(article.getPublishedAt()));
                sdf.format(currentDate);
                article.setFetchedDate(sdf.getCalendar().getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return articles;
    }
}
