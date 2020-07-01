package com.ijniclohot.goodapplication.request_coroutine

import com.ijniclohot.goodapplication.models.response.RecipeResponse
import com.ijniclohot.goodapplication.models.response.RecipeSearchResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeApiCoroutine {
    @GET("api/search")
    suspend fun searchRecipe(
        @Query("q") query: String,
        @Query("page") page: String
    ): Response<RecipeSearchResponse>

    @GET("api/get")
    suspend fun getRecipe(@Query("rid") recipe_id: String): Call<RecipeResponse>
}