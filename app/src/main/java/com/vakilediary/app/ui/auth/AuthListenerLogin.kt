package com.vakilediary.app.ui.auth

import androidx.lifecycle.LiveData


interface AuthListenerLogin {
    fun onStarted()
    fun onSuccess(message: String)
    fun onFailure(message: String)
}