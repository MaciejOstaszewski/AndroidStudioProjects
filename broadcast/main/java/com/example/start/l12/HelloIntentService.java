package com.example.start.l12;

import android.app.IntentService;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

/**
 * Created by Admin on 17.01.2018.
 */

public class HelloIntentService extends IntentService {
    MediaPlayer player;
    /**     * A constructor is required, and must call the super IntentService(String)
     * * constructor with a name for the worker thread.     */
    public HelloIntentService() {
        super("HelloIntentService");
    }
    /**     * The IntentService calls this method from the default worker thread with
     * * the intent that started the service. When this method returns, IntentService
     * * stops the service, as appropriate.     */
    @Override    protected void onHandleIntent(Intent intent)
    {
        // Normally we would do some work here, like download a file.
        // For our sample, we just sleep for 5 seconds.
        try {
            player= MediaPlayer.create(getApplicationContext(), R.raw.getf);
            player.setLooping(false); // Set looping
            player.setVolume(100,100);
            player.start();

            while(player.isPlaying()){
                Thread.sleep(1000);
                Log.d("MyService", "getf keeps playing");
            }

            Intent localIntent = new Intent(MusicStatusReciver.ServiceConstants.BROADCAST_ACTION);
            localIntent.putExtra(MusicStatusReciver.ServiceConstants.EXTENDED_DATA_STATUS, "done");
            LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);

        } catch (Exception e) {
            // Restore interrupt status.
            Thread.currentThread().interrupt();
        }
    }

}
