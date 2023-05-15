package com.papermoon.spaceapp.features.spaceStationDetail.ui

import android.app.ActionBar
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.papermoon.spaceapp.R
import com.papermoon.spaceapp.SpaceApp
import com.papermoon.spaceapp.databinding.FragmentSpaceStationDetailBinding
import com.papermoon.spaceapp.domain.model.spacestation.SpaceStation
import com.papermoon.spaceapp.features.MainActivity
import com.papermoon.spaceapp.features.commons.adapter.BaseViewPagerImageAdapter

class SpaceStationFragment(
    private val spaceStation: SpaceStation
) : Fragment() {

    private var _binding: FragmentSpaceStationDetailBinding? = null
    private val binding: FragmentSpaceStationDetailBinding
        get() = _binding!!

    private var imageFullscreen = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSpaceStationDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        setUiValues()
        setupToolbar()
    }

    private fun setupToolbar() {
        (activity as MainActivity).setSupportActionBar(binding.toolbar)
        (activity as MainActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        binding.toolbar.setNavigationOnClickListener {
            onBackPressedCallback()
        }
    }

    private fun setupAdapter() {
        val adapter = BaseViewPagerImageAdapter(spaceStation.images) { position ->
            if (!imageFullscreen) {
                setPageViewerFullscreen()
            } else {
                with(binding.tvSpaceStationImageDescription) {
                    visibility = if (visibility == View.GONE) {
                        View.VISIBLE
                    } else {
                        View.GONE
                    }
                }
            }
        }
        binding.viewPagerSpaceStation.adapter = adapter
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
            tvStationName.text = spaceStation.name
            tvStationDateOfOperation.text = spaceStation.founded.toString("dd.MM.yyyy")
            tvStationOwners.text = spaceStation.owners.joinToString("\n")
            tvDescription.text = spaceStation.description
            tvSpaceStationCounter.text = getString(
                R.string.label_counter, 1, spaceStation.images.size
            )

            tvStatus.text =
                if (spaceStation.isActive)
                    getString(R.string.label_active)
                else
                    getString(R.string.label_deactivated)

            viewPagerSpaceStation.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    tvSpaceStationImageDescription.text = spaceStation.images[position].description
                    tvSpaceStationCounter.text = getString(
                        R.string.label_counter, position + 1, spaceStation.images.size
                    )
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                }
            })

            if (spaceStation.images.size > 1) {
                TabLayoutMediator(
                    tabLayoutSpaceStationIndicator.root,
                    viewPagerSpaceStation
                ) { tab, position ->
                }.attach()
            } else {
                tvSpaceStationCounter.visibility = View.GONE
            }

            toolbar.title = spaceStation.name
            if (spaceStation.wikiUrl != null) {
                btnStationOpenInWeb.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, spaceStation.wikiUrl)
                    startActivity(intent)
                }
            } else {
                btnStationOpenInWeb.visibility = View.GONE
            }
        }
    }

    private fun setPageViewerFullscreen() {
        binding.toolbar.title = ""
        binding.collapsingToolBar.isTitleEnabled = false

        binding.nestedScrollViewSpaceStation.visibility = View.GONE
        binding.appBarSpaceStation.layoutParams.height = ActionBar.LayoutParams.MATCH_PARENT
        binding.appBarSpaceStation.setExpanded(true)

        disableToolbarScrolling()
        hideImageIndicators()

        imageFullscreen = true
    }

    private fun setPageViewerNormalSize() {
        binding.toolbar.title = spaceStation.name
        binding.collapsingToolBar.isTitleEnabled = true

        binding.nestedScrollViewSpaceStation.visibility = View.VISIBLE
        binding.appBarSpaceStation.layoutParams.height =
            resources.getDimension(R.dimen.big_image_height).toInt()

        permitToolbarScrolling()
        showImageIndicators()
        binding.tvSpaceStationImageDescription.visibility = View.GONE

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
        if (spaceStation.images.size > 1) {
            binding.tabLayoutSpaceStationIndicator.root.visibility = View.VISIBLE
            binding.tvSpaceStationCounter.visibility = View.VISIBLE
        }
    }

    private fun hideImageIndicators() {
        if (spaceStation.images.size > 1) {
            binding.tabLayoutSpaceStationIndicator.root.visibility = View.GONE
            binding.tvSpaceStationCounter.visibility = View.GONE
        }
    }
}
