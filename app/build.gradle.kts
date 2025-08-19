plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
}

// All version variables are defined directly in this file
// This avoids the 'Unresolved reference' error from the root project
val appCompatVersion = "1.4.0"
val activityVersion = "1.4.0"
val roomVersion = "2.3.0"
val lifecycleVersion = "2.4.0"
val coroutines = "1.5.2"
val constraintLayoutVersion = "2.1.2"
val materialVersion = "1.4.0"
val junitVersion = "4.13.2"
val coreTestingVersion = "2.1.0"
val espressoVersion = "3.4.0"
val androidxJunitVersion = "1.1.3"
val kotlin_version = "1.8.20"

android {
    namespace = "com.example.thinkdo"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.thinkdo"
        minSdk = 24
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    // Corrected deprecated method for packaging
    packaging {
        resources {
            excludes.add("META-INF/atomicfu.kotlin_module")
        }
    }

    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:$appCompatVersion")
    implementation("androidx.activity:activity-ktx:$activityVersion")

    // Room components
    implementation("androidx.room:room-ktx:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")
    androidTestImplementation("androidx.room:room-testing:$roomVersion")

    // Lifecycle components
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-common-java8:$lifecycleVersion")

    // Kotlin components
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines")

    // UI
    implementation("androidx.constraintlayout:constraintlayout:$constraintLayoutVersion")
    implementation("com.google.android.material:material:$materialVersion")

    // Testing
    testImplementation("junit:junit:$junitVersion")
    androidTestImplementation("androidx.arch.core:core-testing:$coreTestingVersion")
    androidTestImplementation("androidx.test.espresso:espresso-core:$espressoVersion") {
        exclude(group = "com.android.support", module = "support-annotations")
    }
    androidTestImplementation("androidx.test.ext:junit:$androidxJunitVersion")
}
