package com.codeego.auchadoseperdidos.presenter;

import com.codeego.auchadoseperdidos.model.entities.Post;
import com.codeego.auchadoseperdidos.view.CreatePostMvpView;

/**
 * Created by Gustavo on 2/11/16.
 */
public interface CreatePostPresenter extends Presenter<CreatePostMvpView> {
    void createPost(Post post);
}
