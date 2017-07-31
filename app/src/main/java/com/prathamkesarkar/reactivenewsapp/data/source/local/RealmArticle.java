package com.prathamkesarkar.reactivenewsapp.data.source.local;


import java.util.Date;

import io.realm.RealmModel;
import io.realm.annotations.RealmClass;

/**
 * Created by patty on 25/07/17.
 */

@RealmClass
public class RealmArticle implements RealmModel {

    private String description;
    private String author;
    private String title;
    private String url;
    private String articleImage;
    private Date published;
    private Date fetchedAt;

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getArticleImage() {
        return articleImage;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setArticleImage(String articleImage) {
        this.articleImage = articleImage;
    }

    public Date getPublished() {
        return published;
    }

    public void setPublished(Date published) {
        this.published = published;
    }

    public Date getFetchedAt() {
        return fetchedAt;
    }

    public void setFetchedAt(Date fetchedAt) {
        this.fetchedAt = fetchedAt;
    }
}
