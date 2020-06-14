package com.occian.passworddroid

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_web_view.*
import kotlin.random.Random

class WebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        val bundle: Bundle? = intent.extras
        val tipsLabel: String? = bundle?.getString("tips")

        val actionBar = supportActionBar
        actionBar?.title = tipsLabel
       // actionBar?.setDisplayHomeAsUpEnabled(true)
        val siteUrls: Array<String> = arrayOf(
            "https://www.mentalfloss.com/article/504786/8-tips-make-your-passwords-strong-possible",
            "https://blog.avast.com/strong-password-ideas",
            "https://www.mcafee.com/blogs/consumer/family-safety/15-tips-to-better-password-security/",
            "https://edu.gcfglobal.org/en/techsavvy/password-tips/1/",
            "https://www.wired.com/story/7-steps-to-password-perfection/",
            "https://www.consumerreports.org/digital-security/tips-for-better-passwords/"
        )
        val webView = findViewById<WebView>(R.id.tipsWebView)
        val rand = Random
        var randomUrl = rand.nextInt(siteUrls.size)
        //var currentUrl = tipsWebView.loadUrl(siteUrls.get(randomUrl)) // fetch urls at random

        webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                val toast = Toast.makeText(applicationContext, "Page Loading..", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0,0)
                toast.show()
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                super.onReceivedError(view, request, error)
                Toast.makeText(applicationContext, "Page could not be loaded. Do you have internet?", Toast.LENGTH_LONG).show()

            }
        } // webclient view
        webView.loadUrl(siteUrls[randomUrl])

    } // on create


    // options menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.webview_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.help -> {
                val helpToast = Toast.makeText(applicationContext,
                    "Learn about secure passwords, and how to choose them, from randomly selected websites." +
                            "A different site opens every time you open this screen", Toast.LENGTH_LONG)
                helpToast.setGravity(Gravity.CENTER_HORIZONTAL, 0,0)
                helpToast.show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }
} // class
