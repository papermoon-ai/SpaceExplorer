package com.papermoon.spaceapp.features.launch.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.papermoon.spaceapp.R
import com.papermoon.spaceapp.databinding.FragmentLaunchBinding
import com.papermoon.spaceapp.domain.model.Launch
import com.papermoon.spaceapp.features.MainActivity
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
        binding.launchDateTextView.text = launch.launchDate.toString("dd.MM.yyyy hh:mm:ss")
        binding.launchPadLocationTextView.text = launch.pad.location
        binding.launchPadNameTextView.text = launch.pad.name
        binding.launchMissionNameTextView.text = launch.mission?.name ?: "-"
        binding.launchMissionDescriptionTextView.text = (launch.mission?.description ?: "-")

        (activity as MainActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        if (launch.pad.wikiUrl.toString().isNotEmpty()) {
            binding.launchOpenInWebImageButton.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, launch.pad.wikiUrl)
                startActivity(intent)
            }
        } else {
            binding.launchOpenInWebImageButton.visibility = View.GONE
        }
        if (launch.pad.mapUrl.toString().isNotEmpty()) {
            binding.launchOpenMapImageButton.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, launch.pad.mapUrl)
                startActivity(intent)
            }
        } else {
            binding.launchOpenMapImageButton.visibility = View.GONE
        }

        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_open_pad_wiki -> {
                val intent = Intent(Intent.ACTION_VIEW, launch.pad.wikiUrl)
                startActivity(intent)
            }
            R.id.action_open_map -> {
                val intent = Intent(Intent.ACTION_VIEW, launch.pad.mapUrl)
                startActivity(intent)
            }
        }
        return true
    }

    override fun onResume() {
        super.onResume()
        activity!!.title = launch.name
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
