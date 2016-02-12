package com.codeego.auchadoseperdidos.ui.activities;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.codeego.auchadoseperdidos.R;
import com.codeego.auchadoseperdidos.model.entities.Post;
import com.codeego.auchadoseperdidos.presenter.CreatePostPresenter;
import com.codeego.auchadoseperdidos.presenter.CreatePostPresenterFB;
import com.codeego.auchadoseperdidos.view.CreatePostMvpView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CreatePostActivity extends AppCompatActivity implements CreatePostMvpView,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private static final String TAG = CreatePostActivity.class.getSimpleName();
    final private int PERMISSION_FINE_LOCATION = 1;
    private CreatePostPresenter mCreatePostPresenter;
    private Post mNewPost;
    private GoogleApiClient mGoogleApiClient;

    private Location mLastLocation;
    private double mLatitude;
    private double mLongitude;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.title)
    EditText mTitle;

    @Bind(R.id.description)
    EditText mDescription;

    @Bind(R.id.submit)
    Button mSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mNewPost = new Post();

        mCreatePostPresenter = new CreatePostPresenterFB();
        mCreatePostPresenter.attachView(this);


        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mNewPost.setTitle(mTitle.getText().toString());
                mNewPost.setDescription(mDescription.getText().toString());

                mCreatePostPresenter.createPost(mNewPost);

            }
        });


        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCreatePostPresenter.detachView();
    }

    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();

        requestLocationPermission();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        stopLocationUpdates();
        super.onStop();
    }

    @Override
    public void onPostCreated() {
        Toast.makeText(getContext(), "Ped under rescue!", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onPostFailed(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onConnected(Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        startLocationUpdates(createLocationRequest(5000, 5000));
        getLastKnownLocation();

    }

    @Override
    public void onConnectionSuspended(int cause) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    private void getLastKnownLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if (mLastLocation != null) {
            mLatitude = mLastLocation.getLatitude();
            mLongitude = mLastLocation.getLongitude();
        }

        Log.i(TAG, "Latitude: " + mLatitude + " - Longitude: " + mLongitude);
    }

    private void requestLocationPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSION_FINE_LOCATION);
        }

    }

    protected void startLocationUpdates(LocationRequest request) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, request, this);
    }

    protected void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(
                mGoogleApiClient, this);
    }

    private LocationRequest createLocationRequest(int interval, int fastestInterval) {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(interval);
        locationRequest.setFastestInterval(fastestInterval);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        return locationRequest;
    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
        if (mLastLocation != null) {
            mLatitude = mLastLocation.getLatitude();
            mLongitude = mLastLocation.getLongitude();
        }
        Log.i(TAG, "New Location -> Latitude: " + mLatitude + " - Longitude: " + mLongitude);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_FINE_LOCATION: {
                startLocationUpdates(createLocationRequest(5000,5000));
            }

        }
    }
}
