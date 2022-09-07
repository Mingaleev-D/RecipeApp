package com.example.recipeapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.recipeapp.data.model.MealXFood
import com.example.recipeapp.databinding.FragmentHomeBinding
import com.example.recipeapp.ui.adapter.HomeAdapter
import com.example.recipeapp.ui.viewmodel.HomeViewModel
import com.example.recipeapp.util.Constants.Companion.ID
import com.example.recipeapp.ui.activities.DetailsActivity


class HomeFragment : Fragment() {

    private var mBinding: FragmentHomeBinding? = null
    private val binding get() = mBinding!!

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var homeAdapter: HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        homeAdapter = HomeAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerViewHome()
        getFoodsByCategory()
        observeFoods()
        setOnClickHome()
        showProgressbar()

    }

    private fun showProgressbar() {
        binding.homemProgressBar.visibility = View.VISIBLE
    }

    private fun setOnClickHome() {
        homeAdapter.setOnClickMaterialCardView(object : HomeAdapter.OnClickMaterialCardView {
            override fun onClick(foodsHome: MealXFood) {
                Intent(activity, DetailsActivity::class.java).also {
                    it.putExtra(ID, foodsHome.idMeal)
                    startActivity(it)
                }
            }

        })
    }

    private fun observeFoods() {
        homeViewModel.observeFoods().observe(viewLifecycleOwner) {
            homeAdapter.setFoods(it.meals)
            hideProgressBar()
        }
    }

    private fun hideProgressBar() {
        binding.homemProgressBar.visibility = View.GONE
    }

    private fun getFoodsByCategory() {
        homeViewModel.getFoodsByCategory("beef")
    }

    private fun setUpRecyclerViewHome() {
        binding.homemRecyclerView.apply {
            adapter = homeAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }


}