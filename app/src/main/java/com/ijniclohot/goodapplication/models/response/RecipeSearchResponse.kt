package com.ijniclohot.goodapplication.models.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.ijniclohot.goodapplication.models.Recipe

data class RecipeSearchResponse(
    @SerializedName("count") @Expose() val count: Int,
    @SerializedName("recipes") @Expose() val recipes: List<Recipe>
)