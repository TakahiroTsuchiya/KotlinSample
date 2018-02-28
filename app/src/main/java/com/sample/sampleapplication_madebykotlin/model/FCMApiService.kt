package com.sample.sampleapplication_madebykotlin.model

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit



interface FCMApiService {

    // TODO: change parameter settings, deivded environment

    /*
     *
     */
    @POST("send")
    fun sendPushNotification(@Header("Content-Type") contentType: String
                             , @Header("Authorization") authorization: String
                             , @Body notificationRequest: NotificationRequest): Call<NotificationResponse>

    companion object Factory {

        val endPoint = "https://fcm.googleapis.com/fcm/"
        val contentType = "Content-Type"
        val contentTypeJson = "application/json"

        fun create(): FCMApiService {
            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(FCMApiService.endPoint)
                    .build()
            return retrofit.create(FCMApiService::class.java)
        }
    }
}