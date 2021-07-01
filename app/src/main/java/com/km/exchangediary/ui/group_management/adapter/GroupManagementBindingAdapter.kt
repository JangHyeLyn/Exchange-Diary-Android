package com.km.exchangediary.ui.group_management.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.km.exchangediary.ui.group_management.model.DiaryGroup

@BindingAdapter("setList")
fun setList(rv: RecyclerView, list: List<DiaryGroup>?) {
    list?.let {
        (rv.adapter as? GroupManagementAdapter)?.submitList(it)
    }
}