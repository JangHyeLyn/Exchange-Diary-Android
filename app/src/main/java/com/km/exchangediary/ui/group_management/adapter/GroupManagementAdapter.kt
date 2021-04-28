package com.km.exchangediary.ui.group_management.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.km.exchangediary.R
import com.km.exchangediary.databinding.ItemGroupManagementBinding
import com.km.exchangediary.ui.group_management.GroupItemDragListener
import com.km.exchangediary.ui.group_management.model.DiaryGroup
import java.util.*

class GroupManagementAdapter : RecyclerView.Adapter<GroupManagementAdapter.GroupManagementViewHolder>(), GroupItemMoveListener {
    private var listener: GroupItemDragListener? = null

    var groupList = arrayListOf<DiaryGroup>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupManagementViewHolder =
        GroupManagementViewHolder(
            DataBindingUtil.inflate(
                parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater,
                R.layout.item_group_management,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = groupList.size

    override fun onBindViewHolder(holder: GroupManagementViewHolder, position: Int) {
        holder.onBind(groupList[position])
    }

    fun setDragListener(listener: GroupItemDragListener) {
        this.listener = listener
    }

    fun addGroupList(items: List<DiaryGroup>) {
        groupList.clear()
        groupList.addAll(items)
        notifyDataSetChanged()
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
        }
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        Collections.swap(groupList, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }
}