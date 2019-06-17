package com.xx.SarahaCloneAppMobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.xx.SarahaCloneAppMobile.utils.BtnNanigationViewHelper;

public class SendNewMsgActivity extends AppCompatActivity {
    private static final String TAG ="SendNewMsgActivity" ;
    BottomNavigationViewEx navigationViewExBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_send_new_msg);

        navigationViewExBtn=findViewById(R.id.bottmNavView);
        setUpNavBar();

    }

    private void setUpNavBar() {
        navigationViewExBtn = findViewById(R.id.bottmNavView);
        Log.d(TAG, "on creat started");
        BtnNanigationViewHelper.moveAnimatAndText(navigationViewExBtn);
        BtnNanigationViewHelper.enableNavigation(this, navigationViewExBtn);

        //to change icon for navigation
        Menu menu = navigationViewExBtn.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
    }
}
