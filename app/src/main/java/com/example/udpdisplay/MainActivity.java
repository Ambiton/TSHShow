package com.example.udpdisplay;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
//
//            @RequiresApi(api = Build.VERSION_CODES.M)
//            @Override
//            public void onClick(View v) {
////                WifiManager manager = (WifiManager) MainActivity.this.getApplicationContext()
////                        .getSystemService(Context.WIFI_SERVICE);
////                UdpUtil udpUtil=new UdpUtil(manager);
//
//                    Thread th=new Thread(new Runnable() {
//
//                        @Override
//                        public void run() {
//                            try {
//                                WifiManager manager = (WifiManager) MainActivity.this.getApplicationContext()
//                                        .getSystemService(Context.WIFI_SERVICE);
//                                UdpUtil udpUtil=new UdpUtil(manager);
//                                udpUtil.StartListen();
//                                UdpUtil.send("Hello Android...");
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }});
//                    th.start();
//
//
//            }
//        });
    }
}