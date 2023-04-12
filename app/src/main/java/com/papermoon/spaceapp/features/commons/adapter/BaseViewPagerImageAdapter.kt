package com.papermoon.spaceapp.features.commons.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.papermoon.spaceapp.R
import com.papermoon.spaceapp.databinding.ItemViewpagerImageBinding
import com.papermoon.spaceapp.domain.model.commons.ImageWithDescription
import com.squareup.picasso.Picasso

class BaseViewPagerImageAdapter(
    private val data: List<ImageWithDescription>,
    private val onClickListener: (Int) -> Unit,
) : RecyclerView.Adapter<BaseViewPagerImageAdapter.PagerViewHolder>() {

    class PagerViewHolder(val binding: ItemViewpagerImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(image: ImageWithDescription) {
            Picasso.get()
                .load(image.imageUrl)
                .into(binding.imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PagerViewHolder(ItemViewpagerImageBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
        if (data.size > 1) {
            holder.binding.counter.text =
                holder.itemView.context.getString(R.string.label_counter, position + 1, data.size)
        } else {
            holder.binding.counter.visibility = View.GONE
        }

        holder.itemView.setOnClickListener {
            onClickListener(position)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
