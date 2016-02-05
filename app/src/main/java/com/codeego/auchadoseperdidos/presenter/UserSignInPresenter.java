package com.codeego.auchadoseperdidos.presenter;

import com.codeego.auchadoseperdidos.view.SignInMvpVIew;

/**
 * Created by Gustavo on 2/2/16.
 */
public interface UserSignInPresenter extends Presenter<SignInMvpVIew> {
    void login(String email, String password);
}
