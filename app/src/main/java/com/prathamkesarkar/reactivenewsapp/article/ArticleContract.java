package com.prathamkesarkar.reactivenewsapp.article;

import com.prathamkesarkar.reactivenewsapp.BasePresenter;
import com.prathamkesarkar.reactivenewsapp.BaseView;
import com.prathamkesarkar.reactivenewsapp.data.source.remote.Article;

import java.util.List;

/**
 * Created by patty on 20/07/17.
 */

public interface ArticleContract {


    interface Presenter extends BasePresenter<View> {
        void loadArticles();
    }

    interface View extends BaseView<Presenter> {
        void showMessage(String message);
        void showArticles(List<Article> articles);
    }

}
