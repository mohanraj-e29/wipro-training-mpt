package com.example.backgrndsurvillencesys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var titleTextView: TextView
    private lateinit var startStopButton: Button
    private lateinit var statusTextView: TextView

    private var isSurveillanceRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        titleTextView = findViewById(R.id.title)
        startStopButton = findViewById(R.id.button_start_stop)
        statusTextView = findViewById(R.id.status_text)

        // Set initial UI state
        updateStatusText()

        // Button click listener
        startStopButton.setOnClickListener {
            if (isSurveillanceRunning) {
                stopSurveillance()
            } else {
                startSurveillance()
            }
        }
    }

    private fun startSurveillance() {
        // TODO: Implement logic to start surveillance service or functionality

        // Example logic: Update status and UI
        isSurveillanceRunning = true
        updateStatusText()
        startStopButton.text = "Stop Surveillance"
    }

    private fun stopSurveillance() {
        // TODO: Implement logic to stop surveillance service or functionality

        // Example logic: Update status and UI
        isSurveillanceRunning = false
        updateStatusText()
        startStopButton.text = "Start Surveillance"
    }

    private fun updateStatusText() {
        val status = if (isSurveillanceRunning) "Running" else "Stopped"
        statusTextView.text = "Status: $status"
    }
}
