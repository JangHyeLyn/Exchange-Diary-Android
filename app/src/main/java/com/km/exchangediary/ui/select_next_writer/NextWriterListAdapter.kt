package com.km.exchangediary.ui.select_next_writer

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.km.exchangediary.R
import com.km.exchangediary.databinding.ItemSelectNextWriterBinding

class NextWriterListAdapter :
    RecyclerView.Adapter<NextWriterListAdapter.SelectNextWriterViewHolder>() {
    private val nextWriterList = arrayListOf<NextWriterData>(
        NextWriterData("쭈피"),
        NextWriterData("개똥이"),
        NextWriterData("안경무"),
        NextWriterData("택형"),
        NextWriterData("이운기")
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectNextWriterViewHolder =
        NextWriterListAdapter.SelectNextWriterViewHolder(
            DataBindingUtil.inflate(
                parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater,
                R.layout.item_select_next_writer,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: SelectNextWriterViewHolder, position: Int) {
        holder.onBind(nextWriterList[position])
        if (position == 0) holder.disableUser()
    }

    override fun getItemCount(): Int = nextWriterList.size

    class SelectNextWriterViewHolder(private val binding: ItemSelectNextWriterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: NextWriterData) {
            binding.tvSelectNextUserName.text = item.userName
        }

        fun disableUser() {
            binding.clSelectNextWriter.alpha = 0.4F
        }
    }
}