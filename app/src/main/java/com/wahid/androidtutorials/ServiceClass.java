package com.wahid.androidtutorials;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Wahid on 8/11/18.
 */

public class ServiceClass extends Service {

    private static final String TAG = "ServiceClass";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind() called");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand() called");
        Toast.makeText(this, "Service has Started", Toast.LENGTH_SHORT).show();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy() called");
        super.onDestroy();
        Toast.makeText(this, "Service is Destroyed", Toast.LENGTH_SHORT).show();
    }
}
