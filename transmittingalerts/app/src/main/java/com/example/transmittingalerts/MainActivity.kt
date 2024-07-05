package com.example.transmittingalerts

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private val CAMERA_PERMISSION_CODE = 101
    private val IMAGE_CAPTURE_CODE = 102

    private lateinit var captureImageButton: Button
    private lateinit var sendAlertButton: Button

    private var capturedImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        captureImageButton = findViewById(R.id.button_capture_image)
        sendAlertButton = findViewById(R.id.button_send_alert)

        captureImageButton.setOnClickListener {
            if (checkCameraPermission()) {
                openCamera()
            } else {
                requestCameraPermission()
            }
        }

        sendAlertButton.setOnClickListener {
            if (capturedImageUri != null) {
                // Here you would implement code to send the alert with the captured image URI
                // For demonstration, we'll show a toast and update the UI

                // Simulate sending alert process
                sendAlert()
            } else {
                showToast("Sending alert with image...")
                // Do nothing here, as we handle this scenario in the sendAlertButton click listener
            }
        }
    }

    private fun checkCameraPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestCameraPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.CAMERA),
            CAMERA_PERMISSION_CODE
        )
    }

    private fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera()
            } else {
                showToast("Camera permission denied")
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_CAPTURE_CODE && resultCode == RESULT_OK) {
            val imageUri: Uri? = data?.data
            if (imageUri != null) {
                capturedImageUri = imageUri
                showToast("Image captured successfully!")
            } else {
                val extras = data?.extras
                val imageBitmap = extras?.get("data") as? Bitmap
                if (imageBitmap != null) {
                    // Save the bitmap to a file and get its URI
                    capturedImageUri = saveBitmapAndGetUri(imageBitmap)
                    showToast("Image captured successfully!")
                } else {
                    showToast("Failed to capture image")
                }
            }
        }
    }

    private fun saveBitmapAndGetUri(bitmap: Bitmap): Uri? {
        // Placeholder for saving bitmap to a file and returning its URI
        return null
    }

    private fun sendAlert() {
        // Placeholder for sending alert functionality with capturedImageUri
        showToast("Alert sent successfully!")
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
