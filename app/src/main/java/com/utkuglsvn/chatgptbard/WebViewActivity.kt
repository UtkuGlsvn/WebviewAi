package com.utkuglsvn.chatgptbard

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.CookieManager
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.utkuglsvn.chatgptbard.databinding.ActivityMainBinding
import com.utkuglsvn.chatgptbard.databinding.ActivityWebviewBinding

class WebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebviewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val bundle = intent.extras
        binding.apply {
            includeLayout.title.text = bundle?.getString("title")
            includeLayout.back.setOnClickListener {
                finish()
            }
        }
        initWebView(bundle?.getString("url"))
    }


    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView(url: String?) {
        url?.let {

            val webView = binding.webView
            webView.webViewClient = WebViewClient()
            val webSettings: WebSettings = webView.settings
            webSettings.javaScriptEnabled = true
            val cookieManager: CookieManager = CookieManager.getInstance()
            cookieManager.setAcceptCookie(true)
            CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true)
            webSettings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
            webView.loadUrl(url)
        }
    }

}