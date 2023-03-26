package com.papermoon.spaceapp.features.launchOverview.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.papermoon.spaceapp.Screens
import com.papermoon.spaceapp.SpaceApp
import com.papermoon.spaceapp.databinding.FragmentLaunchOverviewBinding
import com.papermoon.spaceapp.features.launchOverview.adapter.LaunchOverviewAdapter
import com.papermoon.spaceapp.features.launchOverview.adapter.OnClickListener
import com.papermoon.spaceapp.features.launchOverview.vm.LaunchOverviewViewModel
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

        val adapter = LaunchOverviewAdapter(OnClickListener {
            SpaceApp.INSTANCE.router.navigateTo(Screens.launchScreen(it))
        })

        launchViewModel.upcomingLaunches.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        binding.launchesList.adapter = adapter

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
