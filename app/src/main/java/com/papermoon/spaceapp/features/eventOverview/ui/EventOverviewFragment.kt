package com.papermoon.spaceapp.features.eventOverview.ui

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
import com.papermoon.spaceapp.databinding.FragmentEventOverviewBinding
import com.papermoon.spaceapp.features.MainActivity
import com.papermoon.spaceapp.features.eventOverview.adapter.EventOverviewAdapter
import com.papermoon.spaceapp.features.eventOverview.adapter.OnClickListener
import com.papermoon.spaceapp.features.eventOverview.vm.EventOverviewViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventOverviewFragment : Fragment() {

    private var _binding: FragmentEventOverviewBinding? = null
    private val binding: FragmentEventOverviewBinding
        get() = _binding!!

    private val eventViewModel: EventOverviewViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEventOverviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showShimmer()

        setupToolbar()
        setupErrorUi()
        setupSwipeToRefresh()

        val adapter = setupAdapter()

        eventViewModel.upcomingEvents.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            hideShimmer()
            binding.swipeToRefresh.visibility = View.VISIBLE
        }

        eventViewModel.showShimmer.observe(viewLifecycleOwner) { showShimmer ->
            if (showShimmer) {
                if (binding.swipeToRefresh.visibility == View.GONE) {
                    showShimmer()
                    binding.viewGroupError.visibility = View.GONE
                }
                eventViewModel.doneLoadingMessage()
            }
        }

        eventViewModel.showUnableToLoadEventsMessage.observe(viewLifecycleOwner) { showMessage ->
            if (showMessage) {
                hideShimmer()
                binding.swipeToRefresh.visibility = View.GONE
                binding.viewGroupError.visibility = View.VISIBLE
                eventViewModel.doneUnableToLoadMessage()
            }
        }
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
            eventViewModel.updateUpcomingEvents()
        }
    }

    private fun setupSwipeToRefresh() {
        val swipeLayout = binding.swipeToRefresh

        val colorSecondary = MaterialColors.getColor(context!!, com.google.android.material.R.attr.colorSecondary, Color.BLACK)
        val colorSurface = MaterialColors.getColor(context!!, com.google.android.material.R.attr.colorSurface, Color.WHITE)

        swipeLayout.setColorSchemeColors(colorSecondary)
        swipeLayout.setProgressBackgroundColorSchemeColor(colorSurface)

        swipeLayout.setOnRefreshListener {
            eventViewModel.updateUpcomingEvents()
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

    private fun setupAdapter(): EventOverviewAdapter {
        val adapter = EventOverviewAdapter(OnClickListener {
            SpaceApp.INSTANCE.router.navigateTo(Screens.eventScreen(it))
        })
        binding.eventList.adapter = adapter
        return adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
