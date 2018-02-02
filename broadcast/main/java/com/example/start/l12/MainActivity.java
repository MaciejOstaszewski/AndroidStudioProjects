package com.example.start.l12;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.SystemClock;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button start;
    Button stop;
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        IntentFilter statusIntentFilter = new IntentFilter(MusicStatusReciver.ServiceConstants.BROADCAST_ACTION);
        MusicStatusReciver mMusicStatusReceiver = new MusicStatusReciver();
        LocalBroadcastManager.getInstance(this).registerReceiver(mMusicStatusReceiver, statusIntentFilter);

        start = (Button) findViewById(R.id.buttonStart);
        stop = (Button) findViewById(R.id.buttonStop);
        start.setOnClickListener(startListener);
        stop.setOnClickListener(stopListener);
    }

    View.OnClickListener startListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), HelloIntentService.class);
            startService(intent);
        }
    };

    View.OnClickListener stopListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlarmManager alarmMgr = (AlarmManager)getApplicationContext().getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(getApplicationContext(), AlarmReciver.class);
            PendingIntent alarmIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, 0);
            alarmMgr.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() +10 * 1000, alarmIntent);
        }
    };


}
