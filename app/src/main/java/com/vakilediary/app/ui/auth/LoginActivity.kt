package com.vakilediary.app.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.vakilediary.app.R
import com.vakilediary.app.data.db.AppDatabase
import com.vakilediary.app.data.db.entities.User
import com.vakilediary.app.data.preferences.PreferenceProvider
import com.vakilediary.app.databinding.ActivityLoginBinding
import com.vakilediary.app.network.MyApi
import com.vakilediary.app.network.NetworkConnectionInterceptor
import com.vakilediary.app.repository.UserRepository
import com.vakilediary.app.ui.home.profile.Profile
import com.vakilediary.app.util.hide
import com.vakilediary.app.util.show
import com.vakilediary.app.util.snackbar
import com.vakilediary.app.util.toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(),AuthListenerLogin,AuthListenerVerifyOtp {
    override fun onSuccessOtp(user:User) {
        login_progress_bar.hide()
    }

    override fun onFailureOtp(message: String) {
    toast(message)
     }

    override fun onStarted() {
login_progress_bar.show()
    }
    override fun onSuccess(message: String) {
        login_progress_bar.hide()
        login_layout.snackbar(message)
        login_btn.visibility= View.INVISIBLE
        mobile_number_login.visibility=View.INVISIBLE
        verify_otp_btn.visibility=View.VISIBLE
        otp_login.visibility=View.VISIBLE
    }

    override fun onFailure(message: String) {
        login_progress_bar.hide()
login_layout.snackbar(message)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var networkConnectionInterceptor= NetworkConnectionInterceptor(this)
        var api= MyApi(networkConnectionInterceptor)
        var db= AppDatabase(this)
        var reprosity= UserRepository(api,db)
        var factory=AuthViewModelFactory(reprosity)
        var prefs=PreferenceProvider(this)
        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this,factory).get(AuthViewModel::class.java)
        binding.viewmodel=viewModel
        var user=viewModel.getLoggedInUser()
        if(user!=null)
        {
            user.observe(this, Observer {
                prefs.savelastSavedAt(it.token!!)
            })
            startActivity(Intent(this,Profile::class.java))
            finish()
        }
        viewModel.authListenerLogin=this
        viewModel.authListenerVerifyOtp=this
    }
}
