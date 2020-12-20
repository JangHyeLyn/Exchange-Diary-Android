object Dependencies {

    object AndroidXCore {
        private const val VERSION = "1.3.2"

        const val CORE_KTX = "androidx.core:core-ktx:$VERSION"
    }

    object Retrofit {
        private const val VERSION = "2.9.0"

        const val RETROFIT = "com.squareup.retrofit2:retrofit:$VERSION"
        const val CONVERTER_GSON = "com.squareup.retrofit2:converter-gson:$VERSION"
    }

    object Dagger {
        private const val VERSION = "2.30.1"

        const val DAGGER = "com.google.dagger:dagger:$VERSION"
        const val DAGGER_COMPILER = "com.google.dagger:dagger-compiler:$VERSION"

        const val DAGGER_ANDROID = "com.google.dagger:dagger-android:$VERSION"
        const val DAGGER_ANDROID_SUPPORT = "com.google.dagger:dagger-android-support:$VERSION"
        const val DAGGER_ANDROID_PROCESSOR = "com.google.dagger:dagger-android-processor:$VERSION"
    }

    object Koin {
        private const val VERSION = "2.2.1"

        const val KOIN_SCOPE = "org.koin:koin-androidx-scope:$VERSION"
        const val KOIN_VIEWMODEL = "org.koin:koin-androidx-viewmodel:$VERSION"
        const val KOIN_FRAGMENT = "org.koin:koin-androidx-fragment:$VERSION"
        const val KOIN_WORKMANAGER = "org.koin:koin-androidx-workmanager:$VERSION"
        const val KOIN_COMPOSE = "org.koin:koin-androidx-compose:$VERSION"
        const val KOIN_EXT = "org.koin:koin-androidx-ext:$VERSION"
    }

    object Firebase {
        private const val VERSION = "26.1.1"

        const val BOM = "com.google.firebase:firebase-bom:$VERSION"
        const val KTX = "com.google.firebase:firebase-analytics-ktx"
    }

    const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.50"
    const val APPCOMPAT = "androidx.appcompat:appcompat:1.2.0"
    const val TIMBER = "com.jakewharton.timber:timber:4.7.1"
    const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:2.0.4"
    const val MATERIAL = "com.google.android.material:material:1.2.1"

    const val GSON = "com.google.code.gson:gson:2.8.5"
    const val LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:4.9.0"

    object Test {
        const val JUNIT = "junit:junit:4.12"
    }

    object AndroidXTest {
        const val JUNIT = "androidx.test.ext:junit:1.1.2"
        const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:3.3.0"
    }
}