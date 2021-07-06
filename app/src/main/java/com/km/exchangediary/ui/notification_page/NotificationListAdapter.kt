package com.km.exchangediary.ui.notification_page

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.km.exchangediary.R
import com.km.exchangediary.data.remote.response.Notification
import com.km.exchangediary.data.remote.response.NotificationResponse
import com.km.exchangediary.databinding.ItemNotificationBinding

class NotificationListAdapter(private val notificationList: NotificationResponse) : RecyclerView.Adapter<NotificationListAdapter.NotificationViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder =
        NotificationViewHolder(DataBindingUtil.inflate(
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater,
            R.layout.item_notification,
            parent,
            false)
        )

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.onBind(notificationList.data[position])
    }

    override fun getItemCount(): Int = notificationList.data.size

    class NotificationViewHolder(private val binding: ItemNotificationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: Notification) {
            /* TODO Glide를 통해 다이어리 커버 이미지 바인딩 */
            binding.tvNotificationDiaryTitle.text = item.diaryTitle
            binding.tvNotificationDetail.text = item.message
            binding.tvNotificationGroupNameAndTime.text = "${item.diaryGroup} · ${item.createdAt}"
        }
    }
}