package com.papermoon.spaceapp.features.astronaut.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.papermoon.spaceapp.databinding.FragmentAstronautBinding
import com.papermoon.spaceapp.domain.model.Astronaut
import com.papermoon.spaceapp.features.MainActivity
import com.squareup.picasso.Picasso

class AstronautFragment(
    private val astronaut: Astronaut
) : Fragment() {

    private var _binding: FragmentAstronautBinding? = null
    private val binding: FragmentAstronautBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAstronautBinding.inflate(inflater, container, false)

        Picasso.get()
            .load(astronaut.profileImage)
            .into(binding.astronautImageView)

        binding.astronautNameTextView.text = astronaut.name
        binding.astronautCountryTextView.text = astronaut.nationality
        binding.astronautSpacecraftTextView.text = astronaut.spacecraft
        binding.astronautDateOfBirthTextView.text = astronaut.dateOfBirth.toString("dd.MM.yyyy")
        binding.astronautFirstFlightTextView.text = astronaut.firstFlight.toString("dd.MM.yyyy")
        binding.astronautLastFlightTextView.text = astronaut.lastFlight.toString("dd.MM.yyyy")
        binding.astronautBioTextView.text = astronaut.bio
        binding.astronautOpenWikiImageButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, astronaut.wikiUrl)
            startActivity(intent)
        }

        (activity as MainActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        activity!!.title = astronaut.name
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
