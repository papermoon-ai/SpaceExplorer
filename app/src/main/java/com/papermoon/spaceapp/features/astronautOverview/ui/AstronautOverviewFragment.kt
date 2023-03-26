package com.papermoon.spaceapp.features.astronautOverview.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.papermoon.spaceapp.databinding.FragmentAstronautOverviewBinding
import com.papermoon.spaceapp.features.astronautOverview.adapter.AstronautOverviewAdapter
import com.papermoon.spaceapp.features.astronautOverview.vm.AstronautOverviewViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AstronautOverviewFragment : Fragment() {

    private var _binding: FragmentAstronautOverviewBinding? = null
    private val binding: FragmentAstronautOverviewBinding
        get() = _binding!!

    private val astronautViewModel: AstronautOverviewViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAstronautOverviewBinding.inflate(inflater, container, false)

        val adapter = AstronautOverviewAdapter()
        binding.astronautsList.adapter = adapter

        astronautViewModel.astronautList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}