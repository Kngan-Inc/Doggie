package com.doggie.app.view.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.doggie.app.R
import com.doggie.app.databinding.FragmentBottomSheetDialogBinding
import com.doggie.app.epoxy.controller.BottomDialogController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MyBottomSheetDialogFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentBottomSheetDialogBinding? = null
    private val binding get() = _binding!!
    private val controller by lazy {
        BottomDialogController(
            context = requireContext(),
            buttonListener = {
                findNavController().navigate(R.id.action_myBottomFragment_to_searchOneFragment)
            }
        )
    }

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

        val options: ArrayList<Pair<Int, String>> = arrayListOf(
            Pair(first = R.drawable.ic_baseline_search_24, second = "ធ្វើមិត្តជាមួយឆ្កែ"),
            Pair(first = R.drawable.ic_baseline_comment_24, second = "រាយការណ៍ឆ្កែនេះ"),
            Pair(first = R.drawable.ic_baseline_data_usage_24, second = "រារាំងឆ្កែនេះ"),
            Pair(first = R.drawable.ic_setting, second = "បន្ថែមឆ្កែនេះទៅសំណព្វ"),
        )

        binding.listRecyclerView.setController(controller)
        controller.submitList(options)
    }

}