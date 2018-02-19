package com.sample.sampleapplication_madebykotlin.model

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.text.TextUtils
import android.util.Log
import com.android.databinding.library.baseAdapters.BR

class InputDetail(title: String, body: String): BaseObservable() {

    @Bindable
    var title = title
        set(value) {
            field = value
            Log.d("InputDetail", " title changed")
            notifyPropertyChanged(BR.submitConditon)
        }

    @Bindable
    var body = title
        set(value) {
            field = value
            Log.d("InputDetail", " body changed")
            notifyPropertyChanged(BR.submitConditon)
        }

    @get:Bindable
    var isSubmitConditon:Boolean = false
//        set(value) {
//            val isCondition = (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(body))
//            Log.d("InputDetail", "CONDITION : $isCondition")
//            field = isCondition
//        }
        get() {
            val isCondition = (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(body))
            Log.d("InputDetail", "CONDITION : $isCondition")
            return isCondition
        }
}