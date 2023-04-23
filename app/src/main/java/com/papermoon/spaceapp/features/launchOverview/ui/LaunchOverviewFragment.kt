package com.papermoon.spaceapp.features.launchOverview.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

        binding.shimmerLayout.startShimmer()

        val adapter = setupAdapter()

        launchViewModel.upcomingLaunches.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            binding.shimmerLayout.stopShimmer()
            binding.shimmerLayout.visibility = View.GONE
            binding.launchesList.visibility = View.VISIBLE
        }

        launchViewModel.showLoadingMessage.observe(viewLifecycleOwner) { showMessage ->
            if (showMessage) {
                if (binding.launchesList.visibility == View.GONE) {
                    binding.shimmerLayout.visibility = View.VISIBLE
                    binding.shimmerLayout.startShimmer()
                    binding.viewGroupError.visibility = View.GONE
                }
                launchViewModel.doneLoadingMessage()
            }
        }

        launchViewModel.showUnableToLoadRateMessage.observe(viewLifecycleOwner) { showMessage ->
            if (showMessage) {
                binding.shimmerLayout.stopShimmer()
                binding.shimmerLayout.visibility = View.GONE
                binding.launchesList.visibility = View.GONE

                setupErrorUi()
            }
        }

        setupToolbar()

        return binding.root
    }

    private fun setupErrorUi() {
        binding.tvErrorMessage.text = getString(R.string.message_error_unable_to_update)
        binding.btnRetry.text = getString(R.string.button_try_again)
        binding.btnRetry.setOnClickListener {
            launchViewModel.updateUpcomingLaunches()
        }
        binding.viewGroupError.visibility = View.VISIBLE
    }

    private fun setupToolbar() {
        (activity as MainActivity).setSupportActionBar(binding.toolbar.root)
        (activity as MainActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        setHasOptionsMenu(true)

        binding.toolbar.root.setNavigationOnClickListener {
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

    override fun onResume() {
        super.onResume()
        activity!!.title = getString(R.string.label_orbital_launches)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_info_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_update) {
            launchViewModel.updateUpcomingLaunches()
        }
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
