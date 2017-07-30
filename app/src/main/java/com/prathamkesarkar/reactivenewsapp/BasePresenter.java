package com.prathamkesarkar.reactivenewsapp;

/**
 * Created by patty on 20/07/17.
 */

public interface  BasePresenter<V> {


    void bind(V view);


    void unBind();


}
