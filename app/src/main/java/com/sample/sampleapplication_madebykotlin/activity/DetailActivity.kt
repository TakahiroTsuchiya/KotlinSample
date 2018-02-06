package com.sample.sampleapplication_madebykotlin.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.sample.sampleapplication_madebykotlin.R
import com.sample.sampleapplication_madebykotlin.databinding.ActivityDetailBinding
import com.sample.sampleapplication_madebykotlin.model.User

class DetailActivity: AppCompatActivity() {

    private val TAG = DetailActivity::class.java.getSimpleName()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var firstName = "dummy first name"
        var lastName = "dummy lastName name"
        if (intent != null) {
            Log.d(TAG, "intent > firstName : " + intent.extras.getString("firstName"))
            Log.d(TAG, "intent > lastName : " + intent.extras.getString("lastName"))
            firstName = intent.extras.getString("firstName")
            lastName = intent.extras.getString("lastName")
        }
        val user = User(firstName, lastName)
        // set DataBinding
        val binding = DataBindingUtil.setContentView<ActivityDetailBinding>(this, R.layout.activity_detail)
        binding.user = user
    }
}