package com.doggie.app.view.data

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.doggie.app.R

class DataOneFragment : Fragment() {

    companion object {
        fun newInstance() = DataOneFragment()
    }

    private lateinit var viewModel: DataOneViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_data_one, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DataOneViewModel::class.java)
        // TODO: Use the ViewModel
    }

}