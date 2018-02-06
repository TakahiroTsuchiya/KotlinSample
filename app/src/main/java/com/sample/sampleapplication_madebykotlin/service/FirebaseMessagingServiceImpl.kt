package com.sample.sampleapplication_madebykotlin.service

import android.app.PendingIntent
import android.content.Intent
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.sample.sampleapplication_madebykotlin.R
import java.lang.Exception
import android.media.RingtoneManager
import android.app.NotificationManager
import android.content.Context
import android.support.v4.app.NotificationCompat

/**
 * [Firebase Notifications Composer](https://console.firebase.google.com/project/_/notification)
 * [Firebase Cloud Messaging HTTP Protocol](https://firebase.google.com/docs/cloud-messaging/http-server-ref)
 */
class FirebaseMessagingServiceImpl : FirebaseMessagingService() {

    private val TAG = FirebaseMessagingServiceImpl::class.java.getSimpleName()

    /*

     */
    override fun onDeletedMessages() {
        super.onDeletedMessages()

        Log.d(TAG, "onDeletedMessages")
    }

    /*
        TODO:
        - メッセージは受信から 10 秒以内に処理されるようにします
        - メッセージの処理にもう少し時間が必要な場合は、Firebase Job Dispatcher を使用
        - アプリがバックグラウンドで動作しているときに配信された通知メッセージ。この場合、通知は端末のシステムトレイに配信されます。通知をユーザーがタップすると、デフォルトではアプリのランチャーが開きます。
        - 通知とデータ ペイロードの両方を含む、バックグラウンドおよびフォアグラウンドのメッセージ。この場合、通知は端末の通知領域（システムトレイ）に配信され、データ ペイロードはランチャー アクティビティのインテントの追加部分で配信されます。
     */
    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)

        Log.d(TAG, "onMessageReceived - START")

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ

        if (remoteMessage == null) {
            Log.d(TAG, "onMessageReceived - remoteMessage nothing.")
            Log.d(TAG, "onMessageReceived - END")
            return
        }

        var notificationTitle = ""
        var notificationMessage = ""

        // Check if message contains a data payload.
        // [Firebase Notifications Composer - カスタムデータ](https://console.firebase.google.com/project/_/notification)
        if (remoteMessage.data.isNotEmpty()) {
            // {custom key=custom value}
            Log.d(TAG, "Message data payload: " + remoteMessage.data);

            if (/* Check if data needs to be processed by long running job */ true) {
                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
                scheduleJob()
            } else {
                // Handle message within 10 seconds
                handleNow()
            }

            val data: Map<String, String> = remoteMessage.data

            // TODO: activity と処理を合わせる
            val id = data.get("ID")
            val type = data.get("TYPE")

            // Notificationを生成
        }

        //  Check if message contains a notification payload.
        // [Firebase Notifications Composer - メッセージ文, 詳細オプション > タイトル](https://console.firebase.google.com/project/_/notification)
        if (remoteMessage.notification != null) {
            Log.d(TAG, "Message Notification Title: " + remoteMessage.notification!!.title);
            Log.d(TAG, "Message Notification Body: " + remoteMessage.notification!!.body);

            if (! remoteMessage.notification!!.title.isNullOrBlank()) {
                notificationTitle = remoteMessage.notification!!.title.toString()
            }

            if (! remoteMessage.notification!!.body.isNullOrBlank()) {
                notificationMessage = remoteMessage.notification!!.body.toString()
            }

            // [Firebase Notifications Composer からは指定できない](https://console.firebase.google.com/project/_/notification)
            Log.d(TAG, "Message Notification icon: " + remoteMessage.notification!!.icon);
            Log.d(TAG, "Message Notification Color: " + remoteMessage.notification!!.color);
            Log.d(TAG, "Message Notification Click_Action: " + remoteMessage.notification!!.clickAction);
            Log.d(TAG, "Message Notification Sound: " + remoteMessage.notification!!.sound);
            Log.d(TAG, "Message Notification Tag: " + remoteMessage.notification!!.tag);
        }
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.

        sendNotification(notificationTitle, notificationMessage)

        Log.d(TAG, "onMessageReceived - END")
    }

    override fun onMessageSent(p0: String?) {
        super.onMessageSent(p0)

        Log.d(TAG, "onMessageSent")
    }

    override fun onSendError(p0: String?, p1: Exception?) {
        super.onSendError(p0, p1)

        Log.d(TAG, "onSendError")
    }

    private fun scheduleJob() {
        // TODO: Stub, you make here.
    }

    private fun handleNow() {
        // TODO: Stub, you make here.
    }

    private fun sendNotification(title: String, messageBody: String) {

        // TODO: mode class.
        val intent = Intent()
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)

        val channelId = getString(R.string.default_notification_channel_id)
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        var notificationBuilder = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_stat_ic_notification)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent)

        if (title.isNotBlank()) {
            notificationBuilder.setContentTitle(title)
        }

        if (messageBody.isNotBlank()) {
            notificationBuilder.setContentText(messageBody)
        }

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // TODO: "id" をカウントアップすれば複数表示できる？

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build())


    }
}