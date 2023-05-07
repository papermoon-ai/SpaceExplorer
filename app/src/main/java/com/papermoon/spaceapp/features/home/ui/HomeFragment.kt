package com.papermoon.spaceapp.features.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.papermoon.spaceapp.R
import com.papermoon.spaceapp.Screens.astronautOverviewScreen
import com.papermoon.spaceapp.Screens.celestialBodyOverviewScreen
import com.papermoon.spaceapp.Screens.eventOverviewScreen
import com.papermoon.spaceapp.Screens.launchOverviewScreen
import com.papermoon.spaceapp.Screens.spaceStationOverviewScreen
import com.papermoon.spaceapp.SpaceApp
import com.papermoon.spaceapp.databinding.FragmentHomeBinding
import com.papermoon.spaceapp.features.MainActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        (activity as MainActivity).setSupportActionBar(binding.toolbar.root)

        binding.cardViewPlanetsOption.setOnClickListener {
            SpaceApp.INSTANCE.router.navigateTo(celestialBodyOverviewScreen())
        }
        binding.cardViewLaunchOption.setOnClickListener {
            SpaceApp.INSTANCE.router.navigateTo(launchOverviewScreen())
        }
        binding.cardViewAstronautOption.setOnClickListener {
            SpaceApp.INSTANCE.router.navigateTo(astronautOverviewScreen())
        }
        binding.cardViewSpaceStationOption.setOnClickListener {
            SpaceApp.INSTANCE.router.navigateTo(spaceStationOverviewScreen())
        }
        binding.cardViewEventOption.setOnClickListener {
            SpaceApp.INSTANCE.router.navigateTo(eventOverviewScreen())
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        activity!!.title = getString(R.string.app_name)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
