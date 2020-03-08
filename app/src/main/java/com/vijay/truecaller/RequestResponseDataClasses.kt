package com.vijay.truecaller

import com.google.gson.annotations.SerializedName


//-------------------------------------------------------common error response
data class DefaultResponse(
    @SerializedName("message") var errorMessage: String
)


