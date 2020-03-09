package com.vijay.truecaller.model


import androidx.viewpager.widget.PagerAdapter
import com.google.gson.internal.bind.ArrayTypeAdapter.FACTORY
import com.google.gson.internal.bind.DateTypeAdapter.FACTORY
import com.google.gson.internal.bind.ObjectTypeAdapter.FACTORY
import com.vijay.truecaller.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitClient {

    var retrofit: Retrofit? = null

    fun getClient(baseUrl: String): Retrofit? {

        if (retrofit == null) {


            val interceptor = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                interceptor.level = HttpLoggingInterceptor.Level.BODY
            } else {
                interceptor.level = HttpLoggingInterceptor.Level.NONE
            }


            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .build()

            retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return retrofit

    }


}


