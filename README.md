<h1 align="center">DIAGNAL PROGRAMMING TEST</h1>

## Download
Go to the [Demo APK]() to download the latest APK.
Go to the [Firebase App Distribution](https://appdistribution.firebase.dev/i/e53cfffe6e879ad3) to download get invite.

## Tech stack & Open-source libraries
- Minimum SDK level 19
- [Kotlin](https://kotlinlang.org/) based + [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) for asynchronous.
- JetPack
  - LiveData - notify domain layer data to views.
  - Lifecycle - dispose of observing data when lifecycle state changes.
  - ViewModel - UI related data holder, lifecycle aware.
- Architecture
  - MVVM Architecture (View - DataBinding - ViewModel - Model)
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - construct the REST APIs and paging network data.
- [Moshi](https://github.com/square/moshi) - A JSON serialization/deserialization library for Kotlin and Java.
- [Paging3.0](https://developer.android.com/topic/libraries/architecture/paging/v3-overview) - A Paging Library helps you load and display small chunks of data at a time.
- [Material-Components](https://github.com/material-components/material-components-android) - Material design components like ripple animation, cardView.


## Architecture
The application is based on MVVM architecture and a repository pattern.

![architecture](screenshots/mvvm.png)