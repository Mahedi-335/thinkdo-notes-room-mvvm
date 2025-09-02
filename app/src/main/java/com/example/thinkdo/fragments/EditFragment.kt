package com.example.thinkdo.fragments

import NoteViewModel
import VMFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.thinkdo.databinding.FragmentEditBinding
import com.example.thinkdo.fragments.EditFragmentArgs
import kotlinx.coroutines.launch

class EditFragment : Fragment() {

    private var _b: FragmentEditBinding? = null
    private val b get() = _b!!

    private val args: EditFragmentArgs by navArgs()
    private val viewModel: NoteViewModel by viewModels { VMFactory(requireContext()) }

    private var currentNoteId: Long = 0L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _b = FragmentEditBinding.inflate(inflater, container, false)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val noteId = args.itemId


        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getNoteById(noteId).collect { note ->
                if (note != null) {
                    currentNoteId = note.id.toLong()
                    b.titleEditText.setText(note.title)
                    b.contentEditText.setText(note.content)
                } else {
                    Toast.makeText(requireContext(), "Note not found", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                }
            }
        }

        b.editButton.setOnClickListener {
            val title = b.titleEditText.text.toString()
            val content = b.contentEditText.text.toString()
            val color = 0
            val isPinned = false

            viewModel.upsert(
                id = currentNoteId,
                title = title,
                content = content,
                color = color,
                isPinned = isPinned
            )
            Toast.makeText(requireContext(), "Note Updated", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _b = null
    }
}