package com.example.lab7

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.GridView
import android.widget.ListView
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val spinner = findViewById<Spinner>(R.id.spinner)
        val listView = findViewById<ListView>(R.id.listView)
        val gridView = findViewById<GridView>(R.id.gridView)

        // store counts
        val count = ArrayList<String>()
        // store fruits' infos
        val item = ArrayList<Item>()
        // 建立價格範圍
        val priceRange = IntRange(10, 100)
        // 從 R 類別讀取圖檔
        val array = resources.obtainTypedArray(R.array.image_list)
        for(index in 0 until array.length()) {
            // fruit's photo Id
            val photo = array.getResourceId(index, 0)
            // fruit name
            val name = "水果${index + 1}"
            // generate price
            val price = priceRange.random()
            // add info for count
            count.add("${index + 1}個")
            // add info for fruit
            item.add(Item(photo, name, price))
        }
        array.recycle()
        spinner.adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1, count)
        gridView.numColumns = 3
        gridView.adapter = MyAdapter(this, item, R.layout.adapter_vertical)
        listView.adapter = MyAdapter(this, item, R.layout.adapter_horizontal)
    }
}