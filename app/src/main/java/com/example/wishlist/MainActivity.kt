package com.example.wishlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val itemRV = findViewById<RecyclerView>(R.id.itemsRV)
        val items = Item.createItemsList(30)
        val adapter = ItemAdapter(items)
        itemRV.adapter = adapter
        itemRV.layoutManager = LinearLayoutManager(this)
    }
}