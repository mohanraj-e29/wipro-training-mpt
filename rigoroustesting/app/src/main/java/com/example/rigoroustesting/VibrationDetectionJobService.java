package com.example.rigoroustesting;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;

public class VibrationDetectionJobService extends JobService {
    private static final String TAG = "VibrationDetectionJob";

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG, "Job started");
        Intent serviceIntent = new Intent(this, VibrationDetectionService.class);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            startForegroundService(serviceIntent);
        } else {
            startService(serviceIntent);
        }

        scheduleNextJob();
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(TAG, "Job stopped");
        return true;
    }

    private void scheduleNextJob() {
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            // Code to schedule the next job
        }, 15 * 60 * 1000); // delay for 15 minutes
    }
}