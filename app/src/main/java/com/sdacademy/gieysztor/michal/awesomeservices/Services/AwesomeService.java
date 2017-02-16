package com.sdacademy.gieysztor.michal.awesomeservices.Services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.sdacademy.gieysztor.michal.awesomeservices.BuildConfig;
import com.sdacademy.gieysztor.michal.awesomeservices.MainActivity;

/**
 * Created by RENT on 2017-02-16.
 */

public class AwesomeService extends Service {


    public static final String TAG = AwesomeService.class.getSimpleName();


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");
        Toast.makeText(this, ":D", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand");

        if (intent != null) {
            if (intent.hasExtra(MainActivity.KLUCZ)) {
                String s = intent.getStringExtra(MainActivity.KLUCZ);
                Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
            }
        }


//        return super.onStartCommand(intent, flags, startId);
        return Service.START_STICKY;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind");
        return super.onUnbind(intent);

    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.i(TAG, "onRebind");

    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        Log.i(TAG, "onTaskRemoved");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
        Toast.makeText(this, "bleeeee.... x_X", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.setAction("com.sdacademy.gieysztor.michal.SERVICE_IS_DEAD");
        sendBroadcast(intent);
        if (BuildConfig.BUILD_TYPE.equals("debug")) {
            Toast.makeText(this, "Dodatkowy Toast widoczny tylko w trybie debug\nx_X", Toast.LENGTH_SHORT).show();
        }

    }
}
