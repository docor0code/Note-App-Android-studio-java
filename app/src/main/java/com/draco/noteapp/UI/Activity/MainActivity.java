package com.draco.noteapp.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.baoyz.widget.PullRefreshLayout;
import com.draco.noteapp.Adapters.NotesAdapter;
import com.draco.noteapp.R;
import com.draco.noteapp.ViewModels.NoteConnectionViewModel;
import com.draco.noteapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private NoteConnectionViewModel connectionViewModel;

    private NotesAdapter notesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // connect to view model
        connectionViewModel = ViewModelProviders.of(this).get(NoteConnectionViewModel.class);
        // binding view binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // if user click on add note floating button start add note activity in this function !
        addNewNote();

        // get all note for show in app in this function
        getAllNote();

        // if user pull down page for refreshing, call this function
        reloadFromServer();
    }

    private void getAllNote() {
        connectionViewModel.getAllNotesViewModel().observe(this, notesModel -> {
            // if refreshing is true set false after get new Data from server
            binding.refreshingLayout.setRefreshing(false);
            if (notesModel.type){
                // set layout visibilities
                binding.notesRv.setVisibility(View.VISIBLE);
                binding.noteNotFound.setVisibility(View.GONE);
                // create a object from note adapter java class
                notesAdapter = new NotesAdapter(notesModel.notes);
                // set recyclerview layout manager to linear or gridview
                binding.notesRv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                //set rv adapter
                binding.notesRv.setAdapter(notesAdapter);
            } else {
                // set layout visibilities if not note found
                binding.notesRv.setVisibility(View.GONE);
                binding.noteNotFound.setVisibility(View.VISIBLE);
            }
        });
    }

    private void addNewNote() {
        binding.addNewNote.setOnClickListener(view -> {
            startActivity(new Intent(this, AddNewNote.class));
        });
    }

    public void reloadFromServer(){
        binding.refreshingLayout.setRefreshStyle(PullRefreshLayout.STYLE_SMARTISAN);
        binding.refreshingLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAllNote();
                Toast.makeText(getApplicationContext(), "All Data Refreshing from note database...", Toast.LENGTH_SHORT).show();

                // thanks for watching
                // wait a mint, please like or diss like this video, and send me your comment...
                // i make your project and upload learning video for u :)
                // thanks u my friend
                // see you later
                // goodby :)
            }
        });
    }
}