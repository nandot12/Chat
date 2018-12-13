package com.nandohusni.baggit.ui.locations.bottomsheet


import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.nandohusni.baggit.R


/**
 * A simple [Fragment] subclass.
 *
 */
class Bottom_sheetFragment : BottomSheetDialogFragment() {


    fun newInstance(): Bottom_sheetFragment {
        return Bottom_sheetFragment()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

            return inflater.inflate(R.layout.bottom_sheet, container, false)

        }



}
