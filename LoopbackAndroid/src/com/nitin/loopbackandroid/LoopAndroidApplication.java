package com.nitin.loopbackandroid;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.strongloop.android.loopback.RestAdapter;

public class LoopAndroidApplication extends Application {
    RestAdapter adapter;
    public static Context context=null;
    @Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.d("LoopAndroidApplication", getApplicationContext()+"");
        context = getApplicationContext();
	}
	public RestAdapter getLoopBackAdapter() {
        if (adapter == null) {
            // Instantiate the shared RestAdapter. In most circumstances,
            // you'll do this only once; putting that reference in a singleton
            // is recommended for the sake of simplicity.
            // However, some applications will need to talk to more than one
            // server - create as many Adapters as you need.
            adapter = new RestAdapter(
            		context, "http://192.168.26.1:3000/api");
            Log.d("LoopAndroidApplication"," connected"+adapter.isConnected());
        }
        return adapter;
    }
}
