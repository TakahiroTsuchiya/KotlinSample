package com.sample.sampleapplication_madebykotlin.viewmodel

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.text.TextUtils
import android.util.Log
import com.android.databinding.library.baseAdapters.BR
import com.sample.sampleapplication_madebykotlin.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InputDetail(title: String, body: String, to: String = ""): BaseObservable() {

    val TAG = InputDetail::class.java.simpleName

    @Bindable
    var title = title
        set(value) {
            field = value
            notifyPropertyChanged(BR.submitConditon)
        }

    @Bindable
    var body = body
        set(value) {
            field = value
            notifyPropertyChanged(BR.submitConditon)
        }

    @Bindable
    var to = to
        set(value) {
            field = value
            notifyPropertyChanged(BR.submitConditon)
        }

    @get:Bindable
    var isSubmitConditon:Boolean = false
        get() {
            val isCondition = (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(body) && !TextUtils.isEmpty(to))
            Log.d("InputDetail", "CONDITION : $isCondition")
            return isCondition
        }

    @Bindable
    var isSendMessageVisibility:Boolean = false

    @Bindable
    var sendMessage:String = ""

    fun onClickSendNotifications() {

        Log.d("InputDetail", " click send Button, call sendNotification. START --------------")

        // 多段リクエストの禁止
        isSubmitConditon = false

        Log.d("InputDetail", " click send Button, TITLE $title")
        Log.d("InputDetail", " click send Button, BODY $body")
        Log.d("InputDetail", " click send Button, TO $to")

        // TODO: 外部メソッドに処理を外だし
        // TODO: 環境変数ごとに値を切り分けする
        val TEST_API_KEY = ""

        // API Service を呼び出す
        val service = FCMApiService.Factory.create()

        //　パラメータの指定
        val data = Data(title, body)
        val notification = Notification(title, body)
        val notificationRequest = NotificationRequest(arrayOf(to), notification, data)

        // TODO: async request
        val callback = NotificationCallbackImpl()
        // async request
        service.sendPushNotification(FCMApiService.contentTypeJson, TEST_API_KEY, notificationRequest).enqueue(callback)

        // 多段リクエストの禁止 解除
        isSubmitConditon = true

        Log.d("InputDetail", " click send Button, call sendNotification. END --------------")
    }

    private class NotificationCallbackImpl : Callback<NotificationResponse> {

        override fun onFailure(call: Call<NotificationResponse>?, t: Throwable?) {
            Log.d("InputDetail", "onFailure")
        }

        override fun onResponse(call: Call<NotificationResponse>?, response: Response<NotificationResponse>?) {
            Log.d("InputDetail", "onResponse")

            // HTTP Request
            if (response!!.isSuccessful) {
                val body = response.body()

                Log.d("InputDetail", "SUCCESS " + body.toString())

                // check notification send
                if (body != null) {
                    // if send success
                    if (body.success > 0) {
                        Log.d("InputDetail", "SEND SUCCESS COUNT : ${body.success}")
                    }
                    // if send fail
                    Log.d("InputDetail", "SEND FAILURE COUNT : ${body.failure}")
                    Log.d("InputDetail", "SEND CANONICAL_IDS : ${body.canonical_ids}")

                }

                val errorBody = response.errorBody()
                Log.d("InputDetail", "ERROR " + errorBody.toString())

            } else {
                val errorBody = response.errorBody()
                Log.d("InputDetail", "ERROR " + errorBody.toString())
            }
        }
    }
}
