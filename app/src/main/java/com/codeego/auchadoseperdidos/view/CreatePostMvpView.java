package com.codeego.auchadoseperdidos.view;

/**
 * Created by Gustavo on 2/11/16.
 */
public interface CreatePostMvpView extends MvpView{
    void onPostCreated();
    void onPostFailed(String errorMessage);
}
