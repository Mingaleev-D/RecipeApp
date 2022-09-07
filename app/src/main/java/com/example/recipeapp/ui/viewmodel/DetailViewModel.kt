package com.example.recipeapp.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.data.local.FavoriteDatabase
import com.example.recipeapp.data.local.FavoriteRepository
import com.example.recipeapp.data.model.Favorite
import com.example.recipeapp.data.model.Meal
import com.example.recipeapp.data.model.MealListAPIResponse
import com.example.recipeapp.data.remote.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @author : Mingaleev D
 * @data : 6/09/2022
 */

class DetailViewModel(
    app: Application
) : AndroidViewModel(app) {

    private var favoriteRepository:FavoriteRepository
    private var favorites:LiveData<List<Favorite>>
    private val meal = MutableLiveData<Meal>()

    init {
        val favoriteDao = FavoriteDatabase.getInstance(app).favoriteDao()
        favoriteRepository = FavoriteRepository(favoriteDao)
        favorites = favoriteRepository.getAllFavorites
    }

    fun insertFavorite(favorite: Favorite){
        viewModelScope.launch(Dispatchers.IO) {
            favoriteRepository.insertFavorite(favorite)
            withContext(Dispatchers.Main){}
        }
    }

    fun deleteFavoriteById(id:String){
        viewModelScope.launch(Dispatchers.IO) {
            favoriteRepository.deleteFavoriteById(id)
        }
    }

    fun isFavorite(id:String):Boolean {
        var favorite:Favorite?
        runBlocking (Dispatchers.IO){
            favorite = favoriteRepository.getFavoriteById(id)
        }
        if (favorite == null) return false
        return true
    }

    fun getMealById(id:String){
        RetrofitInstance.mealApi.getMealById(id).enqueue(object :Callback<MealListAPIResponse>{
            override fun onResponse(
                call: Call<MealListAPIResponse>,
                response: Response<MealListAPIResponse>
            ) {
                meal.value = response.body()!!.meals!![0]
            }

            override fun onFailure(call: Call<MealListAPIResponse>, t: Throwable) {
                Log.e("TAG", "onFailure: ${t.message.toString()} ", )
            }

        })
    }

    fun observeMeal():LiveData<Meal>{
        return meal
    }
}