package com.example.recipeapp.data.model


import com.google.gson.annotations.SerializedName

data class MealListAPIResponse(
    @SerializedName("meals")
    val meals: List<Meal>
)