package com.ijniclohot.goodapplication.request

import com.ijniclohot.goodapplication.models.response.RecipeResponse
import com.ijniclohot.goodapplication.models.response.RecipeSearchResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeApi {
    //Search
    @GET("api/search")
    fun searchRecipe(
        @Query("q") query: String,
        @Query("page") page: String
    ): Call<RecipeSearchResponse>

    @GET("api/get")
    suspend fun getRecipe(@Query("rid") recipe_id: String): Call<RecipeResponse>
}