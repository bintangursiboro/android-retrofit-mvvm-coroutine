package com.ijniclohot.goodapplication

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ijniclohot.goodapplication.adapter.OnRecipeListener
import com.ijniclohot.goodapplication.adapter.RecipeRecycleAdapter
import com.ijniclohot.goodapplication.models.Recipe
import com.ijniclohot.goodapplication.viewmodel.RecipeListViewModel
import kotlinx.android.synthetic.main.activity_recipe_list.*

class RecipesListActivity : BaseActivity(), OnRecipeListener {

    companion object {
        val TAG = "RecipeListActivity"
    }

    private lateinit var recipeListAdapter: RecipeRecycleAdapter

    private lateinit var mRecipeListViewModel: RecipeListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)

        mRecipeListViewModel = ViewModelProvider(this)[RecipeListViewModel::class.java]

        initRecyclerAdapter()
        subscribeObserver()
        searchRecipeApi("Chicken", 1)
    }

    private fun initRecyclerAdapter(){
        recipeListAdapter = RecipeRecycleAdapter(this)
        recipe_list.adapter = recipeListAdapter
        recipe_list.layoutManager = LinearLayoutManager(this)
    }


    private fun subscribeObserver() {
        mRecipeListViewModel.getRecipes().observe(this,
            Observer<List<Recipe>> {
                it?.forEach { recipe ->
                    Log.d(TAG, "onChanged: " + recipe.title)
                }
                recipeListAdapter.setRecipe(it)
            })
    }

    private fun searchRecipeApi(query: String, pageNumber: Int) {
        mRecipeListViewModel.searchRecipeApi(query, pageNumber)
    }

    override fun onRecipeClick(position: Int) {

    }

    override fun onCategoryClick(category: String) {

    }


}
