package com.example.thinkdo.fragments

import NoteViewModel
import VMFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.thinkdo.databinding.FragmentAddBinding
import com.example.thinkdo.model.Note


class AddFragment : Fragment() {

    private var _b: FragmentAddBinding? = null
    private val b get() = _b!!

    private val viewModel: NoteViewModel by viewModels { VMFactory(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _b = FragmentAddBinding.inflate(inflater, container, false)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        b.btnSave.setOnClickListener {
            saveNote()
        }
    }

    private fun saveNote() {
        val title = b.titleEditText.text.toString().trim()
        val content = b.contentEditText.text.toString().trim()
        val isPinned = b.swPin.isChecked

        if (title.isEmpty() || content.isEmpty()) {
            Toast.makeText(requireContext(), "Title & Content cannot be empty", Toast.LENGTH_SHORT)
                .show()
            return
        }

        val category = "Uncategorized"
        val color = 0

        viewModel.upsert(
            id = 0,
            title = title,
            content = content,
            category = category,
            color = color,
            isPinned = isPinned
        )

        Toast.makeText(requireContext(), "Note Saved", Toast.LENGTH_SHORT).show()


        findNavController().popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _b = null
    }
}
