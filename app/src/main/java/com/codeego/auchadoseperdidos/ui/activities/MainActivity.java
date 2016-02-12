package com.codeego.auchadoseperdidos.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.codeego.auchadoseperdidos.R;
import com.codeego.auchadoseperdidos.adapters.MainPagesAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    MainPagesAdapter mMainAdapter;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.pager)
    ViewPager mPages;

    @Bind(R.id.tabs)
    TabLayout mTabs;

    @Bind(R.id.create)
    FloatingActionButton mCreatePost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // ViewPager set up
        mMainAdapter = new MainPagesAdapter(getSupportFragmentManager(), this);
        mPages.setAdapter(mMainAdapter);
        mTabs.setupWithViewPager(mPages);
    }

    public void createPost(View view) {
        Intent intent = new Intent(this, CreatePostActivity.class);
        startActivity(intent);
    }
}
