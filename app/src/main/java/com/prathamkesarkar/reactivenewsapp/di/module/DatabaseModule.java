package com.prathamkesarkar.reactivenewsapp.di.module;

import com.prathamkesarkar.reactivenewsapp.data.source.local.LocalDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by patty on 30/07/17.
 */

@Module
public class DatabaseModule {

    @Provides
    @Singleton
    LocalDataSource provideDbSource(){
        return new LocalDataSource();
    }
}
