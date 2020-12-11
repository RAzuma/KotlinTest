package jp.razuma.kotlintest

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.util.Linkify
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import jp.razuma.kotlintest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var view = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        Linkify.addLinks(binding.textView, Linkify.WEB_URLS)
    }

    override fun startActivity(intent: Intent?) {

        val alertFlg = intent?.getBooleanExtra("alert", false)

        if(alertFlg!!){
            super.startActivity(intent)
            return
        }

        val urlData = intent.data
        AlertDialog.Builder(this)
                .setMessage("アプリを離れます")
                .setPositiveButton("OK") { _, _->
                    val urlStr = Uri.parse(urlData.toString())
                    val myIntent = Intent(Intent.ACTION_VIEW, (Uri.parse(urlStr.toString())))
                    myIntent.putExtra("alert", true)
                    startActivity(myIntent)
                }
                .setNegativeButton("キャンセル", null)
                .show()
    }
}