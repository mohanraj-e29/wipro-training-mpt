package com.example.thirdeyesystem

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide // Make sure this import is added

class MainActivity : AppCompatActivity() {

    private val CAMERA_PERMISSION_CODE = 101
    private val IMAGE_CAPTURE_CODE = 102

    private lateinit var textMediaDisplay: TextView
    private lateinit var imageMedia: ImageView
    private lateinit var buttonViewMedia: Button
    private lateinit var buttonCaptureImage: Button
    private lateinit var textAlerts: TextView
    private lateinit var listAlerts: ListView

    private var capturedImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        textMediaDisplay = findViewById(R.id.text_media_display)
        imageMedia = findViewById(R.id.image_media)
        buttonViewMedia = findViewById(R.id.button_view_media)
        buttonCaptureImage = findViewById(R.id.button_capture_image)
        textAlerts = findViewById(R.id.text_alerts)
        listAlerts = findViewById(R.id.list_alerts)

        // Setup button click listeners
        buttonViewMedia.setOnClickListener {
            if (capturedImageUri != null) {
                showCapturedMedia(capturedImageUri!!)
            } else {
                showToast("No media to view")
            }
        }

        buttonCaptureImage.setOnClickListener {
            if (checkCameraPermission()) {
                openCamera()
            } else {
                requestCameraPermission()
            }
        }

        // Setup list adapter for alerts
        val alertData = mutableListOf("All Good..", "Nothing To Worry ", "No Interuption") // Example data
        val alertsAdapter = AlertsAdapter(this, alertData)
        listAlerts.adapter = alertsAdapter
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
                    capturedImageUri = saveBitmapAndGetUri(imageBitmap)
                    showToast("Image captured successfully!")
                } else {
                    showToast("Failed to capture image")
                }
            }
        }
    }

    private fun saveBitmapAndGetUri(bitmap: Bitmap): Uri? {
        // Implement logic to save bitmap to a file and return its URI
        // Placeholder implementation
        val path = MediaStore.Images.Media.insertImage(contentResolver, bitmap, "CapturedImage", null)
        return Uri.parse(path)
    }

    private fun showCapturedMedia(imageUri: Uri) {
        // Implement logic to load image into ImageView
        Glide.with(this).load(imageUri).into(imageMedia)
        showToast("Displaying captured media")
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
