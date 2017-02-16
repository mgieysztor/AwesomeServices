package com.sdacademy.gieysztor.michal.awesomeservices;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.sdacademy.gieysztor.michal.awesomeservices.Services.AwesomeService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String KLUCZ = "KLUCZ";
    private RelativeLayout rootView;
    private Button sposobDrugi;
    private MainActivityReceiver receiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootView = (RelativeLayout) findViewById(R.id.activity_main);
        int i = 0;

        if (rootView.getChildCount() > 0) {
            View pierwszySposob = rootView.getChildAt(0);
            if (pierwszySposob instanceof Button) {
                ((Button) pierwszySposob).setText("Wow!");
            }
        }


        sposobDrugi = (Button) findViewById(R.id.startButton);

        Button sposobTrzeci = (Button) rootView.findViewWithTag("superPrzycisk");
        Button sposobCzwarty = (Button) rootView.findViewById(R.id.startButton);

        findViewById(R.id.stopButton).setOnClickListener(this);
        addListeners();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MainActivity", "onResume");
        receiver = new MainActivityReceiver();
        IntentFilter filter = new IntentFilter(MainActivityReceiver.ACTION_SERVICE_DEAD);
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (receiver != null)
            unregisterReceiver(receiver);
    }

    private void addListeners() {
        sposobDrugi.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startButton:
                startAwesomeService();
                break;
            case R.id.stopButton:
                stopAwesomeService();
                break;
        }
    }

    private void startAwesomeService() {
        Intent intentService = new Intent(this, AwesomeService.class);
        intentService.putExtra(KLUCZ, "Cześć!");
        startService(intentService);
    }

    private void stopAwesomeService() {
        Intent intentService = new Intent(this, AwesomeService.class);
        stopService(intentService);
    }

    public class MainActivityReceiver extends BroadcastReceiver {
        public final static String ACTION_SERVICE_DEAD = "com.sdacademy.gieysztor.michal.SERVICE_IS_DEAD";

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i("Receiver", "Złapałem!");

            Intent serviceIntent = new Intent(context, AwesomeService.class);
            serviceIntent.putExtra(KLUCZ, "Piona! MainActivityReceiver tu był");
            startService(serviceIntent);

        }
    }
}
