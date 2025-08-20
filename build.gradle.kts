// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    id("androidx.navigation.safeargs") version "2.7.7" apply false
}

// Ext variables for version management
// This is the correct way to declare shared versions in Kotlin DSL (.kts)
val activityVersion by extra("1.4.0")
val appCompatVersion by extra("1.4.0")
val constraintLayoutVersion by extra("2.1.2")
val coreTestingVersion by extra("2.1.0")
val coroutines by extra("1.5.2")
val lifecycleVersion by extra("2.4.0")
val materialVersion by extra("1.4.0")
val roomVersion by extra("2.3.0")
val junitVersion by extra("4.13.2")
val espressoVersion by extra("3.4.0")
val androidxJunitVersion by extra("1.1.3")
