package com.sample.sampleapplication_madebykotlin.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.sample.sampleapplication_madebykotlin.R

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.getSimpleName()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: [check] Google Play 開発者サービスのチェック
        // チェックにパスしないとアプリを使用できないようにします
        if (checkService()) {
            // TODO: end of apps.
        }

        // TODO: プッシュ通知受信時の処理を記述する
        if (intent != null) {
            val id = intent.getStringExtra("ID")
            val type = intent.getStringExtra("TYPE")
            // 表示したい画面へデータを渡して画面遷移
        }

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    override fun onResume() {
        super.onResume()

        // TODO: [check] Google Play 開発者サービスのチェック
        // ユーザーが [戻る] ボタンなどの他の手段を使って実行中のアプリに戻った場合にもチェックされるようにします
        // チェックにパスしないとアプリを使用できないようにします。

        val newIntent = Intent(this, DetailActivity::class.java)
        newIntent.putExtra("title", "Main TITLE Activity")
        newIntent.putExtra("body", "Main BODY Activity")
        newIntent.putExtra("to", "Main TO Activity")
        startActivity(newIntent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun checkService(): Boolean {

        // TODO: 互換性のある Google Play 開発者サービスの APK が端末にインストールされているかどうかチェックする

        // TODO: 互換性のあるバージョンの Google Play 開発者サービスが端末にインストールされていない場合
        // GoogleApiAvailability.makeGooglePlayServicesAvailable()
        // ユーザーが Google Play ストアから Google Play 開発者サービスをダウンロードできます

        return true
    }
}
