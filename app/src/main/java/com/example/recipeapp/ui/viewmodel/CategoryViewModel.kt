package com.example.recipeapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recipeapp.data.model.CategoryListAPIResponse
import com.example.recipeapp.data.remote.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @author : Mingaleev D
 * @data : 7/09/2022
 */

class CategoryViewModel:ViewModel() {

    private var categories = MutableLiveData<CategoryListAPIResponse>()

    init {
        getCategories()
    }

    private fun getCategories() {
        RetrofitInstance.mealApi.getCategories().enqueue(object :Callback<CategoryListAPIResponse>{
            override fun onResponse(
                call: Call<CategoryListAPIResponse>,
                response: Response<CategoryListAPIResponse>
            ) {
                categories.value = response.body()
            }

            override fun onFailure(call: Call<CategoryListAPIResponse>, t: Throwable) {
                Log.e("TAG", "onFailure: ${t.message.toString()}", )
            }

        })
    }

    fun observeCategories():LiveData<CategoryListAPIResponse>{
        return categories
    }
}