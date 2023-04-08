package com.papermoon.spaceapp.features.spaceStationOverview.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.papermoon.spaceapp.R
import com.papermoon.spaceapp.Screens
import com.papermoon.spaceapp.SpaceApp
import com.papermoon.spaceapp.databinding.FragmentSpaceStationOverviewBinding
import com.papermoon.spaceapp.features.MainActivity
import com.papermoon.spaceapp.features.spaceStationOverview.adapter.SpaceStationAdapter
import com.papermoon.spaceapp.features.spaceStationOverview.vm.SpaceStationOverviewViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SpaceStationOverviewFragment : Fragment() {

    private var _binding: FragmentSpaceStationOverviewBinding? = null
    private val binding: FragmentSpaceStationOverviewBinding
        get() = _binding!!

    private val spaceStationOverviewViewModel: SpaceStationOverviewViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSpaceStationOverviewBinding.inflate(inflater, container, false)

        val adapter = SpaceStationAdapter() {
            SpaceApp.INSTANCE.router.navigateTo(Screens.spaceStationScreen(it))
        }
        spaceStationOverviewViewModel.spaceStationsList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        binding.spaceStationList.adapter = adapter

        (activity as MainActivity).setSupportActionBar(binding.toolbar.root)
        (activity as MainActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        binding.toolbar.root.setNavigationOnClickListener {
            SpaceApp.INSTANCE.router.exit()
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        activity!!.title = getString(R.string.label_space_stations)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
