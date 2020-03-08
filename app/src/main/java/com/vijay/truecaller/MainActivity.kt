package com.vijay.truecaller

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.vijay.truecaller.model.ApiInterface
import com.vijay.truecaller.model.ApiUtils
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    var mAPIInterface: ApiInterface = ApiUtils.apiInterface
    lateinit var tempResp : String
    lateinit var firstCondResult : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_run.setOnClickListener {
            tenthCharacterAPI()
        }
    }

    fun tenthCharacterAPI() {

        process_img_1.visibility = View.VISIBLE
        mAPIInterface.fetchForFirstCond()
            .enqueue(object : Callback<ResponseBody> {

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    process_img_1.visibility = View.GONE


                    if (response.code() == 200) {
                        Log.i("First 200 ", response.body().toString())

//                        tempResp = response.body().toString()
                        solveFirstCond(response.body().toString())

                    }

                    if (response.code() == 400) {

                        val errorResponse = Gson().fromJson(
                            response.errorBody()?.string(),
                            DefaultResponse::class.java
                        )

                        Log.e("400 First", "" + errorResponse.errorMessage)
                        Toast.makeText(
                            applicationContext,
                            "" + errorResponse.errorMessage,
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.e("First Cond", t.toString())
                    Toast.makeText(applicationContext, "Failed!! Check URL", Toast.LENGTH_LONG)
                        .show()
                    process_img_1.visibility = View.GONE

                }
            })
    }

    fun secondCondAPI() {

        process_img_2.visibility = View.VISIBLE
        mAPIInterface.fetchForSecCond()
            .enqueue(object : Callback<ResponseBody> {

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    process_img_2.visibility = View.GONE


                    if (response.code() == 200) {
                        Log.i("First 200 ", response.body().toString())

                        tempResp = response.body().toString()
                        solveFirstCond(tempResp)

                    }

                    if (response.code() == 400) {

                        val errorResponse = Gson().fromJson(
                            response.errorBody()?.string(),
                            DefaultResponse::class.java
                        )

                        Log.e("400 First", "" + errorResponse.errorMessage)
                        Toast.makeText(
                            applicationContext,
                            "" + errorResponse.errorMessage,
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.e("First Cond", t.toString())
                    Toast.makeText(applicationContext, "Failed!! Check URL", Toast.LENGTH_LONG)
                        .show()
                    process_img_1.visibility = View.GONE

                }
            })
    }


    fun solveFirstCond(resp : String){

        if (resp.length >= 11 ) {
            tv1_value.text = resp[11].toString()
        }

    }


}
