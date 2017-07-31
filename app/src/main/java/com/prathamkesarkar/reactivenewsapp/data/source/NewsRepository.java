package com.prathamkesarkar.reactivenewsapp.data.source;

import com.prathamkesarkar.reactivenewsapp.data.source.local.LocalDataSource;
import com.prathamkesarkar.reactivenewsapp.data.source.remote.Article;
import com.prathamkesarkar.reactivenewsapp.data.source.remote.RemoteDataSource;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by patty on 21/07/17.
 */

public class NewsRepository implements NewsDataSource {


    RemoteDataSource remoteSource;
    LocalDataSource dataSource;

    @Inject
    public NewsRepository(RemoteDataSource remoteSource, LocalDataSource dataSource) {
        this.remoteSource = remoteSource;
        this.dataSource = dataSource;

    }

    @Override
    public Observable<List<Article>> getArticles(String source) {

           return Observable.merge(dataSource.getArticles(source),
                    remoteSource.getArticles(source).map(dataSource::saveArticle))
                    .subscribeOn(Schedulers.computation())
                    .cache();

    }
}
