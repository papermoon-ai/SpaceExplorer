package com.papermoon.spaceapp.features.overview.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.papermoon.spaceapp.R
import com.papermoon.spaceapp.databinding.FragmentOverviewBinding
import com.papermoon.spaceapp.features.overview.adapter.MarginItemDecoration
import com.papermoon.spaceapp.features.overview.adapter.OverviewAdapter
import com.papermoon.spaceapp.features.overview.vm.OverviewViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class OverviewFragment : Fragment() {

    private var _binding: FragmentOverviewBinding? = null
    private val binding get() = _binding!!

    private val overviewViewModel: OverviewViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOverviewBinding.inflate(inflater, container, false)

        val adapter = OverviewAdapter()
        overviewViewModel.options.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        binding.optionsList.adapter = adapter

        binding.optionsList.addItemDecoration(
            MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.little_margin))
        )

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}