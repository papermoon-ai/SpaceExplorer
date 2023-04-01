package com.papermoon.spaceapp.features.astronautOverview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.papermoon.spaceapp.R
import com.papermoon.spaceapp.databinding.ItemAstronautBinding
import com.papermoon.spaceapp.domain.model.Astronaut
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class AstronautOverviewAdapter(
    private val onClickListener: (Astronaut) -> Unit
) :
    ListAdapter<Astronaut, AstronautOverviewAdapter.AstronautViewHolder>(DiffUtilCallback()) {

    class AstronautViewHolder(private val binding: ItemAstronautBinding) :
        ViewHolder(binding.root) {

        fun bind(astronaut: Astronaut) {
            binding.astronautItemNameTextView.text = astronaut.name
            binding.astronautItemSpacecraftTextView.text = binding.root.context.getString(
                R.string.description_spacecraft,
                astronaut.spacecraft.ifEmpty { "-" }
            )
            binding.astronautItemNationalityTextView.text = astronaut.nationality

            Picasso.get()
                .load(astronaut.profileImage)
                .resize(
                    binding.root.context.resources.getDimension(R.dimen.base_photo_image_width)
                        .toInt(),
                    binding.root.context.resources.getDimension(R.dimen.base_photo_image_height)
                        .toInt()
                )
                .centerCrop()
                .into(binding.astronautItemImageView, object : Callback {
                    override fun onSuccess() {
                        binding.astronautItemProgressBar.visibility = View.GONE
                    }

                    override fun onError(e: Exception?) {
                        binding.astronautItemProgressBar.visibility = View.GONE
                        binding.astronautItemImageView.setImageResource(R.drawable.ic_image_not_found)
                    }
                })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AstronautViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return AstronautViewHolder(ItemAstronautBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: AstronautViewHolder, position: Int) {
        val item = currentList[position]
        holder.bind(item)
        holder.itemView.setOnClickListener { onClickListener(item) }
    }

    override fun getItemCount(): Int {
        return currentList.size
    }
}

class DiffUtilCallback : DiffUtil.ItemCallback<Astronaut>() {
    override fun areItemsTheSame(oldItem: Astronaut, newItem: Astronaut): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Astronaut, newItem: Astronaut): Boolean {
        return oldItem.name == newItem.name
                && oldItem.spacecraft == newItem.spacecraft
                && oldItem.bio == newItem.bio
                && oldItem.dateOfBirth == newItem.dateOfBirth
                && oldItem.nationality == newItem.nationality
                && oldItem.profileImage == newItem.profileImage
                && oldItem.wikiUrl == newItem.wikiUrl
                && oldItem.firstFlight == newItem.firstFlight
                && oldItem.lastFlight == newItem.lastFlight
    }
}
