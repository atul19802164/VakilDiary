package com.vakilediary.app.data.db.entities


import androidx.room.Entity
import androidx.room.PrimaryKey

const val CURRENT_USER_ID = 0

@Entity
data class User(
    var _id: String? = null,
    var mobile:String?=null,
var otp:String?=null,
var token:String?=null
){
    @PrimaryKey(autoGenerate = false)
    var uid: Int = CURRENT_USER_ID
}