package com.ijniclohot.goodapplication.request

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ijniclohot.goodapplication.AppExecutor
import com.ijniclohot.goodapplication.models.Recipe
import com.ijniclohot.goodapplication.models.response.RecipeSearchResponse
import com.ijniclohot.goodapplication.utils.Constant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response
import retrofit2.awaitResponse
import java.io.IOException
import java.util.concurrent.TimeUnit

class RecipeApiClient {

    private val _mRecipes = MutableLiveData<List<Recipe>>()
    val mRecipe: LiveData<List<Recipe>> = _mRecipes
    private var mRetrieveRecipesRunnable: RetrieveRecipesRunnable? = null

    companion object {
        val TAG = "RecipeApiClient"

        private var instance: RecipeApiClient? = null

        fun getInstance(): RecipeApiClient {
            if (instance == null) {
                instance = RecipeApiClient()
            }
            return instance!!
        }
    }

    fun searchRecipesApi(query: String, pageNumber: Int) {
        if (mRetrieveRecipesRunnable != null) {
            mRetrieveRecipesRunnable = null
        }
        mRetrieveRecipesRunnable = RetrieveRecipesRunnable(query, pageNumber)
        val handler = AppExecutor.getInstance().networkIo().submit(mRetrieveRecipesRunnable!!)
        AppExecutor.getInstance().networkIo().schedule({
            handler.cancel(true)
        }, Constant.NETWORK_TIMOUT, TimeUnit.MILLISECONDS)

        //Using coroutine
//        CoroutineScope(Dispatchers.IO).launch {
//            val response = getRecipes(query, pageNumber)
//            if (response.code() == 200){
//                _mRecipes.postValue(response.body()?.recipes)
//            }else{
//                _mRecipes.postValue(null)
//            }
//        }
    }

    private fun getRecipes(query: String, pageNumber: Int): Call<RecipeSearchResponse> {
        return ServiceGenerator.recipeApi.searchRecipe(query, pageNumber.toString())
    }


    inner class RetrieveRecipesRunnable(
        private val query: String,
        private val pageNumber: Int,
        var cancelRequest: Boolean = false
    ) : Runnable {
        override fun run() {
            try {
                val response = getRecipes(query, pageNumber).execute()
                if (response.code() == 200) {
                    var list: ArrayList<Recipe> = ArrayList()
                    response.body()?.let { responseBody ->
                        list = ArrayList(responseBody.recipes)
                        if (pageNumber == 1) {
                            _mRecipes.postValue(list)
                        } else {
                            val currentRecipe: MutableList<Recipe>? =
                                _mRecipes.value as MutableList<Recipe>
                            currentRecipe?.addAll(list)
                            _mRecipes.postValue(currentRecipe)
                        }
                    }
                } else {
                    _mRecipes.postValue(null)
                }
            } catch (e: IOException) {
                e.printStackTrace()
                _mRecipes.postValue(null)
            }
        }

        private fun getRecipes(query: String, pageNumber: Int): Call<RecipeSearchResponse> {
            return ServiceGenerator.recipeApi.searchRecipe(query, pageNumber.toString())
        }

        private fun cancelRequest() {
            Log.d(TAG, "cancelRequest: canceling the search request")
            cancelRequest = true
        }
    }


}