package com.occian.passworddroid

import android.graphics.Color
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

    private var password = ""
    private var passLength: Int = 0
    private var numOfSwitch: Int = 0
    private val caps = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    private val cops = "abcdefghijklmnopqrstuvwxyz"
    private val nums = "0123456789"
    private val syms = "@#!&$%_+?<>"

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

        switchLowers.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                password += cops
                numOfSwitch += 1
            } else {
                password = password.removeSuffix(cops)
                passwdTextView.text = "Password display here"
                verdictTextView.text = "PW Verdict"
                verdictTextView.setTextColor(Color.DKGRAY)
                progressBar.progress = 0
                numOfSwitch -= 1
            }
        }
        switchUppers.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                password += caps
                numOfSwitch += 1
            } else {
                password = password.removeSuffix(caps)
                passwdTextView.text = "Password display here"
                verdictTextView.text = "PW Verdict"
                verdictTextView.setTextColor(Color.DKGRAY)
                progressBar.progress = 0
                numOfSwitch -= 1
            }
        }
        switchNumbers.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                password += nums
                numOfSwitch += 1
            } else {
                password = password.removeSuffix(nums)
                passwdTextView.text = "Password display here"
                verdictTextView.text = "PW Verdict"
                verdictTextView.setTextColor(Color.DKGRAY)
                progressBar.progress = 0
                numOfSwitch -= 1
            }
        }
        switchSymbols.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                password += syms
                numOfSwitch += 1
            } else {
                password = password.removeSuffix(syms)
                passwdTextView.text = "Password display here"
                verdictTextView.text = "PW Verdict"
                verdictTextView.setTextColor(Color.DKGRAY)
                progressBar.progress = 0
                numOfSwitch -= 1
            }
        }

        // click listener that fires the generate pw function
        floatingActionButtonRun.setOnClickListener {
            if (numOfSwitch < 1) {
                Toast.makeText(applicationContext, "Select at least ONE switch", Toast.LENGTH_SHORT).show()
            } else {
                //password = ""
                passwdTextView.text = "Password display here"
                verdictTextView.text = "PW Verdict"
                verdictTextView.setTextColor(Color.DKGRAY)
                progressBar.progress = 0
            }

                generatePW(passLength)
        } // fab

    } // on create

    private fun generatePW(len: Int): String {
        val sb = StringBuilder(len)
        passwordStrength() // calls function to set progressbar colour
        // initialize the random generator and randomize the concats
        val rand = Random
        if (len >= 8) {
            for (i in 0..len) {
                var index = (password.length * Math.random()).toInt()
                try {
                    sb.append(password[index])
                } catch (e: Exception) {

                }
            }
        }

        passwdTextView.text = sb.toString()
        return passwdTextView.toString()
    } // generate pw func

    private fun passwordStrength() {
        if (numOfSwitch == 0){
            progressBar.progress = 0
        }
        if (passLength == 8 && numOfSwitch >= 1) {
            progressBar.progress = 50
            verdictTextView.text = "Good"
            verdictTextView.setTextColor(Color.parseColor("#ccce0f"))
        }
        if (passLength == 16 && numOfSwitch >= 1) {
            progressBar.progress = 75
            verdictTextView.text = "Strong"
            verdictTextView.setTextColor(Color.parseColor("#98c807"))
        }
        if (passLength == 32 && numOfSwitch >= 1) {
            progressBar.progress = 100
            verdictTextView.text = "Stronger"
            verdictTextView.setTextColor(Color.parseColor("#969600"))
        }

    } // pw strength func

    // for spinner adapter
    override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        val text: String = parent?.getItemAtPosition(pos).toString()
        passLength = text.toInt()
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
