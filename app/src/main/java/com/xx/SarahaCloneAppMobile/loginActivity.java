package com.xx.SarahaCloneAppMobile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;

import static com.android.volley.Request.Method.POST;


public class loginActivity extends AppCompatActivity {
    Button buttonReg;
    LinearLayout layoutLogin,layoutProgress;
    static final int TIME_SPLASH = 3000;
    SharedPreferences.Editor editor;
    String nameStr,passStr;
    EditText et_name,et_pass;
    SharedPreferences sharedpreferences;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        sharedpreferences=getApplicationContext().getSharedPreferences("Preferences", 0);
        intent=new Intent(loginActivity.this,showMsgActivity.class);

            defineWedgit();
            checkSplash();

    }


    private void defineWedgit() {
        buttonReg = findViewById(R.id.buttonReg);
        layoutLogin = findViewById(R.id.linearLogin);
        layoutProgress = findViewById(R.id.layoutProgress);
        et_name=findViewById(R.id.id_et_name);
        et_pass=findViewById(R.id.id_et_pass);
    }
    private void checkSplash() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loginDetailsIsShow();
            }
        }, TIME_SPLASH);
    }


    private void loginDetailsIsShow() {
        layoutProgress.setVisibility(View.GONE);
        layoutLogin.setVisibility(View.VISIBLE);
        buttonReg.setVisibility(View.VISIBLE);
    }

    public void presslogin(View view) {
        nameStr=et_name.getText().toString();
        passStr=et_pass.getText().toString();
        layoutLogin.setVisibility(View.GONE);
        buttonReg.setVisibility(View.GONE);
        layoutProgress.setVisibility(View.VISIBLE);
        checkNameAndPass(nameStr,passStr);


    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    private static final String linkLogin ="https://mahmoudgamel10.000webhostapp.com/api/login.php";
    private void checkNameAndPass(final String name,final String pass) {
        StringRequest stringRequest=new StringRequest(POST, linkLogin, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               if(response.equals("state:false")){
                   layoutProgress.setVisibility(View.GONE);
                   layoutLogin.setVisibility(View.VISIBLE);
                   buttonReg.setVisibility(View.VISIBLE);
                   Toast.makeText(loginActivity.this, "please check your nsmr snd password.!", Toast.LENGTH_SHORT).show();
               }else {

                   editor = sharedpreferences.edit();
                   editor.putString("LOGIN", name);
                   editor.apply();

                   startActivity(intent);
                   finish();
               }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                layoutProgress.setVisibility(View.GONE);
                layoutLogin.setVisibility(View.VISIBLE);
                buttonReg.setVisibility(View.VISIBLE);
                Toast.makeText(loginActivity.this, "Error", Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Map<String, String> getParams()  {
                Map<String,String> parms=new HashMap<String, String>();
                parms.put("name",name);
                parms.put("password",pass);

                return parms;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }


    public void openReg(View view) {
        Intent i = new Intent(loginActivity.this,RegisterActivity.class);
        startActivity(i);
    }
}
