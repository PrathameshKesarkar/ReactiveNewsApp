package com.prathamkesarkar.reactivenewsapp.di.module;

import com.prathamkesarkar.reactivenewsapp.data.source.NewsRepository;
import com.prathamkesarkar.reactivenewsapp.data.source.local.LocalDataSource;
import com.prathamkesarkar.reactivenewsapp.data.source.remote.RemoteDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by patty on 22/07/17.
 */

@Module
public class RepoModule {

    @Provides
    @Singleton
    NewsRepository provideRepository(RemoteDataSource remoteSource, LocalDataSource dataSource){
        return new NewsRepository(remoteSource,dataSource);
    }
}
