package com.vakilediary.app.ui.home.profile

import android.content.Context
import androidx.lifecycle.ViewModel
import com.vakilediary.app.repository.UserRepository


class ProfileViewModel(val repository: UserRepository) : ViewModel() {
    var user=repository.getUser()
}