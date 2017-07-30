package com.prathamkesarkar.reactivenewsapp.di.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.prathamkesarkar.reactivenewsapp.BuildConfig;
import com.prathamkesarkar.reactivenewsapp.data.source.remote.NewsApiService;
import com.prathamkesarkar.reactivenewsapp.data.source.remote.RemoteDataSource;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;
import static okhttp3.logging.HttpLoggingInterceptor.Level.HEADERS;

/**
 * Created by patty on 22/07/17.
 */

@Module
public class NetworkModule {

    @Provides
    OkHttpClient.Builder providesOkHttpBuilder(HttpLoggingInterceptor httpInterceptor,Interceptor apiKeyInterceptor){
        return new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60,TimeUnit.SECONDS)
                .addInterceptor(apiKeyInterceptor)
                .addInterceptor(httpInterceptor);
    }

    @Provides
    Retrofit provideRetrofit(OkHttpClient.Builder httpBuilder,Gson gson){
       return new Retrofit.Builder()
                .baseUrl(BuildConfig.API_BASE_URL)
                .client(httpBuilder.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    HttpLoggingInterceptor provideHttpInterceptor(){
        return new HttpLoggingInterceptor()
                .setLevel(HEADERS);
    }

    @Provides
    Interceptor provideParamInterceptor(){
        return chain -> {
            Request original = chain.request();
            HttpUrl originalUrl = original.url();

            HttpUrl modifiedUrl =originalUrl.newBuilder()
                    .addQueryParameter("apiKey", BuildConfig.API_KEY)
                    .build();
            Request modifiedRequest =original.newBuilder()
                    .url(modifiedUrl)
                    .build();
            return chain.proceed(modifiedRequest);
        };
    }

    @Provides
    Gson providesGson(){
        return new GsonBuilder()
                .setLenient()
                .serializeNulls()
                .create();
    }

    @Provides
    NewsApiService provideApiService(Retrofit retrofit){
        return retrofit.create(NewsApiService.class);
    }

    @Provides
    @Singleton
    RemoteDataSource provideRemoteService(NewsApiService apiService){
        return new RemoteDataSource(apiService);
    }
}
