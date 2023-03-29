package com.papermoon.spaceapp.features.celestialBodyOverview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.papermoon.spaceapp.R
import com.papermoon.spaceapp.databinding.ItemCelestialBodyBinding
import com.papermoon.spaceapp.domain.model.CelestialBody
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class CelestialBodyAdapter :
    ListAdapter<CelestialBody, CelestialBodyAdapter.CelestialBodyViewHolder>(DiffUtilCallback()) {
    class CelestialBodyViewHolder(private val binding: ItemCelestialBodyBinding) :
        ViewHolder(binding.root) {

        fun bind(celestialBody: CelestialBody) {
            binding.celestialBodyItemTextView.text = celestialBody.englishName
            binding.celestialBodyShortDescriptionItemTextView.text = celestialBody.shortDescription
            Picasso.get()
                .load(celestialBody.imageUrl)
                .resize(
                    binding.root.context.resources.getDimension(R.dimen.base_photo_image_width).toInt(),
                    binding.root.context.resources.getDimension(R.dimen.base_photo_image_height).toInt()
                )
                .centerCrop()
                .into(binding.celestialBodyItemImageView, object : Callback {
                    override fun onSuccess() {
                        binding.celestialBodyItemProgressBar.visibility = View.GONE
                    }

                    override fun onError(e: Exception?) {
                        binding.celestialBodyItemProgressBar.visibility = View.GONE
                        binding.celestialBodyItemImageView.setImageResource(R.drawable.ic_image_not_found)
                    }
                })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CelestialBodyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CelestialBodyViewHolder(ItemCelestialBodyBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: CelestialBodyViewHolder, position: Int) {
        val item = currentList[position]

        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }
}

class DiffUtilCallback : DiffUtil.ItemCallback<CelestialBody>() {
    override fun areItemsTheSame(oldItem: CelestialBody, newItem: CelestialBody): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: CelestialBody, newItem: CelestialBody): Boolean {
        return oldItem.name == newItem.name
                && oldItem.englishName == newItem.englishName
                && oldItem.density == newItem.density
                && oldItem.description == newItem.description
                && oldItem.discoverDate == newItem.discoverDate
                && oldItem.discoveredBy == newItem.discoveredBy
                && oldItem.imageUrl == newItem.imageUrl
                && oldItem.gravity == newItem.gravity
    }
}
