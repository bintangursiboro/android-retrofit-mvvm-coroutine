package com.ijniclohot.goodapplication.request

import com.ijniclohot.goodapplication.utils.Constant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceGenerator {

    private val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)

    private val client= OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()


    private val retrofitBuilder: Retrofit.Builder = Retrofit.Builder().baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)

    private val instance: Retrofit = retrofitBuilder.build()

    val recipeApi: RecipeApi = instance.create(RecipeApi::class.java)
}