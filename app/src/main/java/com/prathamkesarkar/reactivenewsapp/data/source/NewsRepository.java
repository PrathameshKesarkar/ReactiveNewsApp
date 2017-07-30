package com.prathamkesarkar.reactivenewsapp.data.source;

import com.prathamkesarkar.reactivenewsapp.data.source.local.LocalDataSource;
import com.prathamkesarkar.reactivenewsapp.data.source.remote.Article;
import com.prathamkesarkar.reactivenewsapp.data.source.remote.RemoteDataSource;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by patty on 21/07/17.
 */

public class NewsRepository implements NewsDataSource {


    RemoteDataSource remoteSource;

    @Inject
    public NewsRepository(RemoteDataSource remoteSource) {
        this.remoteSource = remoteSource;
    }

    @Override
    public Observable<List<Article>> getArticles(String source) {
        LocalDataSource dataSource = new LocalDataSource();

        Observable<List<Article>> databaseArticles = dataSource.getArticles(source).subscribeOn(Schedulers.computation());
        Observable<List<Article>> remoteArticles = remoteSource.getArticles(source).subscribeOn(Schedulers.io());
        return Observable.merge(databaseArticles,remoteArticles.map(dataSource::saveArticle))
                .observeOn(Schedulers.computation());

    }
}
