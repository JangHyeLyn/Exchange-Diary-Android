plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("kotlin-android")
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

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
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

    implementation(Dependencies.AndroidXCore.CORE_KTX)
    implementation(Dependencies.MATERIAL)

    implementation(Dependencies.TIMBER)

    implementation(Dependencies.Retrofit.RETROFIT)
    implementation(Dependencies.Retrofit.CONVERTER_GSON)

    implementation(Dependencies.GSON)
    implementation(Dependencies.LOGGING_INTERCEPTOR)

    implementation(Dependencies.Koin.KOIN_SCOPE)
    implementation(Dependencies.Koin.KOIN_VIEWMODEL)
    implementation(Dependencies.Koin.KOIN_FRAGMENT)
    implementation(Dependencies.Koin.KOIN_WORKMANAGER)
    implementation(Dependencies.Koin.KOIN_COMPOSE)
    implementation(Dependencies.Koin.KOIN_EXT)

    implementation(platform(Dependencies.Firebase.BOM))
    implementation(Dependencies.Firebase.KTX)

    implementation(Dependencies.KAKAO_LOGIN)
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${rootProject.extra["kotlin_version"]}")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")

    testImplementation(Dependencies.Test.JUNIT)
    androidTestImplementation (Dependencies.AndroidXTest.JUNIT)
    androidTestImplementation (Dependencies.AndroidXTest.ESPRESSO_CORE)
}