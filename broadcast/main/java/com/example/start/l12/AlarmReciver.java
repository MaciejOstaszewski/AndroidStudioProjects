package com.example.start.l12;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;

/**
 * Created by Admin on 17.01.2018.
 */

public class AlarmReciver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("MyService", "Broadcast recieved from alarm clock");
        MediaPlayer player = MediaPlayer.create(context, R.raw.getf);
        player.setLooping(false);
        player.setVolume(100, 100);
        player.start();
    }
}
