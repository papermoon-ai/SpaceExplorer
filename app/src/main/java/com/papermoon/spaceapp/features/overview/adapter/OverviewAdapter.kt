package com.papermoon.spaceapp.features.overview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.papermoon.spaceapp.databinding.ItemOptionBinding
import com.papermoon.spaceapp.domain.model.menu.MenuOption

class OverviewAdapter(
    private val onClickListener: OnClickListener
) :
    ListAdapter<MenuOption, OverviewAdapter.OptionViewHolder>(DiffUtilCallback()) {

    class OptionViewHolder(private val binding: ItemOptionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(menuOption: MenuOption) {
            binding.optionName.text = menuOption.name
            binding.optionDescription.text = menuOption.description
            binding.imageView.setImageResource(menuOption.srcImageId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return OptionViewHolder(ItemOptionBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {
        val item = currentList[position]
        holder.itemView.setOnClickListener {
            onClickListener.onClick(item)
        }
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }
}

class DiffUtilCallback : DiffUtil.ItemCallback<MenuOption>() {
    override fun areItemsTheSame(oldItem: MenuOption, newItem: MenuOption): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: MenuOption, newItem: MenuOption): Boolean {
        return oldItem.name == newItem.name
                && oldItem.description == newItem.description
                && oldItem.srcImageId == newItem.srcImageId
    }
}

class OnClickListener(val clickListener: (menuOption: MenuOption) -> Unit) {
    fun onClick(menuOption: MenuOption) = clickListener(menuOption)
}
