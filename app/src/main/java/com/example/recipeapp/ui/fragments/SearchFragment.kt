package com.example.recipeapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.recipeapp.R
import com.example.recipeapp.databinding.FragmentSearchBinding
import com.example.recipeapp.ui.viewmodel.SearchViewModel
import com.example.recipeapp.util.Constants.Companion.ID
import com.example.recipeapp.ui.activities.DetailsActivity


class SearchFragment : Fragment() {

    private var mBinding: FragmentSearchBinding? = null
    private val binding get() = mBinding!!

    private lateinit var searchViewModel: SearchViewModel
    private var id = ""
    private var name = ""
    private var imageLink = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeMeal()
        setOnClickSearchView()
        setOnClickMaterialCardView()
    }

    private fun setOnClickMaterialCardView() {
        binding.searchmMaterialCardView.setOnClickListener {
            Intent(context, DetailsActivity::class.java).also {
                it.putExtra(ID,id)
                startActivity(it)
            }
        }
    }

    private fun setOnClickSearchView() {
        binding.mSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(queryName: String?): Boolean {
                if (queryName != null) {
                    searchViewModel.getMealByName(queryName, context)
                }
                return true
            }

            override fun onQueryTextChange(newTextMealName: String?): Boolean {
                return true
            }

        })
    }

    private fun observeMeal() {
        searchViewModel.observeMeal().observe(viewLifecycleOwner) {
            binding.apply {
                id = it.idMeal!!
                name = it.strMeal!!
                imageLink = it.strMealThumb!!

                binding.searchmImageView.load(it.strMealThumb) {
                    placeholder(R.color.red)
                    error(R.color.red)
                    crossfade(true)
                    crossfade(200)

                }
                searchmTextViewName.text = it.strMeal
                searchmMaterialCardView.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }


}