package com.example.dbdatabase

import com.example.dbdatabase.R

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.exdatabase.DBHelper

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btn = findViewById<Button>(R.id.btn)

        btn.setOnClickListener {

            var dbHelper = DBHelper(this)

            dbHelper.readData()

        }

    }
}