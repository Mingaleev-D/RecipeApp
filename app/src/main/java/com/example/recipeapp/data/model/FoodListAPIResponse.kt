package com.example.recipeapp.data.model


import com.google.gson.annotations.SerializedName

data class FoodListAPIResponse(
    @SerializedName("meals")
    val meals: List<MealXFood>
)