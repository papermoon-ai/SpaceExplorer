package com.papermoon.spaceapp.features.launchOverview.ui

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
import com.papermoon.spaceapp.databinding.FragmentLaunchOverviewBinding
import com.papermoon.spaceapp.features.MainActivity
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

        showShimmer()

        setupToolbar()
        setupErrorUi()
        setupSwipeToRefresh()

        val adapter = setupAdapter()

        launchViewModel.upcomingLaunches.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            hideShimmer()
            binding.swipeToRefresh.visibility = View.VISIBLE
        }

        launchViewModel.showShimmer.observe(viewLifecycleOwner) { showShimmer ->
            if (showShimmer) {
                if (binding.swipeToRefresh.visibility == View.GONE) {
                    showShimmer()
                    binding.viewGroupError.visibility = View.GONE
                }
                launchViewModel.doneLoadingMessage()
            }
        }

        launchViewModel.showUnableToLoadRateMessage.observe(viewLifecycleOwner) { showMessage ->
            if (showMessage) {
                hideShimmer()
                binding.swipeToRefresh.visibility = View.GONE
                binding.viewGroupError.visibility = View.VISIBLE
                launchViewModel.doneUnableToLoadMessage()
            }
        }

        return binding.root
    }

    private fun hideShimmer() {
        binding.shimmerLayout.stopShimmer()
        binding.shimmerLayout.visibility = View.GONE
    }

    private fun showShimmer() {
        binding.shimmerLayout.visibility = View.VISIBLE
        binding.shimmerLayout.startShimmer()
    }

    private fun setupErrorUi() {
        binding.tvErrorMessage.text = getString(R.string.message_error_unable_to_update)
        binding.btnRetry.text = getString(R.string.button_try_again)
        binding.btnRetry.setOnClickListener {
            launchViewModel.updateUpcomingLaunches()
        }
    }

    private fun setupSwipeToRefresh() {
        val swipeLayout = binding.swipeToRefresh

        val colorPrimary = MaterialColors.getColor(context!!, com.google.android.material.R.attr.colorPrimary, Color.BLACK)
        val colorSurface = MaterialColors.getColor(context!!, com.google.android.material.R.attr.colorSurface, Color.WHITE)

        swipeLayout.setColorSchemeColors(colorPrimary)
        swipeLayout.setProgressBackgroundColorSchemeColor(colorSurface)

        swipeLayout.setOnRefreshListener {
            launchViewModel.updateUpcomingLaunches()
            binding.swipeToRefresh.isRefreshing = false
        }
    }

    private fun setupToolbar() {
        (activity as MainActivity).setSupportActionBar(binding.toolbar)
        (activity as MainActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            SpaceApp.INSTANCE.router.exit()
        }
    }

    private fun setupAdapter(): LaunchOverviewAdapter {
        val adapter = LaunchOverviewAdapter(OnClickListener {
            SpaceApp.INSTANCE.router.navigateTo(Screens.launchScreen(it))
        })
        binding.launchesList.adapter = adapter
        return adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
