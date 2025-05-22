plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.carcar.telemedicine"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.carcar.telemedicine"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.glide)
    annotationProcessor(libs.glide.compiler)
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("pl.droidsonroids.gif:android-gif-drawable:1.2.27")
    implementation("androidx.room:room-runtime:2.6.1")
    annotationProcessor("androidx.room:room-compiler:2.6.1")
    implementation ("androidx.biometric:biometric:1.2.0-alpha05")
    implementation("com.google.code.gson:gson:2.8.8")
    implementation("pl.droidsonroids.gif:android-gif-drawable:1.2.25")
    implementation ("androidx.room:room-runtime:2.6.1")
    annotationProcessor ("androidx.room:room-compiler:2.6.1")
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}