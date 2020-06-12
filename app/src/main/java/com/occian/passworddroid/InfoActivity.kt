package com.occian.passworddroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
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
//            if (position == 2) {
//                val themesIntent = Intent(applicationContext, ThemesActivity::class.java)
//                themesIntent.putExtra("key", "Themes")
//                startActivity(themesIntent)
//            }
//            if (position == 3) {
//                val themesIntent = Intent(applicationContext, ThemesActivity::class.java)
//                themesIntent.putExtra("key", "Themes")
//                startActivity(themesIntent)
//            }
//            if (position == 4) {
//                val themesIntent = Intent(applicationContext, ThemesActivity::class.java)
//                themesIntent.putExtra("key", "Themes")
//                startActivity(themesIntent)
//            }
        }
    } // on create

    override fun onResume() {
        super.onResume()
        val actionBar = supportActionBar
        actionBar?.title = "Info"
        //actionBar?.setDisplayHomeAsUpEnabled(true)
    }
} // class
