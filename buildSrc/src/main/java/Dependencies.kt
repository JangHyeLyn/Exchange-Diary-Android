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

    const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.50"
    const val APPCOMPAT = "androidx.appcompat:appcompat:1.2.0"
    const val TIMBER = "com.jakewharton.timber:timber:4.7.1"
    const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:2.0.4"
    const val MATERIAL = "com.google.android.material:material:1.2.1"

    const val GSON = "com.google.code.gson:gson:2.8.5"

    object Test {
        const val JUNIT = "junit:junit:4.12"
    }

    object AndroidXTest {
        const val JUNIT = "androidx.test.ext:junit:1.1.2"
        const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:3.3.0"
    }
}