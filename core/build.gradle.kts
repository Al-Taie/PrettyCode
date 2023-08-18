import com.altaie.buildscr.Config.Version

plugins {
    id ("java-library")
    id ("org.jetbrains.kotlin.jvm")
    alias(libs.plugins.kotlin.serialization)
}

java {
    sourceCompatibility = Version.JVM
    targetCompatibility = Version.JVM
}

dependencies {
    implementation(libs.web.retrofit)
    implementation(libs.web.gson)
    api(libs.httpcore)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.serialization.json)

}
