package com.ijniclohot.goodapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ijniclohot.goodapplication.R
import com.ijniclohot.goodapplication.models.Recipe
import kotlin.math.roundToInt

class RecipeRecycleAdapter(
    private var onRecipeListener: OnRecipeListener
) : RecyclerView.Adapter<RecipeRecycleAdapter.RecipeViewHolder>() {

    private var mRecipes: List<Recipe>? = null

    override fun getItemCount(): Int {
        mRecipes?.let {
            return it.size
        }
        return 0
    }

    fun setRecipe(recipe: List<Recipe>) {
        mRecipes = recipe
        notifyDataSetChanged()
    }

    inner class RecipeViewHolder(var view: View, var onRecipeListener: OnRecipeListener) :
        RecyclerView.ViewHolder(view) {
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

        val requestOptions = RequestOptions().placeholder(R.drawable.ic_launcher_foreground)

        mRecipes?.let {mRecipes ->
            Glide.with(holder.view.context)
                .setDefaultRequestOptions(requestOptions)
                .load(mRecipes[position].image_url)
                .into(holder.image)

            holder.title.text = mRecipes[position].title
            holder.publisher.text = mRecipes[position].publisher
            holder.socialScore.text = mRecipes[position].social_rank.roundToInt().toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_recipe_list, parent, false)
        return RecipeViewHolder(view, onRecipeListener)
    }
}