package com.sample.sampleapplication_madebykotlin.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log

// TODO: 必要に応じて継承元を AndroidViewModel(Application) に変更する
class InputDetailViewModel: ViewModel() {

    private val TAG = InputDetailViewModel::class.java.simpleName

    private var inputData: MutableLiveData<SendData>? = null

    fun getInputData() : MutableLiveData<SendData>? {

        if (inputData == null) {
            inputData = MutableLiveData()
            load()
        }

        return inputData
    }

    private fun load() {
        val sendData = SendData("VM tittle", "VM Body", "VM To")
        inputData?.postValue(sendData)
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "onCleared")
        // TODO:　参照の解放
    }


}

data class SendData(var title: String, var body: String, var to: String = "")