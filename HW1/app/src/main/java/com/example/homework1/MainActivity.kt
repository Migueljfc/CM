package com.example.homework1

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.homework1.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var displayText = ""
    private var permissioncode = 100
    private val speedDials = Array(3){ "-" ; "-"; "-"}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.speeddial1.setOnClickListener {
            addTextView(speedDials[0], true)
        }
        binding.speeddial2.setOnClickListener {
            addTextView(speedDials[1], true)
        }
        binding.speeddial3.setOnClickListener {
            addTextView(speedDials[2], true)
        }
        binding.speeddial1.setOnLongClickListener {
            /*val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)*/
            showdialog(0)
            true
        }
        binding.speeddial2.setOnLongClickListener {
            /*val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)*/
            showdialog(1)
            true
        }
        binding.speeddial3.setOnLongClickListener {
            /*val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)*/
            showdialog(2)
            true
        }
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
           if(s == ""){
               if(displayText.isEmpty()){
                   return
               }
               displayText = displayText.substring(0,displayText.length-1)
               binding.result.text = displayText
           }
            else{
               displayText = s
               binding.result.text = displayText
           }
        } else {
            displayText += s
            binding.result.text = displayText
        }
    }

    private fun dialPhoneNumber(phoneNumber: String) {
        if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CALL_PHONE),
                permissioncode)

        } else {
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$phoneNumber"))
            startActivity(intent)
        }
    }

    private fun showdialog(id: Int){
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Speed Dial Details")
        builder.setMessage("Enter the name to be associated with the following number: $displayText" )
        val input = EditText(this)
        input.hint = "Enter the name"
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)
        builder.setPositiveButton("OK") { _, _ ->
            var name = input.text.toString()
            if(name.length > 6){
                name = name.substring(0,6)
            }
            speedDials[id] = displayText
            when(id) {
                0 -> binding.speeddial1.text = name
                1 -> binding.speeddial2.text = name
                else -> binding.speeddial3.text = name
            }
        }
        builder.setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }
        builder.show()

    }


}


