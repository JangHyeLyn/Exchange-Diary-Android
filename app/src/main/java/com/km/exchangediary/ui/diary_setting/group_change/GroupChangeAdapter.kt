package com.km.exchangediary.ui.diary_setting.group_change

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.km.exchangediary.R
import com.km.exchangediary.databinding.ItemGroupChangeRadioButtonBinding

class GroupChangeAdapter : RecyclerView.Adapter<GroupChangeAdapter.GroupChangeViewHolder>() {
    private var selectCheck: ArrayList<Int> = arrayListOf()

    private var groupChangeList = arrayListOf(
        "그룹미지정",
        "평균 28세들",
        "건물주",
        "돼지파티",
        "전설의 레전드",
        "건강챙기자"
    )

    init {
        for (i in groupChangeList) {
            if (i == "그룹미지정") {
                selectCheck.add(1)
            } else {
                selectCheck.add(0)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GroupChangeViewHolder =
        GroupChangeViewHolder(
            DataBindingUtil.inflate(
                parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater,
                R.layout.item_group_change_radio_button,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: GroupChangeViewHolder, position: Int) {
        holder.onBind(groupChangeList[position], position)
    }

    override fun getItemCount(): Int = groupChangeList.size

    inner class GroupChangeViewHolder(private val binding: ItemGroupChangeRadioButtonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: String, position: Int) {
            binding.rbGroupName.apply {
                text = item
                isChecked = selectCheck[position] == 1
                setOnClickListener {
                    for (k in selectCheck.indices) {
                        if (k == position) {
                            selectCheck[k] = 1
                        } else {
                            selectCheck[k] = 0
                        }
                    }
                    notifyDataSetChanged()
                }
            }
        }
    }
}