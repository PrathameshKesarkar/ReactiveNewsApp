package com.prathamkesarkar.reactivenewsapp.data.source.remote;

import java.util.List;

/**
 * Created by patty on 21/07/17.
 */

public class ArticleResponse {
    private String status;
    private String source;
    private String sortBy;
    private List<Article> articles;

    public String getStatus() {
        return status;
    }

    public String getSource() {
        return source;
    }

    public String getSortBy() {
        return sortBy;
    }

    public List<Article> getArticleList() {
        return articles;
    }
}
