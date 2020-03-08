package com.vijay.truecaller.model

import com.vijay.truecaller.Common


object ApiUtils {


    val apiInterface: ApiInterface
    get() = RetrofitClient.getClient(Common.BASE_URL)!!.create(ApiInterface::class.java)

}