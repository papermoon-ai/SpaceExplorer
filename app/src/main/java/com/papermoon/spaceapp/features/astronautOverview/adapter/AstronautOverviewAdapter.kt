package com.papermoon.spaceapp.features.astronautOverview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.papermoon.spaceapp.R
import com.papermoon.spaceapp.databinding.AstronautItemBinding
import com.papermoon.spaceapp.domain.model.Astronaut
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class AstronautOverviewAdapter(
    private val onClickListener: (Astronaut) -> Unit
) :
    ListAdapter<Astronaut, AstronautOverviewAdapter.AstronautViewHolder>(DiffUtilCallback()) {

    class AstronautViewHolder(private val binding: AstronautItemBinding) :
        ViewHolder(binding.root) {
        val astronautImageView = binding.astronautItemImageView
        val astronautProgressBar = binding.astronautItemProgressBar

        fun bind(astronaut: Astronaut) {
            binding.astronautItemNameTextView.text = astronaut.name
            binding.astronautItemSpacecraftTextView.text = binding.root.context.getString(
                R.string.spacecraft_name,
                astronaut.spacecraft.ifEmpty { "-" }
            )
            binding.astronautItemNationalityTextView.text = astronaut.nationality
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AstronautViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return AstronautViewHolder(AstronautItemBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: AstronautViewHolder, position: Int) {
        val item = currentList[position]

        Picasso.get()
            .load(item.profileImage)
            .resize(200, 200)
            .centerCrop()
            .into(holder.astronautImageView, object : Callback {
                override fun onSuccess() {
                    holder.astronautProgressBar.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    holder.astronautProgressBar.visibility = View.GONE
                    holder.astronautImageView.setImageResource(R.drawable.image_not_found_icon)
                }
            })

        holder.itemView.setOnClickListener { onClickListener(item) }

        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }
}

class DiffUtilCallback() : DiffUtil.ItemCallback<Astronaut>() {
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
