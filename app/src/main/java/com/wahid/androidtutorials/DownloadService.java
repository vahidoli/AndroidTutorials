package com.wahid.androidtutorials;

import android.app.Activity;
import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by roopabai on 08-02-2019.
 */

public class DownloadService extends IntentService {

    private int result = Activity.RESULT_CANCELED;
    public static  final String URL = "urlpath";
    public static  final String FILE_NAME = "filename";
    public static  final String FILE_PATH = "filepath";
    public static  final String RESULT = "result";
    public static  final String NOTIFICATION = "com.wahid.androidtutorials";

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */
    public DownloadService() {
        super("DownloadService");
    }

    /**
     * This method is invoked on the worker thread (asynchronously) with a request to process.
     * Only one Intent is processed at a time, but the processing happens on a
     * worker thread that runs independently from other application logic.
     * So, if this code takes a long time, it will hold up other requests to
     * the same IntentService, but it will not hold up anything else.
     * When all requests have been handled, the IntentService stops itself,
     * so you should not call {@link #stopSelf}.
     *
     * @param intent The value passed to {@link
     *               Context#startService(Intent)}.
     *               This may be null if the service is being restarted after
     *               its process has gone away; see
     *               {@link Service#onStartCommand}
     *               for details.
     */
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String urlPath = intent.getStringExtra(URL);
        String fileName = intent.getStringExtra(FILE_NAME);
        File file = new File(Environment.getExternalStorageDirectory(),fileName);
        if(file.exists())
            file.delete();
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            java.net.URL url = new URL(urlPath);
            inputStream = url.openConnection().getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            fileOutputStream = new FileOutputStream(file.getPath());
            int next = -1;
            while ((next = inputStreamReader.read()) != -1)
                fileOutputStream.write(next);
            result = Activity.RESULT_OK;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ie) {
                    ie.printStackTrace();
                }
            }

            if(fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException ie) {
                    ie.printStackTrace();
                }
            }
            publishResults(file.getAbsolutePath(), result);
        }
    }

    private void publishResults(String outputPath, int result) {
        Intent intent = new Intent(NOTIFICATION);
        intent.putExtra(FILE_PATH, outputPath);
        intent.putExtra(RESULT, result);
        sendBroadcast(intent);
    }
}
