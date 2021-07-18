package com.doggie.app.view.search

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.doggie.app.R

class SearchOneFragment : Fragment() {

    companion object {
        fun newInstance() = SearchOneFragment()
    }

    private lateinit var viewModel: SearchOneViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_one, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SearchOneViewModel::class.java)
        // TODO: Use the ViewModel
    }

}