package com.dsrise.mynote.di.repository

import android.util.Log
import com.dsrise.mynote.data.model.Note
import com.dsrise.mynote.data.model.util.Constant.MainApp.LOGGER_TAG
import com.dsrise.mynote.di.annotation.ActivityScope
import com.dsrise.mynote.di.annotation.ApplicationScope
import com.dsrise.mynote.di.annotation.FirebaseAnalyticsQualifier
import com.dsrise.mynote.di.services.AnalyticsService
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

interface NoteRepository {
    fun saveNote(note: Note)
    fun updateNote(note: Note)
}

@ActivityScope
class SqlRepository @Inject constructor(@Named("mixpanel") private val analyticsService: AnalyticsService) :
    NoteRepository {
    override fun saveNote(note: Note) {
        Log.d(LOGGER_TAG, "saveNote: Saving User Note in DB <> $note ")
        analyticsService.trackEvent("SAVING NOTE", "CREATE")
    }

    override fun updateNote(note: Note) {
        Log.d(LOGGER_TAG, "updateNote: Updating Note Information in DB <> $note")
        analyticsService.trackEvent("UPDATING NOTE", "UPDATE")
    }
}

class FirebaseRepository(
    private val retryCount: Int,
    @FirebaseAnalyticsQualifier private val analyticsService: AnalyticsService
) : NoteRepository {
    override fun saveNote(note: Note) {
        Log.d(
            LOGGER_TAG, "saveNote: Saving User Note in Firebase <> $note " +
                    "\n with Retry Count : $retryCount"
        )
        analyticsService.trackEvent("SAVING NOTE", "CREATE")
    }

    override fun updateNote(note: Note) {
        Log.d(LOGGER_TAG, "updateNote: Updating Note Information in Firebase <> $note")
        analyticsService.trackEvent("UPDATING NOTE", "UPDATE")
    }

}