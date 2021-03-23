package com.km.exchangediary.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.km.exchangediary.R
import com.km.exchangediary.databinding.ItemHomeWritingDiaryBinding
import com.km.exchangediary.ui.home.model.HomeDiary

// RecyclerView
class HomeWritingDiaryAdapter : RecyclerView.Adapter<HomeWritingDiaryAdapter.HomeWritingDiaryListViewHolder>() {
    private val diaryList: ArrayList<HomeDiary> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeWritingDiaryListViewHolder =
            HomeWritingDiaryListViewHolder(DataBindingUtil.inflate(
                    parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater,
                    R.layout.item_home_writing_diary,
                    parent,
                    false)
            )

    override fun getItemCount(): Int = diaryList.size

    override fun onBindViewHolder(holder: HomeWritingDiaryListViewHolder, position: Int) {
        holder.onBind(diaryList[position])
    }

    class HomeWritingDiaryListViewHolder(private val binding: ItemHomeWritingDiaryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: HomeDiary) {
            binding.tvName.text = item.title
        }
    }
}