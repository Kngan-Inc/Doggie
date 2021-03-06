package com.doggie.app.view.search

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.doggie.app.R
import com.doggie.app.databinding.FragmentSearchBinding
import com.doggie.app.epoxy.controller.DoggieController
import com.doggie.app.util.ItemDecoration
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.seanghay.statusbar.statusBar


class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModels()
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val controller by lazy {
        DoggieController(
            context = requireContext(),
            navController = findNavController(),
            buttonListener = {
                findNavController().navigate(R.id.action_searchFragment_to_myBottomFragment)
            },
            removeListener = {
                viewModel.removeItem(item = it)
            }
        )
    }

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        controller.onRestoreInstanceState(savedInstanceState)
        statusBar.color(Color.TRANSPARENT).light(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        controller.onSaveInstanceState(outState)
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
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun initView() {

        val spanCount = 2
        val layoutManager = GridLayoutManager(context, spanCount)
        controller.spanCount = spanCount
        layoutManager.spanSizeLookup = controller.spanSizeLookup
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.setController(controller = controller)
        binding.recyclerView.addItemDecoration(ItemDecoration(spanCount, 12, true))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.recyclerView.setDelayMsWhenRemovingAdapterOnDetach(0)
        binding.recyclerView.adapter = null
        _binding = null
    }
}
