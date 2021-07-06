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
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeDiaryGroupFragment : Fragment() {
    private lateinit var binding: FragmentHomeDiaryListBinding
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_diary_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvDiaryListInGroup.apply {
            adapter = HomeDiaryGroupAdapter()
            viewModel.getJoinedDiaryList()

            layoutManager = GridLayoutManager(context, 3)
            addItemDecoration(HomeDiaryGroupDecoration())
        }
    }
}