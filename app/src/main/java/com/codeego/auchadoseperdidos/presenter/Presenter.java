package com.codeego.auchadoseperdidos.presenter;

/**
 * Created by Gustavo on 2/2/16.
 */
public interface Presenter<V> {

    void attachView(V view);

    void detachView();

}
