package com.codeego.auchadoseperdidos.presenter;

import android.util.Log;

import com.codeego.auchadoseperdidos.utils.Constants;
import com.codeego.auchadoseperdidos.view.SignInMvpVIew;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

/**
 * Created by Gustavo on 2/2/16.
 */
public class UserSignInPresenterFB implements UserSignInPresenter {

    private static final String TAG = UserSignInPresenterFB.class.getSimpleName();
    private SignInMvpVIew signInMvpVIew;

    @Override
    public void login(String email, String password) {
        Firebase ref = new Firebase(Constants.FIREBASE_URL);
        ref.authWithPassword(email, password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                signInMvpVIew.onLoginSuccess();
                Log.i(TAG, authData.toString());
            }
            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                Log.e(TAG, firebaseError.getMessage());
            }
        });
    }

    @Override
    public void attachView(SignInMvpVIew view) {
        signInMvpVIew = view;
    }

    @Override
    public void detachView() {
        signInMvpVIew = null;
    }
}
