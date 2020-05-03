package com.vakilediary.app.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.vakilediary.app.repository.UserRepository
import com.vakilediary.app.util.ApiException
import com.vakilediary.app.util.Coroutines
import com.vakilediary.app.util.NoInternetException

class AuthViewModel(var repository: UserRepository) : ViewModel() {
    var mobile:String?=null
    var authListenerLogin:AuthListenerLogin?=null
    var otp:String?=null
    var authListenerVerifyOtp:AuthListenerVerifyOtp?=null
    fun getLoggedInUser()=repository.getUser()
    fun onLoginButtonClick(view:View){
        authListenerLogin!!.onStarted()
        if(mobile.isNullOrEmpty())
authListenerLogin!!.onFailure("Please enter valid mobile number")
        else{
            Coroutines.main{
                try{
                    var loginResponse=repository.userLogin(mobile!!)
                    authListenerLogin!!.onSuccess(loginResponse.message!!)

                }
                catch (e: ApiException){
                    authListenerLogin!!.onFailure(e.message!!)
                }
                catch (e: NoInternetException){
                    authListenerLogin!!.onFailure(e.message!!)
                }
            }
        }
    }
    fun onVerifyOTPButtonClick(view: View){
        authListenerVerifyOtp!!.onStarted()
        if(otp.isNullOrEmpty())
            authListenerVerifyOtp!!.onFailureOtp("Please enter a valid otp")
        else{
            Coroutines.main{
                try{
                    var otpResponse=repository.otpVerification(mobile!!,otp!!)
                    repository.saveUser(otpResponse.user)
                    authListenerVerifyOtp!!.onSuccessOtp(otpResponse.user)

                }
                catch (e: ApiException){
                    authListenerVerifyOtp!!.onFailureOtp(e.message!!)
                }
                catch (e: NoInternetException){
                    authListenerVerifyOtp!!.onFailureOtp(e.message!!)
                }
            }
        }
    }
}