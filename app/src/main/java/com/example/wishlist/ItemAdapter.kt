package com.example.wishlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
class ItemAdapter(private val mItems: List<Item>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Your holder should contain and initialize a member variable
        // for any view that will be set as you render a row
        val itemNameDisplay: TextView = itemView.findViewById(R.id.ItemNameDisplay)
        val itemPriceDisplay: TextView = itemView.findViewById(R.id.ItemPriceDisplay)
        val itemURLDisplay: TextView = itemView.findViewById(R.id.ItemURLDisplay)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val itemView = inflater.inflate(R.layout.item, parent, false)
        // Return a new holder instance
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mItems[position]
        val itemNameDisplay = holder.itemNameDisplay
        itemNameDisplay.text = item.name
        val itemPriceDisplay = holder.itemPriceDisplay
        itemPriceDisplay.text = item.price
        val itemURLDisplay = holder.itemURLDisplay
        itemURLDisplay.text = item.url
    }

    override fun getItemCount(): Int {
        return mItems.size
    }


}