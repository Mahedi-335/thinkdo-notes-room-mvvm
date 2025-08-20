import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thinkdo.model.Note
import com.example.thinkdo.repo.NoteRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NoteViewModel(private val repo: NoteRepository) : ViewModel() {

    val notes: StateFlow<List<Note>> =
        repo.getAll().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun upsert(
        id: Int? = 0,
        title: String,
        content: String,
        category: String,
        color: Int,
        isPinned: Boolean
    ) = viewModelScope.launch {

        if (id != null && id != 0) {

            repo.getByID(id).collect { oldNote ->
                val updatedNote = oldNote.copy(
                    title = title,
                    content = content,
                    category = category,
                    color = color,
                    isPinned = isPinned,
                    updateAt = System.currentTimeMillis()
                )
                repo.update(updatedNote)
            }
        } else {
            val newNote = Note(
                title = title,
                content = content,
                category = category,
                color = color,
                isPinned = isPinned
            )
            repo.insert(newNote)
        }
    }

    fun delete(note: Note) = viewModelScope.launch { repo.delete(note) }
}