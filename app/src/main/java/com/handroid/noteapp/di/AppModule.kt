package com.handroid.noteapp.di


import android.app.Application
import androidx.room.Room
import com.handroid.noteapp.feature_note.data.data_source.NoteDatabase
import com.handroid.noteapp.feature_note.data.repository.NoteRepositoryImpl
import com.handroid.noteapp.feature_note.domain.repository.NoteRepository
import com.handroid.noteapp.feature_note.domain.use_cases.DeleteNoteUseCase
import com.handroid.noteapp.feature_note.domain.use_cases.GetNotesUseCase
import com.handroid.noteapp.feature_note.domain.use_cases.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotes = GetNotesUseCase(repository),
            deleteNoteUseCase = DeleteNoteUseCase(repository)
        )
    }
}