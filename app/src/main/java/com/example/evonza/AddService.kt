package com.example.evonza

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_add_service.*

class AddService : AppCompatActivity() {
    private var webView: WebView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_service)

        navigationView.setOnNavigationItemSelectedListener(
            BottomNavigationView.OnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.dashboard -> {
                        val intent = Intent(this, Dashboard::class.java)
                        startActivity(intent)
                    }

                    R.id.addservice -> {
                        val intent = Intent(this, AddService::class.java)
                        startActivity(intent)
                    }

                    R.id.account -> {
                        val intent = Intent(this, Account::class.java)
                        startActivity(intent)
                    }
                }
                return@OnNavigationItemSelectedListener true
            })

        webView = findViewById<View>(R.id.addservice) as WebView
        webView!!.webViewClient = WebViewClient()
        webView!!.loadUrl("https://docs.google.com/forms/d/e/1FAIpQLScBmm7KAAYNRnAYtVylgIcdjT3hp2kINSFsL_-c6QCFBpsPLQ/viewform?usp=sf_link")
        val webSettings = webView!!.settings
        webSettings.javaScriptEnabled = true
    }

    override fun onBackPressed() {
        if (webView!!.canGoBack()) {
            webView!!.goBack()
        } else {
            super.onBackPressed()
        }
    }
}
