package com.example.todo


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.room.Room

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class CreateCard : AppCompatActivity() {
    private lateinit var database: myDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_card)
        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java, "To_Do"
        ).build()
        findViewById<Button>(R.id.save_button).setOnClickListener {
            if (findViewById<Button>(R.id.create_task).text.toString().trim { it <= ' ' }.isNotEmpty()

            ) {
                var task = findViewById<Button>(R.id.create_task).getText().toString()

                DataObject.setData(task)
                GlobalScope.launch {
                    database.dao().insertTask(Entity(0, task))

                }

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}

