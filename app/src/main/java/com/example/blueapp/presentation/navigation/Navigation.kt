package com.example.blueapp.presentation.navigation

sealed class Navigation(val destination: String) {
    data object Books : Navigation("books")
}