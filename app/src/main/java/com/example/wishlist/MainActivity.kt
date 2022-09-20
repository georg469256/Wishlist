package com.example.wishlist

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.webkit.URLUtil
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.DecimalFormat

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

        /**
         * Checks if given string is a valid url
         */
        fun isValidUrl(url: String): Boolean {
            return URLUtil.isValidUrl(url)
        }

        val itemRV = findViewById<RecyclerView>(R.id.itemsRV)
        val items = Item.createItemsList(20)
        val adapter = ItemAdapter(items)
        val itemName = findViewById<EditText>(R.id.ItemName)
        val itemURL = findViewById<EditText>(R.id.ItemURL)
        val itemPrice = findViewById<EditText>(R.id.ItemPrice)
        val submitButton = findViewById<Button>(R.id.SubmitButton)

        /**
         * Returns true if all EditTexts have valid input
         */
        fun enableSubmit():Boolean {
            return (itemName.text.toString() != "" && itemPrice.text.toString() != "" && isValidUrl(itemURL.text.toString()))
        }


        itemName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                submitButton.isEnabled = enableSubmit()
            }

        })

        itemPrice.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                if(itemPrice.text.toString() != "") {
                    itemPrice.removeTextChangedListener(this)
                    val cleanString = itemPrice.text.toString().replace("[$,.]".toRegex(),"")
                    val currencyFormat = DecimalFormat("###,##0.00")
                    val formatted = "$" + currencyFormat.format(cleanString.toDouble()/100).toString()
                    itemPrice.setText(formatted)
                    itemPrice.setSelection(formatted.length)
                    submitButton.isEnabled = enableSubmit()
                    itemPrice.addTextChangedListener(this)
                }
            }
        })

        itemURL.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(input: CharSequence?, start: Int, before: Int, count: Int) {
                if(itemURL.text.toString() != "" && !isValidUrl(itemURL.text.toString())) {
                    itemURL.error = "URL is not formatted correctly"
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                submitButton.isEnabled = enableSubmit()
            }
        })

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
