package com.papermoon.spaceapp.features.launch.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
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
            .into(binding.imgLaunch)

        binding.tvLaunchName.text = launch.name
        binding.tvLaunchAgency.text = launch.launchServiceProvider
        binding.tvLaunchDate.text = launch.launchDate.toString("dd.MM.yyyy hh:mm:ss")
        binding.tvLaunchLocation.text = launch.pad.location
        binding.tvLaunchPadName.text = launch.pad.name
        binding.tvLaunchMission.text = launch.mission?.name ?: "-"
        binding.tvLaunchDescription.text = (launch.mission?.description ?: "-")

        (activity as MainActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        if (launch.pad.wikiUrl.toString().isNotEmpty()) {
            binding.btnLaunchOpenWeb.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, launch.pad.wikiUrl)
                startActivity(intent)
            }
        } else {
            binding.btnLaunchOpenWeb.visibility = View.GONE
        }
        if (launch.pad.mapUrl.toString().isNotEmpty()) {
            binding.btnLaunchOpenMap.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, launch.pad.mapUrl)
                startActivity(intent)
            }
        } else {
            binding.btnLaunchOpenMap.visibility = View.GONE
        }

        (activity as MainActivity).supportActionBar?.themedContext?.setTheme(R.style.TransparentAppBar)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        activity!!.title = ""
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
