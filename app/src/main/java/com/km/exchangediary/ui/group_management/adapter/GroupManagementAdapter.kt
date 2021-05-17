package com.km.exchangediary.ui.group_management.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.km.exchangediary.R
import com.km.exchangediary.databinding.ItemGroupManagementBinding
import com.km.exchangediary.ui.group_management.DeleteDiaryGroupListener
import com.km.exchangediary.ui.group_management.GroupItemDragListener
import com.km.exchangediary.ui.group_management.model.DiaryGroup
import java.util.*

class GroupManagementAdapter : ListAdapter<DiaryGroup, GroupManagementAdapter.GroupManagementViewHolder>(DIFF_UTIL), GroupItemMoveListener {
    private var listener: GroupItemDragListener? = null
    private var deleteDiaryGroupListener: DeleteDiaryGroupListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupManagementViewHolder =
        GroupManagementViewHolder(
            DataBindingUtil.inflate(
                parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater,
                R.layout.item_group_management,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: GroupManagementViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    fun setDragListener(listener: GroupItemDragListener) {
        this.listener = listener
    }

    fun setDeleteDiaryGroupListener(listener: DeleteDiaryGroupListener) {
        this.deleteDiaryGroupListener = listener
    }

    inner class GroupManagementViewHolder(private val binding: ItemGroupManagementBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("ClickableViewAccessibility")
        fun onBind(item: DiaryGroup) {
            binding.tvGroupName.text = item.title
            binding.tvNumberOfPeople.text = "(${item.numberOfPeople})"
            binding.ivItemChanger.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                    listener?.onDrag(this)
                }

                return@setOnTouchListener true
            }
            /* TODO: 더보기 만들기 */
            binding.ivMore.setOnClickListener {
                deleteDiaryGroupListener?.deleteGroup(item.id)
            }
        }
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
//        Collections.swap(currentList, fromPosition, toPosition)
//        notifyItemMoved(fromPosition, toPosition)
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<DiaryGroup>() {
            override fun areItemsTheSame(oldItem: DiaryGroup, newItem: DiaryGroup): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DiaryGroup, newItem: DiaryGroup): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}