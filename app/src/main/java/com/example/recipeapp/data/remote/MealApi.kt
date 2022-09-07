package com.example.recipeapp.data.remote

import com.example.recipeapp.data.model.CategoryListAPIResponse
import com.example.recipeapp.data.model.FoodListAPIResponse
import com.example.recipeapp.data.model.MealListAPIResponse
import com.example.recipeapp.util.Constants.Companion.END_POINT_CATEGORY
import com.example.recipeapp.util.Constants.Companion.END_POINT_FILTER
import com.example.recipeapp.util.Constants.Companion.END_POINT_LOOKUP
import com.example.recipeapp.util.Constants.Companion.END_POINT_SEARCH
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author : Mingaleev D
 * @data : 6/09/2022
 */

interface MealApi {

    @GET(END_POINT_FILTER)
    fun getFoodsByCategory(
        @Query("c") categoryName:String
    ):Call<FoodListAPIResponse>

    @GET(END_POINT_CATEGORY)
    fun getCategories():Call<CategoryListAPIResponse>

    @GET(END_POINT_LOOKUP)
    fun getMealById(
        @Query("i") id:String
    ):Call<MealListAPIResponse>

    @GET(END_POINT_SEARCH)
    fun getMealByNameSearch(
        @Query("s") mealName:String
    ):Call<MealListAPIResponse>
}