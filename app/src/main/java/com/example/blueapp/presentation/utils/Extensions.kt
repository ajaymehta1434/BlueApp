package com.example.blueapp.presentation.utils

import android.view.View


object Extensions {
    fun View.toggleVisibility(shouldDisplay: Boolean) {
        if (shouldDisplay) {
            this.visibility = View.VISIBLE
        } else {
            this.visibility = View.GONE
        }
    }
}