package com.example.rigoroustesting;


import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class VibrationDetectionService extends Service {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Start vibration detection logic here
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
