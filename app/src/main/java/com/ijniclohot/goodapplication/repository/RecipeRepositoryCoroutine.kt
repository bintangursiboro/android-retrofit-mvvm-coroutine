package com.ijniclohot.goodapplication.repository

import com.ijniclohot.goodapplication.request_coroutine.RecipeApiClientCoroutine

class RecipeRepositoryCoroutine {
    companion object {
        private var instance: RecipeRepositoryCoroutine? = null

        fun getInstance() : RecipeRepositoryCoroutine{
            if (instance == null){
                instance = RecipeRepositoryCoroutine()
            }
            return instance!!
        }
    }

     fun getRecipeApiClient() : RecipeApiClientCoroutine {
        return RecipeApiClientCoroutine.getInstance()
    }

    fun searchRecipeApi(query:String, pageNumber:Int){
        getRecipeApiClient().searchRecipe(query,pageNumber)
    }
}