package com.example.recipeapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recipeapp.data.model.FoodListAPIResponse
import com.example.recipeapp.data.remote.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @author : Mingaleev D
 * @data : 7/09/2022
 */

class HomeViewModel:ViewModel() {

    private val foods = MutableLiveData<FoodListAPIResponse>()

    fun getFoodsByCategory(categoryName:String){
        RetrofitInstance.mealApi.getFoodsByCategory(categoryName).enqueue(object:Callback<FoodListAPIResponse>{
            override fun onResponse(
                call: Call<FoodListAPIResponse>,
                response: Response<FoodListAPIResponse>
            ) {
                foods.value = response.body()
            }

            override fun onFailure(call: Call<FoodListAPIResponse>, t: Throwable) {
                Log.e("TAG", "onFailure: ${t.message.toString()}", )
            }

        })
    }

    fun observeFoods():LiveData<FoodListAPIResponse>{
        return foods
    }
}