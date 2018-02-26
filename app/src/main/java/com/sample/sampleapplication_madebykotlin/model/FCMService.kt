package com.sample.sampleapplication_madebykotlin.model

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface FCMService {

    companion object {
        val END_POINT = "https://fcm.googleapis.com/fcm/"
        val CONTENT_TYPE = "Content-Type"
        val CONTENT_TYPE_JSON = "application/json"
    }

    // TODO: change parameter settings, deivded environment

    @POST("send")
    fun default(@Header("Content-Type") contentType: String = CONTENT_TYPE, @Header("Authorization") authorization: String, @Body notificationRequest: NotificationRequest): Call<NotificationResponse>
}