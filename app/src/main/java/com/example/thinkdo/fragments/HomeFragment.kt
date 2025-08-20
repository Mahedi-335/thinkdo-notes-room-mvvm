package com.example.thinkdo.fragments

import NoteViewModel
import VMFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.thinkdo.adapter.NoteAdapter
import com.example.thinkdo.databinding.FragmentHomeBinding
import com.example.thinkdo.model.Note
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _b: FragmentHomeBinding? = null
    private val b get() = _b!!


    private val viewModel: NoteViewModel by viewModels { VMFactory(requireContext()) }


    private lateinit var adapter: NoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _b = FragmentHomeBinding.inflate(inflater, container, false)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        adapter = NoteAdapter { note: Note ->
            val action = HomeFragmentDirections.actionHomeFragmentToEditFragment(note.id)
            findNavController().navigate(action)
        }


        b.recyclerView.adapter = adapter


        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.notes.collect { notes ->
                adapter.submitList(notes)
            }
        }


        b.floatingButton.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToAddFragment()
            findNavController().navigate(action)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _b = null
    }
}
