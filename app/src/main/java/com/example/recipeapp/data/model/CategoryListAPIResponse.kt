package com.example.recipeapp.data.model


import com.google.gson.annotations.SerializedName

data class CategoryListAPIResponse(
    @SerializedName("categories")
    val categories: List<Category>
)