package com.km.exchangediary.ui.profile

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.km.exchangediary.databinding.FragmentSelectProfileImageBinding

class SelectProfileImageFragment : DialogFragment() {
    lateinit var binding: FragmentSelectProfileImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSelectProfileImageBinding.inflate(inflater, container, false)

        binding.layoutSelectFromFile.setOnClickListener {
            (activity as ProfileEditActivity).checkPermission()
            dialog?.dismiss()
        }
        binding.layoutChangeToDefaultImage.setOnClickListener {
            (activity as ProfileEditActivity).setDefaultImage()
            dialog?.dismiss()
        }

        return binding.root
    }
}