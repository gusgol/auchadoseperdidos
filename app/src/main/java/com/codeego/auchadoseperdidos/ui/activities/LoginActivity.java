package com.codeego.auchadoseperdidos.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.codeego.auchadoseperdidos.R;
import com.codeego.auchadoseperdidos.presenter.UserSignInPresenter;
import com.codeego.auchadoseperdidos.presenter.UserSignInPresenterFB;
import com.codeego.auchadoseperdidos.view.SignInMvpVIew;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements SignInMvpVIew {

    private Context mContext;

    private UserSignInPresenter mUserSignInPresenter;

    @Bind(R.id.email)
    EditText mEmail;

    @Bind(R.id.password)
    EditText mPassword;

    @Bind(R.id.sign_in)
    Button mSignIn;

    @Bind(R.id.register)
    TextView mRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mContext = this;

        mUserSignInPresenter = new UserSignInPresenterFB();
        mUserSignInPresenter.attachView(this);

        mRegister.setOnClickListener(mOnClickedRegister);
        mSignIn.setOnClickListener(mOnClickedLogin);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUserSignInPresenter.detachView();
    }

    private View.OnClickListener mOnClickedRegister = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, SignUpActivity.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener mOnClickedLogin = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mUserSignInPresenter.login(mEmail.getText().toString(), mPassword.getText().toString());
        }
    };

    @Override
    public void onLoginSuccess() {
        Toast.makeText(getContext(), "User loged in!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginFailed() {
        Toast.makeText(getContext(), "Login failed!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() {
        return this;
    }
}
