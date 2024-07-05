package com.example.imagecompress

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.location.Location
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.io.ByteArrayOutputStream
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var accelerometer: Sensor? = null
    private lateinit var vibrationDataTextView: TextView
    private lateinit var startButton: Button
    private lateinit var previewView: PreviewView
    private lateinit var capturedImageView: ImageView
    private lateinit var cameraExecutor: ExecutorService
    private lateinit var imageCapture: ImageCapture
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val MAX_IMAGE_SIZE = 1024 // Maximum image size in KB
    private val handler = Handler(Looper.getMainLooper())
    private val photoInterval = 2000L // 2 seconds
    private var isDetectionStarted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vibrationDataTextView = findViewById(R.id.tv_vibration_data)
        startButton = findViewById(R.id.button_compress)
        previewView = findViewById(R.id.previewView)
        capturedImageView = findViewById(R.id.iv_captured_image)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        cameraExecutor = Executors.newSingleThreadExecutor()

        startButton.setOnClickListener {
            if (!isDetectionStarted) {
                startVibrationDetection()
                startButton.text = "Stop Detection"
            } else {
                stopVibrationDetection()
                startButton.text = "Start Detection"
            }
        }

        requestPermissions()
    }

    private fun startVibrationDetection() {
        accelerometer?.also { accelerometer ->
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
        }
        isDetectionStarted = true
        startCamera()
    }

    private fun stopVibrationDetection() {
        sensorManager.unregisterListener(this)
        handler.removeCallbacksAndMessages(null)
        isDetectionStarted = false
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener(Runnable {
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(previewView.surfaceProvider)
                }

            imageCapture = ImageCapture.Builder().build()

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, imageCapture
                )
            } catch (exc: Exception) {
                Log.e("CameraXApp", "Use case binding failed", exc)
            }

        }, ContextCompat.getMainExecutor(this))
    }

    private fun takePhoto() {
        val photoFile = File(
            externalMediaDirs.firstOrNull(),
            "photo_${System.currentTimeMillis()}.jpg"
        )

        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(this),
            object : ImageCapture.OnImageSavedCallback {
                override fun onError(exc: ImageCaptureException) {
                    Log.e("CameraXApp", "Photo capture failed: ${exc.message}", exc)
                }

                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                    val msg = "Photo capture succeeded: ${photoFile.absolutePath}"
                    Log.d("CameraXApp", msg)
                    Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()

                    // Display the captured image
                    val bitmap = BitmapFactory.decodeFile(photoFile.absolutePath)
                    val compressedBitmap = compressBitmap(bitmap)
                    runOnUiThread {
                        capturedImageView.setImageBitmap(compressedBitmap)
                        capturedImageView.visibility = ImageView.VISIBLE
                    }

                    // Save image with location data
                    saveImageWithLocation(photoFile)
                }
            }
        )
    }

    private fun compressBitmap(bitmap: Bitmap): Bitmap {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream) // Adjust compression quality as needed
        val byteArray = stream.toByteArray()
        val options = BitmapFactory.Options()
        options.inPreferredConfig = Bitmap.Config.RGB_565
        options.inSampleSize = calculateInSampleSize(options, MAX_IMAGE_SIZE)
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size, options)
    }

    private fun calculateInSampleSize(options: BitmapFactory.Options, maxSize: Int): Int {
        val imageByteCount = options.outHeight * options.outWidth * 4 // ARGB_8888 format
        var inSampleSize = 1
        if (imageByteCount > maxSize * 1024) {
            val halfHeight = options.outHeight / 2
            val halfWidth = options.outWidth / 2
            while ((halfHeight / inSampleSize) * (halfWidth / inSampleSize) * 4 > maxSize * 1024) {
                inSampleSize *= 2
            }
        }
        return inSampleSize
    }

    private fun saveImageWithLocation(photoFile: File) {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                location?.let {
                    val latitude = location.latitude
                    val longitude = location.longitude
                    Log.d("Location", "Latitude: $latitude, Longitude: $longitude")

                    val newName = appendLocationToFileName(photoFile, latitude, longitude)
                    val renamedFile = File(photoFile.parent, newName)

                    if (photoFile.renameTo(renamedFile)) {
                        Log.d("Location", "File renamed successfully: ${renamedFile.absolutePath}")
                    } else {
                        Log.e("Location", "Failed to rename file")
                    }
                }
            }
            .addOnFailureListener { e ->
                Log.e("Location", "Failed to get location: ${e.message}", e)
            }
    }

    private fun appendLocationToFileName(file: File, latitude: Double, longitude: Double): String {
        val fileName = file.nameWithoutExtension
        val extension = file.extension
        val dateFormat = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
        val formattedDate = dateFormat.format(Date())
        return "${fileName}_${formattedDate}_${latitude}_${longitude}.$extension"
    }

    private fun requestPermissions() {
        val requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            if (permissions.all { it.value }) {
                startCamera()
            } else {
                Toast.makeText(this, "Camera permissions are required", Toast.LENGTH_SHORT).show()
            }
        }

        requestPermissionLauncher.launch(arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION
        ))
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]
            val acceleration = Math.sqrt((x * x + y * y + z * z).toDouble()).toFloat()

            vibrationDataTextView.text = "Vibration: $acceleration m/s²"

            if (acceleration > 1.0) {
                handler.postDelayed({
                    takePhoto()
                }, photoInterval)
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Do nothing
    }

    override fun onPause() {
        super.onPause()
        if (isDetectionStarted) {
            sensorManager.unregisterListener(this)
        }
    }

    override fun onResume() {
        super.onResume()
        if (isDetectionStarted) {
            accelerometer?.also { accelerometer ->
                sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }
}
