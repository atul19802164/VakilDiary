package com.vakilediary.app.ui.home.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.vakilediary.app.R
import com.vakilediary.app.data.db.AppDatabase
import com.vakilediary.app.data.preferences.PreferenceProvider
import com.vakilediary.app.databinding.ActivityLoginBinding
import com.vakilediary.app.databinding.ActivityProfileBinding
import com.vakilediary.app.network.MyApi
import com.vakilediary.app.network.NetworkConnectionInterceptor
import com.vakilediary.app.repository.UserRepository
import com.vakilediary.app.ui.auth.AuthViewModel
import com.vakilediary.app.ui.auth.AuthViewModelFactory
import com.vakilediary.app.util.toast

class Profile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        var networkConnectionInterceptor= NetworkConnectionInterceptor(this)
        var api= MyApi(networkConnectionInterceptor)
        var db= AppDatabase(this)
        var reprosity= UserRepository(api,db)
        var factory= ProfileViewModelFactory(reprosity)
        val binding: ActivityProfileBinding = DataBindingUtil.setContentView(this, R.layout.activity_profile)
        val viewModel = ViewModelProviders.of(this,factory).get(ProfileViewModel::class.java)
        binding.viewmodel=viewModel
        binding.lifecycleOwner=this
    }
}
