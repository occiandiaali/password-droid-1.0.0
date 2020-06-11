package com.occian.passworddroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    var temp: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ArrayAdapter.createFromResource(
                this,
                R.array.len_options,
                android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        spinner.onItemSelectedListener = this

        // click listener that fires the generate pw function
        floatingActionButtonRun.setOnClickListener { generatePW(temp) }
    } // on create

    private fun generatePW(len: Int): String {
        // initialize the base components
        val caps = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val cops = "abcdefghijklmnopqrstuvwxyz"
        val nums = "0123456789"
        val syms = "@#!&$%_+?<>"

        // string them together
        val values = caps + syms + cops + nums
        val sb = StringBuilder(len)

        // initialize the random generator
        val rand = Random

        if (len >= 8) {
            for (i in 0..len) {
                var index = (values.length * Math.random()).toInt()
                try {
                    sb.append(values[index])
                } catch (e: Exception) {

                }
            }
        }
        passwdTextView.text = sb.toString()
        return passwdTextView.toString()
    } // generate pw func

    // for spinner adapter
    override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        val text: String = parent?.getItemAtPosition(pos).toString()
        temp = text.toInt()
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
    }

    // options menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.settings -> {
                Toast.makeText(applicationContext, "App Settings", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.help -> {
                Toast.makeText(applicationContext, "Help Settings", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


} // class
