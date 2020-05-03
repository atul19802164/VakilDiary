package com.vakilediary.app.ui.auth

import com.vakilediary.app.data.db.entities.User

interface AuthListenerVerifyOtp{
    fun onStarted()
    fun onSuccessOtp(user: User)
    fun onFailureOtp(message: String)
}