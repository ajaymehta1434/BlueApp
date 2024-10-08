# Project - BlueApp

## Description

BlueApp is your go-to app for exploring a wide variety of book genres and discovering popular books within each category. Whether you're a fan of fiction, non-fiction, fantasy, or any other genre, BlueApp makes it easy to find books that match your reading preferences.

## Features :

Carousel Display: Showcases a variety of items in a carousel view with images and details.

Search Functionality: Allows users to search for items by name or other criteria.

Detailed Statistics: Provides insights into the items, such as the number of items per category and the most common characters in titles. 

Responsive UI: Utilises ViewPager2 for smooth navigation and a BottomSheetDialogFragment for displaying statistics.

## Usage :

Viewing the Carousel: Swipe left or right to navigate through the items in the carousel. Searching: Use the search bar at the top to filter items based on your input. Viewing Statistics: Click on the floating action button (FAB) to view statistics about the items displayed.

## Screenshots

This project includes screenshots to provide a visual overview of the application. You can find them
in the [screenshots](/screenshots) directory.

Here are a few examples:

![Home Screen](screenshots%2Fhome.jpg)

![Home Screen Pager](screenshots%2Fhome_pager.jpg)

![Bottomsheet](screenshots%2Fbottomsheet.jpg)

![Search](screenshots%2Fsearch.jpg)

## Android Studio IDE Setup

For development, the latest version of Android Studio is required. The latest version can be
downloaded from [here](https://developer.android.com/studio)..

## Dependencies

This project leverages several Jetpack Compose libraries to build a modern Android UI:

* **androidx.activity:activity-compose:** This library is s part of the Jetpack Compose toolkit for
  Android, which provides a modern toolkit for building native Android UIs.

* **androidx.core:core-ktx:** Provides Kotlin extensions for core Android framework components.

* **androidx.lifecycle:lifecycle-runtime-ktx:** Offers Kotlin extensions for the Lifecycle
  components,enabling lifecycle-aware behavior in your composables.

* **androidx.compose.material3:material3-android:** The Material3 Design components library,
  offering a wide
  range of pre-built UI elements that adhere to Google's Material Design guidelines, ensuring a
  visually appealing and user-friendly interface.

* **androidx.navigation:navigation-compose:** This library provides Navigation Component support for
  Jetpack Compose Applications.
