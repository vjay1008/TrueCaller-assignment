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
import org.jsoup.Jsoup
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    var mAPIInterface: ApiInterface = ApiUtils.apiInterface
    lateinit var tempResp: String
    lateinit var firstCondResult: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_run.setOnClickListener {
            tenthCharacterAPI()
            secondCondAPI()
            thirdCondAPI()
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
                        val html = response.body()!!.string()
                        val document = Jsoup.parse(html)

                        solveFirstCond(document.toString())

                    }

                    if (response.code() == 400) {

                        val errorResponse = Gson().fromJson(
                            response.errorBody()?.string(),
                            DefaultResponse::class.java
                        )

                        Toast.makeText(
                                applicationContext,
                                "" + errorResponse.errorMessage,
                                Toast.LENGTH_LONG
                            )
                            .show()
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
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
                        val html = response.body()!!.string()
                        val document = Jsoup.parse(html)

                        solveSecondCond(document.toString())

                    }

                    if (response.code() == 400) {

                        val errorResponse = Gson().fromJson(
                            response.errorBody()?.string(),
                            DefaultResponse::class.java
                        )

                        Toast.makeText(
                                applicationContext,
                                "" + errorResponse.errorMessage,
                                Toast.LENGTH_LONG
                            )
                            .show()
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Toast.makeText(applicationContext, "Failed!! Check URL", Toast.LENGTH_LONG)
                        .show()
                    process_img_2.visibility = View.GONE

                }
            })
    }

    fun thirdCondAPI() {

        process_img_3.visibility = View.VISIBLE
        mAPIInterface.fetchForThirdCond()
            .enqueue(object : Callback<ResponseBody> {

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    process_img_3.visibility = View.GONE


                    if (response.code() == 200) {
                        val html = response.body()!!.string()
                        val document = Jsoup.parse(html)

                        solveThirdCond(document.toString())

                    }

                    if (response.code() == 400) {

                        val errorResponse = Gson().fromJson(
                            response.errorBody()?.string(),
                            DefaultResponse::class.java
                        )

                        Toast.makeText(
                                applicationContext,
                                "" + errorResponse.errorMessage,
                                Toast.LENGTH_LONG
                            )
                            .show()
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Toast.makeText(applicationContext, "Failed!! Check URL", Toast.LENGTH_LONG)
                        .show()
                    process_img_3.visibility = View.GONE

                }
            })
    }


    fun solveFirstCond(resp: String) {
        Log.i("10th", resp)

        if (resp.length >= 9) {
            if (resp[9].toString().equals(" "))
                tv1_value.text = "\b (space)"
            else
                tv1_value.text = resp[9].toString()
            Log.i("first 10th", "" + resp[9].toString())
        }

    }

    fun solveSecondCond(resp: String) {

        var tempSecondCond = ""
        Log.i("second-", resp)
        for (i in resp.indices) {

            if (i > 0 && i % 9 == 0) {
                tempSecondCond += resp[i]
            }
        }
        tv2_value.text = tempSecondCond
    }

    fun solveThirdCond(resp: String) {

        val uniqueWords: MutableSet<String> = HashSet()
        val words: List<String> = resp.split(" ")
        for (i in words.indices) {
            uniqueWords.add(words[i])
            Log.i("unique ", words[i])
        } //Here you need not to check with set because it wont allow duplicates

        tv3_value.text = uniqueWords.size.toString()
    }


}
