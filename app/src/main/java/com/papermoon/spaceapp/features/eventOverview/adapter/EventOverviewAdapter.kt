package com.papermoon.spaceapp.features.eventOverview.adapter

import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.papermoon.spaceapp.R
import com.papermoon.spaceapp.databinding.ItemEventBinding
import com.papermoon.spaceapp.domain.model.event.Event
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import org.joda.time.DateTime
import java.util.concurrent.TimeUnit

class EventOverviewAdapter(
    private val onClickListener: OnClickListener
) : ListAdapter<Event, EventOverviewAdapter.EventViewHolder>(DiffUtilCallback()) {

    class EventViewHolder(private val binding: ItemEventBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var timer: CountDownTimer? = null
        var dateTextView: TextView = binding.tvItemEventDate
        var eventImageView: ImageView = binding.imgItemEvent
        var progressBar: ProgressBar = binding.eventItemProgressBar

        fun bind(event: Event) {
            binding.tvItemEventName.text = event.name
            binding.tvItemEventType.text = event.type

            Picasso.get()
                .load(event.images.first().imageUrl)
                .resize(
                    binding.root.context.resources.getDimension(R.dimen.base_photo_image_width)
                        .toInt(),
                    binding.root.context.resources.getDimension(R.dimen.base_photo_image_height)
                        .toInt()
                )
                .centerCrop()
                .into(eventImageView, object : Callback {
                    override fun onSuccess() {
                        progressBar.visibility = View.GONE
                    }

                    override fun onError(e: Exception?) {
                        progressBar.visibility = View.GONE
                        eventImageView.setImageResource(R.drawable.ic_image_not_found)
                    }
                })

            if (event.date.minusDays(1).isBeforeNow) {
                timer?.cancel()
                val millisBeforeLaunch = event.date.millis - DateTime.now().millis

                timer = object : CountDownTimer(millisBeforeLaunch, 1000) {
                    override fun onTick(p0: Long) {
                        val hours = TimeUnit.MILLISECONDS.toHours(p0)
                        val minutes =
                            TimeUnit.MILLISECONDS.toMinutes(p0) % TimeUnit.HOURS.toMinutes(1)
                        val seconds =
                            TimeUnit.MILLISECONDS.toSeconds(p0) % TimeUnit.MINUTES.toSeconds(1)
                        dateTextView.text =
                            String.format("%02d:%02d:%02d", hours, minutes, seconds)
                    }

                    override fun onFinish() {
                        dateTextView.text =
                            itemView.context.getString(R.string.label_event_completed)
                    }
                }.start()
            } else {
                dateTextView.text = event.date.toString("dd.MM.yyyy")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return EventViewHolder(ItemEventBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val item = currentList[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(item)
        }
    }

    override fun getItemCount(): Int {
        return currentList.size
    }
}

class DiffUtilCallback : DiffUtil.ItemCallback<Event>() {
    override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem.name == newItem.name
                && oldItem.type == newItem.type
                && oldItem.images == newItem.images
                && oldItem.description == newItem.description
                && oldItem.newsUrl == newItem.newsUrl
                && oldItem.videoUrl == newItem.videoUrl
                && oldItem.date == newItem.date
    }
}

class OnClickListener(val onClickListener: (event: Event) -> Unit) {
    fun onClick(event: Event) = onClickListener(event)
}
