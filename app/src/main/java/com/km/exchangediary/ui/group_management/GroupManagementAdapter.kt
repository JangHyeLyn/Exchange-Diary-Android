package com.km.exchangediary.ui.group_management

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.km.exchangediary.R
import com.km.exchangediary.databinding.ItemGroupManagementBinding
import com.km.exchangediary.ui.group_management.model.Group

class GroupManagementAdapter : RecyclerView.Adapter<GroupManagementAdapter.GroupManagementViewHolder>() {
    private val groupList = arrayListOf<Group>(
        Group("그룹 미지정", 1),
        Group("평균 28세들", 2),
        Group("그룹명최대열글자까지", 1),
        Group("돼지파티", 4),
        Group("SABAL", 2),
        Group("전설의 레전드", 5),
        Group("전설의 레전드", 5),
        Group("전설의 레전드", 5),
        Group("전설의 레전드", 5),
        Group("전설의 레전드", 5),
        Group("전설의 레전드", 5),
        Group("전설의 레전드", 5),
        Group("전설의 레전드", 5),
        Group("전설의 레전드", 5),
        Group("전설의 레전드", 5),
        Group("전설의 레전드", 5),
        Group("전설의 레전드", 5),
        Group("전설의 레전드", 5),
        Group("전설의 레전드", 5),
        Group("전설의 레전드", 5),
        Group("전설의 레전드", 5),
        Group("전설의 레전드", 5)
    )

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

    class GroupManagementViewHolder(private val binding: ItemGroupManagementBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: Group) {
            binding.tvGroupName.text = item.name
            binding.tvNumberOfPeople.text = "(${item.numberOfPeople})"
        }
    }
}