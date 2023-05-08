package com.papermoon.spaceapp.features.spaceStationOverview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.papermoon.spaceapp.R
import com.papermoon.spaceapp.databinding.ItemSpaceStationBinding
import com.papermoon.spaceapp.domain.model.spacestation.SpaceStation
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class SpaceStationAdapter(
    private val onClickListener: (SpaceStation) -> Unit
) :
    ListAdapter<SpaceStation, SpaceStationAdapter.SpaceStationViewHolder>(DiffUtilCallback()) {

    class SpaceStationViewHolder(private val binding: ItemSpaceStationBinding) :
        ViewHolder(binding.root) {
        fun bind(spaceStation: SpaceStation) {
            binding.spaceStationItemNameTextView.text = spaceStation.name
            binding.spaceStationFoundedTextView.text =
                binding.root.context.getString(
                    R.string.description_date_of_operation,
                    spaceStation.founded.toString("dd.MM.yyyy")
                )

            Picasso.get()
                .load(spaceStation.images.first().imageUrl)
                .fit()
                .into(binding.spaceStationItemImageView, object : Callback {
                    override fun onSuccess() {
                        binding.spaceStationItemProgressBar.visibility = View.GONE
                    }

                    override fun onError(e: Exception?) {
                        binding.spaceStationItemProgressBar.visibility = View.GONE
                        binding.spaceStationItemImageView.setImageResource(R.drawable.ic_image_not_found)
                    }
                })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpaceStationViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SpaceStationViewHolder(ItemSpaceStationBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: SpaceStationViewHolder, position: Int) {
        val item = currentList[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onClickListener(item)
        }
    }

    override fun getItemCount(): Int {
        return currentList.size
    }
}

class DiffUtilCallback : DiffUtil.ItemCallback<SpaceStation>() {
    override fun areItemsTheSame(oldItem: SpaceStation, newItem: SpaceStation): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: SpaceStation, newItem: SpaceStation): Boolean {
        return oldItem.name == newItem.name
                && oldItem.founded == newItem.founded
                && oldItem.images == newItem.images
                && oldItem.owners == newItem.owners
                && oldItem.description == newItem.description
                && oldItem.isActive == newItem.isActive
    }
}
