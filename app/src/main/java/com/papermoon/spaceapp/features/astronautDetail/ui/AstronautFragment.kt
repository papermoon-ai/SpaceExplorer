package com.papermoon.spaceapp.features.astronautDetail.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import androidx.activity.OnBackPressedCallback
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.papermoon.spaceapp.R
import com.papermoon.spaceapp.SpaceApp
import com.papermoon.spaceapp.databinding.FragmentAstronautDetailBinding
import com.papermoon.spaceapp.domain.model.astronaut.Astronaut
import com.papermoon.spaceapp.features.MainActivity
import com.papermoon.spaceapp.features.commons.adapter.BaseViewPagerImageAdapter
import com.papermoon.spaceapp.features.commons.bundle.serializable

class AstronautFragment : Fragment() {

    private var _binding: FragmentAstronautDetailBinding? = null
    private val binding: FragmentAstronautDetailBinding
        get() = _binding!!

    private val astronaut: Astronaut
        get() = arguments!!.serializable(ASTRONAUT_DATA)!!

    private var imageFullscreen = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAstronautDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        setUiValues()
        setupToolbar()

        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    onBackPressedCallback()
                }
            })
    }

    private fun setupToolbar() {
        (activity as MainActivity).setSupportActionBar(binding.toolbar)
        (activity as MainActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            onBackPressedCallback()
        }
    }

    private fun setupAdapter() {
        val adapter = BaseViewPagerImageAdapter(astronaut.images) {
            if (!imageFullscreen) {
                setPageViewerFullscreen()
            } else {
                with(binding.tvAstronautImageDescription) {
                    visibility = if (visibility == View.GONE) {
                        View.VISIBLE
                    } else {
                        View.GONE
                    }
                }
            }
        }
        binding.viewPagerAstronaut.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onBackPressedCallback() {
        if (imageFullscreen) {
            setPageViewerNormalSize()
        } else {
            SpaceApp.INSTANCE.router.exit()
        }
    }

    private fun setUiValues() {
        with(binding) {
            toolbar.title = astronaut.name

            tvAstronautName.text = astronaut.name
            tvAstronautCountry.text = astronaut.country
            tvAstronautSpacecraft.text = astronaut.spacecraft
            tvAstronautDateOfBirth.text = astronaut.dateOfBirth.toString("dd.MM.yyyy")
            tvAstronautFirstFlight.text = astronaut.firstFlight.toString("dd.MM.yyyy")
            tvAstronautLastFlight.text = astronaut.lastFlight.toString("dd.MM.yyyy")
            tvAstronautBio.text = astronaut.bio
            tvAstronautCounter.text = getString(
                R.string.label_counter, 1, astronaut.images.size
            )

            btnAstronautOpenWeb.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, astronaut.wikiUrl?.toUri())
                startActivity(intent)
            }

            if (astronaut.images.size > 1) {
                TabLayoutMediator(
                    tabLayoutAstronautIndicator.root,
                    viewPagerAstronaut
                ) { _, _ ->
                }.attach()
            } else {
                tvAstronautCounter.visibility = View.GONE
            }

            viewPagerAstronaut.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    tvAstronautImageDescription.text = astronaut.images[position].description
                    tvAstronautCounter.text = getString(
                        R.string.label_counter, position + 1, astronaut.images.size
                    )
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                }
            })
        }
    }

    private fun setPageViewerFullscreen() {
        binding.toolbar.title = ""
        binding.collapsingToolBar.isTitleEnabled = false

        binding.nestedScrollViewAstronaut.visibility = View.GONE
        binding.appBarAstronaut.layoutParams.height = LayoutParams.MATCH_PARENT

        disableToolbarScrolling()
        hideImageIndicators()

        imageFullscreen = true
    }

    private fun setPageViewerNormalSize() {
        binding.toolbar.title = astronaut.name
        binding.collapsingToolBar.isTitleEnabled = true

        binding.nestedScrollViewAstronaut.visibility = View.VISIBLE
        binding.appBarAstronaut.layoutParams.height = resources.getDimension(R.dimen.big_image_height).toInt()

        permitToolbarScrolling()
        showImageIndicators()
        binding.tvAstronautImageDescription.visibility = View.GONE

        imageFullscreen = false
    }

    private fun permitToolbarScrolling() {
        val scrollingToolbarParams = binding.collapsingToolBar.layoutParams as AppBarLayout.LayoutParams
        scrollingToolbarParams.scrollFlags =
            AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL + AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED
    }

    private fun disableToolbarScrolling() {
        val scrollingToolbarParams = binding.collapsingToolBar.layoutParams as AppBarLayout.LayoutParams
        scrollingToolbarParams.scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_NO_SCROLL
    }

    private fun showImageIndicators() {
        if (astronaut.images.size > 1) {
            binding.tabLayoutAstronautIndicator.root.visibility = View.VISIBLE
            binding.tvAstronautCounter.visibility = View.VISIBLE
        }
    }

    private fun hideImageIndicators() {
        if (astronaut.images.size > 1) {
            binding.tabLayoutAstronautIndicator.root.visibility = View.GONE
            binding.tvAstronautCounter.visibility = View.GONE
        }
    }

    companion object {
        private const val ASTRONAUT_DATA = "AstronautData"
        fun getNewInstance(astronaut: Astronaut): AstronautFragment {
            return AstronautFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ASTRONAUT_DATA, astronaut)
                }
            }
        }
    }
}
