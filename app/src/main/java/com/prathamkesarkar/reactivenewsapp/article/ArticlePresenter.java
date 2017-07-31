package com.prathamkesarkar.reactivenewsapp.article;

import android.util.Log;

import com.prathamkesarkar.reactivenewsapp.data.source.NewsRepository;
import com.prathamkesarkar.reactivenewsapp.data.source.remote.Article;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by patty on 20/07/17.
 */

public class ArticlePresenter implements ArticleContract.Presenter {

    ArticleContract.View view;
    CompositeDisposable disposable;
    Observable<List<Article>> tempObservable;
    private NewsRepository repository;

    private ArticlePresenter() {

    }

    public ArticlePresenter(NewsRepository repository) {
        this.repository = repository;
    }

    @Override
    public void bind(ArticleContract.View view) {
        this.view = view;
        view.setPresenter(this);
        disposable = new CompositeDisposable();
    }


    @Override
    public void unBind() {
        disposable.clear();
        view.setPresenter(null);
        this.view = null;
    }


    @Override
    public void loadArticles() {

        if(tempObservable==null)
            tempObservable =repository.getArticles("techcrunch")
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnNext(view::showArticles)
                    .doOnError(throwable -> Log.d(Article.class.getSimpleName(), throwable.toString()));
        disposable.add(tempObservable.subscribe());

    }

}
