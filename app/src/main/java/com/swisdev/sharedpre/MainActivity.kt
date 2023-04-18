package com.swisdev.sharedpre

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences

    lateinit var firstName : EditText
    lateinit var lastName : EditText
    lateinit var dateN : EditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = this.getSharedPreferences("data_app", Context.MODE_PRIVATE)

        val isSaved = sharedPreferences.getBoolean("is_saved", false)
        if(isSaved){
            var intent = Intent(this, MainActivity2::class.java).also {
                startActivity(it)
            }
            //Toast.makeText(this, "les donnees sont sauvgardé", Toast.LENGTH_SHORT).show()
        }
        firstName = findViewById(R.id.txtFname)
        lastName = findViewById(R.id.txtLName)
        dateN = findViewById(R.id.txtDate)

        val btn_save_pre = findViewById<Button>(R.id.btnSave)
        val btn_next = findViewById<Button>(R.id.btnNext)

        btn_save_pre.setOnClickListener {
            var firstName = firstName.text.toString()
            var lastName = lastName.text.toString()
            var date = dateN.text.toString()

            saveData(firstName, lastName, date)
        }

        btn_next.setOnClickListener {
            var intent = Intent(this, MainActivity2::class.java).also {
                startActivity(it)
            }
        }
    }


    fun saveData(firstName: String, lastName: String, date: String) {

        val editor = sharedPreferences.edit()

        editor.putBoolean("is_saved", true)
        editor.putString("first_name", firstName)
        editor.putString("last_name", lastName)
        editor.putString("date", date)
        editor.apply()

        Toast.makeText(this, "saved: $firstName + $lastName + $date", Toast.LENGTH_SHORT).show()
    }
}