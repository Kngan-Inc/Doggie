package com.doggie.app.view.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.doggie.app.R
import com.doggie.app.databinding.FragmentBottomSheetDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MyBottomSheetDialogFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentBottomSheetDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBottomSheetDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val options = listOf<String>(
            "ធ្វើមិត្តជាមួយឆ្កែ",
            "រាយការណ៍ឆ្កែនេះ",
            "រារាំងឆ្កែនេះ",
            "បន្ថែមឆ្កែនេះទៅសំណព្វ",
        )
        binding.listViewOptions.adapter = ArrayAdapter<String>(
            requireContext(),
            R.layout.bottom_dialog_item_view,
            options,
        )
        binding.listViewOptions.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

            }
        }
    }

}