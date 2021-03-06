<p align="center">
  <img src="Preview/articles.jpg" width="100%" height="350"/>
</p>

# Popular Articless in Kotlin π
[![GitHub license](https://img.shields.io/badge/License-Khaled-blue.svg)](LICENSE.txt)
[![Android Weekly](https://img.shields.io/badge/Android%20Weekly-%23406-2CA3E6.svg?style=flat)](http://androidweekly.net/issues/issue-406)
[![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://ktlint.github.io/)
![Github Followers](https://img.shields.io/github/followers/KhaledSherifSayed?label=Follow&style=social)
![GitHub forks](https://img.shields.io/github/forks/KhaledSherifSayed/NewsApp?style=social)
![GitHub watchers](https://img.shields.io/github/watchers/KhaledSherifSayed/NewsApp?style=social)
![Twitter Follow](https://img.shields.io/twitter/follow/Meslmawy?label=Follow&style=social)


**News App** is a sample  Android application π± showing most popular articles of the world π built to demonstrate use of *Modern Android development* tools.
 Dedicated to all Android Developers with β€οΈ. 

***You can Install and test latest Popular Articles app from below π***

[![Articles App](https://img.shields.io/badge/Popularπ-APK-red.svg?style=for-the-badge&logo=android)](https://github.com/KhaledSherifSayed/NewsApp/blob/main/app/releases/latest/download/app-debug.apk)

## Build || Run 
- You must login [here](https://newsapi.org) getting API-KEY and Update x's values in [Secret-File](https://github.com/KhaledSherifSayed/NewsApp/blob/master/secrets.properties) with your api key.

## About
It simply loads **Popular Articles** data from API.Peoples will be always loaded from Remote data (from API). 
- Clean and Simple Material UI.
- Clean and Simple Architecture(MVVVM).


## Features 
- user can select country and categories which would like to hear about them in Onboarding Screen. 
- user see headlines from the selected choices,headlines ordred by latest to oldest.
- user can open headline in browser.
- user can search headlines with categories choice and can save his favorite articles.

*Dummy API is used in this app. JSON response is statically hosted [here](https://developer.nytimes.com)*.

## ScreenShots
<p align="center">
<img src="/Preview/1 (1).png" width="32%"/>
<img src="/Preview/1 (2).png" width="32%"/>
<img src="/Preview/1 (3).png" width="32%"/>
<img src="/Preview/1 (4).png" width="32%"/>
<img src="/Preview/1 (5).png" width="32%"/>
<img src="/Preview/1 (6).png" width="32%"/>
<img src="/Preview/1 (7).png" width="32%"/>
</p>


## Architecture
This app uses [***MVVM (Model View View-Model)***](https://developer.android.com/jetpack/docs/guide#recommended-app-arch) architecture.

![](https://miro.medium.com/max/3000/1*TTKpvdzyNXfPBhVyRqD6EA.png)

## Built With π 
- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - For asynchronous and more..
- [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-flow/) - A cold asynchronous data stream that sequentially emits values and completes normally or with an exception.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
  - [StateView](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow) - Flow APIs that enable flows to optimally emit state updates and emit values to multiple consumers.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes. 
  - [DataBinding](https://developer.android.com/topic/libraries/data-binding) - that allows you to bind UI components in your layouts to data sources in your app using a declarative format rather than programmatically.
  - [Room](https://developer.android.com/topic/libraries/architecture/room) - SQLite object mapping library.
- [Dependency Injection](https://developer.android.com/training/dependency-injection) - 
  - [`Koin`](https://insert-koin.io/) DI Version π‘οΈ
- [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.
- [Coil](https://github.com/coil-kt/coil) - An image loading library for Android backed by Kotlin Coroutines.
- [Material Components for Android](https://github.com/material-components/material-components-android) - Modular and customizable Material Design UI components for Android.
- [Gradle Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html) - For writing Gradle build scripts using Kotlin.
- [Gradle Refresh Version](https://jmfayard.github.io/refreshVersions/setup) - Centralize your dependencies in a proper file format.
- [Secrets Gradle Plugin for Android](https://github.com/google/secrets-gradle-plugin)  - A Gradle plugin for providing your secrets securely to your Android project.


# How to generate lint reports ?

Open terminal and type the following command
``` ./gradlew lint```

The lint report will be generated on the following path.
``` app/build/reports ```


## Soon ππͺ
 - articles will be always loaded from local database. Remote data (from API) and Local data is always synchronized.
 -  Try with [Hilt-Dagger](https://dagger.dev/hilt/) - Standard library to incorporate Dagger dependency injection into an Android application.
 
## Find this repository useful? :heart:
Support it by joining __[stargazers](https://github.com/KhaledSherifSayed/PopularPeople/stargazers)__ for this repository. :star: <br>
And __[follow](https://github.com/KhaledSherifSayed)__ me for my next creations! π€©

**Contributed By:** [Khaled Sherif](https://github.com/KhaledSherifSayed)

## License
```

Copyright (c) 2020 Khaled  Sherif
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NON INFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE

```
