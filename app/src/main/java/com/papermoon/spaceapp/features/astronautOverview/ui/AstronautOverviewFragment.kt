package com.papermoon.spaceapp.features.astronautOverview.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.papermoon.spaceapp.R
import com.papermoon.spaceapp.Screens
import com.papermoon.spaceapp.SpaceApp
import com.papermoon.spaceapp.databinding.FragmentAstronautOverviewBinding
import com.papermoon.spaceapp.features.MainActivity
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

        val adapter = AstronautOverviewAdapter {
            SpaceApp.INSTANCE.router.navigateTo(Screens.astronautScreen(it))
        }

        binding.astronautsList.adapter = adapter

        astronautViewModel.astronautList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        (activity as MainActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        activity!!.title = getString(R.string.label_astronauts)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
