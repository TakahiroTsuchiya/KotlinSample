package com.sample.sampleapplication_madebykotlin

import com.sample.sampleapplication_madebykotlin.model.Data
import com.sample.sampleapplication_madebykotlin.model.FCMService
import com.sample.sampleapplication_madebykotlin.model.Notification
import com.sample.sampleapplication_madebykotlin.model.NotificationRequest
import org.junit.Test
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit



class RCMServiceUnitTest {

    @Test
    fun callApi() {

        println("--- callApi Test START -----------------")

        val retrofit = Retrofit.Builder()
                .baseUrl(FCMService.END_POINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val service = retrofit.create(FCMService::class.java)

        val data = Data("title", "body")
        val notification = Notification("title", "body")
        // TODO
        val testDevicesIds = arrayOf("")
        val notificationRequest = NotificationRequest(testDevicesIds, notification, data)
        // TODO
        val TEST_API_KEY = "key="

        // sync request
        val response1 = service.default(FCMService.CONTENT_TYPE_JSON, TEST_API_KEY, notificationRequest).execute()

        // HTTP Request
        if (response1.isSuccessful) {
            val body = response1.body()

            println("SUCCESS " + body.toString())

            // check notification send
            if (body != null) {
                // if send success
                if (body.success > 0) {
                    println("SEND SUCCESS COUNT : ${body.success}")
                }
                // if send fail
                println("SEND FAILURE COUNT : ${body.failure}")
                println("SEND CANONICAL_IDS : ${body.canonical_ids}")

            }

            val errorBody = response1.errorBody()
            println("ERROR " + errorBody.toString())

        } else {
            val errorBody = response1.errorBody()
            println("ERROR " + errorBody.toString())

        }


        print("--- callApi Test END -------------------")
    }
}
