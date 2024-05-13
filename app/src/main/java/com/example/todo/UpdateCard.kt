package com.example.todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class UpdateCard : AppCompatActivity() {

    private lateinit var database: myDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_card)


        val createTask = findViewById<EditText>(R.id.create_task)
        val deleteButton = findViewById<Button>(R.id.delete_button)
        val updateButton = findViewById<Button>(R.id.update_button)

        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java, "To_Do"
        ).build()



        val pos = intent.getIntExtra("id", -1)
        if (pos != -1) {
            val task = DataObject.getData(pos).task

            createTask.setText(task)


            deleteButton.setOnClickListener {
                DataObject.deleteData(pos)
                GlobalScope.launch {
                    database.dao().deleteTask(
                        Entity(
                            pos + 1,
                            createTask.text.toString(),

                            )
                    )
                }

                myIntent()
            }

            updateButton.setOnClickListener {
                DataObject.updateData(
                    pos,
                    createTask.text.toString()
                )
                GlobalScope.launch {
                    database.dao().updateTask(
                        Entity(
                            pos + 1, createTask.text.toString(),

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