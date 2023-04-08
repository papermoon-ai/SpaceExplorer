package com.papermoon.spaceapp.features.astronaut.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.papermoon.spaceapp.SpaceApp
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
            .into(binding.imgAstronaut)

        binding.tvAstronautName.text = astronaut.name
        binding.tvAstronautCountry.text = astronaut.nationality
        binding.tvAstronautSpacecraft.text = astronaut.spacecraft
        binding.tvAstronautDateOfBirth.text = astronaut.dateOfBirth.toString("dd.MM.yyyy")
        binding.tvAstronautFirstFlight.text = astronaut.firstFlight.toString("dd.MM.yyyy")
        binding.tvAstronautLastFlight.text = astronaut.lastFlight.toString("dd.MM.yyyy")
        binding.tvAstronautBio.text = astronaut.bio
        binding.btnAstronautOpenWeb.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, astronaut.wikiUrl)
            startActivity(intent)
        }

        (activity as MainActivity).setSupportActionBar(binding.toolbar)
        (activity as MainActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        binding.toolbar.setNavigationOnClickListener {
            SpaceApp.INSTANCE.router.exit()
        }

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
