package com.example.start.l12;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Admin on 17.01.2018.
 */

public class MusicStatusReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Piosenka sie skonczyla :(", Toast.LENGTH_SHORT).show();
        Log.d("MyService", "received broadcast Mario finished playing");
    }

    class ServiceConstants {
        // Defines a custom Intent action
        public static final String BROADCAST_ACTION = "com.example.zurnalis.myservices.BROADCAST";
        public static final String EXTENDED_DATA_STATUS = "com.example.zurnalis.myservices.STATUS";
    }
}
