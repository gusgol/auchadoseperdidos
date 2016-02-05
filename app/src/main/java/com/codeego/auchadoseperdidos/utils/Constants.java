package com.codeego.auchadoseperdidos.utils;

import com.codeego.auchadoseperdidos.BuildConfig;

/**
 * Created by Gustavo on 2/2/16.
 */
public class Constants {

    /**
     * Constants for Firebase URL
     */
    public static final String FIREBASE_URL = BuildConfig.UNIQUE_FIREBASE_ROOT_URL;
    public static final String FIREBASE_LOCATION_USERS = "users";
    public static final String FIREBASE_URL_USERS = FIREBASE_URL + "/" + FIREBASE_LOCATION_USERS;



    /**
     * Constants for Firebase object properties
     */
    public static final String FIREBASE_PROPERTY_TIMESTAMP_LAST_CHANGED = "timestampLastChanged";
    public static final String FIREBASE_PROPERTY_TIMESTAMP = "timestamp";
    public static final String FIREBASE_PROPERTY_EMAIL = "email";
    public static final String FIREBASE_PROPERTY_NAME = "name";
    public static final String FIREBASE_PROPERTY_UID = "uid";


}
