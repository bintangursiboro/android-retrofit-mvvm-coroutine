package com.ijniclohot.goodapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ijniclohot.goodapplication.models.Recipe
import com.ijniclohot.goodapplication.request.RecipeApiClient

class RecipeRepository {
    companion object {
        private var instance: RecipeRepository? = null

        fun getInstance() : RecipeRepository{
            if (instance == null){
                instance = RecipeRepository()
            }
            return instance!!
        }
    }

    fun getRecipeApiClient() : RecipeApiClient{
        return RecipeApiClient.getInstance()
    }

    fun searchRecipeApi(query:String, pageNumber:Int){
        getRecipeApiClient().searchRecipesApi(query,pageNumber)
    }

}