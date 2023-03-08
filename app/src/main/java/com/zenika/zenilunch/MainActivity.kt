package com.zenika.zenilunch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val grid = findViewById<RecyclerView>(R.id.listItems)
        grid.layoutManager = LinearLayoutManager(this)

        // Add restaurants
        val restos = ArrayList<ItemView>()
        for (i in 1..20) {
            val item = ItemView("Restaurant " + i)
            restos.add(item)
        }

        val adapter = ListAdapter(restos)
        grid.adapter = adapter
    }
}