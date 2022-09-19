package com.example.wishlist

class Item(val name: String, val price: String, val url: String) {
    companion object {
        fun createItemsList(numItems: Int): ArrayList<Item> {
            val items = ArrayList<Item>()
            for (i in 1..numItems) {
                items.add(Item("Item $i", "5.00", "www.item.com"))
            }
            return items
        }
    }
}