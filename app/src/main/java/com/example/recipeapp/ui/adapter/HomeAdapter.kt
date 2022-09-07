package com.example.recipeapp.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.recipeapp.R
import com.example.recipeapp.data.model.MealXFood
import com.example.recipeapp.databinding.HomeItemBinding

/**
 * @author : Mingaleev D
 * @data : 6/09/2022
 */

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.MyHomeViewHolder>() {

    private var foodsHome: List<MealXFood> = ArrayList()
    private lateinit var onClickMaterialCardView: OnClickMaterialCardView

    class MyHomeViewHolder(val binding: HomeItemBinding) : RecyclerView.ViewHolder(binding.root)

    @SuppressLint("NotifyDataSetChanged")
    fun setFoods(foodsH: List<MealXFood>) {
        this.foodsHome = foodsH
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHomeViewHolder {
        return MyHomeViewHolder(
            HomeItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyHomeViewHolder, position: Int) {
        val currentFoods = foodsHome[position]

        holder.binding.apply {
            homeImageView.load(currentFoods.strMealThumb){
                placeholder(R.color.red)
                error(R.color.red)
                crossfade(true)
                crossfade(400)
            }
            homeTextViewName.text = currentFoods.strMeal

        }
        holder.binding.homeCardView.setOnClickListener {
            onClickMaterialCardView.onClick(currentFoods)
        }
    }

    override fun getItemCount(): Int {
        return foodsHome.size
    }

    fun setOnClickMaterialCardView(onClickMaterialCardView: OnClickMaterialCardView) {
        this.onClickMaterialCardView = onClickMaterialCardView
    }

    interface OnClickMaterialCardView {
        fun onClick(foodsHome: MealXFood)
    }
}