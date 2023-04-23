package com.papermoon.spaceapp.features.spaceStationOverview.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.color.MaterialColors
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

        binding.shimmerLayout.startShimmer()

        setupToolbar()
        setupErrorUi()
        setupSwipeToRefresh()

        val adapter = setupAdapter()
        spaceStationOverviewViewModel.spaceStationsList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            hideShimmer()
            binding.swipeToRefresh.visibility = View.VISIBLE
        }

        spaceStationOverviewViewModel.showShimmer.observe(viewLifecycleOwner) { showMessage ->
            if (showMessage) {
                if (binding.swipeToRefresh  .visibility == View.GONE) {
                    showShimmer()
                    binding.viewGroupError.visibility = View.GONE
                }
                spaceStationOverviewViewModel.doneLoadingMessage()
            }
        }

        spaceStationOverviewViewModel.showUnableToLoadRateMessage.observe(viewLifecycleOwner) { showMessage ->
            if (showMessage) {
                hideShimmer()
                binding.swipeToRefresh.visibility = View.GONE
                binding.viewGroupError.visibility = View.VISIBLE
                spaceStationOverviewViewModel.doneUnableToLoadMessage()
            }
        }

        binding.swipeToRefresh.setOnRefreshListener {
            spaceStationOverviewViewModel.updateSpaceStationsList()
            binding.swipeToRefresh.isRefreshing = false
        }

        return binding.root
    }

    private fun showShimmer() {
        binding.shimmerLayout.visibility = View.VISIBLE
        binding.shimmerLayout.startShimmer()
    }

    private fun setupErrorUi() {
        binding.tvErrorMessage.text = getString(R.string.message_error_unable_to_update)
        binding.btnRetry.text = getString(R.string.button_try_again)
        binding.btnRetry.setOnClickListener {
            spaceStationOverviewViewModel.updateSpaceStationsList()
        }
    }

    private fun setupToolbar() {
        (activity as MainActivity).setSupportActionBar(binding.toolbar.root)
        (activity as MainActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        binding.toolbar.root.setNavigationOnClickListener {
            SpaceApp.INSTANCE.router.exit()
        }
    }

    private fun setupSwipeToRefresh() {
        val swipeLayout = binding.swipeToRefresh

        val colorPrimary = MaterialColors.getColor(context!!, com.google.android.material.R.attr.colorPrimary, Color.BLACK)
        val colorSurface = MaterialColors.getColor(context!!, com.google.android.material.R.attr.colorSurface, Color.WHITE)

        swipeLayout.setColorSchemeColors(colorPrimary)
        swipeLayout.setProgressBackgroundColorSchemeColor(colorSurface)

        swipeLayout.setOnRefreshListener {
            spaceStationOverviewViewModel.updateSpaceStationsList()
            binding.swipeToRefresh.isRefreshing = false
        }
    }

    private fun setupAdapter(): SpaceStationAdapter {
        val adapter = SpaceStationAdapter {
            SpaceApp.INSTANCE.router.navigateTo(Screens.spaceStationScreen(it))
        }
        binding.spaceStationList.adapter = adapter
        return adapter
    }

    private fun hideShimmer() {
        binding.shimmerLayout.stopShimmer()
        binding.shimmerLayout.visibility = View.GONE
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
