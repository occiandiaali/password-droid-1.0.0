package com.occian.passworddroid

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val bundle: Bundle? = intent.extras
        val settingsLabel: String? = bundle?.getString("settings")

        val actionBar = supportActionBar
        actionBar?.title = settingsLabel
        //actionBar?.setDisplayHomeAsUpEnabled(true)

        val arrayAdapter: ArrayAdapter<*>
        val settingsSections = arrayOf(
            "App Suggestions",
            "Wallpaper",
            "Notifications"
        )

        arrayAdapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, settingsSections
        )
        settingsListView.adapter = arrayAdapter

        settingsListView.setOnItemClickListener { parent, view, position, id ->
            if (position == 0) {
                Toast.makeText(applicationContext, "You would see other apps we made here", Toast.LENGTH_SHORT).show()
            }
            if (position == 1) {
                val paperIntent = Intent(Intent.ACTION_SET_WALLPAPER)
                startActivity(Intent.createChooser(paperIntent, "Set wallpaper"))
            }
            if (position == 2) {
                Toast.makeText(applicationContext, "Do something about notifications here", Toast.LENGTH_SHORT).show()
            }
        } // listview on item click

        darkSwitch.setOnClickListener {
            val isNightTheme = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
            when (isNightTheme) {
                Configuration.UI_MODE_NIGHT_YES ->
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                Configuration.UI_MODE_NIGHT_NO ->
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }
    } // on create


    override fun onResume() {
        super.onResume()
        val actionBar = supportActionBar
        actionBar?.title = "Settings"
        //actionBar?.setDisplayHomeAsUpEnabled(true)
    }

} // class
