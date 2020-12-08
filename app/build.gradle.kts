plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(Version.COMPLIE_SDK)

    defaultConfig {
        minSdkVersion(Version.MIN_SDK)
        targetSdkVersion(Version.TARGET_SDK)
        versionCode = Version.APP_VERSION_CODE
        versionName = Version.APP_VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            isDebuggable = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Dependencies.KOTLIN)
    implementation(Dependencies.APPCOMPAT)
    implementation(Dependencies.CONSTRAINT_LAYOUT)

    implementation (Dependencies.AndroidXCore.CORE_KTX)
    implementation(Dependencies.MATERIAL)

    implementation(Dependencies.TIMBER)

    implementation(Dependencies.Retrofit.RETROFIT)
    implementation(Dependencies.Retrofit.CONVERTER_GSON)

    implementation(Dependencies.GSON)

    testImplementation(Dependencies.Test.JUNIT)

    androidTestImplementation (Dependencies.AndroidXTest.JUNIT)
    androidTestImplementation (Dependencies.AndroidXTest.ESPRESSO_CORE)
}