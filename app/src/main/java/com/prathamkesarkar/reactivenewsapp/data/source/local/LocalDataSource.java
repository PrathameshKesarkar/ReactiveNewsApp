package com.prathamkesarkar.reactivenewsapp.data.source.local;

import com.prathamkesarkar.reactivenewsapp.data.source.NewsDataSource;
import com.prathamkesarkar.reactivenewsapp.data.source.remote.Article;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by patty on 25/07/17.
 */

public class LocalDataSource implements NewsDataSource {



    /**
     * Get all the Articles from the Realm and if the result is empty
     * pass the empty ArrayList to the UI.
     * */
    @Override
    public Observable<List<Article>> getArticles(String source) {
        return Observable.create(e -> {
            Realm realm = Realm.getDefaultInstance();
            List<Article> articleList = new ArrayList<>();
            realm.beginTransaction();
            RealmResults<RealmArticle> realmArticles = realm.where(RealmArticle.class).findAll();
            if (!realmArticles.isEmpty())
                for (RealmArticle article : realmArticles) {
                    Article pojoArticle = new Article();
                    pojoArticle.setAuthor(article.getAuthor());
                    pojoArticle.setTitle(article.getTitle());
                    pojoArticle.setUrl(article.getUrl());
                    pojoArticle.setDescription(article.getDescription());
                    pojoArticle.setUrlToImage(article.getArticleImage());
                    pojoArticle.setPublishedAt("null");


                    articleList.add(pojoArticle);
                    pojoArticle = null;
                }
            realmArticles = null;
            realm.close();

            e.onNext(articleList);
            e.onComplete();
        });
    }


    /**
     * This method Save Article into database. also the method checks whether the article exist.
     * If the article exist don't add it into the database and also remove it from the list.
     * */
    public List<Article> saveArticle(List<Article> articleList) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        for (int i=0;i<articleList.size();i++) {
            Article article = articleList.get(i);
            try {
                //query the database to check if the article already exist.
                RealmArticle realmArticle =realm.where(RealmArticle.class).equalTo("title",article.getTitle()).findFirst();
                //Save article only if it doesn't exist.
                if(realmArticle==null) {

                    realmArticle = realm.createObject(RealmArticle.class);
                    realmArticle.setArticleImage(article.getUrlToImage());
                    realmArticle.setAuthor(article.getAuthor());
                    realmArticle.setUrl(article.getUrl());
                    realmArticle.setDescription(article.getDescription());
                    realmArticle.setTitle(article.getTitle());
                    //This makes realm object available for GC
                    realmArticle = null;
                }
                //delete the article from the response.
                else{
                    articleList.remove(i);
                    if(articleList.isEmpty()){
                        articleList = Collections.emptyList();
                    }
                }
            } catch (Exception e) {
                //Cancel the transaction in case of some error and close realm database.
                realm.cancelTransaction();
                realm.close();
            }
        }
        realm.commitTransaction();
        realm.close();
        return articleList;

    }


}
