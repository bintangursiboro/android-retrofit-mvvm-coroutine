package com.ijniclohot.goodapplication.models.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.ijniclohot.goodapplication.models.Recipe

data class RecipeResponse(@SerializedName("recipe") @Expose() val recipe: Recipe)