package com.papermoon.spaceapp.features.launch.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.papermoon.spaceapp.Screens
import com.papermoon.spaceapp.SpaceApp
import com.papermoon.spaceapp.databinding.FragmentLaunchBinding
import com.papermoon.spaceapp.domain.model.launch.Launch
import com.papermoon.spaceapp.features.MainActivity
import com.papermoon.spaceapp.features.commons.adapter.BaseViewPagerImageAdapter

class LaunchFragment(private val launch: Launch) : Fragment() {
    private var _binding: FragmentLaunchBinding? = null
    private val binding: FragmentLaunchBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLaunchBinding.inflate(inflater, container, false)

        val adapter = BaseViewPagerImageAdapter(launch.images) { position ->
            SpaceApp.INSTANCE.router.navigateTo(Screens.imageViewerScreen(launch.images, position))
        }
        binding.viewPagerLaunch.adapter = adapter

        if (launch.images.size > 1) {
            TabLayoutMediator(
                binding.tabLayoutCelestialBodyIndicator.root,
                binding.viewPagerLaunch
            ) { tab, position ->
            }.attach()
        }

        binding.tvLaunchName.text = launch.name
        binding.tvLaunchAgency.text = launch.launchServiceProvider
        binding.tvLaunchDate.text = launch.launchDate.toString("dd.MM.yyyy hh:mm:ss")
        binding.tvLaunchLocation.text = launch.pad.location
        binding.tvLaunchPadName.text = launch.pad.name
        binding.tvLaunchMission.text = launch.mission?.name ?: "-"
        binding.tvLaunchDescription.text = (launch.mission?.description ?: "-")

        (activity as MainActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        if (launch.pad.wikiUrl.toString().isNotEmpty()) {
            binding.btnLaunchOpenWeb.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, launch.pad.wikiUrl)
                startActivity(intent)
            }
        } else {
            binding.btnLaunchOpenWeb.visibility = View.GONE
        }
        if (launch.pad.mapUrl.toString().isNotEmpty()) {
            binding.btnLaunchOpenMap.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, launch.pad.mapUrl)
                startActivity(intent)
            }
        } else {
            binding.btnLaunchOpenMap.visibility = View.GONE
        }

        (activity as MainActivity).setSupportActionBar(binding.toolbar)
        (activity as MainActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        binding.toolbar.setNavigationOnClickListener {
            SpaceApp.INSTANCE.router.exit()
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        activity!!.title = launch.name
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
