package com.example.wanandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;

import activity.HomeActivity;
import config.ApiConfig;
import httputil.HttpCallbackListener;
import httputil.HttpUtil;
import json.ParseJson;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button login=findViewById(R.id.login_btn);
        EditText user_view=findViewById(R.id.user_view);
        EditText password_view=findViewById(R.id.password_view);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=user_view.getText().toString();
                String password=password_view.getText().toString();
                HttpUtil.sendLogRequest(ApiConfig.LOGIN, new HttpCallbackListener() {
                    @Override
                    public void onFinish(String response) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    int responsecode=ParseJson.parseUserPassword(response);
                                    if(responsecode==0){
                                        Intent intent=new Intent(MainActivity.this, HomeActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }else{
                                        Toast.makeText(MainActivity.this,"登录失败！",Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }

                    @Override
                    public void onError(Exception e) {
                        e.printStackTrace();
                    }
                },user,password);
            }
        });
    }
}