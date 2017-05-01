package com.raj.shashank.testapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BroadcastReceiver yourReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

            }
        };
        final IntentFilter filters = new IntentFilter();
        filters.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        filters.addAction("android.net.wifi.STATE_CHANGE");
        super.registerReceiver(yourReceiver, filters);

        ConnectivityManager conMngr = (ConnectivityManager)this.getSystemService(this.CONNECTIVITY_SERVICE);
        android.net.NetworkInfo wifi = conMngr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        android.net.NetworkInfo mobile = conMngr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        boolean isConnected = wifi != null && wifi.isConnectedOrConnecting() ||
                mobile != null && mobile.isConnectedOrConnecting();
        if (isConnected) {
            Toast.makeText(MainActivity.this,
                    "YES : Network Available", Toast.LENGTH_LONG).show();
            //Log.d("Network Available ", "YES");
        } else {
            Toast.makeText(MainActivity.this,
                    "NO : Network Available", Toast.LENGTH_LONG).show();
            //Log.d("Network Available ", "NO");
        }

        //WebView browser = (WebView) findViewById(R.id.webview);

        //browser.loadUrl("http://www.google.com");
    }
}
