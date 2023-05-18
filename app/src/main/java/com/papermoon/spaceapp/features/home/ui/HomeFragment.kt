package com.papermoon.spaceapp.features.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.papermoon.spaceapp.R
import com.papermoon.spaceapp.Screens
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).setSupportActionBar(binding.toolbar)
        setHasOptionsMenu(true)

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
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_settings, menu)
        return
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.settingsOption) {
            SpaceApp.INSTANCE.router.navigateTo(Screens.settingsScreen())
            return true
        }
        return false
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
