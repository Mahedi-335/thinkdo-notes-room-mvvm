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
import androidx.navigation.fragment.findNavController
import com.example.thinkdo.databinding.FragmentAddBinding
import com.example.thinkdo.model.Note

class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NoteViewModel by viewModels { VMFactory(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Save button click listener
        binding.saveButton.setOnClickListener {
            val title = binding.titleEditText.text.toString()
            val content = binding.contentEditText.text.toString()
            val isPinned = false
            val color = 0

            if (title.isNotBlank() || content.isNotBlank()) {
                viewModel.upsert(
                    title = title,
                    content = content,
                    color = color,
                    isPinned = isPinned
                )

                Toast.makeText(requireContext(), "Note Saved", Toast.LENGTH_SHORT).show()


                findNavController().popBackStack()
            } else {
                Toast.makeText(requireContext(), "Please add title or content", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
