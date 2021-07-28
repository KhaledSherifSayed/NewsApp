// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {

    repositories {
        google()
        mavenCentral()
        jcenter()
    }

    dependencies {
        classpath(Libs.gradle.gradle_plugin)
        classpath (Libs.gradle.kotlin_gradle_plugin)
        classpath (Libs.gradle.safe_args_gradle_plugin)
        classpath (Libs.gradle.jacocoVersion)
    }
}



allprojects {
    repositories {
        google()
        mavenCentral()
        jcenter()
        maven { url = uri("https://jitpack.io") }
    }
}

tasks.register("clean", Delete::class){
    delete(rootProject.buildDir)
}
