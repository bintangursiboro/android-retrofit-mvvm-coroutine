package com.ijniclohot.goodapplication.adapter

interface OnRecipeListener {
    fun onRecipeClick(position: Int)

    fun onCategoryClick(category: String)
}