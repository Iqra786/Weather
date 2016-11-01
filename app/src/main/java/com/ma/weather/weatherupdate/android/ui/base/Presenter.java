package com.ma.weather.weatherupdate.android.ui.base;


public interface Presenter<V> {
void onViewAttached(V view);
void onViewDetached();
void onDestroyed();
}
