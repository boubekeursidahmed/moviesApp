package com.swisdev.sharedpre

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity2 : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        sharedPreferences = getSharedPreferences("data_app", Context.MODE_PRIVATE)

        val txtLastName = findViewById<TextView>(R.id.txtLastName)
        val txtFristName = findViewById<TextView>(R.id.txtFirsName)
        val btnDetele = findViewById<Button>(R.id.btnDelete)

        var isData = sharedPreferences.getBoolean("is_saved", false)

        if (isData){
            Toast.makeText(this, "!isdata $isData", Toast.LENGTH_SHORT).show()
            txtFristName.text = sharedPreferences.getString("first_name", "NO_DATA")
            txtLastName.text = sharedPreferences.getString("last_name", "NO_DATA")
        }else{
            Toast.makeText(this, "isdata $isData ", Toast.LENGTH_SHORT).show()
        }
        btnDetele.setOnClickListener {
            val editor = sharedPreferences.edit()

            editor.putBoolean("is_saved", false)
            editor.remove("first_name")
            editor.remove("last_name")
            editor.remove("date")
            editor.apply()

            //quiter l'activity
            finish()
        }
    }
}