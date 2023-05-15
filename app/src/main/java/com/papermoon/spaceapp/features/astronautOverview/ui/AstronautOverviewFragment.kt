package com.papermoon.spaceapp.features.astronautOverview.ui

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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showShimmer()

        setupToolbar()
        setupErrorUi()
        setupSwipeToRefresh()

        val adapter = setupAdapter()
        astronautViewModel.astronautList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            hideShimmer()
            binding.swipeToRefresh.visibility = View.VISIBLE
        }

        astronautViewModel.showShimmer.observe(viewLifecycleOwner) { showShimmer ->
            if (showShimmer) {
                if (binding.swipeToRefresh.visibility == View.GONE) {
                    showShimmer()
                    binding.viewGroupError.visibility = View.GONE
                }
                astronautViewModel.doneLoadingMessage()
            }
        }

        astronautViewModel.showUnableToLoadAstronautsMessage.observe(viewLifecycleOwner) { showMessage ->
            if (showMessage) {
                hideShimmer()
                binding.swipeToRefresh.visibility = View.GONE
                binding.viewGroupError.visibility = View.VISIBLE

                astronautViewModel.doneUnableToLoadMessage()
            }
        }
    }

    private fun setupSwipeToRefresh() {
        val swipeLayout = binding.swipeToRefresh

        val colorSecondary = MaterialColors.getColor(context!!, com.google.android.material.R.attr.colorSecondary, Color.BLACK)
        val colorSurface = MaterialColors.getColor(context!!, com.google.android.material.R.attr.colorSurface, Color.WHITE)

        swipeLayout.setColorSchemeColors(colorSecondary)
        swipeLayout.setProgressBackgroundColorSchemeColor(colorSurface)

        swipeLayout.setOnRefreshListener {
            astronautViewModel.updateAstronauts()
            binding.swipeToRefresh.isRefreshing = false
        }
    }

    private fun setupErrorUi() {
        binding.tvErrorMessage.text = getString(R.string.message_error_unable_to_update)
        binding.btnRetry.text = getString(R.string.button_try_again)
        binding.btnRetry.setOnClickListener {
            astronautViewModel.updateAstronauts()
        }
    }

    private fun showShimmer() {
        binding.shimmerLayout.visibility = View.VISIBLE
        binding.shimmerLayout.startShimmer()
    }

    private fun hideShimmer() {
        binding.shimmerLayout.stopShimmer()
        binding.shimmerLayout.visibility = View.GONE
    }

    private fun setupToolbar() {
        (activity as MainActivity).setSupportActionBar(binding.toolbar)
        (activity as MainActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            SpaceApp.INSTANCE.router.exit()
        }
    }

    private fun setupAdapter(): AstronautOverviewAdapter {
        val adapter = AstronautOverviewAdapter {
            SpaceApp.INSTANCE.router.navigateTo(Screens.astronautScreen(it))
        }
        binding.astronautsList.adapter = adapter
        return adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
