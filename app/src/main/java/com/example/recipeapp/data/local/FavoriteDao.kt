package com.example.recipeapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.recipeapp.data.model.Favorite

/**
 * @author : Mingaleev D
 * @data : 6/09/2022
 */
@Dao
interface FavoriteDao {

    @Insert
    fun insertFavorite(favorite: Favorite)

    @Query("DELETE FROM favorite_table WHERE id =:id")
    fun deleteFavoriteById(id:String)

    @Query("SELECT * FROM favorite_table WHERE id =:id")
    fun getFavoriteById(id:String):Favorite

    @Query("SELECT * FROM favorite_table order by name asc")
    fun getAllFavorites():LiveData<List<Favorite>>
}