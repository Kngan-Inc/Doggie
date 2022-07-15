package com.doggie.app.view.chat

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StableIdKeyProvider
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.LinearLayoutManager
import com.doggie.app.databinding.FragmentChatBinding
import com.doggie.app.view.chat.adapter.ChatAdapter
import com.doggie.app.view.chat.adapter.MyItemDetailsLookup
import com.doggie.app.view.data.DataViewModel
import com.doggie.app.view.search.SearchViewModel
import com.seanghay.statusbar.statusBar
import java.lang.Exception

class ChatFragment : Fragment() {

    companion object {
        fun newInstance() = ChatFragment()
    }

    private val viewModel: DataViewModel by viewModels()
    private val searchViewModel: SearchViewModel by viewModels()
    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!
    private val controller by lazy {
        ChatController(
            onSelectedChangeListener = { dog ->
//                searchViewModel.updateSelectionStatus(dog = dog)
            }
        )
    }
    private var adapter: ChatAdapter? = null
    private var tracker: SelectionTracker<Long>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        statusBar.color(Color.TRANSPARENT).light(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initObserver()
    }

    private fun initView() {
        adapter = ChatAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = adapter
        tracker = SelectionTracker.Builder(
            "mySelection",
            binding.recyclerView,
            StableIdKeyProvider(binding.recyclerView),
            MyItemDetailsLookup(binding.recyclerView),
            StorageStrategy.createLongStorage()
        ).withSelectionPredicate(
            SelectionPredicates.createSelectSingleAnything()
        ).build()
        tracker?.addObserver(
            object : SelectionTracker.SelectionObserver<Long>() {
                override fun onSelectionChanged() {
                    super.onSelectionChanged()
                    val items = tracker?.selection
                    if(items?.isEmpty == true) {
                        searchViewModel.updateSelectionStatus(dog = null)
                    } else {
                        items?.forEach {
                            try {
                                val dog = adapter?.currentList?.get(it.toInt())
                                dog?.let { it1 -> searchViewModel.updateSelectionStatus(dog = it1) }
                            } catch (e: Exception) {
                                println(e.message)
                            }
                        }
                    }
                }
            })
        adapter!!.tracker = tracker
    }

    private fun initObserver() {
        searchViewModel.doggies.observe(viewLifecycleOwner) {
//            controller.submitList(it)
            adapter?.submitList(it)
            tracker?.select(it.indexOf(it.find { item -> item == searchViewModel.selectedDog}).toLong())

        }
    }
}
