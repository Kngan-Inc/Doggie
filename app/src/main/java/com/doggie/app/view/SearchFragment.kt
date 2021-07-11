package com.doggie.app.view

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.doggie.app.databinding.FragmentSearchBinding
import com.doggie.app.epoxy.controller.SelectionController
import com.seanghay.statusbar.statusBar


class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModels()
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val controller by lazy {
        SelectionController(
            navController = findNavController(),
            viewModel = viewModel
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        statusBar.light(false).color(Color.TRANSPARENT)
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

    private fun initObservation (){
        viewModel.pagedList.observe(viewLifecycleOwner, {
            controller.submitList(it)
        })
    }
    private fun initView() {
        binding.recyclerView.setController(controller = controller)
    }
}