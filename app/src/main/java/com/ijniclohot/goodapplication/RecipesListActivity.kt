package com.ijniclohot.goodapplication

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ijniclohot.goodapplication.models.Recipe
import com.ijniclohot.goodapplication.viewmodel.RecipeListViewModel
import kotlinx.android.synthetic.main.activity_recipe_list.*

class RecipesListActivity : BaseActivity() {

    companion object {
        val TAG = "RecipeListActivity"
    }

    private lateinit var mRecipeListViewModel: RecipeListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)

        mRecipeListViewModel = ViewModelProvider(this)[RecipeListViewModel::class.java]

        test.setOnClickListener {
            searchRecipeApi("Chicken Breast", 1)
        }

        subscribeObserver()
    }

    private fun subscribeObserver() {
        mRecipeListViewModel.getRecipes().observe(this,
            Observer<List<Recipe>> {
                it?.forEach { recipe ->
                    Log.d(TAG, "onChanged: " + recipe.title)
                }
            })
    }

    private fun searchRecipeApi(query: String, pageNumber: Int) {
        mRecipeListViewModel.searchRecipeApi(query, pageNumber)
    }


}
