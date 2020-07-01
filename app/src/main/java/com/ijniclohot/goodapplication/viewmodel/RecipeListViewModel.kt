package com.ijniclohot.goodapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ijniclohot.goodapplication.models.Recipe
import com.ijniclohot.goodapplication.repository.RecipeRepository
import com.ijniclohot.goodapplication.repository.RecipeRepositoryCoroutine

class RecipeListViewModel : ViewModel() {
    private var mRecipeRepository: RecipeRepository = RecipeRepository.getInstance()
    private var mRecipeRepositoryCoroutine: RecipeRepositoryCoroutine =
        RecipeRepositoryCoroutine.getInstance()

    fun getRecipes(): LiveData<List<Recipe>> {
        return mRecipeRepository.getRecipeApiClient().mRecipe
    }


    fun getRecipesCoroutine(): LiveData<List<Recipe>> {
        return mRecipeRepositoryCoroutine.getRecipeApiClient().mRecipe
    }

    fun searchRecipeApiCoroutine(query: String, pageNumber: Int) {
        mRecipeRepositoryCoroutine.searchRecipeApi(query, pageNumber)
    }

    fun searchRecipeApi(query: String, pageNumber: Int) {
        mRecipeRepository.searchRecipeApi(query, pageNumber)
    }
}