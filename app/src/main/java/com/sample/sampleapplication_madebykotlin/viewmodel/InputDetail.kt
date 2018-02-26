package com.sample.sampleapplication_madebykotlin.viewmodel

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.text.TextUtils
import android.util.Log
import com.android.databinding.library.baseAdapters.BR

class InputDetail(title: String, body: String, to: String = ""): BaseObservable() {

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

    fun onClickSendNotifications() {
        Log.d("InputDetail", " click send Button, call sendNotification.")
        Log.d("InputDetail", " click send Button, TITLE $title")
        Log.d("InputDetail", " click send Button, BODY $body")
        Log.d("InputDetail", " click send Button, TO $to")
    }
}