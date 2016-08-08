package com.example.freatnor.notifications_lab;

import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ReceiptActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);

        Intent intent = getIntent();

        if(intent.getBooleanExtra("hasConnectivity", false)){
            NotificationManagerCompat.from(this.getApplicationContext()).cancel(intent.getIntExtra("notification_id", 1));
        }
    }
}
