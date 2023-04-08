package com.papermoon.spaceapp.features.imageViewer.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.papermoon.spaceapp.databinding.ItemImagePageBinding
import com.squareup.picasso.Picasso

class ImageViewerAdapter(
    private val data: List<Uri>,
    private val onClickListener: (Unit) -> Unit
) : RecyclerView.Adapter<ImageViewerAdapter.PagerViewHolder>() {

    class PagerViewHolder(val binding: ItemImagePageBinding) : ViewHolder(binding.root) {
        fun bind(image: Uri) {
            Picasso.get()
                .load(image)
                .into(binding.pageItemImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PagerViewHolder(ItemImagePageBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onClickListener(Unit)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
