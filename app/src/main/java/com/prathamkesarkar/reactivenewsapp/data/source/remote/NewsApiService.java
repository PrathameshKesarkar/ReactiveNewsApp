package com.prathamkesarkar.reactivenewsapp.data.source.remote;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by patty on 21/07/17.
 */

public interface NewsApiService {

    String apiVersion = "v1";

    @GET(apiVersion+"/articles")
    Observable<ArticleResponse> getArticles(@Query("source")String source);
}
