package com.prathamkesarkar.reactivenewsapp.article;

import android.util.Log;

import com.prathamkesarkar.reactivenewsapp.data.source.NewsRepository;
import com.prathamkesarkar.reactivenewsapp.data.source.remote.Article;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by patty on 20/07/17.
 */

public class ArticlePresenter implements ArticleContract.Presenter {

    ArticleContract.View view;
    CompositeDisposable disposable;
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

        disposable.add(repository.getArticles("techcrunch")
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(view::showArticles)
                .doOnError(throwable -> {
                    Log.d(Article.class.getSimpleName(),throwable.toString());
                })
                .subscribe());
    }

}
