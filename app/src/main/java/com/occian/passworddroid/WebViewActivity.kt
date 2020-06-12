package com.occian.passworddroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        val rand = Random
        var randomUrl = rand.nextInt(siteUrls.size)

        tipsWebView.loadUrl(siteUrls.get(randomUrl)) // fetch urls at random
    } // on create
} // class
