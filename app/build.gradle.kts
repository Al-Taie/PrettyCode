@file:Suppress("UnstableApiUsage")
import com.altaie.buildscr.Config
import com.altaie.buildscr.Config.Version
import com.altaie.buildscr.Modules


plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = Config.APPLICATION_ID
    compileSdk = Version.COMPILE_SDK
    buildToolsVersion = Version.BUILD_TOOLS

    defaultConfig {
        applicationId = Config.APPLICATION_ID
        minSdk = Version.MIN_SDK
        targetSdk = Version.TARGET_SDK
        versionCode = 2
        versionName = "1.1"

        testInstrumentationRunner = Config.ANDROID_TEST_INSTRUMENTATION
        vectorDrawables {
            useSupportLibrary = true
        }
    }



    buildTypes {
        release {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true

            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = Version.JVM
        targetCompatibility = Version.JVM
    }

    kotlinOptions {
        jvmTarget = Version.JVM.toString()
        freeCompilerArgs =
                freeCompilerArgs + listOf(
                        "-opt-in=androidx.compose.ui.ExperimentalComposeUiApi",
                        "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
                        "-opt-in=androidx.compose.animation.ExperimentalAnimationApi",
                        "-opt-in=com.google.accompanist.permissions.ExperimentalPermissionsApi",
                )
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Version.COMPOSE_COMPILER
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    dependenciesInfo {
        includeInApk = false
        includeInBundle = false
    }
}

kotlin {
    jvmToolchain(Version.JVM.majorVersion.toInt())
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.compose.activity)
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.material3)
    implementation(libs.utils.timber)

    testImplementation(libs.test.junit)
    androidTestImplementation(libs.test.ext.junit)
    androidTestImplementation(libs.test.espresso)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.compose.test.junit4)
    debugImplementation(libs.compose.test.manifest)
}

kapt { correctErrorTypes = true }
