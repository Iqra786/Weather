package com.ma.weather.weatherupdate.android.ui.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.ma.weather.weatherupdate.R;
import com.ma.weather.weatherupdate.android.ui.base.PresenterFactory;
import com.ma.weather.weatherupdate.model.Condition;
import com.ma.weather.weatherupdate.model.Item;

import butterknife.BindView;
import butterknife.ButterKnife;


public class WeatherActivity extends BaseActivity<WeatherActivityPresenter , WeatherActivityView> implements WeatherActivityView {


    public static final String TAG = WeatherActivity.class.getSimpleName();

    private SupportMapFragment mSupportMapFragment;

    @BindView(R.id.tv_Title)
    TextView tv_Title;
    @BindView(R.id.tv_Text)
    TextView tv_Text;
    @BindView(R.id.tv_Temp)
    TextView tv_Temp;
    @BindView(R.id.ll_Main_Layout)
    LinearLayout ll_Main;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        ButterKnife.bind(this);
        mSupportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected PresenterFactory<WeatherActivityPresenter> getPresenterFactory() {
        return new PresenterFactoryImpl(getApplicationContext());
    }


    @Override
    public void setData(Item result) {
        String text_Title = result.getTitle();
        if (!text_Title.isEmpty()) {
            tv_Title.setText(text_Title);
        } else {
            tv_Title.setText(R.string.no_data);
        }


        if (result.getCondition() != null) {
            Condition condition = result.getCondition();
            String text_Text = condition.getText();
            if (!text_Text.isEmpty()) {
                tv_Text.setText(text_Text);
            } else {
                tv_Title.setText(R.string.no_data);
            }
            String text_Temp = condition.getTemp();
            if (!text_Temp.isEmpty()) {
                setBackgroundColour(Integer.valueOf(text_Temp));
                tv_Temp.setText(text_Temp);
            } else {
                tv_Temp.setText(R.string.no_data);
            }
        }
    }


    private void setBackgroundColour(int temp) {
        if (temp < 20) {
            ll_Main.setBackgroundColor(Color.RED);
        } else {
            ll_Main.setBackgroundColor(Color.YELLOW);
        }

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void locationUpdated() {
        WeatherActivityPresenter   mainActivityPresenter = (WeatherActivityPresenter) presenter;
        mainActivityPresenter.loadMap(mSupportMapFragment);
    }

    @Override
    public void dropPin(Double latitude, Double longitude, GoogleMap googleMap) {
//        dropMarker(latitude, longitude, googleMap);
    }
}
