package com.sample.sampleapplication_madebykotlin.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.sample.sampleapplication_madebykotlin.R
import com.sample.sampleapplication_madebykotlin.databinding.ActivityDetailBinding
import com.sample.sampleapplication_madebykotlin.viewmodel.InputDetail
import com.sample.sampleapplication_madebykotlin.viewmodel.InputDetailViewModel

class DetailActivity: AppCompatActivity() {

    private val TAG = DetailActivity::class.java.getSimpleName()

    // set DataBinding
    private val binding: ActivityDetailBinding by lazy {
        DataBindingUtil.setContentView<ActivityDetailBinding>(this, R.layout.activity_detail)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var title = "dummy first name"
        var body = "dummy body name"
        var to = "dummy to"
        if (intent != null) {
            Log.d(TAG, "intent > title : " + intent.extras.getString("title"))
            Log.d(TAG, "intent > body : " + intent.extras.getString("body"))
            Log.d(TAG, "intent > to : " + intent.extras.getString("to"))
            title = intent.extras.getString("title")
            body = intent.extras.getString("body")
            to = intent.extras.getString("to")
        }
        val detail = InputDetail(title, body, to)
        // viewmodel を生成し、設定する
        binding.viewModel = detail

        val viewModel = ViewModelProviders.of(this).get(InputDetailViewModel::class.java)

        binding.setLifecycleOwner(this)

        binding.viewModel2 = viewModel

        viewModel.getInputData()?.observe(this, Observer {
            Log.d(TAG, "$it.title")
            Log.d(TAG, "$it.body")
            Log.d(TAG, "$it.to")
            // Update the UI.
        })
    }
}