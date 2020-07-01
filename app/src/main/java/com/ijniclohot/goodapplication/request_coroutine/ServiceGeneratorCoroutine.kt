package com.ijniclohot.goodapplication.request_coroutine

import com.ijniclohot.goodapplication.request.RecipeApi
import com.ijniclohot.goodapplication.utils.Constant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceGeneratorCoroutine {

    private val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()


    private val retrofitBuilder: Retrofit.Builder = Retrofit.Builder().baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)

    private val instance: Retrofit = retrofitBuilder.build()

    val recipeApiCoroutine: RecipeApiCoroutine = instance.create(RecipeApiCoroutine::class.java)

}