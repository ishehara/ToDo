package com.example.todo


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


// entity - table
// dao - queries
// database

class MainActivity : AppCompatActivity() {

    private lateinit var database: myDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java, "To_Do"
        ).build()

        findViewById<Button>(R.id.add).setOnClickListener {
            val intent = Intent(this, CreateCard::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.deleteAll).setOnClickListener {
            DataObject.deleteAll()

            GlobalScope.launch {
                database.dao().deleteAll()
            }

            setRecycler()
        }

        setRecycler()

    }

    fun setRecycler() {
        val recycler_view = findViewById<RecyclerView>(R.id.recycler_view)
        recycler_view.adapter = Adapter(DataObject.getAllData())
        recycler_view.layoutManager = LinearLayoutManager(this)
    }
}