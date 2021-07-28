package com.ibtikar.mvvm_starter_koin_coroutines.ui.settings

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ibtikar.mvvm_starter_koin_coroutines.R
import com.ibtikar.mvvm_starter_koin_coroutines.data.local.SharedPreferencesInterface
import com.ibtikar.mvvm_starter_koin_coroutines.databinding.ChooseLanguageBottomDialogBinding
import com.ibtikar.mvvm_starter_koin_coroutines.utils.LanguageCodes
import com.ibtikar.mvvm_starter_koin_coroutines.utils.getKoinInstance

class ChangeLanguageBottomSheet : BottomSheetDialogFragment() {

    private lateinit var callBack: (languageCode: String) -> Unit
    val sharedPreferences by getKoinInstance<SharedPreferencesInterface>()

    companion object {
        fun showDialog(fragmentManager: FragmentManager, callback: (languageCode: String) -> Unit) {
            val bottomSheet = ChangeLanguageBottomSheet()
            bottomSheet.callBack = callback
            bottomSheet.show(fragmentManager, ChangeLanguageBottomSheet::class.java.simpleName)
        }
    }

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(requireContext(), theme)
    }


    private val mBottomSheetBehaviorCallback = object : BottomSheetBehavior.BottomSheetCallback() {
        override fun onStateChanged(bottomSheet: View, newState: Int) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss()
            }
        }

        override fun onSlide(bottomSheet: View, slideOffset: Float) {}
    }

    override fun setupDialog(dialog: Dialog, style: Int) {
        //Get the content View
        val chooseLanguageBinding = ChooseLanguageBottomDialogBinding.inflate(
            LayoutInflater.from(requireContext()), null, false
        )
        dialog.setContentView(chooseLanguageBinding.root)
        //Set the coordinator layout behavior
        val params = (chooseLanguageBinding.root.parent as View).layoutParams as
                CoordinatorLayout.LayoutParams
        val behavior = params.behavior

        //Set callback
        if (behavior != null && behavior is BottomSheetBehavior<*>) {
            behavior.setBottomSheetCallback(mBottomSheetBehaviorCallback)
            behavior.state = BottomSheetBehavior.PEEK_HEIGHT_AUTO
        }
        if (sharedPreferences.language == LanguageCodes.ARABIC) {
            chooseLanguageBinding.textViewArabic.setTextColor(resources.getColor(R.color.colorPrimary))
        } else {
            chooseLanguageBinding.textViewEnglish.setTextColor(resources.getColor(R.color.colorPrimary))
        }
        chooseLanguageBinding.textViewEnglish.setOnClickListener {
            dismiss()
            callBack.invoke("en")
        }

        chooseLanguageBinding.textViewArabic.setOnClickListener {
            dismiss()
            callBack.invoke("ar")
        }

        chooseLanguageBinding.textViewCancel.setOnClickListener {
            dismiss()
        }

    }
}