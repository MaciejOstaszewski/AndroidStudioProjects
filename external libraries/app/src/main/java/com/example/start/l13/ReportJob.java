package com.example.start.l13;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.widget.Toast;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobRequest;

import java.util.Random;

/**
 * Created by start on 2018-02-03.
 */

public class ReportJob extends Job {

    public static final String TAG = "report_job";

    @NonNull
    @Override
    protected Result onRunJob(Params params) {

        Log.d("LOG", "JOB");

        if (ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getContext(),
                        Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity)
                            getContext(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
        }

        LocationManager locationManager = (LocationManager)
                getContext().getSystemService(Context.LOCATION_SERVICE);
        String locationProvider = LocationManager.NETWORK_PROVIDER;

        @SuppressLint("MissingPermission") Location lastKnownLocation =
                locationManager.getLastKnownLocation(locationProvider);
        PendingIntent pi = PendingIntent.getActivity(getContext(), 0,
                new Intent(getContext(), MainActivity.class), 0);
        Notification notification = new NotificationCompat.Builder(getContext())
                .setContentTitle("Android Job Demo")
                .setContentText("Last loc lat: "+ lastKnownLocation.getLatitude() +
                        "long: " + lastKnownLocation.getLongitude())
                .setAutoCancel(true)
                .setContentIntent(pi)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setShowWhen(true)
                .setColor(Color.RED)
                .setLocalOnly(true)
                .build();
        NotificationManagerCompat.from(getContext())
                .notify(new Random().nextInt(), notification);

//        PendingIntent pi = PendingIntent.getActivity(getContext(), 0,
//                new Intent(getContext(), MainActivity.class), 0);
//        Notification notification = new NotificationCompat.Builder(getContext())
//                .setContentTitle("Android Job")
//                .setContentText("Tekst powiadomienia")
//                .setAutoCancel(true)
//                .setContentIntent(pi)
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setShowWhen(true)
//                .setColor(Color.RED)
//                .setLocalOnly(true)
//                .build();
//        NotificationManagerCompat.from(getContext())
//                .notify(new Random().nextInt(), notification);


        return Result.SUCCESS;
    }

    public static void scheduleJob() {
        new JobRequest.Builder(ReportJob.TAG)
                .setExact(5_000L)
                .build()
                .schedule();

    }
}
