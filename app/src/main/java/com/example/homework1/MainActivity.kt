package com.example.homework1

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.homework1.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var displayText = ""
    private var PERMISSION_CODE = 100;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button0.setOnClickListener{
            addTextView("0", clear = false)
        }
        binding.button1.setOnClickListener{
            addTextView("1", clear = false)
        }
        binding.button2.setOnClickListener{
            addTextView("2", clear = false)
        }
        binding.button3.setOnClickListener{
            addTextView("3", clear = false)
        }
        binding.button4.setOnClickListener{
            addTextView("4", clear = false)
        }
        binding.button5.setOnClickListener{
            addTextView("5", clear = false)
        }
        binding.button6.setOnClickListener{
            addTextView("6", clear = false)
        }
        binding.button7.setOnClickListener{
            addTextView("7", clear = false)
        }
        binding.button8.setOnClickListener{
            addTextView("8", clear = false)
        }
        binding.button9.setOnClickListener{
            addTextView("9", clear = false)
        }
        binding.buttoncardinal.setOnClickListener{
            addTextView("#", clear = false)
        }
        binding.buttonplus.setOnClickListener{
            addTextView("+", clear = false)
        }
        binding.deletebutton.setOnClickListener {
            addTextView("", clear = true)
        }
        binding.buttoncall.setOnClickListener {
           dialPhoneNumber(displayText)

        }

    }

    private fun addTextView(s: String, clear: Boolean) {
        if(clear) {
            if(displayText.isEmpty()){
                return
            }
            displayText = displayText.substring(0,displayText.length-1)
            binding.result.setText(displayText)
        } else {
            displayText += s
            binding.result.setText(displayText)
        }
    }

    fun dialPhoneNumber(phoneNumber: String) {
        if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CALL_PHONE),
                PERMISSION_CODE)

        } else {
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber))
            startActivity(intent)
        }
    }
}