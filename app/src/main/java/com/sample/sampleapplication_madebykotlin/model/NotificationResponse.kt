package com.sample.sampleapplication_madebykotlin.model

data class NotificationResponse(@Transient val multicast_id: Long, val success: Long, val failure: Long, val canonical_ids: Long, @Transient val results: Array<Result>)

data class Result(val message_id: String?, val registration_id: String?, val error: String)

