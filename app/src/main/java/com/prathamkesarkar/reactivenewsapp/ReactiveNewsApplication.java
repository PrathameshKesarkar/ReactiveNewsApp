package com.prathamkesarkar.reactivenewsapp;

import android.app.Application;

import com.prathamkesarkar.reactivenewsapp.data.source.local.NewsModule;
import com.prathamkesarkar.reactivenewsapp.di.component.ApplicationComponent;
import com.prathamkesarkar.reactivenewsapp.di.component.DaggerApplicationComponent;
import com.prathamkesarkar.reactivenewsapp.di.module.RepoModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by patty on 22/07/17.
 */

public class ReactiveNewsApplication extends Application {

    ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .repoModule(new RepoModule())
                .build();

        Realm.init(this);

        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("news.realm")
                .schemaVersion(1)
                .modules(new NewsModule())
                .build();
       // if (BuildConfig.DEBUG)
           // Realm.deleteRealm(configuration);


        Realm.setDefaultConfiguration(configuration);
    }

    public ApplicationComponent getComponent() {
        return component;
    }
}
