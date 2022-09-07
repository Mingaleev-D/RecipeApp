package com.example.recipeapp.data.local

import androidx.lifecycle.LiveData
import com.example.recipeapp.data.model.Favorite

/**
 * @author : Mingaleev D
 * @data : 6/09/2022
 */

class FavoriteRepository(
    private val favoriteDao: FavoriteDao
) {
    fun insertFavorite(favorite: Favorite){
        favoriteDao.insertFavorite(favorite)
    }

    fun deleteFavoriteById(id:String){
        favoriteDao.deleteFavoriteById(id)
    }

    fun getFavoriteById(id:String):Favorite{
        return favoriteDao.getFavoriteById(id)
    }

    val getAllFavorites:LiveData<List<Favorite>> = favoriteDao.getAllFavorites()
}