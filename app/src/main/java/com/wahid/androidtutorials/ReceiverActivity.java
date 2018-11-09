package com.wahid.androidtutorials;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Wahid on 8/11/18.
 */

public class ReceiverActivity extends AppCompatActivity {

    private Button button_startReceiver;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_receiver);
        button_startReceiver = (Button) findViewById(R.id.button_startReceiver);

        button_startReceiver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent();
                    intent.setAction(ReceiverClass.INTENT_ACTION);
                    sendBroadcast(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "No specific actions found for the Intent", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
