package com.wahid.androidtutorials;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Wahid on 08-02-2019.
 */

public class DownloadActivity extends AppCompatActivity {

    private TextView textView;

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                String filePath = extras.getString(DownloadService.FILE_PATH);
                int result = extras.getInt(DownloadService.RESULT);
                if(result == RESULT_OK) {
                    Toast.makeText(DownloadActivity.this, "Download Success. URI: " + filePath, Toast.LENGTH_LONG).show();
                    textView.setText("Success");
                } else {
                    Toast.makeText(DownloadActivity.this, "Download Failed", Toast.LENGTH_LONG).show();
                    textView.setText("Failed");
                }
            }
        }
    };

    @Override
    public void onCreate(Bundle onSavedInstance) {
        super.onCreate(onSavedInstance);
        setContentView(R.layout.download_layout);
        textView = (TextView) findViewById(R.id.status);
    }

    private void checkStoragePermission(){
        if (ContextCompat.checkSelfPermission(DownloadActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(DownloadActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(DownloadActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1001);
            }
        } else {
            startService();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1001: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, do your work....
                    startService();
                } else {
                    // permission denied
                    // Disable the functionality that depends on this permission.
                    finish();
                }
                return;
            }
            // other 'case' statements for other permssions
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(receiver, new IntentFilter(DownloadService.NOTIFICATION));
    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    public void onClick(View view) {
        checkStoragePermission();
    }

    private void startService() {
        Intent intent = new Intent(this, DownloadService.class);
        intent.putExtra(DownloadService.FILE_NAME, "index.html");
        intent.putExtra(DownloadService.URL,"http://www.vogella.com/index.html");
        startService(intent);
        textView.setText("Service Started");
    }
}