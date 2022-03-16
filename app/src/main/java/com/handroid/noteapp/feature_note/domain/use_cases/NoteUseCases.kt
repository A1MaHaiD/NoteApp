package com.handroid.noteapp.feature_note.domain.use_cases

data class NoteUseCases(
    val getNotes:GetNotesUseCase,
    val deleteNoteUseCase: DeleteNoteUseCase
)
