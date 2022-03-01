package com.draco.noteapp.Repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.draco.noteapp.Models.CreateNoteModel;
import com.draco.noteapp.Models.NotesModel;
import com.draco.noteapp.rest.API;
import com.draco.noteapp.rest.RetrofitConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoteConnectionRepository {

    public LiveData<CreateNoteModel> createNoteModelLiveData(String title, String content, String backgroundColor){

        MutableLiveData<CreateNoteModel> createNoteModelMutableLiveData = new MutableLiveData<>();

        RetrofitConnection.getInstance().create(API.class).createNote(title, content, backgroundColor).enqueue(new Callback<CreateNoteModel>() {
            @Override
            public void onResponse(Call<CreateNoteModel> call, Response<CreateNoteModel> response) {
                if (response.code() == 200)
                    createNoteModelMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<CreateNoteModel> call, Throwable t) {

            }
        });
     return createNoteModelMutableLiveData;
    }

    public LiveData<NotesModel> getAllNotes(){

        MutableLiveData<NotesModel> getAllNotesMutableLiveData = new MutableLiveData<>();

        RetrofitConnection.getInstance().create(API.class).getAllNotes().enqueue(new Callback<NotesModel>() {
            @Override
            public void onResponse(Call<NotesModel> call, Response<NotesModel> response) {
                if (response.code() == 200)
                    getAllNotesMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<NotesModel> call, Throwable t) {

            }
        });
     return getAllNotesMutableLiveData;
    }

}
