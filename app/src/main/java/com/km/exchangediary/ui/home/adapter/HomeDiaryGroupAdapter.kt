package com.km.exchangediary.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.km.exchangediary.R
import com.km.exchangediary.databinding.ItemHomeGroupDiaryBinding
import com.km.exchangediary.ui.home.model.HomeDiary

// ViewPager2 in RecyclerView
class HomeDiaryGroupAdapter : RecyclerView.Adapter<HomeDiaryGroupAdapter.DiaryGroupDetailViewHolder>() {
    private val diaryList = arrayListOf<HomeDiary>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryGroupDetailViewHolder =
        DiaryGroupDetailViewHolder(DataBindingUtil.inflate(
                parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater,
                R.layout.item_home_group_diary,
                parent,
                false)
        )

    override fun getItemCount(): Int = diaryList.size

    override fun onBindViewHolder(holder: DiaryGroupDetailViewHolder, position: Int) {
        holder.onBind(diaryList[position])
    }

    fun addDiaries(homeDiaryList: ArrayList<HomeDiary>) {
        for (item in homeDiaryList) {
            this.diaryList.add(item)
        }
    }

    class DiaryGroupDetailViewHolder(private val binding: ItemHomeGroupDiaryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: HomeDiary) {
            binding.tvTitle.text = item.title
        }
    }
}