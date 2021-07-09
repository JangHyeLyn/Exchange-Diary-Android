package com.km.exchangediary.ui.diary_setting.group_change

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.km.exchangediary.R
import com.km.exchangediary.databinding.ItemGroupChangeRadioButtonBinding
import kotlin.properties.Delegates

class GroupChangeAdapter : RecyclerView.Adapter<GroupChangeAdapter.GroupChangeViewHolder>() {
    private var lastChecked: RadioButton? = null
    private var lastCheckedPos = 0

    private var groupChangeList = arrayListOf<GroupChangeData>(
        GroupChangeData("그룹미지정"),
        GroupChangeData("평균 28세들"),
        GroupChangeData("건물주"),
        GroupChangeData("돼지파티"),
        GroupChangeData("전설의 레전드"),
        GroupChangeData("건강챙기자")
    )

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

    }

    override fun getItemCount(): Int = groupChangeList.size

    inner class GroupChangeViewHolder(private val binding: ItemGroupChangeRadioButtonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: GroupChangeData) {
            binding.rbGroupName.text = item.groupName
        }
    }
}