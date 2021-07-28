object Libs {

    // sharedPrefs alternative
    const val dataStore: String = "androidx.datastore:datastore-preferences:_"

    //Loading
    const val aviLoader: String = "com.wang.avi:library:_"

    // expandable_text_view
    const val expandable_text_view : String = "com.ms-square:expandableTextView:_"
    // coroutines adapter
    const val courtinesAdapter : String = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:_"

    // httpLogger
    const val logger : String = "com.github.ihsanbal:LoggingInterceptor:3.1.0"

    // rooms
    object rooms {
        const val rooms_runtime = "androidx.room:room-runtime:_"
        const val rooms_complier = "com.google.code.gson:gson:_"
    }

    object services {
        const val google_services = "com.google.gms:google-services:_"
        const val gson = "com.google.code.gson:gson:_"
    }

    object koin {
        const val koin_android: String = "org.koin:koin-android:_"
        const val koin_android_viewModel: String = "org.koin:koin-androidx-viewmodel:_"
        const val koin_androidx_scope: String = "org.koin:koin-androidx-scope:_"
        const val koin_android_ext: String = "org.koin:koin-androidx-ext:_"
    }

    object dimensions {
        const val sdp: String = "com.intuit.sdp:sdp-android:_"
        const val ssp: String = "com.intuit.ssp:ssp-android:_"
    }

    object test {
        const val kaspresso: String = "com.kaspersky.android-components:kaspresso:_"
        const val kotest: String = "io.kotlintest:kotlintest-runner-junit5:_"
    }

    object gradle {
        const val gradle_plugin = "com.android.tools.build:gradle:_"
        const val kotlin_gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:_"
        const val safe_args_gradle_plugin = "androidx.navigation:navigation-safe-args-gradle-plugin:_"
        const val jacocoVersion  = "org.jacoco:org.jacoco.core:_"
    }
}
