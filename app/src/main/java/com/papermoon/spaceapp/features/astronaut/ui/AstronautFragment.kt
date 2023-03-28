package com.papermoon.spaceapp.features.astronaut.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.papermoon.spaceapp.R
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
        binding.astronautCountryTextView.text = getString(R.string.country, astronaut.nationality)
        binding.astronautSpacecraftTextView.text = getString(R.string.spacecraft_name, astronaut.spacecraft)
        binding.astronautDateOfBirthTextView.text =
            getString(R.string.date_of_birth, astronaut.dateOfBirth.toString("dd.MM.yyyy"))
        binding.astronautFirstFlightTextView.text =
            getString(R.string.first_flight_date, astronaut.firstFlight.toString("dd.MM.yyyy"))
        binding.astronautLastFlightTextView.text =
            getString(R.string.last_flight_date, astronaut.lastFlight.toString("dd.MM.yyyy"))
        binding.astronautBioTextView.text = astronaut.bio

        (activity as MainActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        activity!!.title = astronaut.name
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.astronaut_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_open_astronaut_wiki) {
            val intent = Intent(Intent.ACTION_VIEW, astronaut.wikiUrl)
            startActivity(intent)
        }
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
