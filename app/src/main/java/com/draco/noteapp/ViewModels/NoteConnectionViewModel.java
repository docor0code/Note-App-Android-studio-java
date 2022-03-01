package com.draco.noteapp.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.draco.noteapp.Models.CreateNoteModel;
import com.draco.noteapp.Models.NotesModel;
import com.draco.noteapp.Repository.NoteConnectionRepository;

public class NoteConnectionViewModel extends AndroidViewModel {

    private LiveData<CreateNoteModel> createNoteModelLiveData;
    private LiveData<NotesModel> notesModelLiveData;
    private NoteConnectionRepository connectionRepository;


    public NoteConnectionViewModel(@NonNull Application application) {
        super(application);
        connectionRepository = new NoteConnectionRepository();
    }

    public LiveData<CreateNoteModel> createNote(String title, String content, String backgroundColor){
        return createNoteModelLiveData = connectionRepository.createNoteModelLiveData(title, content, backgroundColor);
    }
    public LiveData<NotesModel> getAllNotesViewModel(){
        return notesModelLiveData = connectionRepository.getAllNotes();
    }
}
