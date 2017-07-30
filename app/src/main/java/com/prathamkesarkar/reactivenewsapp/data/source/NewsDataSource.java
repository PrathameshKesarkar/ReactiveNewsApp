package com.prathamkesarkar.reactivenewsapp.data.source;

import com.prathamkesarkar.reactivenewsapp.data.source.remote.Article;

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by patty on 21/07/17.
 */

public interface NewsDataSource {

    Observable<List<Article>> getArticles(String source);
}
