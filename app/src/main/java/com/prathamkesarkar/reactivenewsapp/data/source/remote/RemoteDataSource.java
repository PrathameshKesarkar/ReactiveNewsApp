package com.prathamkesarkar.reactivenewsapp.data.source.remote;

import android.util.Log;

import com.prathamkesarkar.reactivenewsapp.data.source.NewsDataSource;

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
                .map(ArticleResponse::getArticleList);
    }


}
