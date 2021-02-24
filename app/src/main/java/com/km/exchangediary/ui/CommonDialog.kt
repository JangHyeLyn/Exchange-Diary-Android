package com.km.exchangediary.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.km.exchangediary.databinding.DialogCommonBinding
import com.km.exchangediary.utils.toPx

enum class DialogContentType {
    TEXT_VIEW,
    EDIT_TEXT
}

class CommonDialog(private val titleVisible: Boolean = true,
                   private val contentType: DialogContentType = DialogContentType.TEXT_VIEW,
                   private val titleText: String = "", private val contentText: String = "",
                   private val confirmText: String = "확인", private val cancelText: String = "취소",
                   private val onSuccess : () -> Unit = {}) : DialogFragment() {
    lateinit var binding: DialogCommonBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DialogCommonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.layoutTitle.visibility = if (titleVisible) {
            binding.tvTitle.text = titleText
            View.VISIBLE
        } else {
            (binding.layoutTextContents.layoutParams as ViewGroup.MarginLayoutParams).topMargin = 20.toPx()
            View.GONE
        }

        when (contentType) {
            DialogContentType.TEXT_VIEW -> {
                binding.tvContents.visibility = View.VISIBLE
                binding.etContents.visibility = View.GONE
                binding.tvContents.text = contentText
            }
            DialogContentType.EDIT_TEXT -> {
                binding.tvContents.visibility = View.GONE
                binding.etContents.visibility = View.VISIBLE
            }
        }

        binding.tvCancel.text = cancelText
        binding.tvConfirm.text = confirmText

        binding.btnConfirm.setOnClickListener {
            onSuccess()
            dialog?.dismiss()
        }

        binding.btnCancel.setOnClickListener {
            dialog?.cancel()
        }
    }
}