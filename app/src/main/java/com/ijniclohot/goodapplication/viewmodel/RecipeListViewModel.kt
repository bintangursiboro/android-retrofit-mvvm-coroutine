package com.ijniclohot.goodapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ijniclohot.goodapplication.models.Recipe
import com.ijniclohot.goodapplication.repository.RecipeRepository

class RecipeListViewModel : ViewModel() {
    private var mRecipeRepository: RecipeRepository = RecipeRepository.getInstance()

    fun getRecipes() : LiveData<List<Recipe>>{
        return mRecipeRepository.getRecipeApiClient().mRecipe
    }

    fun searchRecipeApi(query: String, pageNumber: Int) {
        mRecipeRepository.searchRecipeApi(query, pageNumber)
    }
}