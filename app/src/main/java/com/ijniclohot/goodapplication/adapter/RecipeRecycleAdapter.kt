package com.ijniclohot.goodapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.ijniclohot.goodapplication.R
import com.ijniclohot.goodapplication.models.Recipe

class RecipeRecycleAdapter(private var mRecipes: List<Recipe>, private var onRecipeListener: OnRecipeListener) : RecyclerView.Adapter<RecipeRecycleAdapter.RecipeViewHolder>(){

    override fun getItemCount(): Int {
        return mRecipes.size
    }

    fun setRecipe(recipe: List<Recipe>){
        mRecipes = recipe
        notifyDataSetChanged()
    }

    inner class RecipeViewHolder(var view : View, var onRecipeListener: OnRecipeListener) : RecyclerView.ViewHolder(view){
        var title = view.findViewById<TextView>(R.id.recipe_title)
        var publisher = view.findViewById<TextView>(R.id.recipe_publisher)
        var socialScore = view.findViewById<TextView>(R.id.recipe_social_score)
        var image = view.findViewById<AppCompatImageView>(R.id.recipe_image)

        init {
            view.setOnClickListener {
                onRecipeListener.onRecipeClick(adapterPosition)
            }
        }
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.title.text = mRecipes[position].title
        holder.publisher.text = mRecipes[position].publisher
        holder.socialScore.text = mRecipes[position].social_rank.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_recipe_list, parent, false)
        return RecipeViewHolder(view, onRecipeListener)
    }
}