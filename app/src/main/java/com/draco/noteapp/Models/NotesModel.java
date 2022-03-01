package com.draco.noteapp.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NotesModel {

//    alt + s
//    if u not install gsonformatter plugin, install and press alt + s


    @SerializedName("type")
    public boolean type;
    @SerializedName("message")
    public String message;
    @SerializedName("notes")
    public List<NotesBean> notes;

    public static class NotesBean {
        @SerializedName("id")
        public int id;
        @SerializedName("title")
        public String title;
        @SerializedName("content")
        public String content;
        @SerializedName("bg_color")
        public String bgColor;
    }

}
