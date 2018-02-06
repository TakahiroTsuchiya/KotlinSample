package com.sample.sampleapplication_madebykotlin.service

import android.util.Log
import com.google.firebase.iid.FirebaseInstanceIdService
import com.google.firebase.iid.FirebaseInstanceId


/*
 TODO: 単一の端末を宛先とするか、端末グループを作成する場合
 カスタマイズする必要がある
 */
class FirebaseInstanceIdServiceImpl : FirebaseInstanceIdService() {

    private val TAG = FirebaseInstanceIdServiceImpl::class.java.getSimpleName()
//    val TAG = "InstanceIdServiceImpl"

    /*
    登録トークンは次のような場合に変更されることがあります。
    アプリによってインスタンス ID が削除される場合
    アプリが新しい端末で復元される場合
    ユーザーがアプリをアンインストール / 再インストールする場合
    ユーザーがアプリのデータを消去する場合
     */
    override fun onTokenRefresh() {

        val refreshedToken: String? = FirebaseInstanceId.getInstance().token

        Log.d(TAG, "Refreshed token: " + refreshedToken)

        if (refreshedToken != null) {
            sendRegistrationToServer(refreshedToken)
        }
    }

    private fun sendRegistrationToServer(token: String) {
        // TODO: Implement this method to send token to your app server.

        // TODO: send To FireStore
    }
}