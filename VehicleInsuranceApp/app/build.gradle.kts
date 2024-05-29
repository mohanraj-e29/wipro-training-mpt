plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.example.vehicleinsuranceapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.vehicleinsuranceapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
<<<<<<< HEAD
<<<<<<< HEAD
=======

    implementation("com.android.volley:volley:1.2.0")

>>>>>>> e5820d4 (Initial commit on appdev branch)
=======
>>>>>>> origin/appdev
}