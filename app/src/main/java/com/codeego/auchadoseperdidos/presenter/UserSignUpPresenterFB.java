package com.codeego.auchadoseperdidos.presenter;

import android.util.Log;

import com.codeego.auchadoseperdidos.model.entities.User;
import com.codeego.auchadoseperdidos.utils.Constants;
import com.codeego.auchadoseperdidos.view.SignUpMvpView;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ServerValue;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gustavo on 2/2/16.
 */
public class UserSignUpPresenterFB implements UserSignUpPresenter {

    private static final String TAG = UserSignUpPresenterFB.class.getSimpleName();
    private SignUpMvpView mSignUpMvpView;
    private Firebase mFirebaseRef;

    @Override
    public void createUser(String email, String password, final String name) {

        email = email.toLowerCase();
        final String finalEmail = email;
        final String finalName = name;

        mFirebaseRef.createUser(finalEmail, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                Log.i(TAG, result.toString());

                String uid = result.get(Constants.FIREBASE_PROPERTY_UID).toString();
                HashMap<String, Object> timestampJoined = new HashMap<>();
                timestampJoined.put(Constants.FIREBASE_PROPERTY_TIMESTAMP, ServerValue.TIMESTAMP);

                User newUser = new User(finalName, finalEmail ,timestampJoined );
                HashMap<String, Object> newUserMap = (HashMap<String, Object>)
                        new ObjectMapper().convertValue(newUser, Map.class);

                HashMap<String, Object> userAndUidMapping = new HashMap<String, Object>();
                userAndUidMapping.put("/" + Constants.FIREBASE_LOCATION_USERS + "/" + uid, newUserMap);

                mFirebaseRef.updateChildren(userAndUidMapping, new Firebase.CompletionListener() {
                    @Override
                    public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                        if (firebaseError != null) {
                            Log.e(TAG, firebaseError.getMessage());
                            mSignUpMvpView.onSignUpFailure();



                        } else {
                            mSignUpMvpView.onSignUpSuccess();

                            //TODO sign user in
                        }
                    }
                });

            }

            @Override
            public void onError(FirebaseError firebaseError) {
                Log.e(TAG, firebaseError.getMessage());
                mSignUpMvpView.onSignUpFailure();
            }
        });
    }



    @Override
    public void attachView(SignUpMvpView view) {
        mSignUpMvpView = view;
        mFirebaseRef = new Firebase(Constants.FIREBASE_URL);
    }

    @Override
    public void detachView() {
        mSignUpMvpView = null;

    }
}
