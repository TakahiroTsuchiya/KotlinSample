package com.sample.sampleapplication_madebykotlin

import com.sample.sampleapplication_madebykotlin.model.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.CountDownLatch

class RCMServiceUnitTest {

    var signal: CountDownLatch? = null

    @Before
    fun setUp() {
        println("--- setUp START -----------------")
        signal = null
        signal = CountDownLatch(1)
        println("--- setUp END   -----------------")
    }

    @After
    fun tearDown() {
        println("--- tearDown START -----------------")
        signal = null
        println("--- tearDown END   -----------------")
    }

    @Test
    fun callApiSync() {

        println("--- callApiSync Test START -----------------")

        val data = Data("title", "body")
        val notification = Notification("title", "body")
        // TODO: Push Send to devices id
        val to = ""
        // TODO:
        val testDevicesIds = arrayOf(to)
        val notificationRequest = NotificationRequest(testDevicesIds, notification, data)
        // TODO:
        val TEST_API_KEY = ""

        // API Service を呼び出す
        val service = FCMApiService.Factory.create()

        // sync request
        val response = service.sendPushNotification(FCMApiService.contentTypeJson, TEST_API_KEY, notificationRequest).execute()

        // HTTP Request
        if (response.isSuccessful) {
            val body = response.body()

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

            val errorBody = response.errorBody()
            println("ERROR " + errorBody.toString())

        } else {
            val errorBody = response.errorBody()
            println("ERROR " + errorBody.toString())

        }

        println("--- callApiSync Test END -------------------")
    }

    @Test
    fun callApiAsync() {

        println("--- callApiAsync Test START -----------------")

        val data = Data("title", "body")
        val notification = Notification("title", "body")
        // TODO: Push Send to devices id
        val to = ""
        // TODO:
        val testDevicesIds = arrayOf(to)
        val notificationRequest = NotificationRequest(testDevicesIds, notification, data)
        // TODO:
        val TEST_API_KEY = ""
        // API Service を呼び出す
        val service = FCMApiService.Factory.create()

        val callback = CallbackImpl()
        callback.signal = signal

        // async request
        service.sendPushNotification(FCMApiService.contentTypeJson, TEST_API_KEY, notificationRequest).enqueue(callback)

        signal!!.await()
        println("--- callApiAsync Test END -------------------")
    }


    private class CallbackImpl() : Callback<NotificationResponse> {

        var signal: CountDownLatch? = null

        init {
            println("--- init ------------")
        }

        override fun onFailure(call: Call<NotificationResponse>?, t: Throwable?) {
            println("--- onFailure ------------")
            signal!!.countDown()
        }

        override fun onResponse(call: Call<NotificationResponse>?, response: Response<NotificationResponse>?) {
            println("--- onResponse ------------")
            signal!!.countDown()
        }
    }
}
