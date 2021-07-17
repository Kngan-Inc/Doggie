package com.doggie.app.view

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.doggie.app.R
import com.doggie.app.databinding.FragmentSearchBinding
import com.doggie.app.epoxy.controller.SelectionController
import com.doggie.app.util.ItemDecoration
import com.seanghay.statusbar.statusBar
import www.sanju.motiontoast.MotionToast


class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModels()
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val controller by lazy {
        SelectionController()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        statusBar.light(true).color(Color.TRANSPARENT)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initAction()
        initObservation()

    }

    private fun initAction() {
    }

    private fun initObservation() {
        viewModel.doggies.observe(viewLifecycleOwner, controller::submitList)
        viewModel.errorMessage.observe(viewLifecycleOwner, {
            MotionToast.createToast(
                requireActivity(),
                "Failed ☹️",
                it,
                MotionToast.TOAST_ERROR,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.LONG_DURATION,
                ResourcesCompat.getFont(requireContext(), R.font.helvetica_regular),
            )

        })
    }

    private fun initView() {

        val spanCount = 2 // 3 columns
        val spacing = 10 // 50px
        val includeEdge = true

        val layoutManager = GridLayoutManager(context, spanCount)
        controller.spanCount = spanCount
        layoutManager.spanSizeLookup = controller.spanSizeLookup
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.setController(controller = controller)
        binding.recyclerView.addItemDecoration(ItemDecoration(spanCount, spacing, includeEdge))
    }

}