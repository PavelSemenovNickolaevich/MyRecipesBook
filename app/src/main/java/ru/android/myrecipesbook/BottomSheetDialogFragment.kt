package ru.android.myrecipesbook

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.android.myrecipesbook.databinding.FragmentBottomSheetDialogBinding


class BottomSheetDialogFragment :
    com.google.android.material.bottomsheet.BottomSheetDialogFragment() {

    lateinit var binding: FragmentBottomSheetDialogBinding

    companion object {
        fun newInstance(): BottomSheetDialogFragment {
            return BottomSheetDialogFragment()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bottom_sheet_dialog, container, false)
    }

}