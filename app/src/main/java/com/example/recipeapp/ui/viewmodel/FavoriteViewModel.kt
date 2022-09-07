package com.example.recipeapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.recipeapp.data.local.FavoriteDatabase
import com.example.recipeapp.data.local.FavoriteRepository
import com.example.recipeapp.data.model.Favorite

/**
 * @author : Mingaleev D
 * @data : 7/09/2022
 */

class FavoriteViewModel(
    app:Application
): AndroidViewModel(app) {


    private var favoriteRepository:FavoriteRepository
    private var favorites: LiveData<List<Favorite>>

    init {
        val favoriteDao = FavoriteDatabase.getInstance(app).favoriteDao()
        favoriteRepository = FavoriteRepository(favoriteDao)
        favorites = favoriteRepository.getAllFavorites
    }

    fun observeFavorites():LiveData<List<Favorite>>{
        return favorites
    }
}