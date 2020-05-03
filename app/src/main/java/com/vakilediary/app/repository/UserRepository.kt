package com.vakilediary.app.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vakilediary.app.data.db.AppDatabase
import com.vakilediary.app.data.db.entities.User
import com.vakilediary.app.network.MyApi
import com.vakilediary.app.network.SafeApiRequest
import com.vakilediary.app.network.responses.LoginResponse
import com.vakilediary.app.network.responses.OTPResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository(var api: MyApi, var db:AppDatabase):SafeApiRequest() {
   suspend fun userLogin(mobile:String):  LoginResponse{
return apiRequest { api.userLogin(mobile) }
    }
    suspend fun otpVerification(mobile: String,otp:String):OTPResponse{
        return apiRequest { api.confirmOTP(mobile,otp) }
    }
    suspend fun saveUser(user: User){
        db.getUserDao().upsert(user)
    }
    fun getUser()=db.getUserDao().getuser()
}