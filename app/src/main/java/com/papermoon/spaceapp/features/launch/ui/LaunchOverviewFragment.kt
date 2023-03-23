package com.papermoon.spaceapp.features.launch.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.papermoon.spaceapp.R
import com.papermoon.spaceapp.databinding.FragmentLaunchOverviewBinding
import com.papermoon.spaceapp.features.launch.adapter.LaunchOverviewAdapter
import com.papermoon.spaceapp.features.launch.vm.LaunchOverviewViewModel
import com.papermoon.spaceapp.features.overview.adapter.MarginItemDecoration
import org.koin.androidx.viewmodel.ext.android.viewModel

class LaunchOverviewFragment : Fragment() {

    private var _binding: FragmentLaunchOverviewBinding? = null
    private val binding: FragmentLaunchOverviewBinding
        get() = _binding!!

    private val launchViewModel: LaunchOverviewViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLaunchOverviewBinding.inflate(inflater, container, false)

        val adapter = LaunchOverviewAdapter()
        launchViewModel.upcomingLaunches.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        binding.launchesList.adapter = adapter

        binding.launchesList.addItemDecoration(
            MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.little_margin))
        )

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
