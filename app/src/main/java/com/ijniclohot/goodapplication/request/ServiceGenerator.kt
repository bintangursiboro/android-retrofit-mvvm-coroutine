package com.ijniclohot.goodapplication.request

import com.ijniclohot.goodapplication.utils.Constant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceGenerator {
    val retrofitBuilder = Retrofit.Builder().baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())

    val instance = retrofitBuilder.build()

    val recipeApi = instance.create(RecipeApi::class.java)
}