package com.vijay.truecaller.model

import com.vijay.truecaller.Common.Companion.BASE_URL
import com.vijay.truecaller.DefaultResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface ApiInterface {

    @GET(BASE_URL)
    fun fetchForFirstCond(): Call<ResponseBody>

    @GET(BASE_URL)
    fun fetchForSecCond(): Call<ResponseBody>

    @GET(BASE_URL)
    fun requestToken(): Call<ResponseBody>

}