package com.example.recipeapp.ui.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.recipeapp.R
import com.example.recipeapp.data.model.Favorite
import com.example.recipeapp.data.model.Meal
import com.example.recipeapp.databinding.ActivityDetailsBinding
import com.example.recipeapp.ui.viewmodel.DetailViewModel
import com.example.recipeapp.util.Constants.Companion.ID
import com.google.android.material.snackbar.Snackbar

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding

    private lateinit var detailsViewModel: DetailViewModel
    private lateinit var meal: Meal
    private var id = ""
    private var linkVideo = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detailsViewModel = ViewModelProvider(this)[DetailViewModel::class.java]

        getIdFromIntent()
        getMealById()
        observeMeal()
        setFloatingActionButtonStatues()
        setOnClickTextViewWatchVideo()
        setOnClickFloatingActionButton()
        showLoading()

    }

    private fun getIdFromIntent() {

        val intent = intent
        id = intent.getStringExtra(ID)!!

    }

    private fun getMealById() {

        detailsViewModel.getMealById(id)

    }

    private fun observeMeal() {

        detailsViewModel.observeMeal().observe(this) {

            this.meal = it
            linkVideo = meal.strYoutube!!

            setViews()
            hideLoading()

        }

    }

    private fun setViews() {

        binding.apply {

            binding.mImageView.load(meal.strMealThumb) {
                placeholder(R.color.red)
                error(R.color.red)
                crossfade(true)
                crossfade(400)
            }
            mTextViewCategory.text = resources.getString(R.string.category) + meal.strCategory
            mTextViewArea.text = resources.getString(R.string.area) + meal.strArea
            mTextViewName.text = meal.strMeal
            mTextViewInstruction.text = meal.strInstructions

            if (meal.strIngredient1 == "") {
                binding.mTextViewIngredient1.visibility = View.GONE
                binding.mTextViewMeasure1.visibility = View.GONE
            } else {
                binding.mTextViewIngredient1.visibility = View.VISIBLE
                binding.mTextViewMeasure1.visibility = View.VISIBLE
                binding.mTextViewIngredient1.text = meal.strIngredient1
                binding.mTextViewMeasure1.text = meal.strMeasure1
            }

            if (meal.strIngredient2 == "") {
                binding.mTextViewIngredient2.visibility = View.GONE
                binding.mTextViewMeasure2.visibility = View.GONE
            } else {
                binding.mTextViewIngredient2.visibility = View.VISIBLE
                binding.mTextViewMeasure2.visibility = View.VISIBLE
                binding.mTextViewIngredient2.text = meal.strIngredient2
                binding.mTextViewMeasure2.text = meal.strMeasure2
            }

            if (meal.strIngredient3 == "") {
                binding.mTextViewIngredient3.visibility = View.GONE
                binding.mTextViewMeasure3.visibility = View.GONE
            } else {
                binding.mTextViewIngredient3.visibility = View.VISIBLE
                binding.mTextViewMeasure3.visibility = View.VISIBLE
                binding.mTextViewIngredient3.text = meal.strIngredient3
                binding.mTextViewMeasure3.text = meal.strMeasure3
            }

            if (meal.strIngredient4 == "") {
                binding.mTextViewIngredient4.visibility = View.GONE
                binding.mTextViewMeasure4.visibility = View.GONE
            } else {
                binding.mTextViewIngredient4.visibility = View.VISIBLE
                binding.mTextViewMeasure4.visibility = View.VISIBLE
                binding.mTextViewIngredient4.text = meal.strIngredient4
                binding.mTextViewMeasure4.text = meal.strMeasure4
            }

            if (meal.strIngredient5 == "") {
                binding.mTextViewIngredient5.visibility = View.GONE
                binding.mTextViewMeasure5.visibility = View.GONE
            } else {
                binding.mTextViewIngredient5.visibility = View.VISIBLE
                binding.mTextViewMeasure5.visibility = View.VISIBLE
                binding.mTextViewIngredient5.text = meal.strIngredient5
                binding.mTextViewMeasure5.text = meal.strMeasure5
            }

            if (meal.strIngredient6 == "") {
                binding.mTextViewIngredient6.visibility = View.GONE
                binding.mTextViewMeasure6.visibility = View.GONE
            } else {
                binding.mTextViewIngredient6.visibility = View.VISIBLE
                binding.mTextViewMeasure6.visibility = View.VISIBLE
                binding.mTextViewIngredient6.text = meal.strIngredient6
                binding.mTextViewMeasure6.text = meal.strMeasure6
            }

            if (meal.strIngredient7 == "") {
                binding.mTextViewIngredient7.visibility = View.GONE
                binding.mTextViewMeasure7.visibility = View.GONE
            } else {
                binding.mTextViewIngredient7.visibility = View.VISIBLE
                binding.mTextViewMeasure7.visibility = View.VISIBLE
                binding.mTextViewIngredient7.text = meal.strIngredient7
                binding.mTextViewMeasure7.text = meal.strMeasure7
            }

            if (meal.strIngredient8 == "") {
                binding.mTextViewIngredient8.visibility = View.GONE
                binding.mTextViewMeasure8.visibility = View.GONE
            } else {
                binding.mTextViewIngredient8.visibility = View.VISIBLE
                binding.mTextViewMeasure8.visibility = View.VISIBLE
                binding.mTextViewIngredient8.text = meal.strIngredient8
                binding.mTextViewMeasure8.text = meal.strMeasure8
            }

            if (meal.strIngredient9 == "") {
                binding.mTextViewIngredient9.visibility = View.GONE
                binding.mTextViewMeasure9.visibility = View.GONE
            } else {
                binding.mTextViewIngredient9.visibility = View.VISIBLE
                binding.mTextViewMeasure9.visibility = View.VISIBLE
                binding.mTextViewIngredient9.text = meal.strIngredient9
                binding.mTextViewMeasure9.text = meal.strMeasure9
            }

            if (meal.strIngredient10 == "") {
                binding.mTextViewIngredient10.visibility = View.GONE
                binding.mTextViewMeasure10.visibility = View.GONE
            } else {
                binding.mTextViewIngredient10.visibility = View.VISIBLE
                binding.mTextViewMeasure10.visibility = View.VISIBLE
                binding.mTextViewIngredient10.text = meal.strIngredient10
                binding.mTextViewMeasure10.text = meal.strMeasure10
            }

            if (meal.strIngredient11 == "") {
                binding.mTextViewIngredient11.visibility = View.GONE
                binding.mTextViewMeasure11.visibility = View.GONE
            } else {
                binding.mTextViewIngredient11.visibility = View.VISIBLE
                binding.mTextViewMeasure11.visibility = View.VISIBLE
                binding.mTextViewIngredient11.text = meal.strIngredient11
                binding.mTextViewMeasure11.text = meal.strMeasure11
            }

            if (meal.strIngredient12 == "") {
                binding.mTextViewIngredient12.visibility = View.GONE
                binding.mTextViewMeasure12.visibility = View.GONE
            } else {
                binding.mTextViewIngredient12.visibility = View.VISIBLE
                binding.mTextViewMeasure12.visibility = View.VISIBLE
                binding.mTextViewIngredient12.text = meal.strIngredient12
                binding.mTextViewMeasure12.text = meal.strMeasure12
            }

            if (meal.strIngredient13 == "") {
                binding.mTextViewIngredient13.visibility = View.GONE
                binding.mTextViewMeasure13.visibility = View.GONE
            } else {
                binding.mTextViewIngredient13.visibility = View.VISIBLE
                binding.mTextViewMeasure13.visibility = View.VISIBLE
                binding.mTextViewIngredient13.text = meal.strIngredient13
                binding.mTextViewMeasure13.text = meal.strMeasure13
            }

            if (meal.strIngredient14 == "") {
                binding.mTextViewIngredient14.visibility = View.GONE
                binding.mTextViewMeasure14.visibility = View.GONE
            } else {
                binding.mTextViewIngredient14.visibility = View.VISIBLE
                binding.mTextViewMeasure14.visibility = View.VISIBLE
                binding.mTextViewIngredient14.text = meal.strIngredient14
                binding.mTextViewMeasure14.text = meal.strMeasure14
            }

            if (meal.strIngredient15 == "") {
                binding.mTextViewIngredient15.visibility = View.GONE
                binding.mTextViewMeasure15.visibility = View.GONE
            } else {
                binding.mTextViewIngredient15.visibility = View.VISIBLE
                binding.mTextViewMeasure15.visibility = View.VISIBLE
                binding.mTextViewIngredient15.text = meal.strIngredient15
                binding.mTextViewMeasure15.text = meal.strMeasure15
            }

            if (meal.strIngredient16 == "") {
                binding.mTextViewIngredient16.visibility = View.GONE
                binding.mTextViewMeasure16.visibility = View.GONE
            } else {
                binding.mTextViewIngredient16.visibility = View.VISIBLE
                binding.mTextViewMeasure16.visibility = View.VISIBLE
                binding.mTextViewIngredient16.text = meal.strIngredient16
                binding.mTextViewMeasure16.text = meal.strMeasure16
            }

            if (meal.strIngredient17 == "") {
                binding.mTextViewIngredient17.visibility = View.GONE
                binding.mTextViewMeasure17.visibility = View.GONE
            } else {
                binding.mTextViewIngredient17.visibility = View.VISIBLE
                binding.mTextViewMeasure17.visibility = View.VISIBLE
                binding.mTextViewIngredient17.text = meal.strIngredient17
                binding.mTextViewMeasure17.text = meal.strMeasure17
            }

            if (meal.strIngredient18 == "") {
                binding.mTextViewIngredient18.visibility = View.GONE
                binding.mTextViewMeasure18.visibility = View.GONE
            } else {
                binding.mTextViewIngredient18.visibility = View.VISIBLE
                binding.mTextViewMeasure18.visibility = View.VISIBLE
                binding.mTextViewIngredient18.text = meal.strIngredient18
                binding.mTextViewMeasure18.text = meal.strMeasure18
            }

            if (meal.strIngredient19 == "") {
                binding.mTextViewIngredient19.visibility = View.GONE
                binding.mTextViewMeasure19.visibility = View.GONE
            } else {
                binding.mTextViewIngredient19.visibility = View.VISIBLE
                binding.mTextViewMeasure19.visibility = View.VISIBLE
                binding.mTextViewIngredient19.text = meal.strIngredient19
                binding.mTextViewMeasure19.text = meal.strMeasure19
            }

            if (meal.strIngredient20 == "") {
                binding.mTextViewIngredient20.visibility = View.GONE
                binding.mTextViewMeasure20.visibility = View.GONE
            } else {
                binding.mTextViewIngredient20.visibility = View.VISIBLE
                binding.mTextViewMeasure20.visibility = View.VISIBLE
                binding.mTextViewIngredient20.text = meal.strIngredient1
                binding.mTextViewMeasure20.text = meal.strMeasure1
            }

        }

    }

    private fun setFloatingActionButtonStatues() {

        if (isFavorite()) {
            binding.mFloatingActionButton.setImageResource(R.drawable.ic_heart_broken)
        } else {
            binding.mFloatingActionButton.setImageResource(R.drawable.ic_heart_favotite)
        }

    }

    private fun setOnClickTextViewWatchVideo() {

        binding.mTextViewWatchVideo.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(linkVideo)))
        }

    }

    private fun setOnClickFloatingActionButton() {

        binding.mFloatingActionButton.setOnClickListener {

            if (isFavorite()) {

                deleteFavoriteById()
                binding.mFloatingActionButton.setImageResource(R.drawable.ic_heart_favotite)
                Snackbar.make(
                    findViewById(android.R.id.content),
                    R.string.meal_removed_from_favorites,
                    Snackbar.LENGTH_SHORT
                ).show()

            } else {

                insertFavorite()
                binding.mFloatingActionButton.setImageResource(R.drawable.ic_heart_broken)
                Snackbar.make(
                    findViewById(android.R.id.content),
                    R.string.meal_saved_in_favorites,
                    Snackbar.LENGTH_SHORT
                ).show()

            }

        }

    }

    private fun showLoading() {

        binding.mImageView.visibility = View.GONE
        binding.mTextViewCategory.visibility = View.GONE
        binding.mTextViewArea.visibility = View.GONE
        binding.mTextViewName.visibility = View.GONE
        binding.mTextViewInstruction.visibility = View.GONE
        binding.mTextViewWatchVideo.visibility = View.GONE
        binding.mFloatingActionButton.visibility = View.GONE
        binding.mProgressBar.visibility = View.VISIBLE

    }

    private fun hideLoading() {

        binding.mImageView.visibility = View.VISIBLE
        binding.mTextViewCategory.visibility = View.VISIBLE
        binding.mTextViewArea.visibility = View.VISIBLE
        binding.mTextViewName.visibility = View.VISIBLE
        binding.mTextViewInstruction.visibility = View.VISIBLE
        binding.mTextViewWatchVideo.visibility = View.VISIBLE
        binding.mFloatingActionButton.visibility = View.VISIBLE
        binding.mProgressBar.visibility = View.GONE

    }

    private fun insertFavorite() {

        val favorite = Favorite(
            id = meal.idMeal!!.toInt(),
            name = meal.strMeal!!,
            imageLink = meal.strMealThumb!!
        )

        detailsViewModel.insertFavorite(favorite)

    }

    private fun deleteFavoriteById() {

        detailsViewModel.deleteFavoriteById(id)

    }

    private fun isFavorite(): Boolean {

        return detailsViewModel.isFavorite(id)

    }
}
