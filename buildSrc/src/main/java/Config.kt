package com.altaie.buildscr

import org.gradle.api.JavaVersion

object Config {
    object Version {
        const val MIN_SDK = 24
        const val TARGET_SDK = 34
        const val COMPILE_SDK = 34
        val JVM = JavaVersion.VERSION_17
        const val BUILD_TOOLS = "34.0.0"
        const val COMPOSE_COMPILER = "1.5.1"
    }

    const val APPLICATION_ID = "com.altaie.prettycode"
    const val ANDROID_TEST_INSTRUMENTATION = "androidx.test.runner.AndroidJUnitRunner"
}
