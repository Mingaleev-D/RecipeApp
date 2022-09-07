package com.example.recipeapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.recipeapp.data.model.Category
import com.example.recipeapp.databinding.FragmentCategoryBinding
import com.example.recipeapp.ui.activities.FoodActivity
import com.example.recipeapp.ui.viewmodel.CategoryViewModel
import com.example.recipeapp.util.Constants.Companion.CATEGORY
import com.example.recipeapp.util.adapter.CategoryAdapter


class CategoryFragment : Fragment() {

    private var mBinding:FragmentCategoryBinding? = null
    private val binding get() = mBinding!!

    private lateinit var categoryViewModel:CategoryViewModel
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categoryViewModel = ViewModelProvider(this)[CategoryViewModel::class.java]
        categoryAdapter = CategoryAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentCategoryBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        observeCategories()
        setOnClickListener()
    }

    private fun setUpRecyclerView() {
        binding.mRecyclerView.apply {
            adapter = categoryAdapter
        }
    }

    private fun observeCategories() {
        categoryViewModel.observeCategories().observe(viewLifecycleOwner){
            categoryAdapter.setCategories(it.categories)
        }
    }


    private fun setOnClickListener() {
        categoryAdapter.setOnClickMaterialCardView(object : CategoryAdapter.OnClickMaterialCardView{
            override fun onClick(category: Category) {
               Intent(context,FoodActivity::class.java).also {
                   it.putExtra(CATEGORY,category.strCategory)
                   startActivity(it)
               }
            }
        })
    }





    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }

}