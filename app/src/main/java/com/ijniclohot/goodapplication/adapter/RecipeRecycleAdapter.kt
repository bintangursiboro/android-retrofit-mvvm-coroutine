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
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        var RECIPE_TYPE = 1
        var LOADING_TYPE = 2
    }

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

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {

        var itemViewType = getItemViewType(position)

        if (itemViewType == RECIPE_TYPE) {
            val requestOptions = RequestOptions().placeholder(R.drawable.ic_launcher_foreground)

            mRecipes?.let { mRecipes ->
                var holder = viewHolder as RecipeViewHolder
                Glide.with(holder.view.context)
                    .setDefaultRequestOptions(requestOptions)
                    .load(mRecipes[position].image_url)
                    .into(holder.image)

                holder.title.text = mRecipes[position].title
                holder.publisher.text = mRecipes[position].publisher
                holder.socialScore.text = mRecipes[position].social_rank?.roundToInt().toString()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (mRecipes?.get(position)?.title.equals("LOADING..."))
            LOADING_TYPE
        else RECIPE_TYPE
    }

    fun displayLoading(){
        if(!isLoading()){
            val recipe = Recipe()
            recipe.title = "LOADING..."
            val loadingList = ArrayList<Recipe>()
            loadingList.add(recipe)
             mRecipes = loadingList
            notifyDataSetChanged()
        }
    }

    private fun isLoading() : Boolean{
        mRecipes?.let {
            if (it.isNotEmpty()){
                if (it[it.size - 1].title!! == "LOADING..."){
                    return true
                }
            }
        }
        return false
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view: View? = null
        when (viewType) {
            RECIPE_TYPE -> {
                view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.layout_recipe_list, parent, false)
                return RecipeViewHolder(view, onRecipeListener)
            }
            LOADING_TYPE -> {
                view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.layout_loading_list_item, parent, false)
                return LoadingViewHolder(view)
            }
            else -> {
                view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.layout_recipe_list, parent, false)
                return RecipeViewHolder(view, onRecipeListener)
            }
        }

    }

}