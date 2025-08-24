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
                    b.titleEditText.setText(note.title)
                    b.contentEditText.setText(note.content)
                    b.swPin.isChecked = note.isPinned
                } else {
                    Toast.makeText(requireContext(), "Note not found", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                }
            }
        }

        b.btnEdit.setOnClickListener {
            val title = b.titleEditText.text.toString()
            val content = b.contentEditText.text.toString()
            val isPinned = b.swPin.isChecked
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
            Toast.makeText(requireContext(), "Note Updated", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _b = null
    }
}