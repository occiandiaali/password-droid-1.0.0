package com.occian.passworddroid

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_info.*

class InfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        val bundle: Bundle? = intent.extras
        val infoLabel: String? = bundle?.getString("info")

        val actionBar = supportActionBar
        actionBar?.title = infoLabel
        //actionBar?.setDisplayHomeAsUpEnabled(true)

        val arrayAdapter: ArrayAdapter<*>
        val infoSections = arrayOf(
            "About PassWordDroid",
            "Tips for passwords",
            "Terms of service",
            "Feedback"
        )

        arrayAdapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, infoSections
        )
        infoListView.adapter = arrayAdapter

        infoListView.setOnItemClickListener { parent, view, position, id ->
            if (position == 0) {
                val aboutIntent = Intent(applicationContext, AboutActivity::class.java)
                aboutIntent.putExtra("key", "About")
                startActivity(aboutIntent)
            }
            if (position == 1) {
                val webIntent = Intent(applicationContext, WebViewActivity::class.java)
                webIntent.putExtra("tips", "Tips for passwords")
                startActivity(webIntent)
            }
            if (position == 2) {
                val url = "https://www.termsofservicegenerator.net/live.php?token=KEjFyRkpoagszbFNxEH4Jul5Qd81MSfz"
                val termsIntent = Intent(Intent.ACTION_VIEW)
                termsIntent.setData(Uri.parse(url))
                startActivity(termsIntent)
            }
            if (position == 3) {
                val feedbackIntent = Intent(Intent.ACTION_SENDTO)
                feedbackIntent.data = Uri.parse("mailto:")
                feedbackIntent.putExtra(Intent.EXTRA_EMAIL, "ocean.diaali@gmail.com")
                feedbackIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback")
                if (feedbackIntent.resolveActivity(packageManager) != null) {
                    startActivity(feedbackIntent)
                }
            }


        }
    } // on create

    override fun onResume() {
        super.onResume()
        val actionBar = supportActionBar
        actionBar?.title = "Info"
        //actionBar?.setDisplayHomeAsUpEnabled(true)
    }
} // class
