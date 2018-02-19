package com.sample.sampleapplication_madebykotlin.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.sample.sampleapplication_madebykotlin.R
import com.sample.sampleapplication_madebykotlin.databinding.ActivityDetailBinding
import com.sample.sampleapplication_madebykotlin.model.InputDetail

class DetailActivity: AppCompatActivity() {

    private val TAG = DetailActivity::class.java.getSimpleName()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var title = "dummy first name"
        var body = "dummy body name"
        if (intent != null) {
            Log.d(TAG, "intent > title : " + intent.extras.getString("title"))
            Log.d(TAG, "intent > body : " + intent.extras.getString("body"))
            title = intent.extras.getString("title")
            body = intent.extras.getString("body")
        }
        val detail = InputDetail(title, body)
        // set DataBinding
        val binding = DataBindingUtil.setContentView<ActivityDetailBinding>(this, R.layout.activity_detail)
        binding.detail = detail
    }
}