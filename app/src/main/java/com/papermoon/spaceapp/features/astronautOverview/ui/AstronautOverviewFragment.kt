package com.papermoon.spaceapp.features.astronautOverview.ui

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

        showShimmer()

        setupToolbar()
        setupErrorUi()

        val adapter = setupAdapter()
        astronautViewModel.astronautList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            hideShimmer()
            binding.astronautsList.visibility = View.VISIBLE
        }

        astronautViewModel.showShimmer.observe(viewLifecycleOwner) { showShimmer ->
            if (showShimmer) {
                if (binding.astronautsList.visibility == View.GONE) {
                    showShimmer()
                    binding.viewGroupError.visibility = View.GONE
                }
                astronautViewModel.doneLoadingMessage()
            }
        }

        astronautViewModel.showUnableToLoadRateMessage.observe(viewLifecycleOwner) { showMessage ->
            if (showMessage) {
                hideShimmer()
                binding.astronautsList.visibility = View.GONE
                binding.viewGroupError.visibility = View.VISIBLE

                astronautViewModel.doneUnableToLoadMessage()
            }
        }

        return binding.root
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
        (activity as MainActivity).setSupportActionBar(binding.toolbar.root)
        (activity as MainActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        setHasOptionsMenu(true)

        binding.toolbar.root.setNavigationOnClickListener {
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

    override fun onResume() {
        super.onResume()
        activity!!.title = getString(R.string.label_astronauts)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_info_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_update) {
            astronautViewModel.updateAstronauts()
        }
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
