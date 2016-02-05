package com.codeego.auchadoseperdidos.presenter;

import com.codeego.auchadoseperdidos.view.SignUpMvpView;

/**
 * Created by Gustavo on 2/2/16.
 */
public interface UserSignUpPresenter extends Presenter<SignUpMvpView> {

    void createUser(String email, String password, String name);
}
