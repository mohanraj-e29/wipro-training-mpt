package com.example.powermanagementsystem

import android.content.Context
import android.os.PowerManager
import android.util.Log

object PowerManager {

    fun reduceBatteryUsage(context: Context) {
        val powerManager = context.getSystemService(Context.POWER_SERVICE) as PowerManager

        if (powerManager.isPowerSaveMode) {
            Log.d("PowerManager", "Already in power save mode")
        } else {
            // Enter power save mode or take necessary actions to reduce battery usage
            Log.d("PowerManager", "Entering power save mode")
            // Custom logic to reduce battery usage can be added here
        }
    }
}