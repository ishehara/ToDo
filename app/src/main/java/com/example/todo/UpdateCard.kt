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

class UpdateCard : AppCompatActivity() {
    private lateinit var database: myDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_card)
        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java, "To_Do"
        ).build()
        val pos = intent.getIntExtra("id", -1)
        if (pos != -1) {
            val task = DataObject.getData(pos).task

            findViewById<Button>(R.id.create_task).setText(task)


            findViewById<Button>(R.id.delete_button).setOnClickListener {
                DataObject.deleteData(pos)
                GlobalScope.launch {
                    database.dao().deleteTask(
                        Entity(
                            pos + 1,
                            findViewById<Button>(R.id.create_task).text.toString(),

                        )
                    )
                }
                myIntent()
            }

            findViewById<Button>(R.id.update_button).setOnClickListener {
                DataObject.updateData(
                    pos,
                    findViewById<Button>(R.id.create_task).text.toString(),

                )
                GlobalScope.launch {
                    database.dao().updateTask(
                        Entity(
                            pos + 1, findViewById<Button>(R.id.create_task).text.toString(),

                        )
                    )
                }
                myIntent()
            }

        }
    }

    fun myIntent() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}