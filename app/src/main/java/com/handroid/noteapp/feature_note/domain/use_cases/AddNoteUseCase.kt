package com.handroid.noteapp.feature_note.domain.use_cases

import com.handroid.noteapp.feature_note.domain.model.InvalidNoteException
import com.handroid.noteapp.feature_note.domain.model.Note
import com.handroid.noteapp.feature_note.domain.repository.NoteRepository
import kotlin.jvm.Throws

class AddNoteUseCase(
    private val repository: NoteRepository
) {
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank()) {
            throw InvalidNoteException("The tittle of the note can't be empty.")
        }
        if (note.content.isBlank()) {
            throw InvalidNoteException("The content if the note can't be empty.")
        }
        repository.insertNote(note)
    }
}