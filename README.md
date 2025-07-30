# SwapcardTest

SwapcardTest is an Android application for browsing and discovering random users.

## Features

- Browse users
- Display a specific user details
- bookmark/unbookmark users

## Technologies Used

- Kotlin
- Jetpack Compose for UI
- Hilt for dependency injection
- Android Navigation Component
- Material Design 3
- Gradle with Kotlin DSL

## Project Structure

The project is organized into the following main components:

- `app`: Main application module
- `users`: Feature module for displaying the list of users
- `user-detail`: Feature module for displaying a user detail
- `core`: module containing shared data models, use‑cases and utilities used by both feature modules

## Setup

1. Clone the repository
2. Open the project in Android Studio
3. Sync the project with Gradle files
4. Run the app on an emulator or physical device

## Building and Running

This project uses Gradle for building. You can build the project using Android Studio or by running the following command in the project root:
```
./gradlew build
```
To run the app, use Android Studio's run feature or use:
```
./gradlew installDebug
```


## Project Architecture


The project follows modern Android architecture principles:


- MVI (Model-View-Intent) architecture

- Clean Architecture principles

- Dependency Injection with Hilt

- Jetpack Compose for declarative UI