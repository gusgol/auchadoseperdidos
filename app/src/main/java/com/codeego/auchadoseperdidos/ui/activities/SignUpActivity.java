package com.codeego.auchadoseperdidos.ui.activities;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.codeego.auchadoseperdidos.R;
import com.codeego.auchadoseperdidos.presenter.UserSignUpPresenter;
import com.codeego.auchadoseperdidos.presenter.UserSignUpPresenterFB;
import com.codeego.auchadoseperdidos.view.SignUpMvpView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity implements SignUpMvpView {

    private UserSignUpPresenter mUserSignUpPresenter;

    @Bind(R.id.name)
    EditText mName;

    @Bind(R.id.email)
    EditText mEmail;

    @Bind(R.id.password)
    EditText mPassword;

    @Bind(R.id.register)
    Button mRegister;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//            mToolbar.setPadding(0, getStatusBarHeight(), 0, 0);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mToolbar.getLayoutParams();
            params.setMargins(0,getStatusBarHeight(),0,0);
            mToolbar.setLayoutParams(params);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        mUserSignUpPresenter = new UserSignUpPresenterFB();
        mUserSignUpPresenter.attachView(this);

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserSignUpPresenter.createUser(mEmail.getText().toString(), mPassword.getText().toString(), mName.getText().toString());
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mUserSignUpPresenter != null) {
            mUserSignUpPresenter.detachView();
        }
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(getContext(), "User signed up!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignUpFailure() {
        Toast.makeText(getContext(), "Sign up failed!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() {
        return this;
    }
}
