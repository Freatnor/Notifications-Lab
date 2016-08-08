package com.example.freatnor.notifications_lab;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.net.ConnectivityManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private NotificationCompat.Builder mBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        ConnectivityManager conManager = (ConnectivityManager) this.getApplicationContext().getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conManager.getActiveNetworkInfo();

        Intent notifIntent = new Intent(MainActivity.this, ReceiptActivity.class);
        notifIntent.putExtra("notification_id", 1977);

        mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setContentTitle("Notification Alert!");
        mBuilder.setSmallIcon(android.R.drawable.ic_dialog_alert);
        mBuilder.setPriority(NotificationCompat.PRIORITY_MAX);

        NotificationCompat.BigPictureStyle pictureStyle = new android.support.v4.app.NotificationCompat.BigPictureStyle();

        if (networkInfo != null && networkInfo.isConnected()) {

            notifIntent.putExtra("hasConnectivity", true);

            pictureStyle.bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.wifi_128));
        } else {
            notifIntent.putExtra("hasConnectivity", false);

            pictureStyle.bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.no_network_ca_512));
        }

        mBuilder.setStyle(pictureStyle);
        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, (int) System.currentTimeMillis(), notifIntent, 0);
        mBuilder.setContentIntent(pendingIntent);
        NotificationManagerCompat.from(this.getApplicationContext()).notify(1977, mBuilder.build());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void callButton(View view){
        //ToDo change this intent to call the system phone
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:0123456789"));
        startActivity(intent);
    }
}
