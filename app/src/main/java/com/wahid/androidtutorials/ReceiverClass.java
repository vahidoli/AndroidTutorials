package com.wahid.androidtutorials;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Wahid on 9/11/18.
 */

public class ReceiverClass extends BroadcastReceiver {

    private static final String TAG = "ReceiverClass";
    public static final String INTENT_ACTION = "com.wahid.EXAMPLE_BROADCAST";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive called");
        Toast.makeText(context, "Intent Detected", Toast.LENGTH_SHORT).show();
    }
}


