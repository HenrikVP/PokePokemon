plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "dk.tec.pokepokemon"
    compileSdk = 34

    defaultConfig {
        applicationId = "dk.tec.pokepokemon"
        minSdk = 26
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    // https://mvnrepository.com/artifact/com.android.volley/volley
    implementation(libs.volley)
    // https://mvnrepository.com/artifact/com.google.code.gson/gson
    implementation(libs.gson)
    // https://mvnrepository.com/artifact/com.squareup.picasso/picasso
    implementation(libs.picasso)
}