package com.ma.weather.weatherupdate.android.ui.base;

import android.content.Context;
import android.support.v4.content.Loader;

public final class PresenterLoader<T extends Presenter> extends Loader<T> {

    private PresenterFactory<T> presenterFactory;
    private T presenter;


    public PresenterLoader(Context context , PresenterFactory<T> presenterFactory) {
        super(context);
        this.presenterFactory = presenterFactory;
    }


    @Override
    protected void onStartLoading() {
//        super.onStartLoading();
        if (presenter != null) {
            deliverResult(presenter);
            return;
        }
        forceLoad();
    }


    @Override
    protected void onForceLoad() {
//        super.onForceLoad();
        presenter = presenterFactory.create();
        deliverResult(presenter);
    }


    @Override
    protected void onStopLoading() {
       super.onStopLoading();

    }


    @Override
    public void deliverResult(T data) {
        super.deliverResult(data);
    }

    @Override
    protected void onReset() {
//        super.onReset();
        if (presenter != null) {
            presenter.onDestroyed();
            presenter = null;
        }
    }
}
