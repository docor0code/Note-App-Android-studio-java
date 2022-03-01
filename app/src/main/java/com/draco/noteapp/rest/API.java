package com.draco.noteapp.rest;

import com.draco.noteapp.Models.CreateNoteModel;
import com.draco.noteapp.Models.NotesModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface API {

    @POST("createNote")
    @FormUrlEncoded
    Call<CreateNoteModel> createNote(@Field("title") String title, @Field("contents") String content, @Field("bgColor") String backgroundColor);

    @GET("getNote")
    Call<NotesModel> getAllNotes();
}
