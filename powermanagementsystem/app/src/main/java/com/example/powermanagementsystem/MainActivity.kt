package com.example.powermanagementsystem

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var startStopButton: Button
    private lateinit var statusText: TextView
    private lateinit var batteryInfo: TextView
    private val handler = Handler(Looper.getMainLooper())
    private var isPowerManagementActive = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startStopButton = findViewById(R.id.button_start_stop)
        statusText = findViewById(R.id.status_text)
        batteryInfo = findViewById(R.id.battery_info)

        startStopButton.setOnClickListener {
            togglePowerManagement()
        }
    }

    private fun togglePowerManagement() {
        if (isPowerManagementActive) {
            stopPowerManagement()
        } else {
            startPowerManagement()
        }
    }

    private fun startPowerManagement() {
        isPowerManagementActive = true
        startStopButton.text = "Stop Power Management"
        statusText.text = "Power Management Active"
        Toast.makeText(this, "Power Management Started", Toast.LENGTH_SHORT).show()

        // Simulate battery-saving functionality
        handler.postDelayed({
            showBatterySavingInfo()
        }, 2000) // Delay to simulate some processing
    }

    private fun stopPowerManagement() {
        isPowerManagementActive = false
        startStopButton.text = "Start Power Management"
        statusText.text = "Power Management Inactive"
        batteryInfo.visibility = TextView.GONE
        Toast.makeText(this, "Power Management Stopped", Toast.LENGTH_SHORT).show()

        // Remove any pending callbacks
        handler.removeCallbacksAndMessages(null)
    }

    private fun showBatterySavingInfo() {
        batteryInfo.visibility = TextView.VISIBLE
        batteryInfo.text = "Battery usage minimized by turning off non-essential services."
        Toast.makeText(this, "Battery usage minimized", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }
}
