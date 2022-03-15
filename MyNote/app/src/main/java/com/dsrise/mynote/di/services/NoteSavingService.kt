package com.dsrise.mynote.di.services

import com.dsrise.mynote.data.model.Note
import com.dsrise.mynote.di.annotation.SqlQualifier
import com.dsrise.mynote.di.repository.NoteRepository
import javax.inject.Inject
import javax.inject.Named

class NoteSavingService @Inject constructor(
    @Named("firebase") private val noteRepository: NoteRepository
) {
    fun saveNote(note: Note) {
        noteRepository.saveNote(note)
    }

    fun updateNote(note: Note) {
        noteRepository.updateNote(note)
    }
}