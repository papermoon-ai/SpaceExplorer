package com.papermoon.spaceapp.features.imageViewer.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.papermoon.spaceapp.databinding.ItemImagePageBinding
import com.papermoon.spaceapp.domain.model.commons.ImageWithDescription
import com.squareup.picasso.Picasso

class ImageViewerAdapter(
    private val data: List<ImageWithDescription>,
    private val onClickListener: (Unit) -> Int,
) : RecyclerView.Adapter<ImageViewerAdapter.PagerViewHolder>() {

    private var descriptionVisibility = View.GONE
    private val viewes = ArrayList<PagerViewHolder>()

    class PagerViewHolder(val binding: ItemImagePageBinding) : ViewHolder(binding.root) {
        fun bind(image: ImageWithDescription) {
            Picasso.get()
                .load(image.imageUrl)
                .into(binding.imgPageItem)

            binding.tvPageItemDescription.text = image.description
            binding.tvPageItemDescription.visibility = View.GONE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PagerViewHolder(ItemImagePageBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
        holder.binding.tvPageItemDescription.visibility = descriptionVisibility
        viewes.add(holder)

        holder.itemView.setOnClickListener {
            descriptionVisibility = onClickListener(Unit)
            for (i in 0 until viewes.size) {
                viewes[i].binding.tvPageItemDescription.visibility = descriptionVisibility
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
