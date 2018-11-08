package com.wahid.androidtutorials;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreateCalled");
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStartCalled");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResumeCalled");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPauseCalled");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStopCalled");
    }

    @Override
    public void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestartCalled");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroyCalled");
    }

    @Override
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Log.d(TAG, "ConfigChange:" + configuration.toString());
    }
}
