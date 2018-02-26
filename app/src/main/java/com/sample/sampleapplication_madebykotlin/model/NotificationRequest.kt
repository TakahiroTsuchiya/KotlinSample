package com.sample.sampleapplication_madebykotlin.model

data class NotificationRequest(val registration_ids: Array<String>, val notification: Notification, val data: Data)

data class Notification(val title: String, val body: String)

data class Data(val title: String, val body: String)
