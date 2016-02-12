package com.codeego.auchadoseperdidos.presenter;

import com.codeego.auchadoseperdidos.model.entities.Post;
import com.codeego.auchadoseperdidos.utils.Constants;
import com.codeego.auchadoseperdidos.view.CreatePostMvpView;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gustavo on 2/11/16.
 */
public class CreatePostPresenterFB implements CreatePostPresenter {

    private CreatePostMvpView mCreatePostMvpView;
    private Firebase mPostsRef;

    @Override
    public void createPost(Post post) {
        if(mPostsRef !=  null) {

            HashMap<String, Object> postMap = (HashMap<String, Object>) new ObjectMapper().convertValue(post, Map.class);

            mPostsRef.push().setValue(postMap, new Firebase.CompletionListener() {
                @Override
                public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                    if(firebaseError == null) {
                        mCreatePostMvpView.onPostCreated();
                    } else {
                        mCreatePostMvpView.onPostFailed(firebaseError.getMessage());
                    }
                }
            });
        }
    }

    @Override
    public void attachView(CreatePostMvpView view) {
        mCreatePostMvpView = view;
        mPostsRef = new Firebase(Constants.FIREBASE_URL_POSTS);
    }

    @Override
    public void detachView() {
        mCreatePostMvpView = null;
    }
}
