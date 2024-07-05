package com.example.rigoroustesting;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class ImageTransmissionService extends Service {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Code to transmit images and alerts
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
