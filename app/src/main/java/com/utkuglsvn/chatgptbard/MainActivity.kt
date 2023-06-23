package com.utkuglsvn.chatgptbard

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import com.utkuglsvn.chatgptbard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.apply {
            includeLayout.title.text = getString(R.string.app_name)
            btnBard.setOnClickListener {
                openActivity("https://bard.google.com/","BARD")
            }
            btnChatGpt.setOnClickListener {
                openActivity("https://chat.openai.com/","ChatGPT")
            }
            includeLayout.back.setOnClickListener {
                finish()
            }
            myApp.setOnClickListener {
                val developerId = "FoxyCode"
                val playStoreUrl = "https://play.google.com/store/apps/developer?id=$developerId"
                try {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(playStoreUrl)

                    startActivity(intent)
                }catch (e:Exception){
                    e.printStackTrace()
                    openActivity(playStoreUrl,"My APP")
                }
            }

            myInfo.setOnClickListener {
                openActivity("https://utkuglsvn.github.io/","My Info")
            }
        }
    }


    private fun openActivity(url: String, title: String) {
        val intent = Intent(this, WebViewActivity::class.java)
        val bundle = Bundle()
        bundle.putString("url", url)
        bundle.putString("title", title)
        intent.putExtras(bundle)
        startActivity(intent)
    }

}