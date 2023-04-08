package com.papermoon.spaceapp.features.celestialBodyOverview.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.papermoon.spaceapp.R
import com.papermoon.spaceapp.Screens
import com.papermoon.spaceapp.SpaceApp
import com.papermoon.spaceapp.databinding.FragmentCelestialBodyOverviewBinding
import com.papermoon.spaceapp.features.MainActivity
import com.papermoon.spaceapp.features.celestialBodyOverview.adapter.CelestialBodyAdapter
import com.papermoon.spaceapp.features.celestialBodyOverview.vm.CelestialBodyOverviewViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CelestialBodyOverviewFragment : Fragment() {

    private var _binding: FragmentCelestialBodyOverviewBinding? = null
    private val binding: FragmentCelestialBodyOverviewBinding
        get() = _binding!!

    private val celestialBodyOverviewViewModel: CelestialBodyOverviewViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCelestialBodyOverviewBinding.inflate(inflater, container, false)

        val adapter = CelestialBodyAdapter() {
            SpaceApp.INSTANCE.router.navigateTo(Screens.celestialBodyScreen(it))
        }
        binding.celestialBodyList.adapter = adapter

        celestialBodyOverviewViewModel.planets.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        (activity as MainActivity).setSupportActionBar(binding.toolbar.root)
        (activity as MainActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        binding.toolbar.root.setNavigationOnClickListener {
            SpaceApp.INSTANCE.router.exit()
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        activity!!.title = getString(R.string.label_planets)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
