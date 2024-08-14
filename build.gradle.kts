// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.1")
    }
}


plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.jetbrains.kotlin.parcelize) apply false


}