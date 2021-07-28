import KotlinX.html

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    jacoco
    id("androidx.navigation.safeargs")
    id("org.jlleitschuh.gradle.ktlint") version "9.4.0"
    id("com.google.secrets_gradle_plugin") version "0.6"
}

tasks.withType<JacocoReport> {
    reports {
        xml.isEnabled = true
        csv.isEnabled = false
        html.destination = file("${buildDir}/reports/jacoco")
    }
}

secrets {
    // Optionally specify a different file name containing your secrets.
    // The plugin defaults to "local.properties"
    propertiesFileName = "secrets.properties"
}

android {

    compileSdkVersion(30)

    defaultConfig {
        applicationId = "com.ibtikar.mvvm_starter_koin_coroutines"
        minSdkVersion(26)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }


    buildTypes {

        getByName("debug") {
            isDebuggable = true
            isTestCoverageEnabled = true
        }

        getByName("release") {

            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    kotlinOptions {
        // We have to add the explicit cast before accessing the options itself.
        val options = this
        options.jvmTarget = "1.8"
    }

    compileOptions {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        dataBinding = true
    }

    packagingOptions {
        exclude("META-INF/LICENSE.md")
        exclude("META-INF/LICENSE-notice.md")
    }
}


dependencies {

    implementation(Kotlin.stdlib.jdk8)
    implementation(AndroidX.multidex)
    implementation(AndroidX.core.ktx)
    implementation(AndroidX.appCompat)
    implementation(Google.android.material)
    implementation(AndroidX.constraintLayout)
    implementation(AndroidX.activityKtx)
    implementation(AndroidX.activity)
    implementation(AndroidX.fragmentKtx)
    implementation(AndroidX.fragment)

    implementation(Libs.logger) { exclude("org.json", "json") }

    implementation(Square.okHttp3.okHttp)
    implementation(Square.okHttp3.loggingInterceptor)
    implementation(Square.retrofit2.retrofit)
    implementation(Square.retrofit2.converter.gson)
    implementation(Libs.courtinesAdapter)

    implementation(AndroidX.lifecycle.runtime)
    implementation(AndroidX.lifecycle.viewModelKtx)
    implementation(AndroidX.lifecycle.liveDataKtx)

    implementation(AndroidX.recyclerView)
    // navigation
    implementation(AndroidX.navigation.fragmentKtx)
    implementation(AndroidX.navigation.uiKtx)
    // coroutines
    implementation(KotlinX.coroutines.core)
    implementation(KotlinX.coroutines.android)
    // coil
    implementation(COIL)
    // gson
    implementation(Libs.services.gson)
    // Koin
    implementation(Libs.koin.koin_android)
    implementation(Libs.koin.koin_android_viewModel)
    implementation(Libs.koin.koin_androidx_scope)
    implementation(Libs.koin.koin_android_ext)
    //Loading
    implementation(Libs.aviLoader)
    // expandableTextView
    implementation(Libs.expandable_text_view)
    //Dimentions
    implementation(Libs.dimensions.sdp)
    implementation(Libs.dimensions.ssp)
    // rooms
    implementation(AndroidX.room.runtime)
    implementation(AndroidX.room.migration)
    implementation(AndroidX.room.ktx)
    kapt(AndroidX.room.compiler)
    // Testing
    implementation(AndroidX.test.core)
    implementation(AndroidX.test.coreKtx)
    implementation(AndroidX.test.ext.junit)
    implementation(AndroidX.test.ext.junitKtx)
    implementation(AndroidX.test.runner)
    implementation(AndroidX.test.rules)
    implementation(AndroidX.archCore.testing)
    implementation(Testing.MockK)
    implementation(Testing.mockito)
    androidTestImplementation(Libs.test.kaspresso)
    androidTestImplementation (Libs.test.kotest)

}