package com.papermoon.spaceapp.features.eventDetail.ui

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
import com.papermoon.spaceapp.databinding.FragmentEventDetailBinding
import com.papermoon.spaceapp.domain.model.event.Event
import com.papermoon.spaceapp.features.MainActivity
import com.papermoon.spaceapp.features.commons.adapter.BaseViewPagerImageAdapter

class EventFragment(
    private val event: Event
) : Fragment() {

    private var _binding: FragmentEventDetailBinding? = null
    private val binding: FragmentEventDetailBinding
        get() = _binding!!

    private var imageFullscreen = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEventDetailBinding.inflate(inflater, container, false)

        setupAdapter()
        setupUi()
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
        val adapter = BaseViewPagerImageAdapter(event.images) { position ->
            if (!imageFullscreen) {
                setPageViewerFullscreen()
            } else {
                with(binding.tvEventImageDescription) {
                    visibility = if (visibility == View.GONE) {
                        View.VISIBLE
                    } else {
                        View.GONE
                    }
                }
            }
        }
        binding.viewPagerEvent.adapter = adapter
    }

    private fun onBackPressedCallback() {
        if (imageFullscreen) {
            setPageViewerNormalSize()
        } else {
            SpaceApp.INSTANCE.router.exit()
        }
    }

    private fun setupUi() {
        binding.toolbar.title = event.name

        binding.tvEventName.text = event.name
        binding.tvEventDate.text = event.date.toString("dd.MM.yyyy hh:mm:ss")
        binding.tvEventType.text = event.type
        binding.tvEventLocation.text = event.location
        binding.tvEventDescription.text = event.description

        if (event.newsUrl != null) {
            binding.btnEventNews.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, event.newsUrl)
                startActivity(intent)
            }
        }

        if (event.images.size > 1) {
            TabLayoutMediator(
                binding.tabLayoutEventIndicator.root,
                binding.viewPagerEvent
            ) { tab, position ->
            }.attach()
        } else {
            binding.tvEventCounter.visibility = View.GONE
        }

        binding.viewPagerEvent.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                binding.tvEventImageDescription.text = event.images[position].description
                binding.tvEventCounter.text = getString(
                    R.string.label_counter, position + 1, event.images.size
                )
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }
        })
    }

    private fun setPageViewerFullscreen() {
        binding.toolbar.title = ""

        binding.nestedScrollViewEvent.visibility = View.GONE
        binding.appBarEvent.layoutParams.height = ActionBar.LayoutParams.MATCH_PARENT
        binding.appBarEvent.setExpanded(true)

        val scrollingToolbarParams = binding.collapsingToolBar.layoutParams as AppBarLayout.LayoutParams
        scrollingToolbarParams.scrollFlags = 0
        scrollingToolbarParams.scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_NO_SCROLL

        if (event.images.size > 1) {
            binding.tabLayoutEventIndicator.root.visibility = View.GONE
            binding.tvEventCounter.visibility = View.GONE
        }
        binding.collapsingToolBar.isTitleEnabled = false

        imageFullscreen = true
    }

    private fun setPageViewerNormalSize() {
        binding.toolbar.title = event.name

        binding.nestedScrollViewEvent.visibility = View.VISIBLE
        binding.appBarEvent.layoutParams.height = resources.getDimension(R.dimen.big_image_height).toInt()

        val scrollingToolbarParams = binding.collapsingToolBar.layoutParams as AppBarLayout.LayoutParams
        scrollingToolbarParams.scrollFlags = 0
        scrollingToolbarParams.scrollFlags =
            AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL + AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED

        if (event.images.size > 1) {
            binding.tabLayoutEventIndicator.root.visibility = View.VISIBLE
            binding.tvEventCounter.visibility = View.VISIBLE
        }
        binding.tvEventImageDescription.visibility = View.GONE
        binding.collapsingToolBar.isTitleEnabled = true

        imageFullscreen = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}