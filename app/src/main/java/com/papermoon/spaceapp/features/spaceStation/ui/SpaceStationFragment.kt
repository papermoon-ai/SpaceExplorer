package com.papermoon.spaceapp.features.spaceStation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.papermoon.spaceapp.R
import com.papermoon.spaceapp.databinding.FragmentSpaceStationBinding
import com.papermoon.spaceapp.domain.model.SpaceStation
import com.squareup.picasso.Picasso

class SpaceStationFragment(
    private val spaceStation: SpaceStation
) : Fragment() {

    private var _binding: FragmentSpaceStationBinding? = null
    private val binding: FragmentSpaceStationBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSpaceStationBinding.inflate(inflater, container, false)

        Picasso.get()
            .load(spaceStation.imageUrl)
            .fit()
            .into(binding.spaceStationImageView)

        binding.spaceStationNameTextView.text = spaceStation.name
        binding.spaceStationFoundedTextView.text =
            getString(R.string.start_of_operation, spaceStation.founded.toString("dd.MM.yyyy"))
        binding.spaceStationStatusTextView.text =
            if (spaceStation.isActive)
                getString(R.string.status_active)
            else
                getString(R.string.status_deactivated)
        binding.spaceStationOwnersTextView.text = spaceStation.owners.joinToString()
        binding.spaceStationDescriptionTextView.text = spaceStation.description

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
