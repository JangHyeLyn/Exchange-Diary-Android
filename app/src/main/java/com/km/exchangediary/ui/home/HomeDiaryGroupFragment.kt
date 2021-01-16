package com.km.exchangediary.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.km.exchangediary.R
import com.km.exchangediary.databinding.FragmentHomeDiaryListBinding
import com.km.exchangediary.ui.home.adapter.HomeDiaryGroupAdapter
import com.km.exchangediary.ui.home.itemdecoration.HomeDiaryGroupDecoration
import com.km.exchangediary.ui.home.model.HomeDiary

class HomeDiaryGroupFragment : Fragment() {
    private lateinit var binding: FragmentHomeDiaryListBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_diary_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvDiaryListInGroup.apply {
            adapter = HomeDiaryGroupAdapter().apply {
                addDiaries(arrayListOf(
                        HomeDiary("봄"),
                        HomeDiary("여름"),
                        HomeDiary("가을"),
                        HomeDiary("겨울"),
                        HomeDiary("해"),
                        HomeDiary("달"),
                        HomeDiary("별"),
                        HomeDiary("구름")
                ))
            }
            layoutManager = GridLayoutManager(context, 3)
            addItemDecoration(HomeDiaryGroupDecoration())
        }
    }
}