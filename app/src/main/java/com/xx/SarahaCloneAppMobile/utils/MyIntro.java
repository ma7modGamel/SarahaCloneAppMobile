package com.xx.SarahaCloneAppMobile.utils;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro;
import com.xx.SarahaCloneAppMobile.MainActivity;
import com.xx.SarahaCloneAppMobile.R;

public class MyIntro extends AppIntro {
    Intent i;
    @Override
    public void init(Bundle savedInstanceState) {
        i = new Intent(this, MainActivity.class);
        //adding the three slides for introduction app you can ad as many you needed


        addSlide(AppIntroSampleSlider.newInstance(R.layout.app_intro2));
        addSlide(AppIntroSampleSlider.newInstance(R.layout.app_intro4));
        addSlide(AppIntroSampleSlider.newInstance(R.layout.app_intro3));
        addSlide(AppIntroSampleSlider.newInstance(R.layout.app_intro1));
        addSlide(AppIntroSampleSlider.newInstance(R.layout.app_intro5));
        addSlide(AppIntroSampleSlider.newInstance(R.layout.app_intro6));

        setSeparatorColor(Color.parseColor("#2196F3"));
        setProgressButtonEnabled(true);
        setVibrate(true);
        setVibrateIntensity(30);
        showStatusBar(false);
        showSkipButton(false);
        setDepthAnimation();
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        finish();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        finish();
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.
    }

}