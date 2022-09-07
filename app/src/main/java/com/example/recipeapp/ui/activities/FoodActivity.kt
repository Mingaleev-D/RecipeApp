package com.example.recipeapp.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.recipeapp.data.model.MealXFood
import com.example.recipeapp.databinding.ActivityFoodBinding
import com.example.recipeapp.ui.viewmodel.FoodViewModel
import com.example.recipeapp.util.Constants.Companion.CATEGORY
import com.example.recipeapp.util.Constants.Companion.ID
import com.example.recipeapp.util.adapter.FoodAdapter

class FoodActivity : AppCompatActivity() {
    private lateinit var binding:ActivityFoodBinding

    private lateinit var foodAdapter: FoodAdapter
    private lateinit var foodViewModel: FoodViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        foodViewModel = ViewModelProvider(this)[FoodViewModel::class.java]
        foodAdapter = FoodAdapter()

        setUpRecyclerView()
        getFoodsByCategory()
        observeFoods()
        setOnClickListener()
        showProgressBar()
    }

    private fun showProgressBar() {
        binding.foodmProgressBar.visibility = View.VISIBLE
    }

    private fun setOnClickListener() {
        foodAdapter.setOnClickMaterialCardView(object:FoodAdapter.OnClickMaterialCardView{
            override fun onClick(food: MealXFood) {
                Intent(applicationContext,DetailsActivity::class.java).also {
                    it.putExtra(ID,food.idMeal)
                    startActivity(it)
                }
            }
        })
    }

    private fun observeFoods() {
        foodViewModel.observeFoods().observe(this){
            foodAdapter.setFoods(it.meals)
            hideProgressBar()
        }
    }

    private fun getFoodsByCategory() {
      foodViewModel.getFoodsByCategory(intent.getStringExtra(CATEGORY)!!)
    }

    private fun hideProgressBar() {
        binding.foodmProgressBar.visibility = View.GONE
    }

    private fun setUpRecyclerView() {
        binding.foodmRecyclerView.apply {
            adapter = foodAdapter
        }
    }
}