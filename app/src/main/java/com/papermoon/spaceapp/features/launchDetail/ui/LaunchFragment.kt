package com.papermoon.spaceapp.features.launchDetail.ui

import android.app.ActionBar
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.papermoon.spaceapp.R
import com.papermoon.spaceapp.SpaceApp
import com.papermoon.spaceapp.databinding.FragmentLaunchDetailBinding
import com.papermoon.spaceapp.domain.model.launch.Launch
import com.papermoon.spaceapp.features.MainActivity
import com.papermoon.spaceapp.features.commons.adapter.BaseViewPagerImageAdapter

class LaunchFragment(private val launch: Launch) : Fragment() {
    private var _binding: FragmentLaunchDetailBinding? = null
    private val binding: FragmentLaunchDetailBinding
        get() = _binding!!

    private var imageFullscreen = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLaunchDetailBinding.inflate(inflater, container, false)

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

        return binding.root
    }

    private fun setupToolbar() {
        (activity as MainActivity).setSupportActionBar(binding.toolbar)
        (activity as MainActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        binding.toolbar.setNavigationOnClickListener {
            onBackPressedCallback()
        }
    }

    private fun setupAdapter() {
        val adapter = BaseViewPagerImageAdapter(launch.images) { position ->
            if (!imageFullscreen) {
                setPageViewerFullscreen()
            } else {
                with(binding.tvLaunchImageDescription) {
                    visibility = if (visibility == View.GONE) {
                        View.VISIBLE
                    } else {
                        View.GONE
                    }
                }
            }
        }
        binding.viewPagerLaunch.adapter = adapter
    }

    private fun onBackPressedCallback() {
        if (imageFullscreen) {
            setPageViewerNormalSize()
        } else {
            SpaceApp.INSTANCE.router.exit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUiValues() {
        with(binding) {
            tvLaunchName.text = launch.name
            tvLaunchAgency.text = launch.launchServiceProvider
            tvLaunchDate.text = launch.launchDate.toString("dd.MM.yyyy hh:mm:ss")
            tvLaunchLocation.text = launch.pad.location
            tvLaunchPadName.text = launch.pad.name
            tvLaunchMission.text = launch.mission?.name ?: "-"
            tvLaunchDescription.text = (launch.mission?.description ?: "-")
            tvLaunchCounter.text = getString(
                R.string.label_counter, 1, launch.images.size
            )

            if (launch.images.size > 1) {
                TabLayoutMediator(
                    tabLayoutLaunchIndicator.root,
                    viewPagerLaunch
                ) { tab, position ->
                }.attach()
            } else {
                tvLaunchCounter.visibility = View.GONE
            }

            if (launch.pad.wikiUrl.toString().isNotEmpty()) {
                btnLaunchOpenWeb.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, launch.pad.wikiUrl)
                    startActivity(intent)
                }
            } else {
                btnLaunchOpenWeb.visibility = View.GONE
            }

            if (launch.pad.mapUrl.toString().isNotEmpty()) {
                btnLaunchOpenMap.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, launch.pad.mapUrl)
                    startActivity(intent)
                }
            } else {
                btnLaunchOpenMap.visibility = View.GONE
            }

            toolbar.title = launch.name
            viewPagerLaunch.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    tvLaunchImageDescription.text = launch.images[position].description
                    tvLaunchCounter.text = getString(
                        R.string.label_counter, position + 1, launch.images.size
                    )
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                }
            })
        }
    }

    private fun setPageViewerFullscreen() {
        binding.toolbar.title = ""

        binding.nestedScrollViewLaunch.visibility = View.GONE
        binding.appBarLaunch.layoutParams.height = ActionBar.LayoutParams.MATCH_PARENT
        binding.appBarLaunch.setExpanded(true)

        val scrollingToolbarParams =
            binding.collapsingToolBar.layoutParams as AppBarLayout.LayoutParams
        scrollingToolbarParams.scrollFlags = 0
        scrollingToolbarParams.scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_NO_SCROLL

        if (launch.images.size > 1) {
            binding.tabLayoutLaunchIndicator.root.visibility = View.GONE
            binding.tvLaunchCounter.visibility = View.GONE
        }

        binding.collapsingToolBar.isTitleEnabled = false

        imageFullscreen = true
    }

    private fun setPageViewerNormalSize() {
        binding.toolbar.title = launch.name

        binding.nestedScrollViewLaunch.visibility = View.VISIBLE
        binding.appBarLaunch.layoutParams.height =
            resources.getDimension(R.dimen.big_image_height).toInt()

        val scrollingToolbarParams = binding.collapsingToolBar.layoutParams as AppBarLayout.LayoutParams
        scrollingToolbarParams.scrollFlags = 0
        scrollingToolbarParams.scrollFlags =
            AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL + AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED

        if (launch.images.size > 1) {
            binding.tvLaunchCounter.visibility = View.VISIBLE
            binding.tabLayoutLaunchIndicator.root.visibility = View.VISIBLE
        }
        binding.tvLaunchImageDescription.visibility = View.GONE
        binding.collapsingToolBar.isTitleEnabled = true

        imageFullscreen = false
    }
}
