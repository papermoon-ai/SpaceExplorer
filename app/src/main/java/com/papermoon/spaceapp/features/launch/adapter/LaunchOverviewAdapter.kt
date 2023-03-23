package com.papermoon.spaceapp.features.launch.adapter

import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.papermoon.spaceapp.R
import com.papermoon.spaceapp.databinding.LaunchItemBinding
import com.papermoon.spaceapp.domain.model.Launch
import com.squareup.picasso.Picasso
import org.joda.time.DateTime
import java.util.concurrent.TimeUnit

class LaunchOverviewAdapter :
    ListAdapter<Launch, LaunchOverviewAdapter.LaunchViewHolder>(DiffUtilCallback()) {

    class LaunchViewHolder(private val binding: LaunchItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var timer: CountDownTimer? = null
        var dateTextView: TextView = binding.dateTextView
        var launchImageView: ImageView = binding.launchImageView

        fun bind(launch: Launch) {
            binding.nameTextView.text = launch.name
            binding.locationTextView.text = launch.location
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return LaunchViewHolder(LaunchItemBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: LaunchViewHolder, position: Int) {
        val item = currentList[position]
        Picasso.get()
            .load(item.imageUrl)
            .fit()
            .into(holder.launchImageView)

        if (item.launchDate.minusDays(1).isBeforeNow) {
            if (holder.timer != null) {
                holder.timer!!.cancel()
            }
            val millisBeforeLaunch = item.launchDate.millis - DateTime.now().millis
            holder.timer = object: CountDownTimer(millisBeforeLaunch, 1000) {
                override fun onTick(p0: Long) {
                    val hours = TimeUnit.MILLISECONDS.toHours(p0)
                    val minutes = TimeUnit.MILLISECONDS.toMinutes(p0) % TimeUnit.HOURS.toMinutes(1)
                    val seconds = TimeUnit.MILLISECONDS.toSeconds(p0) % TimeUnit.MINUTES.toSeconds(1)
                    holder.dateTextView.text = String.format("%02d:%02d:%02d", hours, minutes, seconds)
                }

                override fun onFinish() {
                    holder.dateTextView.text = holder.itemView.context.getString(R.string.launchMessage)
                }
            }.start()
        } else {
            holder.dateTextView.text = item.launchDate.toString("dd.MM.yyyy")
        }

        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }
}

class DiffUtilCallback : DiffUtil.ItemCallback<Launch>() {
    override fun areItemsTheSame(oldItem: Launch, newItem: Launch): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Launch, newItem: Launch): Boolean {
        return oldItem.name == newItem.name
                && oldItem.launchServiceProvider == newItem.launchServiceProvider
                && oldItem.imageUrl == newItem.imageUrl
                && oldItem.location == newItem.location
                && oldItem.launchDate == newItem.launchDate
    }
}
