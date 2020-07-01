package com.ijniclohot.goodapplication.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ijniclohot.goodapplication.R
import de.hdodenhof.circleimageview.CircleImageView

class CategoryViewHolder(private val view: View, val onRecipeListener: OnRecipeListener) :
    RecyclerView.ViewHolder(view), View.OnClickListener {

    var categoryImage: CircleImageView = view.findViewById(R.id.category_image)
    var categoryTitle: TextView = view.findViewById(R.id.category_title)

    init {
        view.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        onRecipeListener.onCategoryClick(categoryTitle.text.toString())
    }
}