package com.example.blueapp.presentation.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.blueapp.R
import com.example.blueapp.databinding.BottomSheetDialogBinding
import com.example.blueapp.presentation.activity.BooksViewModel
import com.example.blueapp.presentation.activity.BottomSheetState
import com.example.blueapp.presentation.utils.Extensions.toggleVisibility
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class BooksInsightsBottomSheet() : BottomSheetDialogFragment() {

    private lateinit var mBinding: BottomSheetDialogBinding
    private val viewModel:BooksViewModel  by activityViewModels()

    companion object {
        const val TAG = "BooksInsightsBottomSheet"
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = BottomSheetDialogBinding.inflate(
            inflater,
            container,
            false
        )
        attachObserver()
        return mBinding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        dialog?.setOnShowListener {
            val bottomSheetDialog = it as BottomSheetDialog
            val bottomSheet =
                bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheet?.let { view ->
                val behavior = BottomSheetBehavior.from(view)
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        return super.onCreateDialog(savedInstanceState)
    }

    private fun attachObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.bottomSheetState.collectLatest { state ->
                    when {
                        state.isBottomSheetLoading -> {
                            mBinding.progressBar.toggleVisibility(true)
                        }

                        else -> {
                            mBinding.progressBar.toggleVisibility(false)
                           setContentData(state)
                        }

                    }

                }
            }
        }
    }

    private fun setContentData(state:BottomSheetState) {
        state.bottomSheetData?.let { data ->
            data.itemCount.takeIf { it != null }?.let {
                mBinding.tvItemCount.tvCount.text = it.toString()
            }?: {
                mBinding.tvItemCount.root.toggleVisibility(false)
            }

            data.characters.getOrNull(0)?.let { pair ->
                mBinding.tvFirstItem.tvTitle.text =
                    setTitle(pair.first.toString())
                mBinding.tvFirstItem.tvCount.text = pair.second.toString()
            } ?: {
                mBinding.tvFirstItem.root.visibility = View.GONE
            }

            data.characters.getOrNull(1)?.let { pair ->
                mBinding.tvSecondItem.tvTitle.text =
                   setTitle(pair.first.toString())
                mBinding.tvSecondItem.tvCount.text = pair.second.toString()
            } ?: {
                mBinding.tvSecondItem.root.visibility = View.GONE
            }

            data.characters.getOrNull(2)?.let { pair ->
                mBinding.tvThirdItem.tvTitle.text =
                    setTitle(pair.first.toString())
                mBinding.tvThirdItem.tvCount.text = pair.second.toString()
            } ?: {
                mBinding.tvThirdItem.root.visibility = View.GONE
            }
        }
    }

    private fun setTitle(title:String):String =
        context?.getString(R.string.bullet_symbol).plus(" ").plus(title.plus(context?.getString(R.string.colon_symbol)))

}