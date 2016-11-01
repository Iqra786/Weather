package com.ma.weather.weatherupdate.android.ui.activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;

import com.ma.weather.weatherupdate.android.ui.base.Presenter;
import com.ma.weather.weatherupdate.android.ui.base.PresenterFactory;
import com.ma.weather.weatherupdate.android.ui.base.PresenterLoader;

public abstract class BaseActivity<P extends Presenter<V>, V> extends AppCompatActivity {
    protected Presenter<V> presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        presenter = getPresenterFactory().create();
        getSupportLoaderManager().initLoader(200, null, new LoaderManager.LoaderCallbacks<P>() {
            @Override
            public Loader<P> onCreateLoader(int id, Bundle args) {
                return new PresenterLoader<>(BaseActivity.this , getPresenterFactory());
            }

            @Override
            public void onLoadFinished(Loader<P> loader, P data) {
                BaseActivity.this.presenter = data;
            }

            @Override
            public void onLoaderReset(Loader<P> loader) {
                BaseActivity.this.presenter = null;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onViewAttached(getPresenterView());
    }

    @Override
    protected void onStop() {
        presenter.onViewDetached();
        super.onStop();
    }

    protected V getPresenterView() {
        return (V)this;
    }

    protected abstract int setLayout();

    protected abstract PresenterFactory<P> getPresenterFactory();
}
