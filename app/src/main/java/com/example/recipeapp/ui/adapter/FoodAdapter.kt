package com.example.recipeapp.util.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.recipeapp.R
import com.example.recipeapp.data.model.MealXFood
import com.example.recipeapp.databinding.FoodItemBinding

/**
 * @author : Mingaleev D
 * @data : 6/09/2022
 */

class FoodAdapter : RecyclerView.Adapter<FoodAdapter.MyFoodViewHolder>() {

    private var foods: List<MealXFood> = ArrayList()
    private lateinit var onClickMaterialCardView: OnClickMaterialCardView

    class MyFoodViewHolder(val binding: FoodItemBinding) : RecyclerView.ViewHolder(binding.root)
    @SuppressLint("NotifyDataSetChanged")
    fun setFoods(foods:List<MealXFood>){
        this.foods = foods
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyFoodViewHolder {
        return MyFoodViewHolder(
            FoodItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyFoodViewHolder, position: Int) {
        val food = foods[position]

        holder.binding.apply {
            foodImageView.load(food.strMealThumb){
                placeholder(R.color.red)
                error(R.color.red)
                crossfade(true)
                crossfade(400)
            }
            foodTextViewName.text = food.strMeal
        }

        holder.binding.foodMaterialCardView.setOnClickListener {
            onClickMaterialCardView.onClick(food)
        }

    }

    override fun getItemCount(): Int {
        return foods.size
    }

    fun setOnClickMaterialCardView(onClickMaterialCardView: OnClickMaterialCardView) {
        this.onClickMaterialCardView = onClickMaterialCardView
    }

    interface OnClickMaterialCardView {
        fun onClick(food: MealXFood)
    }

}