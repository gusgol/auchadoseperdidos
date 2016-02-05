package com.codeego.auchadoseperdidos;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by Gustavo on 2/2/16.
 */
public class AuchadosEPerdigosApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
