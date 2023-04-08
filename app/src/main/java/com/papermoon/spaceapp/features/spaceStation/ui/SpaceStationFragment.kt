package com.papermoon.spaceapp.features.spaceStation.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.papermoon.spaceapp.R
import com.papermoon.spaceapp.Screens
import com.papermoon.spaceapp.SpaceApp
import com.papermoon.spaceapp.databinding.FragmentSpaceStationBinding
import com.papermoon.spaceapp.domain.model.SpaceStation
import com.papermoon.spaceapp.features.MainActivity
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
            .into(binding.imgSpaceStation)

        with(spaceStation) {
            binding.tvStationName.text = name
            binding.tvStationDateOfOperation.text = founded.toString("dd.MM.yyyy")
            binding.tvStationOwners.text = owners.joinToString("\n")
            binding.tvDescription.text = description

            binding.tvStatus.text =
                if (spaceStation.isActive)
                    getString(R.string.label_active)
                else
                    getString(R.string.label_deactivated)
        }

        if (spaceStation.wikiUrl != null) {
            binding.btnStationOpenInWeb.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, spaceStation.wikiUrl)
                startActivity(intent)
            }
        } else {
            binding.btnStationOpenInWeb.visibility = View.GONE
        }

        (activity as MainActivity).setSupportActionBar(binding.toolbar)
        (activity as MainActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        binding.toolbar.setNavigationOnClickListener {
            SpaceApp.INSTANCE.router.exit()
        }

        binding.imgSpaceStation.setOnClickListener {
            SpaceApp.INSTANCE.router.navigateTo(Screens.imageViewerScreen(listOf(spaceStation.imageUrl)))
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        activity!!.title = spaceStation.name
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
