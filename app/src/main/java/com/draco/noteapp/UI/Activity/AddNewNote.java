package com.draco.noteapp.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.draco.noteapp.Utils.Helper;
import com.draco.noteapp.ViewModels.NoteConnectionViewModel;
import com.draco.noteapp.databinding.ActivityAddNewNoteBinding;
import com.google.android.material.snackbar.Snackbar;

public class AddNewNote extends AppCompatActivity {

    private ActivityAddNewNoteBinding binding;

    private String bgColor = null;

    private NoteConnectionViewModel connectionViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // connect to viewModel
        connectionViewModel = ViewModelProviders.of(this).get(NoteConnectionViewModel.class);
        // binding get root and inflate
        binding = ActivityAddNewNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // choose user note backgroundColor
        selection();

        // create New Note From Server
        createNote();
    }

    private void createNote() {
        binding.rlCreateNote.setOnClickListener(view -> {
            Helper.ShowProgressORTextView(1, binding.progressSend, binding.tvCreateNewNote);
            connectionViewModel.createNote(binding.title.getText().toString(), binding.content.getText().toString(), bgColor).observe(this, createNoteModel -> {
                // set visibilities
                Helper.ShowProgressORTextView(2, binding.progressSend, binding.tvCreateNewNote);

                // show message
                Snackbar serverMessageSnackBar = Snackbar.make(binding.nestedLayout, createNoteModel.message, Snackbar.LENGTH_LONG);
                serverMessageSnackBar.show();

                if (createNoteModel.type){
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    }, 3000);
                }
            });
        });
    }

    private void selection() {
        binding.greenColor.setOnClickListener(view -> {
            setVisibilities(1);
        });

        binding.redColor.setOnClickListener(view -> {
            setVisibilities(2);
        });

        binding.blueColor.setOnClickListener(view -> {
            setVisibilities(3);
        });

    }

    // lets test section and visibilities

    private void setVisibilities(int i) {

        switch (i) {

            // if user press green color for background
            case 1:
                binding.confirmGreen.setVisibility(View.VISIBLE);
                binding.confirmBlue.setVisibility(View.GONE);
                binding.confirmRed.setVisibility(View.GONE);
                this.bgColor = "#6BFF71";
                break;
            // if user press red color for background
            case 2:
                binding.confirmRed.setVisibility(View.VISIBLE);
                binding.confirmGreen.setVisibility(View.GONE);
                binding.confirmBlue.setVisibility(View.GONE);
                this.bgColor = "#FF8989";
                break;
            // if user press blue color for background
            case 3:
                binding.confirmBlue.setVisibility(View.VISIBLE);
                binding.confirmRed.setVisibility(View.GONE);
                binding.confirmGreen.setVisibility(View.GONE);
                this.bgColor = "##60CEFF";
                break;
            default:
                break;
        }
    }


}