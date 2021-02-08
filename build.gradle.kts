// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        jcenter()
        maven { url = uri("https://jitpack.io") }
    }
    dependencies {
        classpath("com.google.gms:google-services:${Version.GOOGLE_SERVICE_VERSION}")
        classpath("com.android.tools.build:gradle:${Version.GRADLE_VERSION}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.KOTLIN_VERSION}")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean", Delete::class){
    delete(rootProject.buildDir)
}