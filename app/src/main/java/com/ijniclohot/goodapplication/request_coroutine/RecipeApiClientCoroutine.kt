package com.ijniclohot.goodapplication.request_coroutine

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ijniclohot.goodapplication.models.Recipe
import com.ijniclohot.goodapplication.request.RecipeApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RecipeApiClientCoroutine {
    companion object {
        val TAG = "RecipeApiClient"

        private var instance: RecipeApiClientCoroutine? = null

        fun getInstance(): RecipeApiClientCoroutine {
            if (instance == null) {
                instance = RecipeApiClientCoroutine()
            }
            return instance!!
        }
    }

    private val _mRecipes = MutableLiveData<List<Recipe>>()
    val mRecipe : LiveData<List<Recipe>> = _mRecipes
    private val recipeApiCoroutine : RecipeApiCoroutine = ServiceGeneratorCoroutine.recipeApiCoroutine

     fun searchRecipe(q: String, page: Int){
         GlobalScope.launch(Dispatchers.IO){
             val response = recipeApiCoroutine.searchRecipe(q, page.toString())
             if (response.isSuccessful){
                 if (response.code() == 200){
                     if (response.body() != null){
                         _mRecipes.postValue(response.body()!!.recipes)
                     }else{
                         _mRecipes.postValue(null)
                     }
                 }
             }
         }
    }
}