package com.example.recipeapp.util.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.recipeapp.R
import com.example.recipeapp.data.model.Favorite
import com.example.recipeapp.databinding.FavoriteItemBinding

/**
 * @author : Mingaleev D
 * @data : 6/09/2022
 */

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.MyFavoriteViewHolder>() {

    private var favorites: List<Favorite> = ArrayList()
    private lateinit var onClickMaterialCardView: OnClickMaterialCardView

    class MyFavoriteViewHolder(val binding: FavoriteItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    @SuppressLint("NotifyDataSetChanged")
    fun setFavorites(favorite: List<Favorite>){
        this.favorites = favorite
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyFavoriteViewHolder {
        return MyFavoriteViewHolder(
            FavoriteItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyFavoriteViewHolder, position: Int) {
        val favorite = favorites[position]

        holder.binding.apply {
            favoriteImageView.load(favorite.imageLink){
                placeholder(R.color.red)
                error(R.color.red)
                crossfade(true)
                crossfade(400)
            }
            favoriteTextViewName.text = favorite.name
        }
        holder.binding.favoriteMaterialCardView.setOnClickListener {
            onClickMaterialCardView.onClick(favorite)
        }
    }

    override fun getItemCount(): Int {
        return favorites.size
    }

    fun setOnClickMaterialCardView(onClickMaterialCardView: OnClickMaterialCardView) {
        this.onClickMaterialCardView = onClickMaterialCardView
    }

    interface OnClickMaterialCardView {
        fun onClick(favorite: Favorite)
    }


}