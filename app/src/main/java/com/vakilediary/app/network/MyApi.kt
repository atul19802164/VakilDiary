package com.vakilediary.app.network

import com.vakilediary.app.network.responses.LoginResponse
import com.vakilediary.app.network.responses.OTPResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MyApi {

    @FormUrlEncoded
    @POST("users/login-register")
   suspend fun userLogin(
        @Field("mobile") mobile: String
    ): Response<LoginResponse>
    @FormUrlEncoded
    @POST("users/confirm-otp")
    suspend fun confirmOTP(
        @Field("mobile") mobile: String,
        @Field("otp") otp: String
    ): Response<OTPResponse>

    companion object{
        operator fun invoke(networkConnectionInterceptor: NetworkConnectionInterceptor) : MyApi{
            var okHttpClient= OkHttpClient.Builder().addInterceptor(networkConnectionInterceptor).build()
            return Retrofit.Builder().client(okHttpClient)
                .baseUrl("http://139.59.57.1:3078/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }

}