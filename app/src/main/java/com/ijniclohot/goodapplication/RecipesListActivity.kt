package com.ijniclohot.goodapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
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
        initSearchView()
        initRecycleViewItem()
    }

    private fun initRecycleViewItem(){
        recipeListAdapter.displayCategory()
    }

    private fun initRecyclerAdapter() {
        recipeListAdapter = RecipeRecycleAdapter(this)
        recipe_list.adapter = recipeListAdapter
        recipe_list.layoutManager = LinearLayoutManager(this)
    }


    private fun subscribeObserver() {
        mRecipeListViewModel.getRecipes().observe(this,
            Observer {
                it?.forEach { recipe ->
                    Log.d(TAG, "onChanged: " + recipe.title)
                }
                recipeListAdapter.setRecipe(it)
            })
    }

    private fun initSearchView() {
        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.apply {
                    recipeListAdapter.displayLoading()
                    mRecipeListViewModel.searchRecipeApi(this, 1)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    override fun onRecipeClick(position: Int) {

    }

    override fun onCategoryClick(category: String) {

    }


}
