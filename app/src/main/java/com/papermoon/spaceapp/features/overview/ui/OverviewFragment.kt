package com.papermoon.spaceapp.features.overview.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.papermoon.spaceapp.R
import com.papermoon.spaceapp.Screens.astronautOverviewScreen
import com.papermoon.spaceapp.Screens.celestialBodyOverviewScreen
import com.papermoon.spaceapp.Screens.launchOverviewScreen
import com.papermoon.spaceapp.Screens.spaceStationOverviewScreen
import com.papermoon.spaceapp.SpaceApp
import com.papermoon.spaceapp.databinding.FragmentOverviewBinding
import com.papermoon.spaceapp.features.MainActivity
import com.papermoon.spaceapp.features.overview.adapter.MarginItemDecoration
import com.papermoon.spaceapp.features.overview.adapter.OnClickListener
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
    ): View {
        _binding = FragmentOverviewBinding.inflate(inflater, container, false)

        val adapter = OverviewAdapter(OnClickListener {
            when (it.name) {
                "Launches" -> SpaceApp.INSTANCE.router.navigateTo(launchOverviewScreen())
                "Astronauts" -> SpaceApp.INSTANCE.router.navigateTo(astronautOverviewScreen())
                "Space stations" -> SpaceApp.INSTANCE.router.navigateTo(spaceStationOverviewScreen())
                "Planets" -> SpaceApp.INSTANCE.router.navigateTo(celestialBodyOverviewScreen())
            }
            if (it.name == "Launches")
                SpaceApp.INSTANCE.router.navigateTo(launchOverviewScreen())
        })

        overviewViewModel.options.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        binding.optionsList.adapter = adapter

        binding.optionsList.addItemDecoration(
            MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.little_margin))
        )

        (activity as MainActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)

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
