package com.papermoon.spaceapp.features.launch.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.papermoon.spaceapp.R
import com.papermoon.spaceapp.databinding.FragmentLaunchBinding
import com.papermoon.spaceapp.domain.model.Launch
import com.squareup.picasso.Picasso

class LaunchFragment(private val launch: Launch) : Fragment() {
    private var _binding: FragmentLaunchBinding? = null
    private val binding: FragmentLaunchBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLaunchBinding.inflate(inflater, container, false)

        Picasso.get()
            .load(launch.imageUrl)
            .fit()
            .into(binding.launchImageView)

        binding.launchNameTextView.text = launch.name
        binding.launchServiceProviderTextView.text = launch.launchServiceProvider
        binding.launchDateTextView.text = getString(R.string.launch_date, launch.launchDate.toString("dd.MM.yyyy hh:mm:ss"))
        binding.launchPadNameTextView.text = getString(R.string.pad_name, launch.pad.name)
        binding.launchPadLocationTextView.text = getString(R.string.pad_location, launch.pad.location)
        binding.launchMissionNameTextView.text = getString(R.string.mission_name, launch.mission.name)
        binding.launchMissionDescriptionTextView.text = launch.mission.description

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}