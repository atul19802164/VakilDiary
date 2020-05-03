package com.vakilediary.app.network.responses

import com.vakilediary.app.data.db.entities.User

data class OTPResponse(var statusCode:String,
                       var error:String,
                       var message:String,
                       var user:User)