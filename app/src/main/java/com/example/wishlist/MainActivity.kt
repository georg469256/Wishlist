package com.example.wishlist

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * Parameters / Fields:
         *   N/A
         *
         * Returns nothing:
         *   closes soft keyboard
         */
        fun closeKeyboard() {
            val view = this.currentFocus
            if(view != null) {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        }

        val itemRV = findViewById<RecyclerView>(R.id.itemsRV)
        val items = ArrayList<Item>()
        val adapter = ItemAdapter(items)
        val itemName = findViewById<EditText>(R.id.ItemName)
        val itemURL = findViewById<EditText>(R.id.ItemURL)
        val itemPrice = findViewById<EditText>(R.id.ItemPrice)
        val submitButton = findViewById<Button>(R.id.SubmitButton)
        submitButton.setOnClickListener {
            val newItem = Item(itemName.text.toString(), itemPrice.text.toString(), itemURL.text.toString())
            itemName.setText("")
            itemPrice.setText("")
            itemURL.setText("")
            items.add(newItem)
            adapter.notifyItemInserted(items.size-1)
            closeKeyboard()
        }
        itemRV.adapter = adapter
        itemRV.layoutManager = LinearLayoutManager(this)
    }
}