package com.papermoon.spaceapp.features.imageViewer.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.papermoon.spaceapp.SpaceApp
import com.papermoon.spaceapp.databinding.FragmentImageViewerBinding
import com.papermoon.spaceapp.domain.model.commons.ImageWithDescription
import com.papermoon.spaceapp.features.MainActivity
import com.papermoon.spaceapp.features.imageViewer.adapter.ImageViewerAdapter

class ImageViewerFragment(private val images: List<ImageWithDescription>) : Fragment() {

    private var _binding: FragmentImageViewerBinding? = null
    val binding: FragmentImageViewerBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentImageViewerBinding.inflate(inflater, container, false)
        val adapter = ImageViewerAdapter(images) {
            if (binding.toolbar.visibility == View.GONE) {
                binding.toolbar.visibility = View.VISIBLE
                return@ImageViewerAdapter View.VISIBLE
            } else {
                binding.toolbar.visibility = View.GONE
                return@ImageViewerAdapter View.GONE
            }
        }
        binding.imagePager.adapter = adapter

        (activity as MainActivity).setSupportActionBar(binding.toolbar)
        (activity as MainActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        binding.toolbar.setNavigationOnClickListener {
            SpaceApp.INSTANCE.router.exit()
        }

        (activity as MainActivity).setSupportActionBar(binding.toolbar)
        (activity as MainActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        binding.toolbar.setNavigationOnClickListener {
            SpaceApp.INSTANCE.router.exit()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
