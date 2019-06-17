package com.xx.SarahaCloneAppMobile.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.widget.Toast;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.xx.SarahaCloneAppMobile.R;
import com.xx.SarahaCloneAppMobile.SendNewMsgActivity;
import com.xx.SarahaCloneAppMobile.showMsgActivity;

public class BtnNanigationViewHelper {


    public static void moveAnimatAndText(BottomNavigationViewEx navigationViewExBtn) {
        //move animation and text
       /// navigationViewExBtn.setBackgroundColor(Color.parseColor(String.valueOf(Color.red(000000))));
        navigationViewExBtn.enableAnimation(false);
        navigationViewExBtn.setTextVisibility(false);
    }


    public static void enableNavigation(final Context context, BottomNavigationViewEx viewBtn) {

        viewBtn.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                Intent intentPage;

                switch (menuItem.getItemId()) {
                    case R.id.show_id:
                        intentPage = new Intent(context, showMsgActivity.class);//index item:0
                        context.startActivity(intentPage);
                        break;
                    case R.id.search_id:
                        Toast.makeText(context, "Search", Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.favorit_id:
                        Toast.makeText(context, "favorit", Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.send_id:
                        intentPage = new Intent(context, SendNewMsgActivity.class);//index item:1
                        context.startActivity(intentPage);
                        break;


                }

                return false;
            }
        });

    }

}
