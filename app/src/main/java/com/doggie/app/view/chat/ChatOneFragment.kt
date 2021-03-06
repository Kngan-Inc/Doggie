package com.doggie.app.view.chat

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.doggie.app.R

class ChatOneFragment : Fragment() {

    companion object {
        fun newInstance() = ChatOneFragment()
    }

    private lateinit var viewModel: ChatOneViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chat_one, container, false)
    }

}