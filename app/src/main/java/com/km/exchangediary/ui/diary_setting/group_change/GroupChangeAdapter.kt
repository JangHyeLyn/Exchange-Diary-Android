package com.km.exchangediary.ui.diary_setting.group_change

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.km.exchangediary.R
import com.km.exchangediary.databinding.ItemRadioButtonBinding
import kotlin.properties.Delegates

class GroupChangeAdapter : RecyclerView.Adapter<GroupChangeAdapter.GroupChangeViewHolder>() {
    private var groupChangeList = arrayListOf<GroupChangeData>(
        GroupChangeData("그룹미지정"),
        GroupChangeData("평균 28세들"),
        GroupChangeData("건물주"),
        GroupChangeData("돼지파티"),
        GroupChangeData("전설의 레전드"),
        GroupChangeData("건강챙기자")
    )
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private var selectedPosition by Delegates.observable(-1) { property, oldPos, newPos ->
        if (newPos in groupChangeList.indices) {
            notifyItemChanged(oldPos)
            notifyItemChanged(newPos)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GroupChangeViewHolder =
        GroupChangeViewHolder(
            DataBindingUtil.inflate(
                parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater,
                R.layout.item_radio_button,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: GroupChangeViewHolder, position: Int) {
        if (position in groupChangeList.indices) {
            holder.onBind(groupChangeList[position], position == selectedPosition)
            holder.itemView.setOnClickListener { selectedPosition = position }
        }
    }

    override fun getItemCount(): Int = groupChangeList.size

    class GroupChangeViewHolder(private val binding: ItemRadioButtonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: GroupChangeData, selected: Boolean) {
            binding.rbGroupName.text = item.groupName
            binding.rbGroupName.isChecked = selected
        }
    }
}