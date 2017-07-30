package com.prathamkesarkar.reactivenewsapp.di.component;

import com.prathamkesarkar.reactivenewsapp.article.ArticleActivity;
import com.prathamkesarkar.reactivenewsapp.di.module.NetworkModule;
import com.prathamkesarkar.reactivenewsapp.di.module.RepoModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by patty on 22/07/17.
 */

@Singleton
@Component(modules = {RepoModule.class,NetworkModule.class})
public interface ApplicationComponent {
    void inject(ArticleActivity target);
}
