package com.example.recipeapp.ui.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recipeapp.data.model.Meal
import com.example.recipeapp.data.model.MealListAPIResponse
import com.example.recipeapp.data.remote.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @author : Mingaleev D
 * @data : 7/09/2022
 */

class SearchViewModel: ViewModel() {

    private var meal = MutableLiveData<Meal>()

    fun getMealByName(mealName:String,context: Context?){
        RetrofitInstance.mealApi.getMealByNameSearch(mealName).enqueue(object :Callback<MealListAPIResponse>{
            override fun onResponse(
                call: Call<MealListAPIResponse>,
                response: Response<MealListAPIResponse>
            ) {
                if (response.body()?.meals == null)
                    Toast.makeText(context?.applicationContext, "Not found...(", Toast.LENGTH_SHORT).show()
                else meal.value = response.body()!!.meals!![0]
            }

            override fun onFailure(call: Call<MealListAPIResponse>, t: Throwable) {
                Log.e("TAG", "onFailure: ${t.message.toString()}", )
            }

        })
    }

    fun observeMeal():LiveData<Meal>{
        return meal
    }
}