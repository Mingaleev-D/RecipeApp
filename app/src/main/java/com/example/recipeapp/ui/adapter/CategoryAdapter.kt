package com.example.recipeapp.util.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.recipeapp.R
import com.example.recipeapp.data.model.Category
import com.example.recipeapp.databinding.CategoriesItemBinding

/**
 * @author : Mingaleev D
 * @data : 6/09/2022
 */

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.MyCategoryViewHolder>() {

    private var categories: List<Category> = ArrayList()
    private lateinit var onClickMaterialCardView: OnClickMaterialCardView

    class MyCategoryViewHolder(val binding: CategoriesItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    @SuppressLint("NotifyDataSetChanged")
    fun setCategories(category: List<Category>){
        this.categories = category
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCategoryViewHolder {
        return MyCategoryViewHolder(
            CategoriesItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyCategoryViewHolder, position: Int) {
        val category = categories[position]

        holder.binding.apply {
            categoryImageView.load(category.strCategoryThumb){
                placeholder(R.color.red)
                error(R.color.red)
                crossfade(true)
                crossfade(400)
            }
            categoryTextViewName.text = category.strCategory
        }

        holder.binding.categoryMaterialCardView.setOnClickListener {
            onClickMaterialCardView.onClick(category)
        }
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    fun setOnClickMaterialCardView(onClickMaterialCardView: OnClickMaterialCardView) {
        this.onClickMaterialCardView = onClickMaterialCardView
    }

    interface OnClickMaterialCardView {
        fun onClick(category: Category)
    }
}