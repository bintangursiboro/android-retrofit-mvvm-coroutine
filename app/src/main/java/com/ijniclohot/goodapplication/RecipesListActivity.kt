package com.ijniclohot.goodapplication

import android.os.Bundle
import android.util.Log
import android.view.View
import com.ijniclohot.goodapplication.models.response.RecipeSearchResponse
import com.ijniclohot.goodapplication.request.ServiceGenerator
import kotlinx.android.synthetic.main.activity_recipe_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class RecipesListActivity : BaseActivity() {

    companion object {
        val TAG = "RecipeListActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)

        test.setOnClickListener {
            testRetrofitRequest()
        }
    }

    fun testRetrofitRequest() {
        val recipeApi = ServiceGenerator.recipeApi

        val responseCall = recipeApi.searchRecipe(
            "chicken breast",
            "1"
        )

        responseCall.enqueue(object : Callback<RecipeSearchResponse> {
            override fun onFailure(call: Call<RecipeSearchResponse>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<RecipeSearchResponse>,
                response: Response<RecipeSearchResponse>
            ) {
                Log.d(TAG, "onResponse: server response" + response.toString())
                if (response.code() == 200) {
                    Log.d(TAG, "onResponse: " + response.body().toString())
                    val recipes = response.body()?.recipes
                    recipes?.let { it ->
                        it.forEach {
                            Log.d(TAG, "onResponse: " + it.title)
                        }
                    }
                } else {
                    try {
                        Log.d(TAG, "onResponse: " + response.errorBody().toString())
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }

        })
    }
}
