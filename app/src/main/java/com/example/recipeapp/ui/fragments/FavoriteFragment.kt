package com.example.recipeapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.recipeapp.data.model.Favorite
import com.example.recipeapp.databinding.FragmentFavoriteBinding
import com.example.recipeapp.ui.activities.DetailsActivity
import com.example.recipeapp.ui.viewmodel.FavoriteViewModel
import com.example.recipeapp.util.Constants.Companion.ID
import com.example.recipeapp.util.adapter.FavoriteAdapter


class FavoriteFragment : Fragment() {

    private var mBinding: FragmentFavoriteBinding? = null
    private val binding get() = mBinding!!

    private lateinit var favoriteViewModel:FavoriteViewModel
    private lateinit var favoriteAdapter:FavoriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favoriteViewModel = ViewModelProvider(this)[FavoriteViewModel::class.java]
        favoriteAdapter = FavoriteAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentFavoriteBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerViewFavorite()
        observeFavorites()
        setOnClickFavorite()

    }

    private fun setOnClickFavorite() {
        favoriteAdapter.setOnClickMaterialCardView(object :FavoriteAdapter.OnClickMaterialCardView{
            override fun onClick(favorite: Favorite) {
                Intent(context, DetailsActivity::class.java).also {
                    it.putExtra(ID,favorite.id.toString())
                    startActivity(it)
                }
            }

        })
    }

    private fun observeFavorites() {
        favoriteViewModel.observeFavorites().observe(viewLifecycleOwner){
            favoriteAdapter.setFavorites(it)
            if (it.isEmpty()) binding.favoritemTextView.visibility = View.VISIBLE
            else binding.favoritemTextView.visibility = View.GONE
        }
    }

    private fun setUpRecyclerViewFavorite() {
        binding.favoritemRecyclerView.apply {
          adapter =  favoriteAdapter
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }


}