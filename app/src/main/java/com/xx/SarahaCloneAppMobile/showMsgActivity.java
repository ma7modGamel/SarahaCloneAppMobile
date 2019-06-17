package com.xx.SarahaCloneAppMobile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.xx.SarahaCloneAppMobile.utils.BtnNanigationViewHelper;
import com.xx.SarahaCloneAppMobile.utils.MsgModel;
import com.xx.SarahaCloneAppMobile.utils.MsgShowAdabter;
import com.xx.SarahaCloneAppMobile.utils.MySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class showMsgActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    ProgressBar progressBar;

    RecyclerView  recyclerView;
    BottomNavigationViewEx navigationViewExBtn;
    ArrayList<MsgModel> msgModelArrayList;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show_msg);

        navigationViewExBtn=findViewById(R.id.bottmNavView);
        setUpNavigationbar();

        recyclerView=findViewById(R.id.recycleid);
        progressBar = findViewById(R.id.simpleProgressBarMSG);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        RecyclerView.LayoutManager manager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        msgModelArrayList=new ArrayList<>();
        sharedPreferences = getApplicationContext().getSharedPreferences("Preferences", 0);

        String loginUser = sharedPreferences.getString("LOGIN", null);


        if (loginUser != null) {

            getAllmsg(loginUser);

        } else {
            Intent intent = new Intent(this, loginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void getAllmsg(String loginUser) {

        String linkGetAllMsg = "https://mahmoudgamel10.000webhostapp.com/api/showAllmsg.php?username=" + loginUser;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, linkGetAllMsg, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);

                for (int i=0;i<response.length();i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String content_msg = jsonObject.getString("content_msg");
                        String sent_time_stamb = jsonObject.getString("sent_time_stamb");
                        msgModelArrayList.add(new MsgModel(content_msg,sent_time_stamb));
                        Log.e( "onResponse: ",sent_time_stamb );


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                recyclerView.setAdapter(new MsgShowAdabter(showMsgActivity.this,msgModelArrayList));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(showMsgActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonArrayRequest);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


    public void logOut(View view) {

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("LOGIN", null);
        editor.commit();
        Intent intent = new Intent(this, loginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optinal_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.menu1:
                Toast.makeText(getApplicationContext(),"Item 1 Selected",Toast.LENGTH_LONG).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void setUpNavigationbar() {

        //to remove Animat and text
        BtnNanigationViewHelper.moveAnimatAndText(navigationViewExBtn);
        BtnNanigationViewHelper.enableNavigation(this,navigationViewExBtn);

        //to change icon for navigation
        Menu menu=navigationViewExBtn.getMenu();
        MenuItem menuItem=menu.getItem(0);
        menuItem.setChecked(true);

    }
}
